/**
 * 方法描述:系统资源监控
 *
 * author 小刘
 * version v1.0
 * date 2017/4/16 15:35
 */

// 参数设置
var setting = {
    // 折形图标
    myChart:null,
    // JVM仪表盘
    JVMChart:null,
    // CPU仪表盘
    CPUChart:null,
    // RAM仪表盘
    RAMChart:null,
    // 时间
    xtime:"",
    // 初始化时间组
    res:null,
    option:null,
    resData:function(){
        var now = new Date();
        var res = [];
        var len = 20;
        while (len--) {
            var time = now.toLocaleTimeString().replace(/^\D*/, '');
            time = time.substr(time.indexOf(":") + 1);
            res.unshift(time);
            now = new Date(now - 1000);
        }
        return res;
    },
    config:function(key){
        var val = $("#"+key).val();
        $.ajax({
            url: "/setConfig",
            data:{"key":key,"value":val},
            dataType: "json",
            success: function (data) {
                if(data.code > 0){
                    layer.alert(data.msg,{icon:1});
                }else{
                    layer.alert(data.msg,{icon:2});
                }
            }
        });
    },
    // 仪表盘
    JVM:{
        "tooltip": {
            "formatter": "{a} <br/>{b} : {c}%"
        },
        "series": [{
            "name": "JVM使用率",
            "type": "gauge",
            "splitNumber": 5,
            "axisLine": {
                "lineStyle": {
                    "color": [
                        [0.31, "#F37B1D"],
                        [1, "#e9ecf3"]
                    ],
                    "width": 50
                }
            },
            "axisTick": {
                "lineStyle": {
                    "color": "#3bb4f2",
                    "width": 3
                },
                "length": -25,
                "splitNumber": 1
            },
            "axisLabel": {
                "distance": -80,
                "textStyle": {
                    "color": "#000"
                }
            },
            "splitLine": {
                "show": false
            },
            "itemStyle": {
                "normal": {
                    "color": "#494f50"
                }
            },
            "detail": {
                "formatter": "{value}%",
                "offsetCenter": [0, "60%"],
                "textStyle": {
                    "fontSize": 30,
                    "color": "#F37B1D"
                }
            },
            "title": {
                "offsetCenter": [0, "100%"]
            },
            "data": [{
                "name": "JVM",
                "value": 50
            }]
        }]
    },
    CPU:{
        "tooltip": {
            "formatter": "{a} <br/>{b} : {c}%"
        },
        "series": [{
            "name": "CPU实用率",
            "type": "gauge",
            "splitNumber": 5,
            "axisLine": {
                "lineStyle": {
                    "color": [
                        [0.31, "#F37B1D"],
                        [1, "#e9ecf3"]
                    ],
                    "width": 50
                }
            },
            "axisTick": {
                "lineStyle": {
                    "color": "#3bb4f2",
                    "width": 3
                },
                "length": -25,
                "splitNumber": 1
            },
            "axisLabel": {
                "distance": -80,
                "textStyle": {
                    "color": "#000"
                }
            },
            "splitLine": {
                "show": false
            },
            "itemStyle": {
                "normal": {
                    "color": "#494f50"
                }
            },
            "detail": {
                "formatter": "{value}%",
                "offsetCenter": [0, "60%"],
                "textStyle": {
                    "fontSize": 30,
                    "color": "#F37B1D"
                }
            },
            "title": {
                "offsetCenter": [0, "100%"]
            },
            "data": [{
                "name": "CPU",
                "value": 10
            }]
        }]
    },
    RAM:{
        "tooltip": {
            "formatter": "{a} <br/>{b} : {c}%"
        },
        "series": [{
            "name": "内存使用率",
            "type": "gauge",
            "splitNumber": 5,
            "axisLine": {
                "lineStyle": {
                    "color": [
                        [0.31, "#F37B1D"],
                        [1, "#e9ecf3"]
                    ],
                    "width": 50
                }
            },
            "axisTick": {
                "lineStyle": {
                    "color": "#3bb4f2",
                    "width": 3
                },
                "length": -25,
                "splitNumber": 1
            },
            "axisLabel": {
                "distance": -80,
                "textStyle": {
                    "color": "#000"
                }
            },
            "splitLine": {
                "show": false
            },
            "itemStyle": {
                "normal": {
                    "color": "#494f50"
                }
            },
            "detail": {
                "formatter": "{value}%",
                "offsetCenter": [0, "60%"],
                "textStyle": {
                    "fontSize": 30,
                    "color": "#F37B1D"
                }
            },
            "title": {
                "offsetCenter": [0, "100%"]
            },
            "data": [{
                "name": "RAM",
                "value": 65
            }]
        }]
    },
    detectionData:function(str){
        var color = '#5eb95e';
        if (str >= 30 && str <= 60) {
            color = '#F37B1D';
        } else if (str > 60) {
            color = '#dd514c';
        }
        return color;
    },
    wsData:function(){
        // 处理时间
        setting.xtime = (new Date()).toLocaleTimeString().replace(/^\D*/, '');
        setting.xtime = setting.xtime.substr(setting.xtime.indexOf(":") + 1);
        // 处理数据
        var jvm = [];
        var ram = [];
        var cpu = [];
        $.ajax({
            url: "/usage",
            dataType: "json",
            success: function (data) {
                // 表格数字
                $("#cpuUse").html(data.cpuUsage);
                $("#ramUse").html(data.jvmUsage);
                $("#jvmUse").html(data.ramUsage);

                jvm.push(data.jvmUsage);
                ram.push(data.ramUsage);
                cpu.push(data.cpuUsage);

                // 折形图数据处理
                setting.myChart.addData([
                    [0,jvm,false,false],
                    [1,ram,false,false],
                    [2,cpu,false,false,setting.xtime]
                ]);

                // JVM
                setting.JVM.series[0].data[0].value = data.jvmUsage;
                setting.JVM.series[0].axisLine.lineStyle.color[0][0] = data.jvmUsage / 100;
                setting.JVM.series[0].axisLine.lineStyle.color[0][1] = setting.detectionData(data.jvmUsage);
                setting.JVMChart.setOption(setting.JVM, true);

                // CPU
                setting.CPU.series[0].data[0].value = data.cpuUsage;
                setting.CPU.series[0].axisLine.lineStyle.color[0][0] = data.cpuUsage / 100;
                setting.CPU.series[0].axisLine.lineStyle.color[0][1] = setting.detectionData(data.cpuUsage);
                setting.CPUChart.setOption(setting.CPU, true);

                // RAM
                setting.RAM.series[0].data[0].value = data.ramUsage;
                setting.RAM.series[0].axisLine.lineStyle.color[0][0] = data.ramUsage / 100;
                setting.RAM.series[0].axisLine.lineStyle.color[0][1] = setting.detectionData(data.ramUsage);
                setting.RAMChart.setOption(setting.RAM, true);
            }
        });
    }
};

