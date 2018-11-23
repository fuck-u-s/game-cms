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
            url:"/video/video/wsList",
            type:"post",
            data:function(d){
                d.title = $("#title").val();
                d.state = $("#state").val();
                d.cate = $("#cate").val();
            }
        },
        columns: [
            { "data": "title" },
            { "data": "cover" },
            { "data": "url" },
            { "data": "cateName" },
            { "data": "name" },
            { "data": "ihot" },
            { "data": "vtime" },
            { "data": "state" },
            { "data": "id" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row, meta) {
                    return "<img src='"+data+"' width='80px' height='80px'/>";
                },
                "targets": 1
            },
            {
                "render": function(data, type, row, meta) {
                    var html = "<button class=\"layui-btn layui-btn-primary\" onclick=\"videoURL('"+data+"')\">" +
                                   "<i class=\"layui-icon\">&#xe64c;</i>链接" +
                               "</button>";
                    html += "<button class=\"layui-btn layui-btn-primary\" onclick=\"videoPlayer('"+data+"')\">" +
                                    "<i class=\"layui-icon\">&#xe6ed;</i>播放" +
                                "</button>";
                    return html;
                },
                "targets": 2
            },
            {
                "render": function(data, type, row, meta) {
                    return data+"万";
                },
                "targets": 5
            },
            {
                "render": function(data, type, row, meta) {
                    if(data == 1){
                        return "初始化";
                    }else if(data == 2){
                        return "转码中";
                    }else if(data == 3){
                        return "正常";
                    }else {
                        return "删除";
                    }
                },
                "targets": 7
            },
            {
                "render": function(data, type, row, meta) {
                    var html =  "<a href=\"javascript:void(0)\" class=\"layui-btn layui-btn-sm layui-btn-normal\" onclick='edit("+data+")' >" +
                                    "<i class=\"layui-icon\">&#xe642;</i>编辑" +
                                "</a>";
                        if(row.state != 4){
                            html += "<button class=\"layui-btn layui-btn-sm layui-btn-danger\" onclick=\"deleteVideo("+data+")\">" +
                                        "<i class=\"layui-icon\">&#xe640;</i>删除" +
                                    "</button>";
                        }
                    return html;
                },
                "targets": 8
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
            area: ['800px', '600px'],
            content: "/video/setVideo",
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
        maxmin: true,
        offset: 't',
        area: ['800px', '600px'],
        content: "/video/videoInfo?id="+id,
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

// 删除推荐
function deleteVideo(id){
    layer.confirm("是否确认删除此发布者?", {
        btn: ['确定','取消']
    }, function(){
        $.ajax({
            type:"post",
            url:"/video/deleteVideo",
            dataType:"json",
            data:{ id:id,state:4 },
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

// 查看视频地址
function videoURL(data){
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['600px', '240px'], //宽高
        content: data
    });
}

// 视频播放
function videoPlayer(data){
    layer.open({
        type: 1,
        title: '视频',
        skin: 'layui-layer-rim', //加上边框
        area: ['500px', '420px'], //宽高
        content: '<video width="480" height="360" controls><source src="'+data+'" type="video/mp4"></video>'
    });
}

