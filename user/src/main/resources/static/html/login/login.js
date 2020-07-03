var layer;
layui.use(['form'], function () {
    var form = layui.form;
        layer = layui.layer;
    //自定义验证规则
    form.verify({
        username: function (value) {
            if (value.length < 4) {
                return '用户名最少4个字符啊';
            }
            if (value.length > 15) {
                return '用户名最多5个字符';
            }
        }
        , pwd: function (value) {
            if (value.length < 4) {
                return '密码最少4个字符啊';
            }
            if (value.length > 15) {
                return '密码最多5个字符';
            }
        }

    });
    //监听提交
    form.on('submit(loginForm)', function (data) {
        baseTools.xhrAjax({
            url: "../../login",
            contentType: "application/json",
            params:  JSON.stringify($.extend(data.field,{"appid":$.cookie("appid")})),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        //登录成功
                        //$.cookie("CUR_USER",JSON.stringify(jsonObj.data),{path: "/",expires:7});
                       // setTimeout(function(){window.location.href="../index/index.html";},300);
                        window.location.href="../index/index.html?userid="+jsonObj.data.id+"&appid="+jsonObj.data.appid;
                        break;
                    default:
                        layer.alert(jsonObj.msg, {
                            icon: 2,
                            title: '登录失败'
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
    init();//页面初始化
});

//页面初始化
function init() {
    var appid = 0;
    try {
        appid = baseTools.getUrlParam("appid");
        if(appid==null||appid==""||appid==0){
             $.cookie("appid",0,{path: "/user/",expires:7});

            layer.alert("没有应用id", {
                icon: 2,
                title: '提示'
            });
        }
         $.cookie("appid",appid,{path: "/user/",expires:7});

    }catch (e) {
        appid=0;
        $.cookie("appid",appid,{path: "/user/",expires:7});
    }
}