// 初始化
$(function(){

    // 时间初始化
    setting.res = setting.resData();

    // 折形图数据
    setting.option = {
        tooltip : {
            trigger: 'axis'
        },
        legend : {
            data : [ 'jvm内存使用率', '物理内存使用率', 'cpu使用率' ]
        },
        grid : {
            x : 40,
            y : 30,
            x2 : 10,
            y2 : 35,
            borderWidth : 0,
            borderColor : "#FFFFFF"
        },
        xAxis : [ {
            axisLabel : {
                rotate : 40,
            },
            type : 'category',// 坐标轴类型，横轴默认为类目型'category'，纵轴默认为数值型'value'
            data : setting.res
        } ],
        yAxis : [ {
            min : 0,
            max : 100,
            axisLabel : {
                formatter : '{value}%'
            }
        } ],
        series : [
            {
                name : 'jvm内存使用率',
                type : 'line',
                data : [ 12, 25, 31, 11, 45, 50, 11, 09, 28, 35, 40, 24,
                    28, 39, 23, 31, 14, 18, 08, 36 ]
            },
            {
                name : '物理内存使用率',
                type : 'line',
                data : [ 55, 41, 10, 23, 15, 44, 29, 41, 27, 05, 12, 25,
                    31, 11, 45, 50, 11, 09, 28, 35 ]
            },
            {
                name : 'cpu使用率',
                type : 'line',
                data : [ 40, 24, 28, 39, 23, 31, 14, 18, 08, 36, 55, 41,
                    10, 23, 15, 44, 29, 41, 27, 05 ]
            } ]
    };

    // 初始化图标
    setting.myChart = echarts.init(document.getElementById('main'));
    setting.myChart.setOption(setting.option);

    // JVM仪表
    setting.JVMChart = echarts.init(document.getElementById('mainJVM'));
    setting.JVMChart.setOption(setting.JVM);

    // CPU仪表
    setting.CPUChart = echarts.init(document.getElementById('mainCPU'));
    setting.CPUChart.setOption(setting.CPU);

    // RAM仪表
    setting.RAMChart = echarts.init(document.getElementById('mainRAM'));
    setting.RAMChart.setOption(setting.RAM);

    clearInterval(setting.wsData());

    // 数据监控
    setInterval(function(){
        setting.wsData();
    },3000);
});
