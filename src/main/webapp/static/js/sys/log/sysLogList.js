/**
 * 方法描述:系统操作日志
 *
 * author 小刘
 * version v1.0
 * date 2017/4/26 15:45
 */
$(function(){
    $.fn.dataTable.ext.errMode = 'none';
    var tables = $("#dataTables").dataTable({
        serverSide: true,
        searching: false,
        ordering:  false,
        lengthChange: false,
        processing: true,
        pageLength: 10,
        "ajax":{
            url:"/log/wsSysLogList",
            type:"post",
            data:function(d){
                d.user_id = $("#user_id").val();
                d.status = $("#status").val();
                d.start_time = $("#start_time").val();
                d.end_time = $("#end_time").val();
            }
        },
        columns: [
            { "data": "id" },
            { "data": "userName" },
            { "data": "areaName" },
            { "data": "module" },
            { "data": "ip" },
            { "data": "op_time" },
            { "data": "useTime" },
            { "data": "status" },
            { "data": "exception" },
            { "data": "description" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                    return data == 0 ? "<span style='color: green;'>成功</span>":"<span style='color: red;'>失败</span>";
                },
                "targets": 7
            },
            {
                "render": function(data, type, row, meta) {
                    return row.status == 0 ? "":"<a href='javascript:void(0)' onclick='ex("+row.id+")'>查看异常</a>" +
                        "<span style='display: none;' id='ex_"+row.id+"'>"+data+"</span>";
                },
                "targets": 8
            }
        ]
    });

    $("#sub").click(function(){
        tables.dataTable().fnDraw();
    });
});

// 查看异常
function ex(id){
    var msg = $("#ex_"+id).html();
    layer.open({
        title: '异常信息',
        content: msg,
        area: ['500px', '300px']
    });
}