var element,layer;
layui.use(['element','tree'], function(){
    element = layui.element;
    layer = layui.layer;
    if(!$.cookie("CUR_USER")){
        baseTools.gotoLogin();
        return;
    }
    setTimeout("init()",1000);  //初始化
});


//初始化
function  init() {
    var user = JSON.parse($.cookie("CUR_USER"));
    //获取角色菜单树
    baseTools.xhrAjax({
        url: "../../doService",
        contentType: "application/json",
        params:JSON.stringify({"appid":user.appid,"id":$("#id").val(),"pid":$("#pid").val(),realurl:"user-service/sys/selectMenuByRole"}),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功
                    var treeObj  = $("#tree");
                    treeObj  = $.fn.zTree.init(treeObj, {
                        check:{
                            enable: true,
                            chkStyle: "checkbox",
                            chkboxType: { "Y": "ps", "N": "ps" }
                        },
                        data: {
                            key:{
                                name: "name"  //节点名字字段
                            },
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pid",
                                rootPId: "-1"
                            }
                        },
                        callback: {

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

//添加角色权限
function saveRoleMenu(){
    //获取选中的角色
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var checkdNodes = treeObj.getCheckedNodes(true);
    if(checkdNodes.length==0){
        layer.alert("请勾选菜单", {
            icon: 2,
            title: '提示'
        });
        return;
    }
    //获取勾选的menuids
    var id_array = new Array();
    for(var i=0 ;i<checkdNodes.length;i++){
        id_array.push(checkdNodes[i].id);
    }
    var ids = id_array.join(",");

    baseTools.xhrAjax({
        url: "../../doService",
        contentType: "application/json",
        params:  JSON.stringify({"menuids":ids,"roleid":$("#id").val(),realurl:"user-service/sys/insertMenuByRole"}),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功
                    layer.alert("权限保存成功", {
                        icon: 1,
                        title: '成功'
                    },function () {
                        parent.layer.closeAll();
                    });

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