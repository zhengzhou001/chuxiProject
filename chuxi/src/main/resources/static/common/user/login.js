var form,table,element,layer;
layui.use(['element','table',"layer","form"], function(){
    if(baseTools.isBlank($.cookie("CUR_USER"))){

    }else{
        top.location.href=context+"route/index";
    }

    element = layui.element;
    layer = layui.layer;
    table = layui.table;
    var form = layui.form;
    //自定义验证规则
    form.verify({
        name:function(value){
            if(value.length>12||value.length<2){
                return '用户名必须是2-12位！';
            }
        },
        pass: [/^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格'],
        confirmPwd:function(value){
        if($('#password_register').val() != value)
            return '两次密码输入不一致！';
        }
    });
    //监听提交
    form.on('submit(login)', function(data){
        baseTools.xhrAjax({
            url: context + "sys/login",
            contentType: "application/json",
            params: JSON.stringify(data.field),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        //登录成功跳转到首页
                         $.cookie("CUR_USER",JSON.stringify(jsonObj.data),{path: baseWebPath,expires:7});
                         window.location.href=context+"route/index";
                        break;
                    default:
                        layer.alert(jsonObj.msg, {
                            icon: 2,
                            title: '提示'
                        });
                }
            }],
            callbackError: [function (data, xhrArgs) {
                baseTools.hideMash();
            }]
        });
        return false;
    });

    //打开注册表单
    form.on('submit(register)', function(data){
        //弹出层
        layer.open({
            title:"注册用户",
            type:1,
            content:$("#registerForm")
        });
        return false;
    });
    //注册
    form.on('submit(registerFormSubmit)', function(data){
        baseTools.xhrAjax({
            url: context + "sys/register",
            contentType: "application/json",
            params: JSON.stringify(data.field),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        //登录成功跳转到首页
                        $.cookie("CUR_USER",JSON.stringify(jsonObj.data),{path: baseWebPath,expires:7});
                        window.location.href=context+"route/index";
                        break;
                    default:
                        layer.alert(jsonObj.msg, {
                            icon: 2,
                            title: '提示'
                        });
                }
            }],
            callbackError: [function (data, xhrArgs) {
                baseTools.hideMash();
            }]
        });
        return false;
    });

    init();//初始化
});

//初始化
function  init() {

}

