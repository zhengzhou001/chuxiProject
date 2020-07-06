var form,table,element,layer;
layui.use(['element','table',"layer","form"], function(){
    element = layui.element;
    layer = layui.layer;
    table = layui.table;
    var form = layui.form;
    init();//初始化
});

//初始化
function  init() {

    initMenuTab();
}
//菜单选项卡绑定事件
function initMenuTab() {
    //左侧菜单点击事件
    $("#LAY-system-side-menu li a").on("click",function () {
        var menuHref = $(this).attr("menuHref");
        if(baseTools.isNotBlank(menuHref)){
             menuClick($(this).attr("menuId"),$(this).attr("menuName"),$(this).attr("menuHref"));
        }
    });
    //左侧菜单收缩
    $("#resizeMenu").on("click",function () {
        if ($("#base").is(".layadmin-side-shrink")){
            $("#base").removeClass("layadmin-side-shrink");
            $("#resizeMenu i").removeClass("layui-icon-spread-left").addClass("layui-icon-shrink-right");
        }else{
            $("#base").addClass("layadmin-side-shrink");
            $("#resizeMenu i").removeClass("layui-icon-shrink-right").addClass("layui-icon-spread-left");
        }

    });
    //左移
    $("#tabLeft").on("click",function () {
        var index=$("#LAY_app_tabsheader .layui-this").index("#LAY_app_tabsheader li");
        if(index>0){
            element.tabChange('layadmin-layout-tabs',$("#LAY_app_tabsheader li").eq(index-1).attr("lay-id"));//切换tab
        }
    });
    //右移
    $("#tabRight").on("click",function () {
        var index=$("#LAY_app_tabsheader .layui-this").index("#LAY_app_tabsheader li");
        var count = $("#LAY_app_tabsheader li").length;
        if(index<count-1){
            element.tabChange('layadmin-layout-tabs',$("#LAY_app_tabsheader li").eq(index+1).attr("lay-id"));//切换tab
        }
    });
    //关闭其他
    $("#closeOther").on("click",function () {
        var tabtitle = $("#LAY_app_tabsheader li");
        $.each(tabtitle, function (i) {
            if(!$(this).is('.layui-this'))
                element.tabDelete("layadmin-layout-tabs",$(this).attr("lay-id"));
        });
    });
    //关闭当前
    $("#closeNow").on("click",function () {
        element.tabDelete("layadmin-layout-tabs", $("#LAY_app_tabsheader .layui-this").attr("lay-id"));
    });
    //关闭所有
    $("#closeAll").on("click",function () {
        var tabtitle = $("#LAY_app_tabsheader li");
         $.each(tabtitle, function (i) {
             element.tabDelete("layadmin-layout-tabs",$(this).attr("lay-id"));
        });
    });

}
//菜单点击事件
function menuClick(menuId,menuName,menuUrl) {
    if (baseTools.isBlank(menuId))
        return;

    //element.tabChange(filter, layid);
    var exist=$("li[lay-id='"+menuId+"']").length; //判断是否存在tab
    if(exist==0){
        //添加标签页
        element.tabAdd('layadmin-layout-tabs',{
            title: menuName,
            content: "<iframe src='"+context+menuUrl+"' class='layadmin-iframe '>", //支持传入html
            //content: menuId,
            id: menuId,
        });
    }
    element.tabChange('layadmin-layout-tabs',menuId);//切换tab
}