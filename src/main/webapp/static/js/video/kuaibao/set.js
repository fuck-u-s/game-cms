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
        var loading;

        // 自定义验证规则
        form.verify({
            url:function(value){
                if (value.length == 0){
                    return '请上传视频';
                }
            }
        });

        // 视频
        upload.render({
            url: '/upload/video/syncVideo',
            elem: '#videoFile',
            auto: false,
            method: 'post',
            accept: 'video',
            bindAction: '#toUpload',
            before: function(obj){
                loading = layer.load(2);
            },
            done: function(res){
                layer.close(loading);
                if(res.code == 0){
                    console.log(res);
                    $("#url").val(res.data.url);
                    $("#vtime").val(res.data.time);
                    layer.alert('上传成功',{icon:1});
                }else {
                    layer.alert(res.msg,{icon:2});
                }
            },
            error: function(){
                layer.close(loading);
                layer.alert("上传失败",{icon:2});
            }
        });

        // 视频封面
        upload.render({
            url: '/upload/video/subCover',
            elem: '#imageFile',
            method: 'post',
            accept: 'image',
            before: function(obj){
                obj.preview(function(index, file, result){
                    //图片链接（base64）
                    $('#cover_image').attr('src', result);
                });
                loading = layer.load(2);
            },
            done: function(res){
                layer.close(loading);
                if(res.code == 0){
                    $("#cover").val(res.data.url);
                    $("#cover_image").attr("src",res.data.url);
                    $("#hasCover").val(false);
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