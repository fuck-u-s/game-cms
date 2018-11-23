<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门管理</title>

    <link rel="shortcut icon" href="${path}/static/images/favicon.ico">
    <link href="${path}/static/Hui/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/static/Hui/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Data Tables -->
    <link href="${path}/static/Hui/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${path}/static/Hui/css/animate.css" rel="stylesheet">
    <link href="${path}/static/Hui/css/style.css?v=4.2.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>部门管理</h2>
        <ol class="breadcrumb">
            <li>
                <a href="/">主页</a>
            </li>
            <li>
                <strong>包屑导航</strong>
            </li>
        </ol>
    </div>
    <div class="col-sm-8">
        <div class="title-action">
            <a href="${path}/dept/setDept" class="btn btn-primary">新增部门</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>基本 <small>分类，查找</small></h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="table_data_tables.html#">选项1</a>
                            </li>
                            <li><a href="table_data_tables.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <table id="dataTables" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
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
<script src="${path}/static/js/sys/dept/deptList.js?v=333"></script>
</body>
</html>

