//鼠标点击地图
function showInfoClick(e) {
    x = e.lnglat.getLng();
    y = e.lnglat.getLat();

}

//绑定分享按钮
function bindShareBtn() {
    if (x != null || y != null) {
        document.getElementById("share").href = "javascript:void((function(s,d,e,r,l,p,t,z,c){var%20f='http://v.t.sina.com.cn/share/share.php?appkey=" + SINA_APPID + "',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=440,height=430,left=',(s.width-440)/2,',top=',(s.height-430)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','分享一个位置','http://www.ah-signstar.com:8089/mobilephone/dynamiclist/index?type=1&x=" + x + "&y=" + y + "','utf-8'));";
    } else {
        document.getElementById("share").href = "javascript:void((function(s,d,e,r,l,p,t,z,c){var%20f='http://v.t.sina.com.cn/share/share.php?appkey=" + SINA_APPID + "',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=440,height=430,left=',(s.width-440)/2,',top=',(s.height-430)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','','','utf-8'));";
    }
}

function bindSharePosBtn(poi) {
    if (poi != null) {
        document.getElementById("sharepos").href = "javascript:void((function(s,d,e,r,l,p,t,z,c){var%20f='http://v.t.sina.com.cn/share/share.php?appkey=" + SINA_APPID + "',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=440,height=430,left=',(s.width-440)/2,',top=',(s.height-430)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','我的坐标','http://www.ah-signstar.com:8089/mobilephone/dynamiclist/index?type=1&x=" + poi.O + "&" +
            "y=" + poi.P + "','utf-8'));";
    } else {
        M.toast({html: '定位失败!无法分享您的位置'});
    }

}

//导航功能，包括语音导航
function direction(a, b, poi, iplocation) {
    map.clearMap();
    addMarker();
    addPoi();
    if ($('#panel').is(':hidden')) {
        $('#panel').show();
    }

    var walking = new AMap.Walking(); //构造路线导航类
    //根据起终点坐标规划步行路线，您如果想修改结果展现效果，请参考页面：https://lbs.amap.com/fn/css-style/
    // walking.search(poi, new AMap.LngLat(a, b), function (status, result) {
    walking.search(poi, [a, b], function (status, result) {
        if (status === 'complete') {
            console.log(result);
            var steps = result.routes[0].steps;
            var translateSteps = "";
            for (var i = 0, step; i < steps.length; i++) {
                translateSteps = translateSteps + steps[i].instruction + ",";
            }



            // //百度翻译
            var appid = '20190116000257276';
            var key = "eswfgT09COmA0W_uN2Xq";
            var salt = (new Date).getTime();
            var from = "zh";
            var to = "en";
            var query = translateSteps;
            var str1 = appid + query + salt + key;
            var sign = MD5(str1);
            $.ajax({
                url: 'http://api.fanyi.baidu.com/api/trans/vip/translate',
                type: 'get',
                dataType: 'jsonp',
                data: {
                    q: query,
                    appid: appid,
                    salt: salt,
                    from: from,
                    to: to,
                    sign: sign
                },
                success: function (data) {
                    //将返回的字符串根据，分割成字符串数组
                    var endTranslateSteps = data.trans_result[0].dst.split(",");
                    for (var i = 0, step; i < steps.length; i++) {
                        steps[i].instruction = endTranslateSteps[i];
                    };

                    console.log(steps);

                    //调用步行导航的绘制代码
                    var walkingrender = (new Lib.AMap.WalkingRender());
                    //点击事件
                    walkingrender.listener.routeStepItem = function () { //鐐瑰嚮鍚勯┚杞︽柟妗堟椂锛岃礋璐iv鐨勫睍寮€涓庡叧闂?,骞惰皟鐢ㄥ湴鍥剧粯鍒舵柟娉?
                        if (walkingrender._highlightOverlay) {
                            walkingrender._highlightOverlay.setMap(null);
                        }

                        this.style.backgroundColor = "rgb(133,15,15)";
                        if (walkingrender.listener.routeStepItem.last) {
                            walkingrender.listener.routeStepItem.last.style.backgroundColor = "";
                        }
                        walkingrender.listener.routeStepItem.last = this;

                        //寰楀埌鎹箻娈电储寮曞彿
                        var p = this.parentNode;
                        var children = p.children;
                        var stepIndex; //鎹箻娈电储寮曞彿
                        for (var i = 0, child; i < children.length; i++) {
                            child = children[i];
                            if (child == this) {
                                stepIndex = i;
                                break;
                            }
                        }
                        if (stepIndex == 0) { //濡傛灉鐐瑰嚮鐨勬槸璧峰鐐?
                            walkingrender.listener.markerClick.call({
                                _data: walkingrender.options.data.start,
                                getPosition: function () {
                                    return walkingrender.options.data.start.location
                                }
                            });
                            return;
                        }
                        if (stepIndex == children.length - 1) { //濡傛灉鐐瑰嚮鐨勬槸缁撴潫鐐?
                            walkingrender.listener.markerClick.call({
                                _data: walkingrender.options.data.end,
                                getPosition: function () {
                                    return walkingrender.options.data.end.location
                                }
                            });
                            return;
                        }
                        walkingrender._infoWindow.close();
                        stepIndex = stepIndex - 1; //鐢变簬璧峰鐐逛笉鏄崲涔樻锛屾墍浠?-1
                        var step = walkingrender.options.data.routes[walkingrender._currentRouteIndex].steps[stepIndex];
                        var highlight;
                        highlight = new AMap.Polyline({
                            map: walkingrender.options.map,
                            path: step.path,
                            strokeColor: "red", //绾块鑹?
                            strokeOpacity: 0.8, //绾块€忔槑搴?
                            strokeWeight: 6 //绾垮
                        });
                        highlight.isOfficial = true;
                        walkingrender._highlightOverlay = highlight;
                        walkingrender.options.map.setBounds(highlight.getBounds());
                        //如果是ios端
                        if (mobiledetect.os() == "iOS") {
                            //先将当前所有正在播放的关闭掉
                            for (var i = 0; i < children.length - 2; i++) {
                                var temp = document.getElementById("audio_" + i);
                                if (temp.play())
                                    temp.pause();
                            }
                            //然后重新加载音频播放
                            var audio = document.getElementById("audio_" + stepIndex);
                            audio.load();
                            audio.play();
                        } else {
                            // //发送语音请求
                            var newSteps = step.instruction;
                            $.ajax({
                                type: "post",
                                data: {"message": newSteps, "iplocation": iplocation, "index": stepIndex},
                                url: "/mobilephone/sounds",
                                dataType: "json",
                                success: function (msg) {
                                    if (msg.success) {
                                        var audio = document.getElementById("sounds");
                                        audio.src = "/mobilephone/getmusic?fileName=" + msg.message;
                                        audio.load();
                                        audio.play();
                                    } else {
                                        console.log("msg is failec")
                                    }
                                },
                                error: function (data) {
                                    M.toast({html: 'Network exception, please check the network connection'});
                                }
                            });
                        }


                    };
                    walkingrender.autoRender({
                        data: result,
                        map: map,
                        panel: "panel"
                    });
                    // return data.trans_result[0].dst;
                }, error: function () {
                    console.log("false");
                }

            });
        }

    });
    // map.on('click',walking.clear);

}


