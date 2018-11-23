/**
 * 方法描述:菜单管理
 *
 * author 小刘
 * version v1.0
 * date 2017/4/16 09:32
 */
$(function(){

    // 关闭错误提示
    $.fn.dataTable.ext.errMode = 'none';

    // 数据表格
    $("#dataTables").dataTable({
        serverSide: true,
        searching: false,
        ordering:  false,
        lengthChange: false,
        processing: true,
        pageLength: 10,
        "ajax":{
            url:"/menu/wsMenuList",
            type:"post"
        },
        columns: [
            { "data": "id" },
            { "data": "name" },
            { "data": "type" },
            { "data": "id" },
            { "data": "permission" },
            { "data": "url" },
            { "data": "icon" },
            { "data": "ishide" },
            { "data": "description" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                    return data == 1 ? "目录":(data == 2 ? "菜单":"元素");
                },
                "targets": 2
            },
            {
                "render": function(data, type, row, meta) {
                    return "<a href='/menu/childList?parent_id="+data+"'>查看子菜单</a>";
                },
                "targets": 3
            },
            {
                "render": function(data, type, row, meta) {
                    return data == 1 ? "显示":"隐藏";
                },
                "targets": 7
            },
            {
                "render": function(data, type, row, meta) {
                    var html = '<div class="action-buttons">';
                    html += '<a href="/menu/menu?id='+data+'" class="blue" title="修改"><i class="ace-icon fa fa-pencil bigger-130"></i></a>';
                    html += '<a href="javascript:void(0)" class="red" title="删除" onclick="deleMenu('+data+',\''+row.name+'\')"><i class="ace-icon fa fa-trash-o bigger-130"></i></a>';
                    html += '</div>';
                    return html;
                },
                "targets": 9
            }
        ]
    });
});

// 删除菜单
function deleMenu(id,name){
    // 询问框
    layer.confirm('是否删除菜单['+name+']?', {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/menu/delete",
            dataType:"json",
            data:{id:id},
            success:function(data){
                if(data.code > 0){
                    layer.alert("菜单["+name+"]已被删除!",{icon:1},function(){
                        window.location.href = "/menu/menuList"
                    });
                }else{
                    layer.alert("操作失败", {icon: 2});
                }
            }
        });
    });
}
