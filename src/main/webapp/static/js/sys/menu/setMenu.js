/**
 * 方法描述:菜单设置
 *
 * author 小刘
 * version v1.0
 * date 2017/4/16 12:15
 */
$(function(){
    // 模块加载
    layui.use(['form'], function() {
        var form = layui.form;

        //自定义验证规则
        form.verify({
            name: function(value) {
                if (value.length < 2) {
                    return '账号至少得2个字符啊';
                }
            },
            permission: function(value){
                if(value.length < 2){
                    return '菜单标识至少得2个字符啊';
                }
            }
        });

        //监听指定开关
        form.on('switch(switchBox)', function(data){
            this.checked ? $("#ishide").val(1):$("#ishide").val(2);
        });

        //监听提交
        form.on('submit(sub)', function(){
            var action = $("#form").attr("action");
            $.ajax({
                type:"post",
                url:action,
                dataType:"json",
                data:$('#form').serialize(),
                success:function(data){
                    if(data.code >= 0){
                        layer.alert(data.msg,{icon:1}, function(){
                            window.location.href = "/menu/menuList"
                        });
                    }else{
                        layer.alert(data.msg,{icon:2});
                    }
                }
            });
            return false;
        });
    });
});
