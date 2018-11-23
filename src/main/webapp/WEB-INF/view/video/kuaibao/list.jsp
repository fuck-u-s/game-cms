<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>发布人管理</title>

    <link href="${path}/static/Hui/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/static/Hui/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Data Tables -->
    <link href="${path}/static/Hui/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${path}/static/Hui/css/animate.css" rel="stylesheet">
    <link href="${path}/static/Hui/css/style.css?v=4.2.1" rel="stylesheet">
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css" media="all">
</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询条件</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="layui-form layui-form-pane col-sm-12">
                    <div class="layui-form-item">
                        <div class="layui-form-inline">
                            <label class="layui-form-label" style="width: 60px">编号</label>
                            <div class="layui-input-inline">
                                <input type="text" id="id" class="layui-input"/>
                            </div>
                        </div>
                        <div class="layui-form-inline">
                            <label class="layui-form-label" style="width: 70px">VID</label>
                            <div class="layui-input-inline">
                                <input type="text" id="vid" class="layui-input"/>
                            </div>
                        </div>
                        <div class="layui-form-inline">
                            <label class="layui-form-label" style="width: 60px">状态</label>
                            <div class="layui-input-inline" style="width: 70px;">
                                <select id="state">
                                    <option value="1">待处理</option>
                                    <option value="2">已处理</option>
                                </select>
                            </div>
                        </div>
                        <button class="layui-btn layui-btn-primary" id="sub">查询</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>基本 <small>分类，查找</small></h5>
                </div>
                <div class="ibox-content">
                    <table id="dataTables" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="5%">编号</th>
                            <th width="5%">VID</th>
                            <th width="15%">标题</th>
                            <th>来源</th>
                            <th>封面</th>
                            <th text-align="center">地址</th>
                            <th text-align="center">播放地址</th>
                            <th>时长</th>
                            <th>状态</th>
                            <th width="10%">时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/common/includ-js.jsp" %>
<!-- Data Tables -->
<script src="${path}/static/plugins/dataTables/js/jquery.dataTables.js"></script>
<script src="${path}/static/plugins/dataTables/js/dataTables.bootstrap.js"></script>
<script src="${path}/static/plugins/layui/layui.all.js"></script>
<script src="${path}/static/plugins/ConfigUtils.js"></script>
<script src="${path}/static/js/video/kuaibao/list.js?v=v15922"></script>
</body>
</html>

