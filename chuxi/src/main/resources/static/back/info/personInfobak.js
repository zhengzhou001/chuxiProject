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
        //singleFileUploads:true,//单文件上传
        acceptFileTypes : /(gif|jpe?g|png)$/i,//验证图片格式
        maxNumberOfFiles : 1,//最大上传文件数目
        maxFileSize : 9, // 文件上限1MB
        minFileSize : 1,//文件下限  100b
        messages : {//文件错误信息
            acceptFileTypes : '文件类型不匹配',
            maxFileSize : '文件过大',
            minFileSize : '文件过小'
        },

        done: function (e, data) { //文件上传成功后
            alert(2);
            uploadHead(data.result.data);
        },
        progressall: function(e, data) {  //进度条显示
            alert(4);
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $("#asda").html("正在上传:"+progress + '%');
            $("#asda").append("<span id='asd' style='background-color:#117bed;display:block;height:20px;'></span>");
            $('#asd').css('width', progress + '%');
            if(progress==100){
                $("#asda").html("全量包上传完成!");
                $('#asd').remove();
            }
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