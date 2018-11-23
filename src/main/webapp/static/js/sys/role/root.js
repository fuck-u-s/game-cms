/**
 * 方法描述:分配权限
 * @author 小刘
 * @version v1.0
 * @date 2015/10/28
 */
$(function(){
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parent_id",
                rootPId: null
            }
        }
    };

    //获取JSON数据集
    var zNodes = treeList;

    //初始化树
    $.fn.zTree.init($("#tree"), setting, zNodes);

    //提交权限
    $("#sub").click(function(){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var checkCount = treeObj.getCheckedNodes(true);
        var treeArray = "";
        for(var i=0;i<checkCount.length;i++) {
            treeArray += checkCount[i].id +",";
        }
        $.ajax({
            type:"POST",
            url:"/role/roleRoot",
            dataType:"json",
            data:{treeList:treeArray,role_id:$("#role_id").val()},
            success:function(data){
                var code = parseInt(data.code);
                if(code == 0){
                    layer.alert(data.msg, {icon:1},function(){
                        window.location.href="/role/roleList";
                    });
                }else{
                    layer.alert(data.msg, {icon:2});
                }
            },
            error:function(){
                layer.alert("系统出错啦...", {icon:2});
            }
        });
    });
});
