/**
 * 方法描述: 新增公众号
 * author Admin
 * version v1.0
 * date 2018/5/23
 */
$(function(){

    // 模块加载
    layui.use(['form'], function() {

        var form = layui.form;
        var $ = layui.jquery;

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
                    console.log(data);
                    layer.close(index);
                    if(data.code > 0){
                        layer.alert(data.msg,{icon:1}, function(){
                            parent.refresh();
                            var win = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(win);
                        });
                    }else{
                        layer.alert(data.msg,{icon:2});
                    }
                }
            });
            return false;
        });
    });
})