//获取鼠标点击地点
function clickOn() {
    map.on('click', showInfoClick);
}


//关闭窗体
function closeInfoWindow() {
    map.clearInfoWindow();
}


//构建自定义窗体
function createInfoWindow(title, content) {
    var info = document.createElement("div");
    info.className = "custom-info input-card content-window-card";

    //  可以通过下面的方式修改自定义窗体的宽高
    info.style.width = "200px";
    info.style.height = "200px";
    // 定义顶部标题
    var top = document.createElement("div");
    var titleD = document.createElement("div");
    var closeX = document.createElement("img");
    top.className = "info-top";
    top.style = "background-color: white";
    titleD.innerHTML = title;
    closeX.src = "https://webapi.amap.com/images/close2.gif";
    closeX.onclick = closeInfoWindow;

    top.appendChild(titleD);
    top.appendChild(closeX);
    info.appendChild(top);

    // 定义中部内容
    var middle = document.createElement("div");
    middle.className = "info-middle";
    middle.style.overflow = "auto";
    // middle.style.marginRight = "-15px";
    // middle.style.marginLeft = "-15px";
    middle.style.backgroundColor = 'white';
    middle.innerHTML = content;
    info.appendChild(middle);

    // 定义底部内容
    var bottom = document.createElement("div");
    bottom.className = "info-bottom";
    bottom.style.position = 'relative';
    bottom.style.top = '0px';
    bottom.style.margin = '0 auto';
    var sharp = document.createElement("img");
    sharp.src = "https://webapi.amap.com/images/sharp.png";
    bottom.appendChild(sharp);
    info.appendChild(bottom);
    return info;
}

