<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="${path}/static/plugins/layui/css/layui.css"/>
</head>

<body>

<blockquote class="layui-elem-quote">
    鉴于小伙伴的普遍反馈，先温馨提醒两个常见“问题”：
    1. <a href="http://www.layui.com/doc/base/faq.html#form" target="_blank">为什么select/checkbox/radio没显示？</a>
    2. <a href="http://www.layui.com/doc/modules/form.html#render" target="_blank">动态添加的表单元素如何更新？</a>
</blockquote>

<fieldset class="layui-elem-field layui-field-title">
    <legend>修改密码</legend>
</fieldset>

<form class="layui-form" id="form" action="${path}/setPassword" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-input-inline">
                <input type="text" id="oldPwd" name="oldPwd" lay-verify="oldPwd" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-inline">
                <input type="text" id="password" name="password" lay-verify="password" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">确认新密码</label>
            <div class="layui-input-inline">
                <input type="text" id="confirm" name="confirm" lay-verify="confirm" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="sub">立即提交</button>
            <button type="button" class="layui-btn layui-btn-primary" onclick="history.back();">返回</button>
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
<script src="${path}/static/js/sys/user/password.js?v=33333"></script>

</body>

</html>

