/**
 * 方法描述:部门管理
 *
 * author 小刘
 * version v1.0
 * date 2017/4/12 00:02
 */
$(function(){
    // 数据表格
    $("#dataTables").dataTable({
        serverSide: true,
        searching: false,
        ordering:  false,
        lengthChange: false,
        processing: true,
        pageLength: 10,
        paging:false,
        info:false,
        "ajax":{
            url:"/dept/wsList",
            type:"post"
        },
        columns: [
            { "data":  "id" },
            { "data": "name" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                    var html = '<div class="action-buttons">';
                    html += '<a href="/dept/get?id='+data+'" class="blue" title="修改"><i class="ace-icon fa fa-pencil bigger-130"></i></a>';
                    html += '<a href="javascript:void(0)" class="red" title="删除" onclick="deleDept('+data+',\''+row.name+'\')"><i class="ace-icon fa fa-trash-o bigger-130"></i></a>';
                    html += '</div>';
                    return html;
                },
                "targets": 2
            }
        ]
    });
});

// 删除部门
function deleDept(id,name){

    // 询问框
    layer.confirm('是否确定删除部门['+name+']', {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/dept/delete",
            dataType:"json",
            data:{id:id},
            success:function(data){
                if(data.code > 0){
                    layer.alert(data.msg,{icon:1},function(){
                        window.location.href = "/dept/list"
                    });
                }else{
                    layer.alert(data.msg, {icon: 2});
                }
            }
        });
    });
}
