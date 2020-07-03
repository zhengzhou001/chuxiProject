var element;
var user;
layui.use(['element'], function(){
    element = layui.element;
    if(!$.cookie("CUR_USER")){
        baseTools.gotoLogin();
        return;
    }
    init();//初始化
});


//初始化
function  init() {
    user=JSON.parse($.cookie("CUR_USER"));
    //获取配置项
    baseTools.xhrAjax({
        url: "../../getConfig",
        async:false,
        contentType: "application/json",
        params:  "{}",
        callback: [function (config, xhrArgs) {
            $("#yhzx").attr("href",config.yhzx_address+"user/html/index/index.html?userid="+user.id+"&appid=1");
        }],
        callbackError: [function (data, xhrArgs) {
            baseTools.hideMash();
        }]
    });

    var user = JSON.parse($.cookie("CUR_USER"));
    $("#name").html(user.name);
    //根据用户id获取角色权限
    baseTools.xhrAjax({
        url: "../../doService",
        async:false,
        contentType: "application/json",
        params:  JSON.stringify({"id":user.id,"appid":user.appid,flag:0,"realurl":"user-service/sys/getUserRoleMenu"}),
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
                                            ,content: "<iframe style='width: 100%;height: 800px' src='/laxt/"+treeNode.url+"'></iframe>"
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
    $.cookie("CUR_USER","",{path: "/laxt/"});
    $.cookie("appid","",{path: "/laxt/"});
    $.cookie("x-csrf-token","",{path: "/laxt/"});
    //去登录
    baseTools.gotoLogin();
}