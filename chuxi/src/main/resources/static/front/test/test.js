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
    $('#fileupload').fileupload({
        url: context + "file/uploadFile",
        formData: {userid: user.id, path: 'head'},
        dataType: 'json',
        done: function (e, data) {
            console.log(data);
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        }
    });
}