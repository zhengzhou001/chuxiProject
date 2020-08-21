var form, table, element, layer,upload;
layui.use(['element', 'table', "layer", "form","upload"], function () {
    element = layui.element;
    layer = layui.layer;
    table = layui.table;
    upload = layui.upload;
    form = layui.form;

    init();//初始化
});

//初始化
function init() {
    //初始化表单
    initPersonForm();
    //获取用户基本信息
    getPersonInfo();
    //初始化头像上传
    initHead();
}
//初始化表单
function initPersonForm() {
    //自定义验证规则
    form.verify({
        name: function(value){
            if(value.length < 1||value.length > 5){
                return '名字应该是1-5个字符';
            }
        }

    });

    //监听提交
    form.on('submit(updatePersonInfo)', function(data){
        baseTools.xhrAjax({
            url: context + "sys/updateSysUser",
            contentType: "application/json",
            params: JSON.stringify({
                "id":user.id,
                "mobile_NEW":data.field.mobile_NEW,
                "name_NEW":data.field.name_NEW,
            }),
            callback: [function (jsonObj, xhrArgs) {
                switch (parseInt(jsonObj.code)) {
                    case 0://成功
                        layer.alert(jsonObj.msg, {
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
}
//初始化头像上传
function initHead() {
    upload.render({
        elem: '#headImg'
        ,url: context + "file/uploadFile"
        ,data:{userid:user.id, path: 'head'}
        ,accept: 'images' //普通文件
        ,size: 1024*10 //限制文件大小，单位 KB
        ,acceptMime: 'image/*'
        ,done: function(res){
            uploadHead(res.data);
        }
    });
}
//获取用户信息
function getPersonInfo() {
    baseTools.xhrAjax({
        url: context + "sys/getPersonInfo",
        contentType: "application/json",
        params: JSON.stringify({
            "userid":user.id
        }),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功
                    var data = jsonObj.data;
                    if(baseTools.isNotBlank(data.file_name)){
                         $("#headImg").attr('src',context+"file/getFile?realFile="+data.file_path+data.file_name+"."+data.file_ext+"&fileName="+data.user_file_name+"."+data.file_ext);
                    }
                    //表单赋值
                    form.val("personInfo_form", {
                        "name_NEW": data.name
                        ,"mobile_NEW": data.mobile
                        ,"email_NEW": data.email
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
}



//修改头像
function uploadHead(data){
    $("#headImg").attr('src',context+"file/getFile?realFile="+data.filePath+data.fileName+"."+data.userFileExt+"&fileName="+data.userFileName+"."+data.userFileExt);
    baseTools.xhrAjax({
        url: context + "sys/insertSysUserHead",
        contentType: "application/json",
        params: JSON.stringify({
            "userid":user.id,
            "userFileName":data.userFileName,
            "fileExt":data.userFileExt,
            "fileName":data.fileName,
            "filePath":data.filePath,
            "isShow":"1"
        }),
        callback: [function (jsonObj, xhrArgs) {
            switch (parseInt(jsonObj.code)) {
                case 0://成功

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
}