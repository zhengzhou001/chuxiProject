var form, table, element, layer;
layui.use(['element', 'table', "layer", "form"], function () {
    element = layui.element;
    layer = layui.layer;
    table = layui.table;
    var form = layui.form;

    init();//初始化
});

//初始化
function init() {
    $("#headImg").on('click',function () {
        $("#fileupload").trigger("click");
    });
    $('#fileupload').fileupload({
        url: context + "file/uploadFile",
        formData: {userid: user.id, path: 'head'},
        dataType: 'json',
        done: function (e, data) {
            uploadHead(data.result.data);
        }

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