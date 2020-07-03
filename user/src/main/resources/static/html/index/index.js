var element,layer;

layui.use(['element','layer'], function(){
    element = layui.element;
    layer=layui.layer;
    init();//初始化
});


//初始化
function  init() {
    var userid = baseTools.getUrlParam("userid");
    var appid = baseTools.getUrlParam("appid");
    $.cookie("appid",appid,{path: "/user/",expires:7});
    if(appid==null){
       $("body").html("appid为空");
        return ;
    }
    if(userid==null){
        baseTools.gotoLogin();
        return ;
    }else{
        baseTools.xhrAjax({
            url: "../../doService",
            async:false,
            contentType: "application/json",
            params:  JSON.stringify({"id":userid,realurl:"user-service/sys/selectSysUser"}),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                            if(jsonObj.data==null||jsonObj.data.length!=1){
                                layer.alert(jsonObj.msg, {
                                    icon: 2,
                                    title: '用户数据异常'
                                });
                                debugger
                            }else{
                                $.cookie("CUR_USER",JSON.stringify(jsonObj.data[0]),{path: "/user/",expires:7});
                            }

                        break;
                    case -3://cookie失效重新登录
                        baseTools.gotoLogin();
                        break;
                    default:
                        layer.alert(jsonObj.msg, {
                            icon: 2,
                            title: '获取用户信息失败'
                        },function () {
                            baseTools.gotoLogin();
                        });

                }
            }],
            callbackError: [function (data, xhrArgs) {
                baseTools.hideMash();
            }]
        });

    }
    var user = JSON.parse($.cookie("CUR_USER"));
    $("#name").html(user.name);
    //根据用户id获取角色权限
    baseTools.xhrAjax({
        url: "../../doService",
        async:false,
        contentType: "application/json",
        params:  JSON.stringify({id:user.id,appid:user.appid,flag:"1",realurl:"user-service/sys/getUserRoleMenu"}),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功
                    var treeObj = $("#menuTree");
                    treeObj = $.fn.zTree.init(treeObj, {
                        view:{
                            dblClickExpand: false,
                            showLine: false
                        },
                        data: {
                            key: {
                                name: "name"  //节点名字字段
                                ,url:"aaaa"
                            },
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pid",
                                rootPId: "-1"
                            }
                        },
                        callback: {
                            onClick: function(event, treeId, treeNode){
                                var zTree = $.fn.zTree.getZTreeObj("menuTree");
                                zTree.expandNode(treeNode);

                                var exist=$("li[lay-id='"+treeNode.id+"']").length; //判断是否存在tab
                                if(exist==0){
                                    if(treeNode.url!=null&&treeNode.url!=""&&treeNode.url.length>1&&treeNode.url!="null"){
                                        element.tabAdd('menubody', {
                                            title: treeNode.name
                                            ,content: "<iframe style='width: 100%;height: 800px' src='/user/"+treeNode.url+"'></iframe>"
                                            ,id: treeNode.id
                                        });
                                        element.tabChange('menubody', treeNode.id); //切换到：用户管理
                                    }
                                }
                                element.tabChange('menubody',treeNode.id);//切换tab
                            }
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
                        title: '获取权限失败'
                    });

            }
        }],
        callbackError: [function (data, xhrArgs) {
            baseTools.hideMash();
        }]
    });
}

//退出
function logOut(){
    $.cookie("CUR_USER","",{path: "/user/"});
    $.cookie("x-csrf-token","",{path: "/user/"});
    //去登录
    baseTools.gotoLogin();
}