//   解析定位错误信息
function onError(data) {
    M.toast({html: 'Location failed! Failure reason troubleshooting information: Failed to obtain IP address'});    //如果发生异常就采用https进行重新请求
    var url = window.location.href;
    if (url.indexOf("https") < 0) {
        url = url.replace("http:", "https:");
        url = url.replace("8090", "8443");
        window.location.replace(url);
    }

}


//解析定位结果
function onComplete(data) {
    var str = [];
    poi = data.position;
    // str.push('定位结果：' + data.position);
    // str.push('定位类别：' + data.location_type);
    if (data.accuracy) {
        // str.push('精度：' + data.accuracy + ' 米');
    }//如为IP精确定位结果则没有精度信息
    // str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
    M.Toast.dismissAll();
    M.toast({html: 'Successful positioning!'});
    // console.log(str);
}


//限制地图显示范围
// function lockMapBounds() {
//     //设置显示范围
//     var bounds = map.getBounds();
//     // console.log(bounds);
//     // map.setLimitBounds(new AMap.ArrayBounds(new AMap.LngLat(118.363561, 31.325204),new AMap.LngLat(118.378432, 31.332462)));
//     // map.setLimitBounds([118.363561,31.325204],[118.378432,31.332462]);
//     map.setLimitBounds(bounds);
//     // var bounds = map.getBounds();
//     // map.setLimitBounds(bounds);
// }

//限制地图显示范围
function maoDragend() {
    //先获取边界
    var myCenter = map.getCenter();
    var x = myCenter.lng;
    var y = myCenter.lat;
    if (x < 118.362703 || y < 31.324654 || x > 118.378582 || y > 31.33281) {
        if (x < 118.362703 && y < 31.325204) {//左下
            if (mobiledetect.os() == "IOS" || mobiledetect.os() == "Android") {
                map.panTo(new AMap.LngLat(118.365521, 31.328002));
            } else {
                map.panTo(new AMap.LngLat(118.372348, 31.328997));
            }
        } else if (x > 118.362703 && y < 31.324654) {//右下
            if (mobiledetect.os() == "IOS" || mobiledetect.os() == "Android") {
                map.panTo(new AMap.LngLat(118.375917, 31.327984));
            } else {
                map.panTo(new AMap.LngLat(118.372348, 31.328997));
            }
        } else if (x > 118.378432 && y > 31.332462) {//右上
            if (mobiledetect.os() == "IOS" || mobiledetect.os() == "Android") {
                map.panTo(new AMap.LngLat(118.376078, 31.330248));
            } else {
                map.panTo(new AMap.LngLat(118.372348, 31.328997));
            }
        } else if (x < 118.362703 && y > 31.33281) {//左上
            if (mobiledetect.os() == "IOS" || mobiledetect.os() == "Android") {
                map.panTo(new AMap.LngLat(118.36526, 31.329781));
            } else {
                map.panTo(new AMap.LngLat(118.372348, 31.328997));
            }
        } else if (x < 118.362703) {
            if (mobiledetect.os() == "IOS" || mobiledetect.os() == "Android") {
                map.panTo(new AMap.LngLat(118.365521, y));
            } else {
                map.panTo(new AMap.LngLat(118.372348, 31.328997));
            }
        } else if (x > 118.378582) {
            if (mobiledetect.os() == "IOS" || mobiledetect.os() == "Android") {
                map.panTo(new AMap.LngLat(118.376078, y));
            } else {
                map.panTo(new AMap.LngLat(118.372348, 31.328997));
            }
        } else if (y < 31.324654) {
            if (mobiledetect.os() == "IOS" || mobiledetect.os() == "Android") {
                map.panTo(new AMap.LngLat(x, 31.328002));
            } else {
                map.panTo(new AMap.LngLat(118.372348, 31.328997));
            }
        } else if (y > 31.33281) {
            if (mobiledetect.os() == "IOS" || mobiledetect.os() == "Android") {
                map.panTo(new AMap.LngLat(x, 31.329781));
            } else {
                map.panTo(new AMap.LngLat(118.372348, 31.328997));
            }
        }
    }
}

//限制地图显示级别，不小于17级
function mapZoomMove() {
    var zoomState = map.getZoom();
    console.log("begin zoom is :" + zoomState);
    if (zoomState < 15) {
        map.setZoom(15)
    }
}


