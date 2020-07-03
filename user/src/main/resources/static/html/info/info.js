var element,user,layer;
layui.use(['element','tree','table',"jquery","layer","form"], function(){
    element = layui.element;
    layer=layui.layer;
    var form = layui.form;
    //自定义验证规则
    form.verify({
        name: function (value) {
            if (value.length < 2) {
                return '用户名最少2个字符';
            }
            if (value.length > 10) {
                return '用户名最多10个字符';
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
                "name_NEW": $("#name").val(),
                "phone_NEW":$("#phone").val(),
                "email_NEW":$("#email").val(),
                "realurl":"user-service/sys/updateSysUser"
            }),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        layer.alert("修改成功", {
                            icon: 1,
                            title: '提示'
                        },function () {
                            top.location.reload();
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
    $("#name").val(user.name);
    $("#phone").val(user.phone);
    $("#email").val(user.email);
}
