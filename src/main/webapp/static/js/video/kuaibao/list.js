/**
 * 方法描述:公司管理
 *
 * author 小刘
 * version v1.0
 * date 2017/7/24 12:00
 */
var dataTables;
$(function () {

    // 数据表格
    dataTables = $("#dataTables").dataTable({
        serverSide: true,
        searching: false,
        ordering:  false,
        lengthChange: false,
        processing: true,
        pageLength: 20,
        "ajax":{
            url:"/video/wsKuaiBaoList",
            type:"post",
            data:function(d){
                d.id = $("#id").val();
                d.vid = $("#vid").val();
                d.state = $("#state").val();
            }
        },
        columns: [
            { "data": "id" },
            { "data": "vid" },
            { "data": "title" },
            { "data": "source" },
            { "data": "img" },
            { "data": "url" },
            { "data": "playurl" },
            { "data": "duration" },
            { "data": "state" },
            { "data": "dateTime" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                    return "<img src='"+data+"' width='80px' height='80px' onclick=\"preview('"+data+"')\"/>";
                },
                "targets": 4
            },
            {
                "render": function(data, type, row, meta) {
                    return "<a href='"+data+"' target='_blank'><i class=\"layui-icon\">&#xe64c;</i></a>";
                },
                "targets": 5
            },
            {
                "render": function(data, type, row, meta) {
                    return "<a href='"+data+"' target='_blank'><i class=\"layui-icon\">&#xe64c;</i></a>";
                },
                "targets": 6
            },
            {
                "render": function(data, type, row, meta) {
                    return data == 1?"待处理":"已处理";
                },
                "targets": 8
            },
            {
                "render": function(data, type, row, meta) {
                    var html =  "<a href=\"javascript:void(0)\" class=\"layui-btn layui-btn-sm layui-btn-normal\" onclick=\"edit('"+data+"')\" >" +
                                    "<i class=\"layui-icon\">&#xe642;</i>编辑" +
                                "</a>";
                        html += "<button class=\"layui-btn layui-btn-sm layui-btn-danger\" onclick=\"deleteVideo('"+data+"','"+row.vid+"')\">" +
                                    "<i class=\"layui-icon\">&#xe640;</i>删除" +
                                "</button>";
                    return html;
                },
                "targets": 10
            }
        ]
    });

    // 过滤
    $("#sub").click(function(){
        dataTables.dataTable().fnDraw();
    })
});

// 编辑
function edit(id){
    layer.open({
        title: '同步视频',
        type: 2,
        skin: 'layui-layer-demo', //样式类名
        anim: 2,
        maxmin: true,
        offset: 't',
        area: ['800px', '700px'],
        content: "/video/kuaiBaoInfo?id="+id,
        cancel: function(){
            $(document.body).css({
                "overflow-x":"auto",
                "overflow-y":"auto"
            });
        }
    });
}

// 刷新
function refresh(){
    $(document.body).css({
        "overflow-x":"auto",
        "overflow-y":"auto"
    });
    dataTables.dataTable().fnDraw(false);
}

// 删除快报视频
function deleteVideo(id,vid){
    layer.confirm("是否确认删除此发布者?", {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/video/deleteKuaiBao",
            dataType:"json",
            data:{id : id, vid : vid},
            success:function(data){
                if(data.code > 0){
                    layer.alert(data.msg,{icon:1});
                    dataTables.dataTable().fnDraw(false);
                }else{
                    layer.alert(data.msg, {icon: 2});
                }
            }
        });
    });
}

// 封面
function preview(data){
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['700px', '500px'], //宽高
        content: "<img src='"+data+"' width='100%' height='100%'/>"
    });
}


