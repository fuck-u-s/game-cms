<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Login - KitAdminCore</title>
    <script>
        if (window != window.top) top.location.href = self.location.href;
    </script>
    <link href="${path}/static/plugins/layui/css/layui.css" rel="stylesheet" />
    <link href="${path}/static/plugins/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="${path}/static/Hui/css/kitLogin.css" rel="stylesheet" />
    <link href="${path}/static/plugins/sideshow/css/normalize.css" rel="stylesheet" />
    <link href="${path}/static/plugins/sideshow/css/demo.css" rel="stylesheet" />
    <link href="${path}/static/plugins/sideshow/css/component.css" rel="stylesheet" />
    <style>
        canvas {
            position: absolute;
            z-index: -1;
        }

        .kit-login-box header h1 {
            line-height: normal;
        }

        .kit-login-box header {
            height: auto;
        }

        .content {
            position: relative;
        }

        .codrops-demos a {
            border: 2px solid rgba(242, 242, 242, 0.41);
            color: rgba(255, 255, 255, 0.51);
        }

        .kit-pull-right button,
        .kit-login-main .layui-form-item input {
            background-color: transparent;
            color: white;
        }

        .kit-login-main .layui-form-item input::-webkit-input-placeholder {
            color: white;
        }

        .kit-login-main .layui-form-item input:-moz-placeholder {
            color: white;
        }

        .kit-login-main .layui-form-item input::-moz-placeholder {
            color: white;
        }

        .kit-login-main .layui-form-item input:-ms-input-placeholder {
            color: white;
        }

        .kit-pull-right button:hover {
            border-color: #009688;
            color: #009688
        }
    </style>
    <script>
        var error = ${error};
    </script>
</head>


<body class="kit-login-bg">
<div class="container demo-2">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="kit-login-box">
                <header>
                    <h1>ADMIN LOGIN</h1>
                </header>
                <div class="kit-login-main">
                    <form action="${path}/login" class="layui-form" method="post">
                        <div class="layui-form-item">
                            <label class="kit-login-icon">
                                <i class="layui-icon">&#xe612;</i>
                            </label>
                            <input type="text" name="username" lay-verify="required" autocomplete="off" placeholder="这里输入用户名." class="layui-input"/>
                        </div>
                        <div class="layui-form-item">
                            <label class="kit-login-icon">
                                <i class="layui-icon">&#xe642;</i>
                            </label>
                            <input type="password" name="password" lay-verify="required" autocomplete="off" placeholder="这里输入密码." class="layui-input"/>
                        </div>
                        <div class="layui-form-item">
                            <label class="kit-login-icon">
                                <i class="layui-icon">&#xe642;</i>
                            </label>
                            <input type="text" name="captchaCode" lay-verify="required" autocomplete="off" placeholder="输入验证码." class="layui-input" maxlength="4"/>
                            <span class="form-code" id="changeCode" style="position:absolute;right:2px; top:2px;">
                                <img src="${path}/captcha" id="refImg" style="cursor:pointer;" title="点击刷新"/>
                            </span>
                        </div>
                        <div class="layui-form-item">
                            <div class="kit-pull-left kit-login-remember">
                                <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked title="记住帐号?">
                            </div>
                            <div class="kit-pull-right">
                                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
                                    <i class="fa fa-sign-in" aria-hidden="true"></i> 登录
                                </button>
                            </div>
                            <div class="kit-clear"></div>
                        </div>
                    </form>
                </div>
                <footer>
                    <p>KIT ADMIN © QAQ</p>
                </footer>
            </div>
        </div>
    </div>
</div>
<!-- /container -->

<script src="${path}/static/plugins/layui/layui.js"></script>
<script src="${path}/static/plugins/sideshow/js/TweenLite.min.js"></script>
<script src="${path}/static/plugins/sideshow/js/EasePack.min.js"></script>
<script src="${path}/static/plugins/sideshow/js/rAF.js"></script>
<script src="${path}/static/plugins/sideshow/js/demo-1.js"></script>
<script>
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer,
            $ = layui.jquery;

        // 错误消息提示
        if(error.code > 0){
            layer.msg(error.msg);
            $("#username").focus();
        }

        // 刷新验证码
        $('#changeCode').on('click', function() {
            $("#refImg").attr("src","captcha?v="+Math.random());
        });

        // 加载...
        var index = layer.load(2, {
            shade: [0.3, '#333']
        });

        // 加载完毕
        $(window).on('load', function() {
            layer.close(index);
        }());
    });
</script>
</body>
</html>
