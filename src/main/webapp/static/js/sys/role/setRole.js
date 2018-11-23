/**
 * 方法描述:角色创建
 *
 * author 刘志海
 * version v1.0
 * date 2017/4/13 16:41
 */
$(function(){
    // 模块加载
    layui.use(['form'], function() {
        var form = layui.form;

        //自定义验证规则
        form.verify({
            name: function(value) {
                if (value.length < 2) {
                    return '标题至少得2个字符啊';
                }
            }
        });

        //监听指定开关
        form.on('switch(switchBox)', function(data){
            this.checked ? $("#status").val(1):$("#status").val(2);
        });

        //监听提交
        form.on('submit(sub)', function(data){
            var action = $("#form").attr("action");
            if(validator.isNull(data.field.name)){
                layer.alert("未设置角色名称");
                return false;
            }else{
                $.ajax({
                    type:"post",
                    url:action,
                    dataType:"json",
                    data:$('#form').serialize(),
                    success:function(data){
                        if(data.code > 0){
                            layer.alert(data.msg,{icon:1}, function(){
                                window.location.href = "/role/roleList"
                            });
                        }else{
                            layer.alert(data.msg,{icon:2});
                        }
                    }
                });
                return false;
            }
        });
    });
});
