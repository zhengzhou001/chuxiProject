var element;
layui.use(['element','tree','form'], function(){
    var form = layui.form;
    element = layui.element;
    if(!$.cookie("CUR_USER")){
        baseTools.gotoLogin();
        return;
    }
    //监听提交
    form.on('submit(roleAddForm)', function (data) {
        var flag =false;
        baseTools.xhrAjax({
            url: "../../doService",
            contentType: "application/json",
            async:false,
            params:  JSON.stringify($.extend(data.field,{realurl:"user-service/sys/selectSysRoleCount"})),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        if(parseInt(jsonObj.data)!=0){
                            layer.alert("【"+data.field.pname+"】下已经存在角色【"+data.field.name+"】，请勿重复添加", {
                                icon: 2,
                                title: '失败'
                            });
                            flag=true;
                        }

                        break;
                    default:
                        flag=true;
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
        if(flag) {
            return;
        }
        baseTools.xhrAjax({
            url: "../../doService",
            async:false,
            contentType: "application/json",
            params:  JSON.stringify($.extend(data.field,{"state":1,realurl:"user-service/sys/insertSysRole"})),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        layer.alert("添加角色成功", {
                            icon: 1,
                            title: '成功'
                        });
                        break;
                    default:
                        layer.alert(jsonObj.msg, {
                            icon: 2,
                            title: '添加角色失败'
                        });
                }
            }],
            callbackError: [function (data, xhrArgs) {
                baseTools.hideMash();
            }]
        });

    });

    init();//初始化
});


//初始化
function  init() {

}