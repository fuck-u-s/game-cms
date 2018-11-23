/**
 * 方法描述:新增部门
 *
 * author 小刘
 * version v1.0
 * date 2017/4/8 14:32
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

        //监听提交
        form.on('submit(sub)', function(data){
            var action = $("#form").attr("action");
            if(validator.isNull(data.field.name)){
                layer.alert("未设置文章标题");
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
                                window.location.href = "/dept/list"
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
