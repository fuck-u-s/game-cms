/**
 * 方法描述:参数管理
 *
 * author 刘志海
 * version v1.0
 * date 2017/4/13 18:01
 */
$(function(){

    // 数据表格
    $("#dataTables").dataTable({
        serverSide: true,
        searching: false,
        ordering:  false,
        lengthChange: false,
        processing: true,
        pageLength: 20,
        paging:true,
        info:true,
        autoWidth:false,
        "ajax":{
            url:"/config/wsList",
            type:"post"
        },
        columns: [
            { "data": "id" },
            { "data": "name" },
            { "data": "val" },
            { "data": "remark" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                   if(row.id == 8){
                       return "";
                   }
                   return data.length > 50 ? (data.substring(0,50)+"..."):data;
                },
                "targets": 2
            },
            {
                "render": function(data, type, row, meta) {
                    var html = '';
                    html += '<a href="/config/config?id='+data+'" class="blue" title="修改"><i class="ace-icon fa fa-pencil bigger-130"></i></a>';
                    return html;
                },
                "targets": 4
            }
        ]
    });
});