//添加标记
function addMarker() {
    var pos = new Array();
    var posS = new Array();
    var posT = new Array();
    var zoomStyleMapping1 = {
        14: 0,
        15: 0,
        16: 0,
        17: 0,
        18: 0,
        19: 0,
        20: 0
    }


    //景点标记
    posS[0] = [118.373713, 31.330907];
    var markerS0 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[0],
        // offset: new AMap.Pixel(-13, -30),
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS0, 'click', function () {
        infoWindowS0.open(map, markerS0.getPosition());
    });
    AMap.event.addListener(markerS0, 'click', showInfoClick);
    AMap.event.addListener(markerS0, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS0_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[0],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS0.setMap(map);


    posS[1] = [118.373764, 31.330207];
    var markerS1 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[1],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS1, 'click', function () {
        infoWindowS1.open(map, markerS1.getPosition());
    });
    AMap.event.addListener(markerS1, 'click', showInfoClick);
    AMap.event.addListener(markerS1, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS1_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[1],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })


    markerS1.setMap(map);


    posS[2] = [118.37322, 31.329779];
    var markerS2 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[2],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS2, 'click', function () {
        infoWindowS2.open(map, markerS2.getPosition());
    });
    AMap.event.addListener(markerS2, 'click', showInfoClick);
    AMap.event.addListener(markerS2, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS2_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[2],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS2.setMap(map);


    posS[3] = [118.373634, 31.328372];
    var markerS3 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[3],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS3, 'click', function () {
        infoWindowS3.open(map, markerS3.getPosition());
    });
    AMap.event.addListener(markerS3, 'click', showInfoClick);
    AMap.event.addListener(markerS3, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS3_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[3],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS3.setMap(map);


    posS[4] = [118.371867, 31.331176];
    var markerS4 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[4],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS4, 'click', function () {
        infoWindowS4.open(map, markerS4.getPosition());
    });
    AMap.event.addListener(markerS4, 'click', showInfoClick);
    AMap.event.addListener(markerS4, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS4_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[4],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS4.setMap(map);


    posS[5] = [118.372863, 31.331211];
    var markerS5 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[5],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS5, 'click', function () {
        infoWindowS5.open(map, markerS5.getPosition());
    });
    AMap.event.addListener(markerS5, 'click', showInfoClick);
    AMap.event.addListener(markerS5, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS5_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[5],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS5.setMap(map);


    // posS[6] = [118.362167, 31.326852];
    // var markerS6 = new  AMap.ElasticMarker({
    //     map: map,
    //     zooms:[14,20],
    //     position: posS[6],
    //     // offset: new AMap.Pixel(-13, -30)
    //     styles:[{
    //         icon:{
    //             img:"/assets/mobilephone/images/mark_bs.png",
    //             size:[16,16],//可见区域的大小
    //             ancher:[8,8],//锚点
    //             fitZoom:14,//最合适的级别
    //             scaleFactor:2,//地图放大一级的缩放比例系数
    //             maxScale:1.4,//最大放大比例
    //             minScale:0.8//最小放大比例
    //         }
    //     }],
    //     zoomStyleMapping:zoomStyleMapping1
    // });
    // //鼠标点击marker弹出自定义的信息窗体
    // AMap.event.addListener(markerS6, 'click', function () {
    //     infoWindowS6.open(map, markerS6.getPosition());
    // });
    // AMap.event.addListener(markerS6,'click',showInfoClick);
    // AMap.event.addListener(markerS6,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerS6_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,20],
    //         position: posS[6],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })
    // markerS6.setMap(map);
    //
    //
    // posS[7] = [118.378448, 31.336059];
    // var markerS7 = new  AMap.ElasticMarker({
    //     map: map,
    //     zooms:[14,20],
    //     position: posS[7],
    //     // offset: new AMap.Pixel(-13, -30)
    //     styles:[{
    //         icon:{
    //             img:"/assets/mobilephone/images/mark_bs.png",
    //             size:[16,16],//可见区域的大小
    //             ancher:[8,8],//锚点
    //             fitZoom:14,//最合适的级别
    //             scaleFactor:2,//地图放大一级的缩放比例系数
    //             maxScale:1.4,//最大放大比例
    //             minScale:0.8//最小放大比例
    //         }
    //     }],
    //     zoomStyleMapping:zoomStyleMapping1
    // });
    // //鼠标点击marker弹出自定义的信息窗体
    // AMap.event.addListener(markerS7, 'click', function () {
    //     infoWindowS7.open(map, markerS7.getPosition());
    // });
    // AMap.event.addListener(markerS7,'click',showInfoClick);
    // AMap.event.addListener(markerS7,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerS7_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,20],
    //         position: posS[7],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })
    // markerS7.setMap(map);
    //
    //
    // posS[8] = [118.341074, 31.385737];
    // var markerS8 = new  AMap.ElasticMarker({
    //     map: map,
    //     zooms:[14,20],
    //     position: posS[8],
    //     // offset: new AMap.Pixel(-13, -30),
    //     styles:[{
    //         icon:{
    //             img:"/assets/mobilephone/images/mark_bs.png",
    //             size:[16,16],//可见区域的大小
    //             ancher:[8,8],//锚点
    //             fitZoom:14,//最合适的级别
    //             scaleFactor:2,//地图放大一级的缩放比例系数
    //             maxScale:1.4,//最大放大比例
    //             minScale:0.8//最小放大比例
    //         }
    //     }],
    //     zoomStyleMapping:zoomStyleMapping1
    // });
    // //鼠标点击marker弹出自定义的信息窗体
    // AMap.event.addListener(markerS8, 'click', function () {
    //     infoWindowS8.open(map, markerS8.getPosition());
    // });
    // AMap.event.addListener(markerS8,'click',showInfoClick);
    // AMap.event.addListener(markerS8,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerS8_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,20],
    //         position: posS[8],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })
    // markerS8.setMap(map);

    posS[9] = [118.371635, 31.326438];
    var markerS9 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[9],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS9, 'click', function () {
        infoWindowS9.open(map, markerS9.getPosition());
    });
    AMap.event.addListener(markerS9, 'click', showInfoClick);
    AMap.event.addListener(markerS9, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS9_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[9],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS9.setMap(map);


    posS[10] = [118.373558, 31.326406];
    var markerS10 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[10],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS10, 'click', function () {
        infoWindowS10.open(map, markerS10.getPosition());
    });
    AMap.event.addListener(markerS10, 'click', showInfoClick);
    AMap.event.addListener(markerS10, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS10_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[10],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS10.setMap(map);

    posS[11] = [118.370605, 31.328998];
    var markerS11 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[11],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS11, 'click', function () {
        infoWindowS11.open(map, markerS11.getPosition());
    });
    AMap.event.addListener(markerS11, 'click', showInfoClick);
    AMap.event.addListener(markerS11, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS11_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[11],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS11.setMap(map);


    posS[12] = [118.374707, 31.327611];
    var markerS12 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[12],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS12, 'click', function () {
        infoWindowS12.open(map, markerS12.getPosition());
    });
    AMap.event.addListener(markerS12, 'click', showInfoClick);
    AMap.event.addListener(markerS12, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS12_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[12],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS12.setMap(map);

    posS[13] = [118.374717, 31.327783];
    var markerS13 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[13],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS13, 'click', function () {
        infoWindowS13.open(map, markerS13.getPosition());
    });
    AMap.event.addListener(markerS13, 'click', showInfoClick);
    AMap.event.addListener(markerS13, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS13_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[13],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS13.setMap(map);

    posS[14] = [118.372495, 31.329587];
    var markerS14 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[14],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS14, 'click', function () {
        infoWindowS14.open(map, markerS14.getPosition());
    });
    AMap.event.addListener(markerS14, 'click', showInfoClick);
    AMap.event.addListener(markerS14, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS14_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[14],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS14.setMap(map);

    posS[15] = [118.372894, 31.330648];
    var markerS15 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[15],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS15, 'click', function () {
        infoWindowS15.open(map, markerS15.getPosition());
    });
    AMap.event.addListener(markerS15, 'click', showInfoClick);
    AMap.event.addListener(markerS15, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS15_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[15],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS15.setMap(map);

    posS[16] = [118.373865, 31.328124];
    var markerS16 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[16],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS16, 'click', function () {
        infoWindowS16.open(map, markerS16.getPosition());
    });
    AMap.event.addListener(markerS16, 'click', showInfoClick);
    AMap.event.addListener(markerS16, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS16_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[16],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS16.setMap(map);

    posS[17] = [118.37371, 31.327735];
    var markerS17 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[17],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS17, 'click', function () {
        infoWindowS17.open(map, markerS17.getPosition());
    });
    AMap.event.addListener(markerS17, 'click', showInfoClick);
    AMap.event.addListener(markerS17, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS17_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[17],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS17.setMap(map);

    posS[18] = [118.372627, 31.32636];
    var markerS18 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[18],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS18, 'click', function () {
        infoWindowS18.open(map, markerS18.getPosition());
    });
    AMap.event.addListener(markerS18, 'click', showInfoClick);
    AMap.event.addListener(markerS18, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS18_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[18],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS18.setMap(map);

    posS[19] = [118.37368, 31.328908];
    var markerS19 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[19],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS19, 'click', function () {
        infoWindowS19.open(map, markerS19.getPosition());
    });
    AMap.event.addListener(markerS19, 'click', showInfoClick);
    AMap.event.addListener(markerS19, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS19_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[19],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS19.setMap(map);

    posS[20] = [118.370864, 31.328227];
    var markerS20 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[20],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS20, 'click', function () {
        infoWindowS20.open(map, markerS20.getPosition());
    });
    AMap.event.addListener(markerS20, 'click', showInfoClick);
    AMap.event.addListener(markerS20, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS20_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[20],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS20.setMap(map);

    posS[21] = [118.374015, 31.329576];
    var markerS21 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[21],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS21, 'click', function () {
        infoWindowS21.open(map, markerS21.getPosition());
    });
    AMap.event.addListener(markerS21, 'click', showInfoClick);
    AMap.event.addListener(markerS21, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS21_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[21],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS21.setMap(map);

    posS[22] = [118.373039, 31.326246];
    var markerS22 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[22],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS22, 'click', function () {
        infoWindowS22.open(map, markerS22.getPosition());
    });
    AMap.event.addListener(markerS22, 'click', showInfoClick);
    AMap.event.addListener(markerS22, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS22_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[22],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS22.setMap(map);

    posS[23] = [118.375946, 31.330312];
    var markerS23 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[23],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS23, 'click', function () {
        infoWindowS23.open(map, markerS23.getPosition());
    });
    AMap.event.addListener(markerS23, 'click', showInfoClick);
    AMap.event.addListener(markerS23, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS23_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[23],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS23.setMap(map);

    posS[24] = [118.373771, 31.328123];
    var markerS24 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posS[24],
        styles: [{
            icon: {
                img: "/assets/mobilephone/images/mark_bs.png",
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    //鼠标点击marker弹出自定义的信息窗体
    AMap.event.addListener(markerS24, 'click', function () {
        infoWindowS24.open(map, markerS24.getPosition());
    });
    AMap.event.addListener(markerS24, 'click', showInfoClick);
    AMap.event.addListener(markerS24, 'click', function () {
        // map.clearMap();
        addMarker();
        var markerS24_c = new AMap.ElasticMarker({
            map: map,
            zooms: [14, 20],
            position: posS[24],
            styles: [{
                icon: {
                    img: "/assets/mobilephone/images/mark_click_bs.png",
                    size: [16, 16],//可见区域的大小
                    ancher: [8, 8],//锚点
                    fitZoom: 14,//最合适的级别
                    scaleFactor: 2,//地图放大一级的缩放比例系数
                    maxScale: 1.4,//最大放大比例
                    minScale: 0.8//最小放大比例
                }
            }],
            zoomStyleMapping: zoomStyleMapping1
        });
    })
    markerS24.setMap(map);


    //公共厕所位置
    posT[0] = [118.375777, 31.330669];
    var markerT0 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posT[0],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: sheshi[1].icon,
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    // AMap.event.addListener(markerT0,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerT0_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,20],
    //         position: posT[0],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })

    markerT0.setMap(map);


    posT[1] = [118.374079, 31.328041];
    var markerT1 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posT[1],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: sheshi[1].icon,
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    // AMap.event.addListener(markerT1,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerT1_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,20],
    //         position: posT[1],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })
    markerT1.setMap(map);

    posT[2] = [118.374272, 31.331428];
    var markerT2 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posT[2],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: sheshi[1].icon,
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    // AMap.event.addListener(markerT2,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerT2_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,20],
    //         position: posT[2],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })
    markerT2.setMap(map);


    posT[3] = [118.368532, 31.329482];
    var markerT3 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posT[3],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: sheshi[1].icon,
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    // AMap.event.addListener(markerT3,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerT3_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,20],
    //         position: posT[3],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })
    markerT3.setMap(map);

    posT[4] = [118.374207, 31.327365];
    var markerT4 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posT[4],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: sheshi[1].icon,
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    // AMap.event.addListener(markerT4,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerT4_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,20],
    //         position: posT[4],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })
    markerT4.setMap(map);


    posT[5] = [118.375001, 31.327347];
    var markerT5 = new AMap.ElasticMarker({
        map: map,
        zooms: [14, 20],
        position: posT[5],
        // offset: new AMap.Pixel(-13, -30)
        styles: [{
            icon: {
                img: sheshi[1].icon,
                size: [16, 16],//可见区域的大小
                ancher: [8, 8],//锚点
                fitZoom: 14,//最合适的级别
                scaleFactor: 2,//地图放大一级的缩放比例系数
                maxScale: 1.4,//最大放大比例
                minScale: 0.8//最小放大比例
            }
        }],
        zoomStyleMapping: zoomStyleMapping1
    });
    // AMap.event.addListener(markerT5,'click',function(){
    //     // map.clearMap();
    //     addMarker();
    //     var markerT5_c = new  AMap.ElasticMarker({
    //         map: map,
    //         zooms:[14,18],
    //         position: posT[5],
    //         styles:[{
    //             icon:{
    //                 img:"/assets/mobilephone/images/mark_click_bs.png",
    //                 size:[16,16],//可见区域的大小
    //                 ancher:[8,8],//锚点
    //                 fitZoom:14,//最合适的级别
    //                 scaleFactor:2,//地图放大一级的缩放比例系数
    //                 maxScale:1.4,//最大放大比例
    //                 minScale:0.8//最小放大比例
    //             }
    //         }],
    //         zoomStyleMapping:zoomStyleMapping1
    //     });
    // })
    markerT5.setMap(map);

}


//用户点击后添加标记
function addUserMarker() {
    map.clearMap();
    if ($("#panel")[0]) {
        $("#panel").hide();
    }
    addMarker();
    addPoi();
    var userMarker = new AMap.Marker({
        map: map,
        position: [x, y],
        offset: new AMap.Pixel(-13, -30)
    });
}

//添加默认定位点标记
function addPoi() {
    var markerDefaut = new AMap.Marker({
        icon: "https://webapi.amap.com/theme/v1.3/markers/n/loc.png",
        position: poi,
    });
    map.add(markerDefaut);
}

// 绘制3d图层
function draw3dPic(poi0x, poi0y, poi1x, poi1y, poi2x, poi2y, poi3x, poi3y) {
    var object3Dlayer = new AMap.Object3DLayer();
    map.add(object3Dlayer);

    var edge = new AMap.Object3D.Mesh();
    var geometry = edge.geometry;//创建之后获取geometry
    var lnglat0 = new AMap.LngLat(poi0x, poi0y);
    var lnglat1 = new AMap.LngLat(poi1x, poi1y);
    var lnglat2 = new AMap.LngLat(poi2x, poi2y);
    var lnglat3 = new AMap.LngLat(poi3x, poi3y);
    var v0xy = map.lngLatToGeodeticCoord(lnglat0);
    var v1xy = map.lngLatToGeodeticCoord(lnglat1);
    var v2xy = map.lngLatToGeodeticCoord(lnglat2);
    var v3xy = map.lngLatToGeodeticCoord(lnglat3);
    var z = -0.01;//3D地图Z方向朝下，所以负值
    geometry.vertices.push(v0xy.x, v0xy.y, z);//V0
    geometry.vertices.push(v1xy.x, v1xy.y, z);//V1
    geometry.vertices.push(v2xy.x, v2xy.y, z);//V3
    geometry.vertices.push(v3xy.x, v3xy.y, z);//V2

    geometry.faces.push(0, 1, 3);
    geometry.faces.push(1, 2, 3);

    // 颜色填充
    geometry.vertexColors.push(0.9, 0.9, 0.9, 1); //V0
    geometry.vertexColors.push(0.9, 0.9, 0.9, 1); //V1
    geometry.vertexColors.push(0.9, 0.9, 0.9, 1); //V2
    geometry.vertexColors.push(0.9, 0.9, 0.9, 1); //V3


    // edge.textures.push("http://a.amap.com/jsapi_demos/static/tourist/crate.gif");
    // edge.textures.push("http://www.ah-signstar.com:8089/assets/mobilephone/images/xinlangweibo.png");
    geometry.vertexUVs.push(0, 1); //V0
    geometry.vertexUVs.push(1, 1); //V1
    geometry.vertexUVs.push(1, 0); //V2
    geometry.vertexUVs.push(0, 0); //V3
    edge.backOrFront = 'both';//'back'、'front'、'both'
    object3Dlayer.add(edge);
}


//搜索标记
function addPointMarker(point) {
    map.clearMap();
    if($("#panel")[0]){
        $("#panel").hide();
    }
    addMarker();
    addPoi();
    var userMarker = new AMap.Marker({
        map: map,
        position: point,
        offset: new AMap.Pixel(-13, -30)
    });


}
var pointList = [
    {"name":"Second people's hospital","point":[118.377861,31.328707]},
    {"name":"The second hospital","point":[118.394458,31.322843]},
    {"name":"Fifth people's hospital","point":[118.402397,31.337687]},
    {"name":"WuHu general chamber of commerce","point":[118.380065,31.339725]},
    {"name":"Block A of Huayi International Shopping Center","point":[118.369327,31.32925]},
    {"name":" Block B of Huayi International Shopping Center","point":[118.370569,31.330648]},
    {"name":"Sanford fashion","point":[118.370244,31.331105]},
    {"name":"SuNing","point":[118.369439,31.330093]},
    {"name":"Century Mart","point":[118.368474,31.3282]},
    {"name":"Second street","point":[118.402397,31.337687]},
    {"name":"Fenghuang Food Street","point":[118.37076,31.340816]},
    {"name":"Public toilets in the Yuuz Scenic Area 1","point":[118.375777,31.330669]},
    {"name":"Public toilets in the Yuuz Scenic Area 2","point":[118.374272,31.331428]},
    {"name":"Public toilets in the Yuuz Scenic Area 3","point":[118.374079,31.328041]},
    {"name":"Public toilets 1","point":[118.375001,31.327347]},
    {"name":"Public toilets 2","point":[118.368532,31.329482]},
    {"name":"Public toilets 3","point":[118.374207,31.327365]},
    {"name":"Little Jiuzi Bird","point":[118.371867,31.331176]},
    {"name":"Gan Jiang sword","point":[118.373571,31.330827]},
    {"name":"Ancient copper smelting in Nanling","point":[118.373764,31.330207]},
    {"name":"Da Yu leads Zhongjiang","point":[118.37322,31.329779]},
    {"name":"Fanchang \"herringbone cave\"","point":[118.372863,31.331211]},
    {"name":"Historical and cultural promenade","point":[118.372863,31.331211]},
    {"name":"Step moon Bridge","point":[118.373634,31.328372]},
    {"name":"Introduction to Zhang Xiaoxiang","point":[118.372863,31.331211]},
    {"name":"LiBai and Tianmen Mountain","point":[118.372863,31.331211]},
    {"name":"ShenKuo and WanChunWei","point":[118.372863,31.331211]},
    {"name":"Wu Chu Changan Battle","point":[118.372863,31.331211]},
    {"name":"First boat crossing the river","point":[118.372863,31.331211]},
    {"name":"Wuhu pulp dyeing","point":[118.372863,31.331211]},
    {"name":"Wuhu Rice City","point":[118.372863,31.331211]},//
    {"name":"Wuhu Iron Painting","point":[118.372863,31.331211]},//
    {"name":"Wuhu Yangtze River Bridge","point":[118.372863,31.331211]},//
    {"name":"Zhang Xiaoxiang and Jinghu","point":[118.372863,31.331211]},
    {"name":"Anhui Cultural Celebrity Collection","point":[118.371635,31.326438]},
    {"name":"Step Wenting","point":[118.373558,31.326406]},
    {"name":"Ruler pavilion","point":[118.370605,31.328998]},
    {"name":"GanjiangMoye","point":[118.374707,31.327611]},
    {"name":"Guanlan Pavilion","point":[118.374717,31.327783]},
    {"name":"Lake heart pavilion","point":[118.372495,31.329587]},
    {"name":"JiuDingZeRui","point":[118.372894,31.330648]},
    {"name":"Kong Rong Let Pear","point":[118.373865,31.328124]},
    {"name":"Standing wood for the letter","point":[118.37371,31.327735]},
    {"name":"Liu Chunyuan","point":[118.372627,31.32636]},
    {"name":"Hydrophilic square","point":[118.37368,31.328908]},
    {"name":"Wuhu City Art Museum","point":[118.370864,31.328227]},
    {"name":"Wuhu Art Exhibition Hall","point":[118.374015,31.329576]},
    {"name":"XiYouDing","point":[118.373039,31.326246]},
    {"name":"YingBingGe","point":[118.375946,31.330312]},
    {"name":"Piercing the wall","point":[118.373771,31.328123]}
]

function isExist() {
    var isexist = false;
    for(i = 0;i<pointList.length;++i){
        if(pointList[i].name == $("#tipinput").val()){
            isexist = true;
            break;
        }
    }
    return isexist;
}

function searchPoint() {
    if($("#tipinput").val()==null||$("#tipinput").val().trim()=="") {
        M.Toast.dismissAll();
        M.toast({html: '请输入地点关键词'});
    }else if (isExist()) {
        for(i = 0;i<pointList.length;++i){
            if(pointList[i].name == $("#tipinput").val()){
                addPointMarker(pointList[i].point);
                map.setCenter(pointList[i].point);
                // console.log(pointList[i].point);
                var target = document.getElementById("search_panel");
                target.style.display="none";
                direction(pointList[i].point[0],pointList[i].point[1],poi,iplocation);
            }
        }
    }else{
        M.Toast.dismissAll();
        M.toast({html: '请输入正确的地点'});
    }
}





