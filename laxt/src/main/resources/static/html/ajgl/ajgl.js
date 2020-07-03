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
    var user = JSON.parse($.cookie("CUR_USER"));
    $("#name").val(user.name);

}