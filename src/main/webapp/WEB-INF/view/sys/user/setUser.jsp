<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增人员</title>
<link rel="stylesheet" type="text/css" href="${path}/static/plugins/layui/css/layui.css"/>
</head>

<body>

<blockquote class="layui-elem-quote">
    鉴于小伙伴的普遍反馈，先温馨提醒两个常见“问题”：
    1. <a href="http://www.layui.com/doc/base/faq.html#form" target="_blank">为什么select/checkbox/radio没显示？</a>
    2. <a href="http://www.layui.com/doc/modules/form.html#render" target="_blank">动态添加的表单元素如何更新？</a>
</blockquote>

<fieldset class="layui-elem-field layui-field-title">
    <legend>新增人员</legend>
</fieldset>

<form class="layui-form" id="form" action="${path}/user/create" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-inline">
                <input type="text" name="username" lay-verify="username" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="text" name="password" lay-verify="password" value="123456" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="name" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">部门</label>
            <div class="layui-input-inline">
                <select name="dept_id" lay-filter="dept">
                    <c:forEach items="${deptList}" var="obj">
                        <option value="${obj.id}">${obj.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-inline">
                <select name="role_id" lay-filter="role">
                    <c:forEach items="${roles}" var="obj">
                        <option value="${obj.id}">${obj.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-inline">
                <input type="text" name="mobile" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" autocomplete="off" class="layui-input"/>
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
<script src="${path}/static/js/sys/user/setUser.js?v=333"></script>

</body>
</html>