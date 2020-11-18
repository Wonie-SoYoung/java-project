/*
* index页面使用的Js
* */


/*查询首页的用户量*/
getUserCont();
function getUserCont() {
    var userSum = $(".userSum").text();
    $.ajax({
        type: "GET",
        url: "/back/countUser",
        data: {},
        success: function (data) {
            $(".userSum").text(data.userSum);
        }
    });
}

/*查询首页作品量*/
getUProductionCont();
function getUProductionCont() {
    var productionSum = $(".productionSum").text();
    $.ajax({
        type: "GET",
        url: "/production/countProduction",
        data: {},
        success: function (data) {
            $(".productionSum").text(data.productionSum);
        }
    });
}

echarts()
function echarts() {
    var userSum = $(".userSum").text();
    $.ajax({
        type: "GET",
        url: "/queen/echartsList",
        data: {},
        success: function (data) {
            var data1 = [];
            var data2 = [];
            var data3 = [];
            for (var i = 0; i < data.productionByCreatetime.length; i++) {
                data1.push(data.productionByCreatetime[i].createtime);
                data2.push(data.productionByCreatetime[i].ucount)
                data3.push(data.productionByCreatetime[i].pcount)
            }
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            var option = {
                backgroundColor: '#323a5e', /*8C8C8C*/
                tooltip: {
                    trigger: 'axis',
                    axisPointer: { // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '2%',
                    right: '4%',
                    bottom: '14%',
                    top: '16%',
                    containLabel: true
                },
                legend: {
                    data: ['作品发布总数', '用户注册总数'],/*商品总数, '注册订单总数'*/
                    right: 10,
                    top: 12,
                    textStyle: {
                        color: "#fff"
                    },
                    itemWidth: 12,
                    itemHeight: 10,
                    // itemGap: 35
                },
                xAxis: {
                    type: 'category',
                    data: data1,
                    axisLine: {
                        lineStyle: {
                            color: 'white'

                        }
                    },
                    axisLabel: {
                        // interval: 0,
                        // rotate: 40,
                        textStyle: {
                            fontFamily: 'Microsoft YaHei'
                        }
                    },
                },

                yAxis: {
                    type: 'value',

                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: 'white'
                        }
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: 'rgba(255,255,255,0.3)'
                        }
                    },
                    axisLabel: {}
                },
                "dataZoom": [{
                    "show": true,
                    "height": 12,
                    "xAxisIndex": [
                        0
                    ],
                    bottom: '8%',
                    "start": 10,
                    "end": 90,
                    handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                    handleSize: '110%',
                    handleStyle: {
                        color: "#d3dee5",

                    },
                    textStyle: {
                        color: "#fff"
                    },
                    borderColor: "#90979c"
                }, {
                    "type": "inside",
                    "show": true,
                    "height": 15,
                    "start": 1,
                    "end": 35
                }],
                series: [{
                    name: '作品发布总数',
                    type: 'bar',
                    barWidth: '15%',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#fccb05'
                            }, {
                                offset: 1,
                                color: '#f5804d'
                            }]),
                            barBorderRadius: 12,
                        },
                    },
                    data: data3
                },
                    {
                        name: '用户注册总数',
                        type: 'bar',
                        barWidth: '15%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#8bd46e'
                                }, {
                                    offset: 1,
                                    color: '#09bcb7'
                                }]),
                                barBorderRadius: 11,
                            }

                        },
                        data: data2
                    }/*,
                    {
                        name: '注册订单总数',
                        type: 'bar',
                        barWidth: '15%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#248ff7'
                                }, {
                                    offset: 1,
                                    color: '#6851f1'
                                }]),
                                barBorderRadius: 11,
                            }
                        },
                        data: [400, 600, 700]
                    }*/
                ]
            };

            var app = {
                currentIndex: -1,
            };
            setInterval(function() {
                var dataLen = option.series[0].data.length;

                // 取消之前高亮的图形
                myChart.dispatchAction({
                    type: 'downplay',
                    seriesIndex: 0,
                    dataIndex: app.currentIndex
                });
                app.currentIndex = (app.currentIndex + 1) % dataLen;
                //console.log(app.currentIndex);
                // 高亮当前图形
                myChart.dispatchAction({
                    type: 'highlight',
                    seriesIndex: 0,
                    dataIndex: app.currentIndex,
                });
                // 显示 tooltip
                myChart.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: app.currentIndex
                });


            }, 2500);

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
}
