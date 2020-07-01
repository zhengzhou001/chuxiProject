var form,table,element,layer;
layui.use(['element','table',"layer","form"], function(){
    element = layui.element;
    layer = layui.layer;
    table = layui.table;
    var form = layui.form;
    //自定义验证规则
    form.verify({
        pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });
    //监听提交
    form.on('submit(login)', function(data){
        baseTools.xhrAjax({
            url: context + "sys/login",
            contentType: "application/json",
            params: JSON.stringify(data),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        layer.alert(jsonObj.data, {
                            icon: 1,
                            title: '提示'
                        });
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