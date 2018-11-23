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
            url:"/video/cate/wsList",
            type:"post",
            data:function(d){
                d.title = $("#title").val();
            }
        },
        columns: [
            { "data": "id" },
            { "data": "name" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                    var html =  "<a href=\"javascript:void(0)\" class=\"layui-btn layui-btn-sm layui-btn-normal\" onclick='edit("+data+")' >" +
                                    "<i class=\"layui-icon\">&#xe642;</i>编辑" +
                                "</a>";
                        html += "<button class=\"layui-btn layui-btn-sm layui-btn-danger\" onclick=\"deleteCate("+data+")\">" +
                                    "<i class=\"layui-icon\">&#xe640;</i>删除" +
                                "</button>";
                    return html;
                },
                "targets": 2
            }
        ]
    });

    // 过滤
    $("#sub").click(function(){
        dataTables.dataTable().fnDraw();
    })

    // 新的视频
    $("#setVideo").click(function(){
        $(document.body).css({
            "overflow-x":"hidden",
            "overflow-y":"hidden"
        });
        layer.open({
            title: '新的',
            type: 2,
            skin: 'layui-layer-demo',
            anim: 2,
            maxmin: true,
            offset: 't',
            area: ['700px', '600px'],
            content: "/video/cate/setCate",
            cancel: function(){
                $(document.body).css({
                    "overflow-x":"auto",
                    "overflow-y":"auto"
                });
            }
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
        shadeClose: true, //开启遮罩关闭
        maxmin: true,
        offset: 't',
        area: ['500px', '500px'], //宽高
        content: "/video/cate/cateInfo?id="+id,
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

// 删除
function deleteCate(id){
    layer.confirm("是否确认删除此发布者?", {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/video/cate/deleteCate",
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

