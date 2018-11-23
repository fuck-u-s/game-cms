/**
 * 方法描述:登陆JS
 * @author 小刘
 * @date ${DATE}
 * @version v1.0
 */
$(function(){

    var _topWin = window;
    while (_topWin != _topWin.parent.window) {
        _topWin = _topWin.parent.window;
    }

    if (window != _topWin){
        _topWin.document.location.href = "/";
    }

    $('#btn-login-dark').on('click', function(e) {
        $('body').attr('class', 'login-layout');
        $('#id-text2').attr('class', 'white');
        $('#id-company-text').attr('class', 'blue');
        e.preventDefault();
    });

    $('#btn-login-light').on('click', function(e) {
        $('body').attr('class', 'login-layout light-login');
        $('#id-text2').attr('class', 'grey');
        $('#id-company-text').attr('class', 'blue');
        e.preventDefault();
    });

    $('#btn-login-blur').on('click', function(e) {
        $('body').attr('class', 'login-layout blur-login');
        $('#id-text2').attr('class', 'white');
        $('#id-company-text').attr('class', 'light-blue');
        e.preventDefault();
    });

    $(document).on('click', '.toolbar a[data-target]', function(e) {
        e.preventDefault();
        var target = $(this).data('target');
        $('.widget-box.visible').removeClass('visible');
        $(target).addClass('visible');
    });

    // 初始化焦点
    $("#username").focus();

    //刷新验证码
    $("#captcha").click(function(){
        $("#captcha").attr("src","captcha?v="+Math.random());
    });

    $("#username").keyup(function(event){
        var charCode= (navigator.appName=="Netscape")?event.which:event.keyCode;
        if(charCode == 13){
            $("#password").focus();
        }
    });

    $("#password").keyup(function(event){
        var charCode= (navigator.appName=="Netscape")?event.which:event.keyCode;
        if(charCode == 13){
            $("#captchaCode").focus();
        }
    });

    // 回车事件
    $("#captchaCode").keydown(function(event){
        if (event.keyCode == "13") {
            Login();
        }
    });

    // 错误消息提示
    if(error.code > 0){
        layer.msg(error.msg);
        $("#username").focus();
    }

    // 登录
    $("#login").click(function(){
        Login();
    })
});

// 登录操作
function Login(){
    var username = $("#username").val();
    var password = $("#password").val();
    var captchaCode = $("#captchaCode").val();
    if(validator.isNull(username)){
        layer.msg("请输入用户名");
        $("#username").focus();
    }else if(validator.isNull(password)){
        layer.msg("请输入密码");
        $("#password").focus();
    }else if(validator.isNull(captchaCode)){
        layer.msg("请输入验证码");
        $("#captchaCode").focus();
    }else{
        $("#form").submit();
    }
}