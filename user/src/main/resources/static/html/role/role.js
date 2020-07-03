var element,layer;
layui.use(['element', 'tree'], function () {
    element = layui.element;
    layer = layui.layer;
    if (!$.cookie("CUR_USER")) {
        baseTools.gotoLogin();
        return;
    }
    init();//初始化
});


//初始化
function init() {
    //获取用户角色树
    var user = JSON.parse($.cookie("CUR_USER"));
    //根据用户id获取角色权限
    baseTools.xhrAjax({
        url: "../../doService",
        contentType: "application/json",
        params: JSON.stringify({"id": user.id, realurl: "user-service/sys/getUserRole"}),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功

                    var treeObj = $("#tree");
                    treeObj = $.fn.zTree.init(treeObj, {
                        view:{
                            addHoverDom: addHoverDom,
                            removeHoverDom: removeHoverDom
                        },
                        edit: {
                            enable: true,
                            editNameSelectAll: true,
                            showRemoveBtn: setRemoveBtn,
                            removeTitle: "删除角色",
                            showRenameBtn: true,
                            renameTitle: "修改名字"
                        },
                        check: {
                            enable: true,
                            chkStyle: "checkbox",
                            chkboxType: {"Y": "", "N": ""}
                        },
                        data: {
                            key: {
                                name: "name"  //节点名字字段
                            },
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pid"
                            }
                        },
                        callback: {
                            beforeRemove: function (treeId, treeNode) {
                                //删除前函数
                                $.fn.zTree.getZTreeObj(treeId).checkAllNodes(false);
                                $.fn.zTree.getZTreeObj(treeId).checkNode(treeNode, true, false);

                                delRole();
                                return false;
                            },
                            beforeRename: beforeRename
                        }

                    }, jsonObj.data);
                    treeObj.expandAll(true);
                    break;
                case -3://cookie失效重新登录
                    baseTools.gotoLogin();
                    break;
                default:
                    layer.alert(jsonObj.msg, {
                        icon: 2,
                        title: '获取角色失败'
                    });

            }
        }],
        callbackError: [function (data, xhrArgs) {
            baseTools.hideMash();
        }]
    });
}

//添加角色
function addRole() {
    //获取选中的角色
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var checkdNodes = treeObj.getCheckedNodes(true);
    if (checkdNodes.length != 1) {
        layer.alert("请勾选一个上级角色，目前勾选个数为【" + checkdNodes.length + "】", {
            icon: 2,
            title: '提示'
        });
        return;
    }

    layer.open({
        type: 2,
        title: '角色添加',
        shadeClose: true,
        shade: 0.8,
        area: ['380px', '200px'],
        content: './role_add.html',
        success: function (layero, index) {
            var body = layer.getChildFrame('body', index);//少了这个是不能从父页面向子页面传值的
            //获取子页面的元素，进行数据渲染
            body.contents().find("#pid").val(checkdNodes[0].id);
            body.contents().find("#pname").val(checkdNodes[0].name);
        },
        end: function () {
            init();　　//页面刷新
        }
    });
}

//删除角色
function delRole() {
    //获取选中的角色
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var checkdNodes = treeObj.getCheckedNodes(true);

    if (checkdNodes.length == 0) {
        layer.alert("请勾选需要删除的角色，目前勾选个数为【" + checkdNodes.length + "】", {
            icon: 2,
            title: '提示'
        });
        return;
    }
    var id_array = new Array();
    for (var i = 0; i < checkdNodes.length; i++) {
        if (checkdNodes[i].isParent) {
            layer.alert("【" + checkdNodes[i].name + "】是父角色，不允许删除", {
                icon: 2,
                title: '提示'
            });
            return;
        }
        id_array.push(checkdNodes[i].id);
    }
    var ids = id_array.join(",");
    //信息框-例2
    if (checkdNodes.length == 1) {
        layer.msg('你确定删除【' + checkdNodes[0].name + '】角色吗？', {
            time: 0 //不自动关闭
            , btn: ['确定删除', '取消']
            , yes: function (index) {
                delRoleAjax(ids);
                layer.close(index);
                init();
            }
        });
    } else {
        layer.msg('你确定删除【' + checkdNodes.length + '】个角色吗？', {
            time: 0 //不自动关闭
            , btn: ['确定删除', '取消']
            , yes: function (index) {
                delRoleAjax(ids);
                layer.close(index);
                init();
            }
        });
    }


}

