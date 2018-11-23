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

<form class="layui-form" id="form" action="${path}/video/syncToVideo" method="post">

    <div class="layui-form-item" style="padding-top: 5px;">
        <div class="layui-inline">
            <label class="layui-form-label">编号</label>
            <div class="layui-input-inline">
                <input type="text" name="kid" value="${info.id}" class="layui-input" readonly/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">VID</label>
            <div class="layui-input-inline">
                <input type="text" name="vid" value="${info.vid}" class="layui-input" readonly/>
            </div>
        </div>
    </div>

    <div class="layui-form-item" style="padding-top: 5px;">
        <div class="layui-inline">
            <label class="layui-form-label">类型</label>
            <div class="layui-input-inline">
                <select name="cate">
                    <c:forEach items="${cateList}" var="obj">
                        <option value="${obj.id}">${obj.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">发布人</label>
            <div class="layui-input-inline">
                <select name="issuer">
                    <c:forEach items="${issList}" var="obj">
                        <option value="${obj.id}">${obj.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">视频封面</label>
            <div class="layui-input-inline">
                <input type="hidden" id="hasCover" name="hasCover" value="${info.hasCover}"/>
                <input type="hidden" id="cover" name="cover" lay-verify="cover"/>
                <button type="button" class="layui-btn" id="imageFile">头像</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" src="${info.img}" id="cover_image" style="width: 400px; height: 200px;"/>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">视频</label>
            <input type="hidden" id="url" name="url" lay-verify="url"/>
            <button type="button" class="layui-btn layui-btn-normal" id="videoFile">选择文件</button>
            <button type="button" class="layui-btn" id="toUpload">开始上传</button>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">人气</label>
            <div class="layui-input-inline" style="width: 400px">
                <input type="text" name="hot" lay-verify="required|number" value="0" class="layui-input" placeholder="视频人气"/>
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
<script src="${path}/static/js/video/kuaibao/set.js?v=v158"></script>

</body>
</html>