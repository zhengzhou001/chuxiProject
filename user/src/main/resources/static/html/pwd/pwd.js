var element,user,layer;
layui.use(['element','tree','table',"jquery","layer","form"], function(){
    element = layui.element;
    layer=layui.layer;
    var form = layui.form;
    //自定义验证规则
    form.verify({
        newPwd:function(value){
            if($('#pwd').val() == value)
                return '新旧密码不能一样！';

        },
        confirmPwd:function(value){
            if($('#pwd_NEW').val() != value)
                return '两次密码输入不一致！';

        },
        checkOldPwd:function(value){
            var flag=false;
            baseTools.xhrAjax({
                url: "../../doService",
                async:false,
                contentType: "application/json",
                params:  JSON.stringify({
                    "id":user.id,
                    "pwd": $.md5($("#pwd").val()),
                    "realurl":"user-service/sys/selectSysUserCount"
                }),
                callback: [function (jsonObj, xhrArgs) {
                    switch (parseInt(jsonObj.code)) {
                        case 0://成功
                             if(parseInt(jsonObj.data)>0){
                                 flag=true;
                             }
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
            if(!flag){
                return "旧密码不正确";
            }
        },
        pwd:function(value){
            if (value.length < 6) {
                return '密码最少6个字符';
            }
            if (value.length > 12) {
                return '密码最多12个字符';
            }

        }
    });
    //监听提交
    form.on('submit(editFormButton)', function (data) {
        baseTools.xhrAjax({
            url: "../../doService",
            contentType: "application/json",
            params:  JSON.stringify({
                "id":user.id,
                "pwd_NEW": $.md5($("#pwd_NEW").val()),
                "realurl":"user-service/sys/updateSysUser"
            }),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        layer.alert("修改成功", {
                            icon: 1,
                            title: '提示'
                        },function () {
                            window.location.reload();
                        });
                        break;
                    default:
                        layer.alert(jsonObj.msg, {
                            icon: 2,
                            title: '修改失败'
                        });
                    //去登录
                    //gotoLogin();

                }
            }],
            callbackError: [function (data, xhrArgs) {
                baseTools.hideMash();
            }]
        });

    });
    if(!$.cookie("CUR_USER")){
        baseTools.gotoLogin();
        return;
    }
    init();//初始化
});

//初始化
function  init() {
    user = JSON.parse($.cookie("CUR_USER"));
}
