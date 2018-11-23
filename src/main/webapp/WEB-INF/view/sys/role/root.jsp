<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>角色管理</title>

    <link href="${path}/static/Hui/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${path}/static/Hui/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Data Tables -->
    <link href="${path}/static/Hui/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${path}/static/Hui/css/animate.css" rel="stylesheet">
    <link href="${path}/static/Hui/css/style.css?v=4.2.0" rel="stylesheet">
    <link rel="stylesheet" href="${path}/static/plugins/zTree/css/metroStyle/metroStyle.css"/>
</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>权限分配</h2>
        <ol class="breadcrumb">
            <li>
                <a href="${path}/role/roleList">角色管理</a>
            </li>
            <li>
                <strong>权限分配</strong>
            </li>
        </ol>
    </div>
    <div class="col-sm-8">
        <div class="title-action"></div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="widget-box widget-color-blue2">
                <div class="widget-header">
                    <h4 class="widget-title lighter smaller">选择菜单</h4>
                </div>
                <input type="hidden" id="role_id" name="role_id" value="${role_id}"/>
                <div class="widget-body">
                    <div class="widget-main padding-8">
                        <ul id="tree" class="ztree" role="tree"></ul>
                    </div>
                </div>
            </div>
            <button class="btn btn-info" type="button" id="sub">
                <i class="ace-icon fa fa-check bigger-110"></i>
                提交
            </button>

            &nbsp; &nbsp; &nbsp;
            <button class="btn" type="button" onclick="history.go(-1);">
                <i class="ace-icon fa fa-undo bigger-110"></i>
                返回
            </button>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/common/includ-js.jsp" %>
<!-- Data Tables -->
<script src="${path}/static/plugins/dataTables/js/jquery.dataTables.js"></script>
<script src="${path}/static/plugins/dataTables/js/dataTables.bootstrap.js"></script>
<script src="${path}/static/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${path}/static/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript" src="${path}/static/plugins/zTree/js/jquery.ztree.excheck-3.5.min.js" ></script>
<script type="text/javascript">
    var treeList = ${treeList};
</script>
<script type="text/javascript" src="${path}/static/js/sys/role/root.js"></script>
</body>
</html>


