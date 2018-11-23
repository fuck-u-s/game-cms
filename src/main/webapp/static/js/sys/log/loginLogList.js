/**
 * 方法描述:系统登录日志
 *
 * author 小刘
 * version v1.0
 * date 2017/4/26 17:24
 */
$(function(){
    var tables = $("#dataTables").dataTable({
        serverSide: true,
        searching: false,
        ordering:  false,
        lengthChange: false,
        processing: true,
        pageLength: 10,
        "ajax":{
            url:"/log/wsLoginLogList",
            type:"post",
            data:function(d){
                d.user_id = $("#user_id").val();
                d.start_time = $("#start_time").val();
                d.end_time = $("#end_time").val();
            }
        },
        columns: [
            { "data": "id" },
            { "data": "userName" },
            { "data": "areaName" },
            { "data": "ip" },
            { "data": "createTime" }
        ]
    });

    $("#sub").click(function(){
        tables.dataTable().fnDraw();
    });
});