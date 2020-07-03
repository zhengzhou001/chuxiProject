var element,user,insTb;
layui.use(['element'], function(){
    element = layui.element;
    if(!$.cookie("CUR_USER")){
        baseTools.gotoLogin();
        return;
    }
    init();//初始化
});

//初始化
function  init() {
    doSearch();
}

function doSearch(){
    user = JSON.parse($.cookie("CUR_USER"));
    var menuSelectParam ={"appid":user.appid,"realurl":"user-service/sys/selectSysMenu"};

    /*使用模块加载的方式 加载文件*/
    layui.config({
        base: '/user/js/layui/treetable-lay/'
    }).use(['layer', 'treeTable','form','table'], function () {

        var table = layui.table;
         var form = layui.form;
        var treeTable = layui.treeTable;
            baseTools.xhrAjax({
                url: "../../doService",
                contentType: "application/json",
                params:  JSON.stringify(menuSelectParam),
                callback: [function (jsonObj, xhrArgs) {
                        insTb = treeTable.render({

                        tree: {
                            iconIndex: 0,
                            isPidData: true,
                            idName: 'id',
                            pidName: 'pid',
                            arrowType: 'arrow2',
                            getIcon: 'ew-tree-icon-style2'
                        },
                        elem: '#menuTable',
                        data:jsonObj.data,
                            toolbar: 'default',
                            cols: [[
                            //{ type: 'checkbox' },
                            {field: 'name',align:"center", title: '菜单名称'},
                            {field: 'id', align:"center",hide:true, title: 'id'},
                            {field: 'url', align:"center",title: '链接地址'},
                            {field: 'orderid', align:"center",title:"顺序"},
                            {field: 'createDate', align:"center",title: '创建时间'},
                            {field: 'appid', align:"center",hide:true},
                            {field: 'state',align:"center", title: '状态',templet:"#stateTemp"},
                            {field: 'flag',align:"center", title: '是否用户中心显示',templet:"#flagTemp"},
                            {field:'pid',templet: complain,align:"center", title: '操作'}
                        ]],
                    });
                }]
            });
                    function complain(d) {//操作中显示的内容
                        if (d.children!=null&&d.children.length>0) {
                            return [
                                '<a class="layui-btn layui-btn-xs" lay-event="addChildrenMenu">添加</a>',
                                '<a class="layui-btn layui-btn-xs" lay-event="updateMenu">修改</a>'
                            ].join('');
                        } else {
                            return [
                                '<a class="layui-btn layui-btn-xs" lay-event="addChildrenMenu">添加</a>',
                                '<a class="layui-btn layui-btn-xs" lay-event="updateMenu">修改</a>',
                                '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteMenu">删除</a>'
                            ].join('');

                        }
                    }


        //头部工具栏，添加菜单默认pid=-1  到表格右侧工具栏时，pid改为动态获取
        var pid=-1;
        //监听头部工具栏点击事件
        treeTable.on('toolbar(menuTable)', function (obj) {
            switch (obj.event) {
                case 'open':
                    insTb.expandAll();
                    break;
                case 'close':
                    insTb.foldAll();
                    break;
                case 'addMenu':
                    var title="添加一个顶级菜单";
                    layer.open({
                        title:[title, 'color:#008000; font-size:20px;'],
                        offset: 'auto',
                        type:1,
                        content:$("#addForm"),
                        area:["500px","350px"]
                        });
                    break;
            }
        });
        //监听表格右侧工具栏按钮事件
        treeTable.on("tool(menuTable)",function(data){
            switch (data.event) {
                case 'deleteMenu':
                    layer.confirm("确认要删除此菜单吗",function (index) {
                        baseTools.xhrAjax({
                            url: "../../doService",
                            contentType: "application/json",
                            params:  JSON.stringify({"id":data.data.id,"realurl":"user-service/sys/deleteSysMenu"}),
                            callback: [function (jsonObj, xhrArgs) {
                                switch (parseInt(jsonObj.code)) {
                                    case 0://成功
                                        layer.closeAll();
                                        doSearch();
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
                            }]
                        });
                    });
                    break;
                case 'updateMenu':
                    baseTools.xhrAjax({
                        url: "../../doService",
                        contentType: "application/json",
                        params:  JSON.stringify({"id":data.data.id,"realurl":"user-service/sys/selectSysMenu"}),
                        callback: [function (jsonObj, xhrArgs) {
                            form.val("updateFormFilter", jsonObj.data[0]);
                            //弹出层
                            layer.open({
                                title:"修改菜单信息",
                                offset: 'auto',
                                anim: 5,
                                type:1,
                                //获取到修改表格的内容
                                content:$("#updateForm")
                            })
                        }]
                    });
                    break;
                case 'addChildrenMenu':
                    var title="添加一个子菜单";
                    layer.open({
                        title:[title, 'color:#008000; font-size:20px;'],
                        offset: 'auto',
                        type:1,
                        content:$("#addForm"),
                        area:["500px","350px"]
                    });
                    pid=data.data.id;

                    break;
            }
        });
        //监听修改的form的提交事件   使用的弹窗修改名字
        form.on("submit(updateFormSubmit)",function(data){
            baseTools.xhrAjax({
                url: "../../doService",
                contentType: "application/json",
                params:  JSON.stringify(
                    {
                        "id":data.field.id,
                        "pid":data.field.pid,
                        "url_NEW":data.field.url,
                        "state_NEW":data.field.state,
                        "name_NEW":data.field.name,
                        "orderid_NEW":data.field.orderid,
                        "flag_NEW":data.field.flag,
                        "realurl":"user-service/sys/updateSysMenu"
                    }),
                callback: [function (jsonObj, xhrArgs) {
                    switch (parseInt(jsonObj.code)) {
                        case 0://成功
                            layer.closeAll();
                            doSearch();
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
                }]
            });
        });
        //监听添加表格的form的提交事件
        // 使用的弹窗提交信息，将信息添加到数据库
        form.on("submit(addSubmitFilter)",function(data){
            var appid= user.appid;
            baseTools.xhrAjax({
                url: "../../doService",
                contentType: "application/json",
                params:  JSON.stringify({"name":data.field.name,"pid":pid,
                    "realurl":"user-service/sys/selectSysMenu"}),
                callback: [function (jsonObj, xhrArgs) {
                    switch (parseInt(jsonObj.data.length)) {
                        case 0://成功
                            baseTools.xhrAjax({
                                url: "../../doService",
                                contentType: "application/json",
                                params: JSON.stringify(
                                    {
                                        "name": data.field.name,
                                        "pid": pid,
                                        "appid":appid,
                                        "state": data.field.state,
                                        "url": data.field.url,
                                        "orderid": data.field.orderid,
                                        "flag":data.field.flag,
                                        "realurl": "user-service/sys/insertSysMenu"
                                    }),
                                callback: [function (jsonObj, xhrArgs) {
                                    switch (parseInt(jsonObj.code)) {
                                        case 0://成功
                                            layer.closeAll();

                                            doSearch();
                                            layer.alert(jsonObj.msg, {
                                                icon: 1,
                                                title: '提示'
                                            });
                                            $("#addForm")[0].reset();
                                            break;
                                        default:
                                            layer.alert(jsonObj.msg, {
                                                icon: 2,
                                                title: '提示'
                                            });
                                            //$("#addForm")[0].reset();
                                    }
                                }]
                            });
                            break;
                        default:
                            layer.alert("菜单名称已存在！请重新填写菜单名称",{
                                icon: 2,
                                title: '提示'
                            });
                            //$("#addForm")[0].reset();
                    }
                }]
            });
        });
        //自定义验证规则
        form.verify({
            name: function (value) {
                if (value.length < 2) {
                    return '菜单名称最少2个字符';
                }
                if (value.length > 15) {
                    return '菜单名称最多15个字符';
                }
            }
        });
    });
}







