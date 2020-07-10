var form, table, element, layer, carousel;
layui.use(['element', 'table', "layer", "form", "carousel"], function () {
    element = layui.element;
    layer = layui.layer;
    table = layui.table;
    form = layui.form;
    carousel = layui.carousel;
    init();//初始化
});

//初始化
function init() {
    initCarousel();//轮播图初始化
    initRxcpChartBar();//热销产品柱状图
    initTjcpChartBar();//推荐产品柱状图
    //getTestData();
}

//轮播图初始化
function initCarousel() {
    //常规轮播
    carousel.render({
        elem: '#carousel'
        , width: '100%' //设置容器宽度
        , height: '440px'
        , arrow: 'hover'//始终显示箭头 hover（悬停显示） always（始终显示） none（始终不显示）
        , anim: 'fade' //切换动画方式 default（左右切换） updown（上下切换） fade（渐隐渐显切换）
        , autoplay: true //是否自动切换
        , interval: 30000 // 自动切换的时间间隔，单位：ms（毫秒），不能低于800
    });
}

//热销产品柱状图
function initRxcpChartBar() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init($("#rxcpChartBar")[0]);
    var option = {
        color: ['#aa66db','#71db14'],
        title: {
            text: '畅销Top5'
        },
        legend: {
            data: ['销售数量', '销售总价'],
            right: 0
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ['鱼钩', '鱼竿', '钓椅', '钓蹬', '野战蓝鲫'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '销售数量',
                type: 'bar',
                barWidth: '30%',
                data: [10000, 100, 50, 60, 500]

            },
            {
                name: '销售总价',
                type: 'bar',
                barWidth: '30%',
                data: [15000, 10000, 50000, 40000, 20000]

            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

//推荐产品柱状图
function initTjcpChartBar() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init($("#tjcpChartBar")[0]);
    var option = {
        color: ['#db3d25','#dadb0e'],
        title: {
            text: '推荐Top5'
        },
        legend: {
            data: ['销售价格', '推荐指数'],
            right: 0
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ['鱼钩', '鱼竿', '钓椅', '钓蹬', '野战蓝鲫'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '销售价格',
                type: 'bar',
                barWidth: '30%',
                data: [10, 11, 122, 13, 14]

            },
            {
                name: '推荐指数',
                type: 'bar',
                barWidth: '30%',
                data: [1, 2, 3, 4, 5]

            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

//测试
function getTestData() {
    baseTools.xhrAjax({
        url: context + "test/getTestData",
        contentType: "application/json",
        params: JSON.stringify({}),
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
}

//个人主页
function home() {

}