/**
 * 自主封装一个时间插件
 * $dom.bind('click',function(event){time_packer($(this),event)});
 * @author shuaiwu Li
 */

function time_packer(dom, e) {
    var hours = null;//存储 "时"
    var minutes = null;//存储 "分"
    var clientY = dom.offset().top + dom.height();//获取位置
    var clientX = dom.offset().left;
    var date = new Date();
    var nowHours = date.getHours();
    var nowMinutes = date.getMinutes();
    var time_hm=/^(0\d{1}|\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/; //时间正则，防止手动输入的时间不符合规范
    var inputText = dom.is("input") ? dom.val():dom.text();
    //插件容器布局
    var html = '';
    html += '<div class="time_packer">';
        html += '<div class="time_packer-hours" style="display: block;">';
            html += '<div class="time_packer-title"><span>小时</span></div>';
            html += '<div class="time_packer-content">';
                html += '<ul>';
                    var i = 0;
                    while (i < 24)
                    {
                        //var text = i < 10 ? "0" + i : i;
                        if(inputText !== "" && Number(inputText.split(":")[0]) === i){
                            html += '<li class="hoursList timePackerSelect">'+i+'</li>';
                            hours = Number(inputText.split(":")[0]);
                        }else{
                            if(i === nowHours){
                                html += '<li class="hoursList" style="color: #007BDB;">'+i+'</li>';
                            }else{
                                html += '<li class="hoursList">'+i+'</li>';
                            }
                        }
                        i++;
                    }
                html += '</ul>';
            html +=  '</div>';
        html += '</div>';
        html += '<div class="time_packer-minutes" style="display: none;">';
            html += '<div class="time_packer-title"><span>分钟</span><span class="time_packer-back-hours" title="返回小时选择"><img src="avatar-18.png"/> </span></div>';
            html += '<div class="time_packer-content">';
                html += '<ul>';
                    var m = 0;
                    while (m < 60)
                    {
                        var textM = m < 10 ? "0" + m : m;
                        if(inputText !== "" && Number(inputText.split(":")[1]) === textM){
                            html += '<li class="mList timePackerSelect">'+textM+'</li>';
                            minutes = Number(inputText.split(":")[1]);
                        }else{
                            if(m === nowMinutes){
                                html += '<li class="mList" style="color: #007BDB;">'+textM+'</li>';
                            }else{
                                html += '<li class="mList">'+textM+'</li>';
                            }
                        }
                        m++;
                    }
                html += '</ul>';
            html +=  '</div>';
        html += '</div>';
    html += '</div>';
    if($(".time_packer").length > 0){
        $(".time_packer").remove();
    }
    $("body").append(html);
    $(".time_packer").css({
        position:"absolute",
        top:clientY,
        left:clientX
    });
    var _con = $(".time_packer"); // 设置目标区域,如果当前鼠标点击非此插件区域则移除插件
    $(document).mouseup(function(e){
        if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
            _con.remove();
        }
    });
    //小时选择
    $(".hoursList").bind('click',function () {
        $(this).addClass("timePackerSelect").siblings().removeClass("timePackerSelect");
        hours = $(this).text();
        var timer = setTimeout(function () {
            $(".time_packer-hours").css("display","none");
            $(".time_packer-minutes").fadeIn();
            if(minutes !== null){
                var getTime = hours + ":" + minutes;
                if(time_hm.test(getTime)){
                    dom.removeClass("errorStyle");
                }
                dom.is("input") ? dom.val(getTime):dom.text(getTime);
            }
            clearTimeout(timer);
        },100);
    });
    //返回小时选择
    $(".time_packer-back-hours").bind('click',function () {
        var timer = setTimeout(function () {
            $(".time_packer-minutes").css("display","none");
            $(".time_packer-hours").fadeIn();
            clearTimeout(timer);
        },500);
    });
    //分钟选择
    $(".mList").bind('click',function () {
        $(this).addClass("timePackerSelect").siblings().removeClass("timePackerSelect");
        minutes = $(this).text();
        var timer = setTimeout(function () {
            var getTime = hours + ":" + minutes;
            if(time_hm.test(getTime)){
                dom.removeClass("errorStyle");
            }
            dom.is("input") ? dom.val(getTime):dom.text(getTime);
            clearTimeout(timer);
            _con.remove();
        },500);
    })
}
