/**
 * 方法描述:人员管理
 *
 * author 刘志海
 * version v1.0
 * date 2017/4/13 18:37
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
            url:"/user/wsUserList",
            type:"post"
        },
        columns: [
            { "data": "id" },
            { "data": "username" },
            { "data": "name" },
            { "data": "type" },
            { "data": "roleName" },
            { "data": "deptName" },
            { "data": "createTime" },
            { "data": "status" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                    return data == 1 ? "超级管理员":(data == 2 ? "管理员":"人员");
                },
                "targets": 3
            },
            {
                "render": function(data, type, row, meta) {
                    return data == 1 ? "正常":"锁定";
                },
                "targets": 7
            },
            {
                "render": function(data, type, row, meta) {
                    var html = '<div class="action-buttons">';
                    html += '<a href="/temp/tempConfig?user_id='+data+'" class="blue" title="分配模板"><i class="ace-icon fa fa-modx bigger-130"></i></a>';
                    html += '<a href="javascript:void(0)" class="red" title="'+(row.status == 1?'锁定':'激活')+'" ' +
                        'onclick="lock('+data+',\''+row.name+'\','+(row.status == 1?2:1)+')">' +
                        (row.status == 1 ? '<i class="ace-icon fa fa-unlock bigger-130"></i>':'<i class="ace-icon fa fa-lock bigger-130"></i>')+
                    '</a>';
                    html += '<a href="/user/user?user_id='+data+'" class="blue" title="修改"><i class="ace-icon fa fa-pencil bigger-130"></i></a>';
                    html += '<a href="javascript:void(0)" class="red" title="删除" onclick="deleteUser('+data+',\''+row.name+'\')">' +
                        '<i class="ace-icon fa fa-trash-o bigger-130"></i></a>';
                    html += '</div>';
                    return html;
                },
                "targets": 8
            }
        ]
    });
});

// 锁定/解锁用户
function lock(id,name,status){
    // 询问框
    layer.confirm('是否'+(status==1?'解锁':'锁定')+'用户['+name+']?', {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/user/lock",
            dataType:"json",
            data:{status:status,user_id:id},
            success:function(data){
                if(data.code > 0){
                    layer.alert("用户["+name+"]已被"+(status==1?'解锁':'锁定')+"!",{icon:1},function(){
                        window.location.href = "/user/userList"
                    });
                }else{
                    layer.alert("操作失败", {icon: 2});
                }
            }
        });
    });
}

// 删除用户
function deleteUser(id,name){
    // 询问框
    layer.confirm('是否删除用户['+name+']?', {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/user/delete",
            dataType:"json",
            data:{user_id:id},
            success:function(data){
                if(data.code >= 0){
                    layer.alert(data.msg,{icon:1},function(){
                        window.location.href = "/user/userList"
                    });
                }else{
                    layer.alert(data.msg, {icon: 2});
                }
            }
        });
    });
}
