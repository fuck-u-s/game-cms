<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>部门管理</title>
<link rel="shortcut icon" href="/static/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${path}/static/plugins/layui/css/layui.css"/>
</head>

<body>

<blockquote class="layui-elem-quote">
    鉴于小伙伴的普遍反馈，先温馨提醒两个常见“问题”：
    1. <a href="http://www.layui.com/doc/base/faq.html#form" target="_blank">为什么select/checkbox/radio没显示？</a>
    2. <a href="http://www.layui.com/doc/modules/form.html#render" target="_blank">动态添加的表单元素如何更新？</a>
</blockquote>

<fieldset class="layui-elem-field layui-field-title">
    <legend>新增角色</legend>
</fieldset>

<form class="layui-form" id="form" action="${path}/role/create" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="name" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" checked="checked" id="status" name="status" value="1" lay-skin="switch" lay-filter="switchBox" lay-text="ON|OFF"/>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="sub">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary" onclick="history.back();">返回</button>
        </div>
    </div>
</form>

<!-- 全局js -->
<script src="${path}/static/Hui/js/jquery.min.js?v=2.1.4"></script>
<script src="${path}/static/Hui/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${path}/static/plugins/validator.min.js" ></script>

<!-- 自定义js -->
<script src="${path}/static/Hui/js/content.js?v=1.0.0"></script>
<script src="${path}/static/js/sys/role/setRole.js?v=444"></script>

</body>

</html>


