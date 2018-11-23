/**
 * 方法描述:控制面板
 *
 * author 小刘
 * version v1.0
 * date 2015/12/8
 */
$(function(){

    $(".nav-list .submenu li").click(function(){
        $(".nav-list li").each(function(){
            $(this).removeClass("active");
        });
        
        $(this).addClass("active");
    });

    $(".nav-list li").click(function(){
        if($(this).hasClass("open")){
            $(this).addClass("active");
        }
    });

    $("#iframe_id").iFrameResize( [{
        log:true,
        inPageLinks:true
    }] );
});

function url(url){
    $("#iframe_id").attr("src",url);
}