//删除角色Ajax
function delRoleAjax(ids) {
    baseTools.xhrAjax({
        url: "../../doService",
        async: false,
        contentType: "application/json",
        params: JSON.stringify({"ids": ids, realurl: "user-service/sys/deleteSysRole"}),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功
                    layer.alert("删除角色成功", {
                        icon: 1,
                        title: '成功'
                    });
                    init();
                    break;
                default:
                    layer.alert(jsonObj.msg, {
                        icon: 2,
                        title: '失败'
                    });
            }
        }],
        callbackError: [function (data, xhrArgs) {
            baseTools.hideMash();
        }]
    });
}

//角色赋权
function addRoleMenu() {
    //获取选中的角色
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var checkdNodes = treeObj.getCheckedNodes(true);
    if (checkdNodes.length != 1) {
        layer.alert("请勾选一个角色，目前勾选个数为【" + checkdNodes.length + "】", {
            icon: 2,
            title: '提示'
        });
        return;
    }
    layer.open({
        type: 2,
        title: '角色添加',
        shadeClose: true,
        shade: 0.8,
        area: ['380px', '400px'],
        content: './role_addMenu.html',
        success: function (layero, index) {
            var body = layer.getChildFrame('body', index);//少了这个是不能从父页面向子页面传值的
            //获取子页面的元素，进行数据渲染
            //ztree修改了根节点的父节点ID

            body.contents().find("#pid").val(checkdNodes[0].pidAgain);
            body.contents().find("#id").val(checkdNodes[0].id);
        },
        end: function () {

        }
    });

}

//设置哪些节点有删除按钮
function setRemoveBtn(treeId, treeNode) {
    return !treeNode.isParent;
}
//修改名字 button调用
function updateRole(){
    //获取选中的角色
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var checkdNodes = treeObj.getCheckedNodes(true);
    if (checkdNodes.length != 1) {
        layer.alert("请勾选一个角色，目前勾选个数为【" + checkdNodes.length + "】", {
            icon: 2,
            title: '提示'
        });
        return;
    }
    layer.prompt({title: '请输入角色名字', formType: 2}, function(text, index){
        layer.close(index);
        if(text==checkdNodes[0].name){
            return ;
        }
        beforeRename("tree",checkdNodes[0],text,true);
    });

}
//修改名字
function beforeRename(treeId, treeNode, newName, isCancel) {
    //修改名字前函数
    if (newName == treeNode.name) {
        return true
    }
    var flag = false;
    baseTools.xhrAjax({
        url: "../../doService",
        contentType: "application/json",
        async: false,
        params: JSON.stringify({"pid": treeNode.pid, "name": newName, realurl: "user-service/sys/selectSysRoleCount"}),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功
                    if (parseInt(jsonObj.data) != 0) {
                        layer.alert("【" + treeNode.getParentNode().name + "】下已经存在角色【" +newName + "】，请更换名字", {
                            icon: 2,
                            title: '失败'
                        });
                        flag = true;
                    }
                    break;
                default:
                    flag = true;
                    layer.alert(jsonObj.msg, {
                        icon: 2,
                        title: '失败'
                    });
            }
        }],
        callbackError: [function (data, xhrArgs) {
            baseTools.hideMash();
        }]
    });

    if (flag) {
        return false;
    }
    baseTools.xhrAjax({
        url: "../../doService",
        async: false,
        contentType: "application/json",
        params: JSON.stringify({"name_NEW":newName,"id":treeNode.id, realurl: "user-service/sys/updateSysRole"}),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功
                    layer.alert("角色改名成功", {
                        icon: 1,
                        title: '成功'
                    });
                    break;
                default:
                    layer.alert(jsonObj.msg, {
                        icon: 2,
                        title: '失败'
                    });
            }
        }],
        callbackError: [function (data, xhrArgs) {
            baseTools.hideMash();
        }]
    });
    init();

}

//增加节点
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='添加子角色' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        //删除前函数
        $.fn.zTree.getZTreeObj("tree").checkAllNodes(false);
        $.fn.zTree.getZTreeObj("tree").checkNode(treeNode, true, false);
        addRole();
        return false;
    });
};
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};