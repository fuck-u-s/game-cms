/**
 * 方法描述: 发布人
 * version v1.0
 * date 2017/11/17 14:25
 */
$(function(){
    // 模块加载
    layui.use(['form','upload'], function() {

        var form = layui.form;
        var upload = layui.upload;

        // 自定义验证规则
        form.verify({
            cover:function(value){
                if (value.length == 0){
                    return '请上传头像';
                }
            }
        });

        var loading;

        // 文件上传
        upload.render({
            url: '/upload/video/subCover',
            elem: '#logoFile',
            method: 'post',
            accept: 'images',
            before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    //图片链接（base64）
                    $('#cover_file').attr('src', result);
                });
                loading = layer.load(2);
            },
            done: function(res){
                layer.close(loading);
                if(res.code == 0){
                    $("#cover").val(res.data.url);
                    $("#cover_file").attr("src",res.data.url);
                }else {
                    layer.alert(res.msg,{icon:2});
                }
            },
            error: function(){
                layer.close(loading);
                layer.alert("上传失败",{icon:2});
            }
        });

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
                        layer.alert(data.msg,{icon:1});
                        parent.refresh();
                        var win = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(win);
                    }else{
                        layer.alert(data.msg,{icon:2});
                    }
                }
            });
            return false;
        });
    });
});