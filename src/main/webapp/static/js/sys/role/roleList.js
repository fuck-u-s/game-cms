/**
 * 方法描述:角色管理
 *
 * author 刘志海
 * version v1.0
 * date 2017/4/13 16:02
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
            url:"/role/wsRoleList",
            type:"post"
        },
        columns: [
            { "data": "id" },
            { "data": "name" },
            { "data": "status" },
            { "data": "createTime" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                   return data == 1 ? "启用":"禁用";
                },
                "targets": 2
            },
            {
                "render": function(data, type, row, meta) {
                    var html = '<div class="action-buttons">';
                    html += '<a href="/role/role?id='+data+'" class="blue" title="修改"><i class="ace-icon fa fa-pencil bigger-130"></i></a>';
                    html += '<a href="javascript:void(0)" class="red" title="删除" onclick="deleRole('+data+',\''+row.name+'\')"><i class="ace-icon fa fa-trash-o bigger-130"></i></a>';
                    html += '<a href="/role/roleRootList?role_id='+data+'" class="red" title="权限"><i class="ace-icon fa fa-key bigger-130"></i></a>';
                    html += '</div>';
                    return html;
                },
                "targets": 4
            }
        ]
    });

});

// 删除部门
function deleRole(id,name){

    // 询问框
    layer.confirm('是否确定删除角色['+name+']', {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/role/delete",
            dataType:"json",
            data:{id:id},
            success:function(data){
                if(data.code > 0){
                    layer.alert(data.msg,{icon:1},function(){
                        window.location.href = "/role/roleList"
                    });
                }else{
                    layer.alert(data.msg, {icon: 2});
                }
            }
        });
    });
}

