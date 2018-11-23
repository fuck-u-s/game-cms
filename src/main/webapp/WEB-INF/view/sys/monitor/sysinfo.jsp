<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${path}/static/Hui/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/static/Hui/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${path}/static/Hui/css/animate.css" rel="stylesheet">
    <link href="${path}/static/Hui/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="animated fadeInRightBig">
            <div class="col-md-12 portlet ui-sortable">
                <section class="panel panel-success portlet-item">
                    <header class="panel-heading">
                        <i class="fa fa-briefcase"></i> 警告设置
                    </header>
                    <table class="table table-striped table-bordered table-hover" width="100%" style="vertical-align: middle;">
                        <thead>
                        <tr style="background-color: #faebcc; text-align: center;">
                            <td width="100">名称</td>
                            <td width="100">参数</td>
                            <td width="205">预警设置</td>
                            <td width="375">邮箱设置</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td style='padding-left: 10px; text-align: left;vertical-align: middle;'>CPU</td>
                            <td style='padding-left: 10px; text-align: left;vertical-align: middle;'>当前使用率：<span id="cpuUse" style="color: red;">50</span> %</td>
                            <td align="center">
                                <table>
                                    <tr>
                                        <td>使用率超出</td>
                                        <td><input name='cpu' id='cpu' type='text' value="${cpu.val}" maxlength="3"/> %,</td>
                                        <td>发送邮箱提示 <a class='btn btn-info' href='javascript:void(0)' onclick="setting.config('cpu')">修改</a></td>
                                    </tr>
                                </table>
                            </td>
                            <td rowspan='3' align="center" style="vertical-align: middle;">
                                <input style='width: 250px; height: 32px;'name='email' id='email' type='text' value='${email.val}' maxlength="30"/>
                                <a class='btn btn-info' href='javascript:void(0)' onclick="setting.config('email')">修改</a>
                            </td>
                        </tr>
                        <tr>
                            <td style='padding-left: 10px; text-align: left;vertical-align: middle;'>服务器内存</td>
                            <td style='padding-left: 10px; text-align: left;vertical-align: middle;'>当前使用率：<span id="ramUse" style="color: blue;">50</span> %</td>
                            <td align="center">
                                <table>
                                    <tr>
                                        <td>使用率超出</td>
                                        <td><input name='ram' id='ram' type='text' value='${ram.val}' maxlength="3"/> %,</td>
                                        <td>发送邮箱提示 <a class='btn btn-info' href='javascript:void(0)' onclick="setting.config('ram')">修改 </a></td>
                                    </tr>
                                </table>

                            </td>
                        </tr>
                        <tr>
                            <td style='padding-left: 10px; text-align: left;vertical-align: middle;'>JVM内存</td>
                            <td style='padding-left: 10px; text-align: left;vertical-align: middle;'>当前使用率：<span id="jvmUse" style="color: green;">50</span> %</td>
                            <td align="center">
                                <table>
                                    <tr>
                                        <td>使用率超出</td>
                                        <td><input name='jvm' id='jvm' type='text' value='${jvm.val}' maxlength="3"/> %,</td>
                                        <td>发送邮箱提示 <a class='btn btn-info' href='javascript:void(0)' onclick="setting.config('jvm')">修改 </a></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="col-md-6">
                <section class="panel panel-info portlet-item">
                    <header class="panel-heading">
                        <i class="fa fa-th-list"></i> 服务器信息
                    </header>
                    <div class="panel-body" style="padding: 0px" data-url="/monitor/systemInfo.shtml">
                        <table class="table table-striped table-bordered table-hover" style="margin: 0px;">
                            <tbody>
                            <tr>
                                <td class="left">ip地址</td>
                                <td id="hostIp" class="left">${systemInfo.hostIp}</td>
                            </tr>
                            <tr>
                                <td class="left">主机名</td>

                                <td class="left" id="hostName">${systemInfo.hostName}</td>
                            </tr>
                            <tr>
                                <td class="left">操作系统的名称</td>

                                <td class="left" id="osName">${systemInfo.osName}</td>
                            </tr>
                            <tr>
                                <td class="left">操作系统的构架</td>

                                <td class="left" id="arch">${systemInfo.arch}</td>
                            </tr>
                            <tr>
                                <td class="left">操作系统的版本</td>

                                <td class="left" id="osVersion">${systemInfo.osVersion}</td>
                            </tr>
                            <tr>
                                <td class="left">处理器个数</td>

                                <td class="left" id="processors">${systemInfo.processors}</td>
                            </tr>
                            <tr>
                                <td class="left">Java的运行环境版本</td>

                                <td class="left" id="javaVersion">${systemInfo.javaVersion}</td>
                            </tr>
                            <tr>
                                <td class="left">Java供应商的URL</td>

                                <td class="left" id="javaUrl">${systemInfo.vendor}</td>
                            </tr>
                            <tr>
                                <td class="left">Java的安装路径</td>

                                <td class="left" id="javaHome">${systemInfo.javaHome}</td>
                            </tr>
                            <tr>
                                <td class="left">临时文件路径</td>

                                <td class="left" id="tmpdir">${systemInfo.tmpdir}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
            </div>

            <div class="col-md-6">
                <section class="panel panel-danger portlet-item">
                    <header class="panel-heading">
                        <i class="fa fa-fire"></i> 实时监控
                    </header>

                    <div class="panel-body">
                        <div class="panel-body">
                            <div id="main" style="height: 290px;"></div>
                        </div>
                    </div>
                </section>
            </div>

            <div class="col-md-12" style="margin-top: 10px; height: 320px">
                <section class="panel panel-primary portlet-item">
                    <header class="panel-heading">
                        <i class="fa fa-rss-square"></i> 实时监控
                    </header>

                    <div class="panel-body">
                        <table style="width: 100%;">
                            <tr>
                                <td width="33.3%"><div id="mainJVM" style="height: 260px;"></div></td>
                                <td width="33.3%"><div id="mainCPU" style="height: 260px;"></div></td>
                                <td width="33.3%"><div id="mainRAM" style="height: 260px;"></div></td>
                            </tr>
                        </table>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="${path}/static/Hui/js/jquery.min.js?v=2.1.4"></script>
<script src="${path}/static/Hui/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${path}/static/plugins/layui/layui.all.js"></script>
<script src="${path}/static/plugins/echarts/esl/esl.js"></script>
<script src="${path}/static/plugins/echarts/echarts-all.js?v=5555"></script>
<!-- 自定义js -->
<script src="${path}/static/Hui/js/content.js?v=1.0.0"></script>
<script src="${path}/static/js/sys/monitor/monitor.js?v=5555"></script>

</body>

</html>

