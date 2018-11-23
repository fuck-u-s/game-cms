<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>设置视频</title>
    <link rel="stylesheet" type="text/css" href="${path}/static/plugins/layui/css/layui.css"/>
</head>

<body>

<form class="layui-form" id="form" action="${path}/video/issuer/saveIssuer" method="post">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-inline" style="width: 400px">
                <input type="text" name="name" lay-verify="required" maxlength="100" class="layui-input" placeholder="名称"/>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">封面</label>
            <div class="layui-input-inline">
                <input type="hidden" id="cover" name="cover" lay-verify="cover"/>
                <button type="button" class="layui-btn" id="logoFile">头像</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" src="/static/Hui/img/a5.jpg" id="cover_file" style="width: 200px; height: 200px;"/>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" lay-submit lay-filter="sub">立即提交</button>
        </div>
    </div>
</form>

<!-- 全局js -->
<script src="${path}/static/Hui/js/jquery.min.js?v=2.1.4"></script>
<script src="${path}/static/Hui/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${path}/static/plugins/layui/layui.js"></script>
<script src="${path}/static/plugins/validator.min.js"></script>

<!-- 自定义js -->
<script src="${path}/static/Hui/js/content.js?v=1.0.0"></script>
<script src="${path}/static/plugins/ConfigUtils.js"></script>
<script src="${path}/static/js/video/issuer/set.js?v=3151"></script>

</body>
</html>