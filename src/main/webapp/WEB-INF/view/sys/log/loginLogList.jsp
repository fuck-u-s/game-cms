<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>操作日志</title>
    <%@ include file="/WEB-INF/view/common/includ-css.jsp" %>
</head>
<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-12">
        <div class="ibox float-e-margins" style="margin-bottom: 0px;">
            <div class="ibox-title">
                <h5>查询条件</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <form class="form-inline">
                    <div class="form-group">
                        <label class="font-normal">用户：</label>
                        <select class="form-control" id="user_id">
                            <option value="0">全部</option>
                            <c:forEach items="${allUsers}" var="obj">
                                <option value="${obj.id}">${obj.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="font-normal">创建时间：</label>
                        <div class="input-daterange input-group">
                            <input type="text" class="input-sm form-control" style="width: 140px;" id="start_time" readonly
                                   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                            />
                            <span class="input-group-addon">至</span>
                            <input type="text" class="input-sm form-control" id="end_time" style="width: 140px;" readonly
                                   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                            />
                        </div>
                    </div>
                    <button type="button" class="btn btn-white btn-info btn-round" style="margin-bottom: 0px;" id="sub">
                        <i class="ace-icon fa fa-search bigger-120 blue"></i>查询
                    </button>
                </form>
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
                            <th>用户</th>
                            <th>地区</th>
                            <th>IP</th>
                            <th>时间</th>
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
<script src="${path}/static/plugins/layui/layui.all.js"></script>
<script src="${path}/static/plugins/dataTables/js/jquery.dataTables.js?v=44444"></script>
<script src="${path}/static/plugins/dataTables/js/dataTables.bootstrap.js"></script>
<script src="${path}/static/plugins/My97DatePicker/WdatePicker.js?v=2222"></script>
<script src="${path}/static/js/sys/log/loginLogList.js?v=1.0"></script>
</body>
</html>
