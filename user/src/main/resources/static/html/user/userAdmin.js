var element,user;
layui.use(['element','tree','table',"jquery","layer","form"], function(){
    element = layui.element;
    if(!$.cookie("CUR_USER")){
        baseTools.gotoLogin();
        return;
    }
    init();//初始化
});

//初始化
function  init() {
    var form = layui.form;
    baseTools.xhrAjax({
        url: "../../doService",
        contentType: "application/json",
        params:  JSON.stringify({"realurl":"user-service/zull/selectAppZuulList"}),
        callback: [function (jsonObj, xhrArgs) {
            var options="";
            for(var i=0;i<jsonObj.data.length;i++){
                options+="<option value='"+jsonObj.data[i].id+"'>"+jsonObj.data[i].note+"</option>"
            }
            console.log(options)
            $("#appidSelect").html(options);
            form.render();
        }]
    });
doSearch();
}

function doSearch() {
    user = JSON.parse($.cookie("CUR_USER"));
    var name = $("#selectName").val();//获取到搜索输入框输入的内容
    var ajaxParm={"name_LIKE":name,flag:1,"realurl":"user-service/sys/selectSysUser"};

    layui.use(['table',"jquery","layer","form"], function(){
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var jquery = layui.jquery;
        var tableIns=table.render({
            elem:"#userTable",
            url:"../../doService",
            // 此属性是给table添加一个工具栏
            toolbar:"#toolbarDemo",
            page:true,
            limit:10,
            contentType:'application/json',
            where:ajaxParm,
            request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },

            cols:[
                [
                    {type:"checkbox",align:"center"},
                    {field:"id",hide:true,title:"id",align:"center"},
                    {field:"name",title:"姓名",align:"center"},
                    {field:"account",title:"登录账户",align:"center"},
                    {field:"phone",title:"手机号码",align:"center"},
                    {field:"email",title:"电子邮箱",align:"center"},
                    {field:"roleName",title:"角色",align:"center"},
                    {field:"appid",title:"应用系统",align:"center"},
                    {field: 'state',align:"center", title: '状态',templet:"#stateTemp"},
                    {title:"操作",align:"center",templet:"#caoZuoTemplat"}
                ]

            ]
        });
        //监听删除的按钮
        table.on("tool(tableFilter)",function(data){
            if (data.event=="deleteUser") {
                //获取这一行的属性值
                layer.confirm("确认要删除此用户吗",function (index) {
                    baseTools.xhrAjax({
                        url: "../../doService",
                        contentType: "application/json",
                        params:  JSON.stringify({"id":data.data.id,"realurl":"user-service/sys/deleteSysUser"}),
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
            }else if(data.event=="updateUser"){
                baseTools.xhrAjax({
                    url: "../../doService",
                    contentType: "application/json",
                    params:  JSON.stringify({"id":data.data.id,"realurl":"user-service/sys/selectSysUser"}),
                    callback: [function (jsonObj, xhrArgs) {
                        form.val("updateFormFilter", jsonObj.data[0]);
                        //弹出层
                        layer.open({
                            title:"修改用户信息",
                            type:1,
                            //获取到修改表格的内容
                            content:$("#updateForm")
                        })
                    }]
                });
            }else if(data.event=="setRole"){
                layer.open({
                    type: 2,
                    title: '设置角色',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['380px', '300px'],
                    content: './setRole.html',
                    success: function (layero, index) {
                        var body = layer.getChildFrame('body', index);//少了这个是不能从父页面向子页面传值的
                        //获取子页面的元素，进行数据渲染
                        body.contents().find("#useridOther").val(data.data.id);
                    },
                    end: function () {
                        layer.closeAll();
                        init();　　//页面刷新
                    }
                });
            }
        });
        //监控工具栏的toolbar事件属性
        table.on("toolbar(tableFilter)",function(data){
            if(data.event=="search_like"){
                doSearch();
            }else if(data.event=="multiDelete"){
                var obj=table.checkStatus("userTable");
                if(obj.data.length==0){
                    layer.alert("请先勾选需要删除的数据",{
                        icon: 2,
                        title: '提示'
                    });
                }else{
                    layer.confirm("确认删除吗？",function (index) {
                        //把数据中的id分离出来
                        var ids = [];
                        for(var i=0;i<obj.data.length;i++){
                            ids[i]=obj.data[i].id;
                        }
                        //携带数据，发送ajax请求
                        baseTools.xhrAjax({
                            url: "../../doService",
                            contentType: "application/json",
                            params:  JSON.stringify({"ids" :ids,"realurl":"user-service/sys/multiDeleteSysUser"}),
                            callback: [function (jsonObj, xhrArgs) {
                                switch (parseInt(jsonObj.code)) {
                                    case 0://成功
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

                    })
                }
            }else if(data.event=="addUser"){
                layer.open({
                    title:"添加用户信息",
                    type: 1,
                    area:["500px","500px"],
                    content:$("#addForm")
                })

            }else if(data.event=="setRole"){
                var obj=table.checkStatus("userTable");
                if(obj.data.length==0){
                    layer.alert('请勾选一个需要赋权的用户', {
                        icon: 2,
                        title: '提示'
                    });
                }else {
                    layer.open({
                        type: 2,
                        title: '设置角色',
                        shadeClose: true,
                        shade: 0.8,
                        area: ['380px', '300px'],
                        content: './setRole.html',
                        success: function (layero, index) {
                            var body = layer.getChildFrame('body', index);//少了这个是不能从父页面向子页面传值的
                            //获取子页面的元素，进行数据渲染
                            body.contents().find("#useridOther").val(obj.data[0].id);
                        },

                        end: function () {

                            init();　　//页面刷新
                        }
                    });

                }
            }
        });
        //监听修改的form的提交事件   使用的弹窗修改名字
        form.on("submit(updateFormSubmit)",function(data){
            console.log(data);
            baseTools.xhrAjax({
                url: "../../doService",
                contentType: "application/json",
                params:  JSON.stringify(
                    {
                        "id":data.field.id,
                        "state_NEW":data.field.state,
                        "name_NEW":data.field.name,
                        "phone_NEW":data.field.phone,
                        "email_NEW":data.field.email,
                        "realurl":"user-service/sys/updateSysUser"
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
            var pwd1=data.field.pwd1;
            var pwd2=data.field.pwd2;
            if (pwd1==pwd2 && pwd1!=null){
                var pwd=$.md5(pwd1);
                baseTools.xhrAjax({
                    url: "../../doService",
                    contentType: "application/json",
                    params:  JSON.stringify({"account":data.field.account,appid:data.field.appid,"realurl":"user-service/sys/selectSysUser"}),
                    callback: [function (jsonObj, xhrArgs) {
                        switch (parseInt(jsonObj.data.length)) {
                            case 0://成功
                                baseTools.xhrAjax({
                                    url: "../../doService",
                                    contentType: "application/json",
                                    params:  JSON.stringify(
                                        {
                                            "id":null,
                                            "appid":data.field.appid,
                                            "account":data.field.account,
                                            "pwd":pwd,
                                            "state":data.field.state,
                                            "name":data.field.name,
                                            "phone":data.field.phone,
                                            "email":data.field.email,
                                            "realurl":"user-service/sys/insertSysUserAdmin"
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
                                                jquery("#addForm")[0].reset();
                                                break;
                                            default:
                                                layer.alert(jsonObj.msg, {
                                                    icon: 2,
                                                    title: '提示'
                                                });
                                                //jquery("#addForm")[0].reset();
                                        }
                                    }]
                                });
                                break;
                            default:
                                layer.alert("登录账户已存在！请重新填写账号",{
                                    icon: 2,
                                    title: '提示'
                                });
                                //jquery("#addForm")[0].reset();
                        }
                    }]
                });
            }else{
                layer.alert("两次密码输入不一致",{
                    icon: 2,
                    title: '提示'
                });
            }
       });
        //自定义验证规则
        form.verify({
            account: function (value) {
                if (value.length < 4) {
                    return '登录账户最少4个字符';
                }
                if (value.length > 15) {
                    return '登录账户最多15个字符';
                }
            }
            , pwd1: function (value) {
                if (value.length < 4) {
                    return '密码最少4个字符';
                }
                if (value.length > 15) {
                    return '密码最多15个字符';
                }
            }

        });
    });
}


