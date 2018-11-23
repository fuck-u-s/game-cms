/**
 * 方法描述:修改密码
 *
 * author lzh
 * version v1.0
 * date 2016/7/8 16:16
 */
$(function(){

    // 初始化焦点
    $("#oldPwd").focus();

    // 模块加载
    layui.use(['form'], function() {
        var form = layui.form;

        //自定义验证规则
        form.verify({
            oldPwd: [/(.+){6,12}$/, '密码必须6到12位'],
            password: [/(.+){6,12}$/, '新密码必须6到12位'],
            confirm:function(value){
                var pass = $("#password").val();
                if(value != pass){
                    return "新密码不一致啊";
                }
            }
        });

        //监听提交
        form.on('submit(sub)', function(data){
            var action = $("#form").attr("action");
            $.ajax({
                type:"post",
                url: "/setPassword",
                data: {
                    oldPwd:data.field.oldPwd,
                    password:data.field.password,
                    confirm:data.field.confirm
                },
                dataType: "json",
                success: function(data){
                    if(data.code > 0){
                        layer.alert(data.msg,{icon:1},function(){
                            window.location.href = "/logout";
                        });
                    }else{
                        layer.alert(data.msg);
                    }
                }
            });
            return false;
        });
    });
});
