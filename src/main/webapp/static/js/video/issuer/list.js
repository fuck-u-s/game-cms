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
            url:"/video/issuer/wsIssuerList",
            type:"post",
            data:function(d){
                d.name = $("#name").val();
                d.state = $("#state").val();
            }
        },
        columns: [
            { "data": "id" },
            { "data": "name" },
            { "data": "cover" },
            { "data": "state" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                    var html = "<img src='"+data+"' style='width: 80px;height: 80px'/>";
                    return html;
                },
                "targets": 2
            },
            {
                "render": function(data, type, row, meta) {
                    return data == 1?"正常":"删除";
                },
                "targets": 3
            },
            {
                "render": function(data, type, row, meta) {
                    var html =  "<a href=\"javascript:void(0)\" class=\"layui-btn layui-btn-sm layui-btn-normal\" onclick='edit("+data+")' >" +
                                    "<i class=\"layui-icon\">&#xe642;</i>编辑" +
                                "</a>";
                        html += "<button class=\"layui-btn layui-btn-sm layui-btn-danger\" onclick=\"deleteIssuer("+data+")\">" +
                                    "<i class=\"layui-icon\">&#xe640;</i>删除" +
                                "</button>";
                    return html;
                },
                "targets": 4
            }
        ]
    });

    // 过滤
    $("#sub").click(function(){
        dataTables.dataTable().fnDraw();
    })

    // 新的推荐
    $("#set").click(function(){
        $(document.body).css({
            "overflow-x":"hidden",
            "overflow-y":"hidden"
        });
        layer.open({
            title: '新的推荐',
            type: 2,
            skin: 'layui-layer-demo', //样式类名
            anim: 2,
            maxmin: true,
            offset: 't',
            area: ['700px', '600px'], //宽高
            content: "/video/issuer/setIssuer"
        });
    })
});

// 编辑
function edit(id){
    layer.open({
        title: '新的推荐',
        type: 2,
        skin: 'layui-layer-demo', //样式类名
        anim: 2,
        maxmin: true,
        offset: 't',
        area: ['700px', '600px'], //宽高
        content: "/video/issuer/issuerInfo?id="+id
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

// 删除推荐
function deleteIssuer(id){
    layer.confirm("是否确认删除此发布者?", {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/video/issuer/deleteIssuer",
            dataType:"json",
            data:{ id:id },
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

