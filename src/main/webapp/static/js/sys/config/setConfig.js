/**
 * 方法描述:参数设置
 *
 * author 小刘
 * version v1.0
 * date 2017/5/25 11:23
 */
$(function(){
    // 模块加载
    layui.use(['form'], function() {
        var form = layui.form;

        //监听提交
        form.on('submit(sub)', function(){
            var index = layer.load(2);
            var action = $("#form").attr("action");
            $.ajax({
                type:"post",
                url:action,
                dataType:"json",
                data:$('#form').serialize(),
                success:function(data){
                    layer.close(index);
                    if(data.code >= 0){
                        layer.alert(data.msg,{icon:1}, function(){
                            window.location.href = "/config/configList"
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