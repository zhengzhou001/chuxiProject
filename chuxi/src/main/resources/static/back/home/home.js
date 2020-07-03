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
//选项卡绑定事件
function initMenuTab() {

    //选项卡切换事件
    element.on('tab(menuTab)', function(data){
        //console.log(this); //当前Tab标题所在的原始DOM元素
        //console.log(data.index); //得到当前Tab的所在下标
        //console.log(data.elem); //得到当前的Tab大容器
    });

    //选项卡删除
    element.on('tabDelete(menuTab)', function(data){
        //console.log(this); //当前Tab标题所在的原始DOM元素
        //console.log(data); //得到当前Tab的所在下标
        //console.log(data.elem); //得到当前的Tab大容器
    });
}
//菜单点击事件
function menuClick(menuId) {
    if (undefined == menuId||menuId==null||menuId=="")
        return;

    //element.tabChange(filter, layid);
    var exist=$("li[lay-id='"+menuId+"']").length; //判断是否存在tab
    if(exist==0){
        element.tabAdd('menuTab',{
            title: menuId,
            //content: "<iframe src='"+context+"route/index' class='layadmin-iframe '>", //支持传入html
            content: menuId,
            id: menuId,
        });
    }
    element.tabChange('menuTab',menuId);//切换tab
}