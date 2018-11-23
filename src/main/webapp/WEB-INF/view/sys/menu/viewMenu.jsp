<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增人员</title>
<link rel="shortcut icon" href="${path}/static/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${path}/static/plugins/layui/css/layui.css"/>
</head>

<body>

<blockquote class="layui-elem-quote">
    鉴于小伙伴的普遍反馈，先温馨提醒两个常见“问题”：
    1. <a href="http://www.layui.com/doc/base/faq.html#form" target="_blank">为什么select/checkbox/radio没显示？</a>
    2. <a href="http://www.layui.com/doc/modules/form.html#render" target="_blank">动态添加的表单元素如何更新？</a>
</blockquote>

<fieldset class="layui-elem-field layui-field-title">
    <legend>新菜单</legend>
</fieldset>

<form class="layui-form" id="form" action="${path}/menu/update" method="post">
    <input type="hidden" name="id" value="${menu.id}"/>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" value="${menu.name}" lay-verify="name" autocomplete="off" class="layui-input" maxlength="20"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单标识</label>
            <div class="layui-input-inline">
                <input type="text" name="permission" value="${menu.permission}" lay-verify="permission" autocomplete="off" class="layui-input" maxlength="30"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单URL</label>
            <div class="layui-input-inline">
                <input type="text" name="url" value="${menu.url}" autocomplete="off" class="layui-input"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">上级菜单</label>
            <div class="layui-input-inline">
                <select name="parent_id" lay-filter="parent_id">
                    <option value="0">------顶级目录------</option>
                    <c:forEach items="${treeList}" var="obj">
                        <option value="${obj.id}" <c:if test="${obj.id == menu.parent_id}">selected="selected"</c:if>>${obj.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单类型</label>
            <div class="layui-input-inline">
                <select name="type" lay-filter="type">
                    <option value="1" <c:if test="${menu.type == 1}">selected="selected"</c:if>>------目录------</option>
                    <option value="2" <c:if test="${menu.type == 2}">selected="selected"</c:if>>------菜单------</option>
                    <option value="3" <c:if test="${menu.type == 3}">selected="selected"</c:if>>------元素------</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单Icon</label>
            <div class="layui-input-inline">
                <input type="text" name="icon" value="${menu.icon}" lay-verify="icon" autocomplete="off" class="layui-input" maxlength="20"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-inline">
                <input type="text" name="description" value="${menu.description}" autocomplete="off" class="layui-input" maxlength="30"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否显示</label>
        <div class="layui-input-block">
            <input type="checkbox" checked="checked" id="ishide" name="ishide" value="${menu.ishide}" lay-skin="switch" lay-filter="switchBox" lay-text="ON|OFF"/>
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
<script src="${path}/static/js/sys/menu/setMenu.js?v=333"></script>

</body>
</html>