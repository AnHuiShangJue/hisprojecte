/**
 * Created with WebStorm.
 * Author: qiang.niu(http://www.siptea.cn)
 * Date: 2015/7/6 11:35
 * 注意，Amap.WalkingRender与Amap.DrivingRender的代码完全一样
 */
if (typeof(Lib) == "undefined") {
    Lib = {};
}
Lib.AMap = Lib.AMap || {};
Lib.AMap.WalkingRender = function() {
    var me = this;
    //me.author="qiang.niu(http://www.siptea.cn)";
    me._currentRouteTitle = null; //当前打开的驾车方案标题div
    me._currentRouteOptenitem = null; //当前打开的驾车方案内容div
    /**
     * 自动渲染
     * @method Lib.AMap.DrivingRender#autoRender
     * @param {object} [options] options
     * @param {AMap.Map} [options.map] 高德js api的map对象
     * @param {HTMLElement|String} [options.panel] html的dom节点或其id号
     */
    me.autoRender = function(options) { //options.map otpions.panel options.data
        me.clear();
        if (!options || !options.data || (!options.panel && !options.map)) {
            return;
        }
        options.data.start = options.data.start || {
            location: options.data.origin,
            name: "起点"
        };
        options.data.end = options.data.end || {
            location: options.data.destination,
            name: "终点"
        };
        options.data.start.type = "start";
        options.data.end.type = "end";
        this.options = options; //保存用户输入的参数
        this.hideMarkers = options['hideMarkers'] || false;
        if (options.map) {
            this._infoWindow = new AMap.InfoWindow({ //点的信息窗体
                size: new AMap.Size(0, 0),
                isCustom: true,
                offset: new AMap.Pixel(0, -30)
            });
            this._overlays = []; //线路
            this._route = []; //鼠标划过驾车方案时绘制的半透明线
            this._highlightOverlay = null; //高亮线路
            if (!options.panel) { //如果传入了panel,在生成panel时会默认绘制第一个驾车方案，所以在这里就不绘制了
                this.drawRouteOverlays(0); //默认绘制第一个驾车方案
            }
        }
        if (options.panel) {
            if (Object.prototype.toString.call(options.panel) == '[object String]') {
                options.panel = document.getElementById(options.panel);
            }
            options.panel.innerHTML = this.view.createPanel(options.data);
            Change();
            this.enableListeners();
        }
    }
    /**
     * 清空面板和地图
     * @method Lib.AMap.DrivingRender#clear
     */
    me.clear = function() {
        this.clearPanel();
        this.clearOverlays();
    };
    /**
     * 绘制某个驾车方案的线路图（只画起始点到结束点之间的半透明线）
     * @method Lib.AMap.DrivingRender#drawRoute
     * @param index  驾车方案索引号
     */
    me.drawRoute = function(index) {
        var data = this.options.data,
            route = data.routes[index];
        var distance = route.distance,
            steps = route.steps;
        var walkPaths = [],
            busPaths = [],
            railwayPaths = [];
        walkPaths.push([data.start.location, steps[0].path[0]]);
        var lastStepPath = steps[steps.length - 1].path
        walkPaths.push([lastStepPath[lastStepPath.length - 1], data.end.location]);
        for (var i = 0, step; i < steps.length; i++) {
            step = steps[i];
            //busPaths.push(step.path);
            busPaths = busPaths.concat(step.path);
        }
        if (busPaths.length > 0) {
            busPaths = [busPaths];
        }
        this.clearRoute();
        this._route = this.addOverlays([], walkPaths, busPaths, railwayPaths, {
            strokeOpacity: 0.4
        });
    }
    /**
     *  清空某个驾车方案的线路图（只画起始点到结束点之间的半透明线）
     *  @method Lib.AMap.DrivingRender#clearRoute
     */
    me.clearRoute = function() {
        if (this._route) {
            for (var i = 0; i < this._route.length; i++) {
                this._route[i].setMap(null);
            }
            this._route = [];
        }
    }
    /**
     * 绘制某个驾车方案的线路图
     * @method Lib.AMap.DrivingRender#drawRouteOverlays
     * @param index 驾车方案索引号
     */
    me.drawRouteOverlays = function(index) {
        var data = this.options.data,
            route = data.routes[index];
        var distance = route.distance,
            steps = route.steps;
        var walkPaths = [],
            busPaths = [],
            points = [],
            railwayPaths = [];
        points.push(data.start);
        points.push(data.end);
        walkPaths.push([data.start.location, steps[0].path[0]]);
        var lastStepPath = steps[steps.length - 1].path
        walkPaths.push([lastStepPath[lastStepPath.length - 1], data.end.location]);
        for (var i = 0, step; i < steps.length; i++) {
            step = steps[i];
            //busPaths.push(step.path);
            busPaths = busPaths.concat(step.path);
        }
        if (busPaths.length > 0) {
            busPaths = [busPaths];
        }
        this.clearOverlays();
        this._overlays = this.addOverlays(points, walkPaths, busPaths, railwayPaths);
        me.options.map.setFitView(this._overlays);
    }
    /**
     * 添加地图覆盖物
     * @method Lib.AMap.DrivingRender#addOverlays
     * @param points  点
     * @param walkPaths  步行线
     * @param busPaths  公交和地铁线
     * @param railwayPaths 铁路线
     * @param styleOptions 样式
     * @returns {Array}
     */
    me.addOverlays = function(points, walkPaths, busPaths, railwayPaths, styleOptions) {
        var map = this.options.map;
        styleOptions = styleOptions || {
            strokeOpacity: 1
        }
        var _overlays = [];
        if (!this.hideMarkers) {
            if (points[0]) {
                //绘制起点
                var start = new AMap.Marker({
                    map: map,
                    position: points[0].location, //基点位置
                    content: '<div class="amap-lib-marker-from"></div>'
                });
                start.isOfficial = true;
                start._data = points[0];
                AMap.event.addListener(start, "click", this.listener.markerClick);
                _overlays.push(start);
            }
            if (points.length > 1) {
                //绘制终点
                var end = new AMap.Marker({
                    map: map,
                    position: points[points.length - 1].location, //基点位置
                    content: '<div class="amap-lib-marker-to"></div>'
                });
                end.isOfficial = true;
                end._data = points[points.length - 1];
                AMap.event.addListener(end, "click", this.listener.markerClick);
                _overlays.push(end);
            }

            for (var i = 1, point; i < points.length - 1; i++) { //绘制途经点
                var transit_mode = points[i].segment.transit_mode.toLowerCase();
                point = new AMap.Marker({
                    map: map,
                    position: points[i].location, //基点位置
                    content: '<div class="amap-lib-marker-stop amap-lib-marker-bus-' + transit_mode + 'Stop"></div>',
                    offset: {
                        x: -12,
                        y: -12
                    } //相对于基点的位置
                });
                point.isOfficial = true;
                point._data = points[i];
                AMap.event.addListener(point, "click", this.listener.markerClick);
                _overlays.push(point);
            }
            if (points[0]) {
                //绘制起点
                var start = new AMap.Marker({
                    map: map,
                    position: points[0].location, //基点位置
                    content: '<div class="amap-lib-marker-from"></div>'
                });
                start.isOfficial = true;
                start._data = points[0];
                AMap.event.addListener(start, "click", this.listener.markerClick);
                _overlays.push(start);
            }
            if (points.length > 1) {
                //绘制终点
                var end = new AMap.Marker({
                    map: map,
                    position: points[points.length - 1].location, //基点位置
                    content: '<div class="amap-lib-marker-to"></div>'
                });
                end.isOfficial = true;
                end._data = points[points.length - 1];
                AMap.event.addListener(end, "click", this.listener.markerClick);
                _overlays.push(end);
            }

            for (var i = 1, point; i < points.length - 1; i++) { //绘制途经点
                var transit_mode = points[i].segment.transit_mode.toLowerCase();
                point = new AMap.Marker({
                    map: map,
                    position: points[i].location, //基点位置
                    content: '<div class="amap-lib-marker-stop amap-lib-marker-bus-' + transit_mode + 'Stop"></div>',
                    offset: {
                        x: -12,
                        y: -12
                    } //相对于基点的位置
                });
                point.isOfficial = true;
                point._data = points[i];
                AMap.event.addListener(point, "click", this.listener.markerClick);
                _overlays.push(point);
            }
        }
        //绘制乘车的路线
        for (var i = 0, busPath; i < busPaths.length; i++) {
            busPath = new AMap.Polyline({
                map: map,
                path: busPaths[i],
                lineJoin: 'round',
                strokeColor: "#0091ff", //线颜色
                strokeOpacity: styleOptions.strokeOpacity, //线透明度
                strokeWeight: 6 //线宽
            });
            busPath.isOfficial = true;
            _overlays.push(busPath);
        }
        //绘制火车发站与到站之间的线
        for (var i = 0, railwayPath; i < railwayPaths.length; i++) {
            railwayPath = new AMap.Polyline({
                map: map,
                path: railwayPaths[i],
                strokeColor: "gray", //线颜色
                strokeStyle: "dashed",
                strokeOpacity: styleOptions.strokeOpacity, //线透明度
                strokeWeight: 4 //线宽
            });
            railwayPath.isOfficial = true;
            _overlays.push(railwayPath);
        }
        //绘制步行的路线
        for (var i = 0, walkPath; i < walkPaths.length; i++) {
            walkPath = new AMap.Polyline({
                map: map,
                path: walkPaths[i],
                strokeColor: "gray", //线颜色
                strokeStyle: "dashed",
                strokeOpacity: styleOptions.strokeOpacity, //线透明度
                strokeWeight: 6 //线宽
            });
            walkPath.isOfficial = true;
            _overlays.push(walkPath);
        }
        return _overlays;
    }
    /**
     * 清空面板
     *  @method Lib.AMap.DrivingRender#clearPanel
     */
    me.clearPanel = function() {
        if (this.options && this.options.panel) {
            this.options.panel.innerHTML = '';
        }
    }
    /**
     * 清空绘制的地图覆盖物
     * @method Lib.AMap.DrivingRender#clearOverlays
     */
    me.clearOverlays = function() {
        if (this._overlays) {
            for (var i = 0, overlay; i < this._overlays.length; i++) {
                overlay = this._overlays[i];
                overlay.setMap(null);
            }
            this._overlays = [];
        }
        if (this._infoWindow) {
            this._infoWindow.close();
        }
        if (this._highlightOverlay) {
            this._highlightOverlay.setMap(null);
            this._highlightOverlay = null;
        }
        this.clearRoute();
    }
    /**
     * 解析出标题
     * @method Lib.AMap.DrivingRender#getTitle
     * @param route
     * @returns {string}
     */
    me.getTitle = function(route) { //
        var policy = route.policy ? " | <span class='blue'>" + route.policy + "</span>" : "";
        var result = me.util.getTime(route.time) + "(" + me.util.getDistance(route.distance) + ")" + policy;
        return result;
    }
    /**
     * 工具类
     * @name util
     * @type {object}
     * @memberof Lib.AMap.DrivingRender#
     */
    me.util = {};
    me.util.getDistance = function(len) { //距离，米换算为千米
        if (len <= 1000) {
            var s = len;
            return s + "米";
        } else {
            var s = Math.round(len / 10);
            return "" + s / 100 + "公里";
        }
    }
    me.util.getTime = function(second) { //距离，米换算为千米
        var minute = (second / 60).toFixed(0);
        if (minute < 60) {
            return "" + minute + "分钟";
        } else {
            return "" + Math.floor(minute / 60) + "小时" + (minute % 60) + "分钟";
        }
    }
    me.util.domstr2dom = function(arg) {
        var objE = document.createElement("div");
        objE.innerHTML = arg;
        return objE;
    }

    /*
     * 根据类名获得元素
     * @method getElementsByClassName
     * @memberof Lib.AMap.DrivingRender#util#
     * @param className  类名
     * @param tag [tag='*']   元素名 默认所有元素
     * @param parent [parent=document]   父元素 默认doucment
     * @returns {Array}
     */
    me.util.getElementsByClassName = function(className, tag, parent) {
        var testClass = new RegExp("(^|\\s)" + className + "(\\s|$)");
        //var testClass = new RegExp("(\w|\s)*" + className + "(\w|\s)*");
        var tag = tag || "*";
        var parent = parent || document;
        var elements = parent.getElementsByTagName(tag);
        var returnElements = [];
        for (var i = 0, current; i < elements.length; i++) {
            current = elements[i];
            if (testClass.test(current.className)) {
                returnElements.push(current);
            }
        }
        return returnElements;
    }
    /**
     * 注册乘车方案点击事件、乘车方案mouseover事件、乘车路段点击事件
     * @method Lib.AMap.DrivingRender#enableListeners
     */
    me.enableListeners = function() {
        var unfocusTitles = me.util.getElementsByClassName("planTitle", "div", me.options.panel);
        for (var i = 0, unfocusTitle; i < unfocusTitles.length; i++) {
            unfocusTitle = unfocusTitles[i];
            AMap.event.addDomListener(unfocusTitle, "click", this.listener.unfocusTitleClick); //驾车方案点击事件
            AMap.event.addDomListener(unfocusTitle, "mouseenter", this.listener.unfocusTitleMouseenter);
            AMap.event.addDomListener(unfocusTitle, "mouseleave", this.listener.unfocusTitleMouseleave);
        }
        var c_divs = this.util.getElementsByClassName("*", "dt", me.options.panel);
        for (var i = 0, c_div; i < c_divs.length; i++) {
            c_div = c_divs[i];
            AMap.event.addDomListener(c_div, "click", this.listener.routeStepItem); //乘车路段点击事件
        }

        if (unfocusTitles.length > 0) {
            me.listener.unfocusTitleClick.call(unfocusTitles[0]); //默认打开第一个乘车方案
        }
    }
    /**
     * 监听类
     * @name listener
     * @type {object}
     * @memberof Lib.AMap.DrivingRender#
     */
    me.listener = {};
    me.listener.markerClick = function() {
        var data = this._data;
        /*    var content=[];
         if(data.segment){//data.segment.transit_mode=='SUBWAY'||data.segment.transit_mode=='BUS'
         var onStation=data.segment.transit.on_station.name,
         offStation=data.segment.transit.off_station.name;
         if(data.name==onStation){
         content.push(data.name+" -- 上车<br>");
         }
         if(data.name==offStation){
         content.push(data.name+" -- 下车<br>");
         }
         content.push(data.segment.transit.lines[0].name+"<br>");
         content.push("出发点："+onStation+"<br>");
         content.push("到达点："+offStation+"<br>");
         }else{
         content.push(data.name+"<br>");
         }*/
        if (data.type == 'start' || data.type == 'end') {
            me._infoWindow.setOffset(new AMap.Pixel(0, -30));
        } else {
            me._infoWindow.setOffset(new AMap.Pixel(0, 0));
        }
        if (data.name == '起点' || data.name == '终点') {} else {
            me._infoWindow.setContent(me.view.createInfowindowContent(data));
            me._infoWindow.open(me.options.map, this.getPosition());
        }

        me.options.map.setCenter(this.getPosition());
    }
    me.listener.routeStepItem = function() { //点击换乘路段时，负责高亮显示此路段并且自动调整地图视野

        if (me._highlightOverlay) {
            me._highlightOverlay.setMap(null);
        }

        this.style.backgroundColor = "rgb(133,15,15)";
        if (me.listener.routeStepItem.last) {
            me.listener.routeStepItem.last.style.backgroundColor = "";
        }
        me.listener.routeStepItem.last = this;

        //得到换乘段索引号
        var p = this.parentNode;
        var children = p.children;
        var stepIndex; //换乘段索引号
        for (var i = 0, child; i < children.length; i++) {
            child = children[i];
            if (child == this) {
                stepIndex = i;
                break;
            }
        }
        if (stepIndex == 0) { //如果点击的是起始点
            me.listener.markerClick.call({
                _data: me.options.data.start,
                getPosition: function() {
                    return me.options.data.start.location
                }
            })
            return;
        }
        if (stepIndex == children.length - 1) { //如果点击的是结束点
            me.listener.markerClick.call({
                _data: me.options.data.end,
                getPosition: function() {
                    return me.options.data.end.location
                }
            })
            return;
        }
        me._infoWindow.close();
        stepIndex = stepIndex - 1; //由于起始点不是换乘段，所以-1
        var step = me.options.data.routes[me._currentRouteIndex].steps[stepIndex];
        var highlight;
        highlight = new AMap.Polyline({
            map: me.options.map,
            path: step.path,
            strokeColor: "red", //线颜色
            strokeOpacity: 0.8, //线透明度
            strokeWeight: 6 //线宽
        });
        highlight.isOfficial = true;
        me._highlightOverlay = highlight;
        me.options.map.setBounds(highlight.getBounds());

    }
    me.listener.driveTitleClick = function() { //点击展开的div标题时，收起此div
        me._currentRouteTitle.style.display = "block";
        me._currentRouteOptenitem.style.display = "none";
        me.clearOverlays();
    }
    me.listener.unfocusTitleClick = function() { //点击各驾车方案时，负责div的展开与关闭,并调用地图绘制方法
        var target = arguments[0] && (arguments[0].target || arguments[0].srcElement);
        if (target && target.tagName == "A") {
            return;
        }
        if (me._currentRouteTitleStatus == "open" && me._currentRouteTitle == this) { //如果已经打开并且点击的是打开的，则关闭
            me.view.closeCurrentPlanDiv();
            me.clearOverlays();
        } else if (me._currentRouteTitleStatus == "open") { //如果已经打开并且点击的是其它换乘方案，则关闭原来的打开新的
            me.view.closeCurrentPlanDiv();
            me.view.openPlanDiv(this);
        } else { //否则打开
            me.view.openPlanDiv(this);
        }
    }
    me.listener.unfocusTitleMouseenter = function(e) {
        var index = this.getAttribute("index");
        me.drawRoute(index);

        me.listener.unfocusTitleMouseenter.last = this;
    }
    me.listener.unfocusTitleMouseleave = function(e) {
        me.clearRoute();
    }
    me.view = {}; //创建dom结构类的方法
    me.view.openPlanDiv = function(planDiv) { //打开一个新的换乘方案panel
        me._currentRouteTitle = planDiv;
        me._currentRouteOptenitem = planDiv.nextSibling;
        me.util.getElementsByClassName('icon-arrow-up', 'div', me._currentRouteTitle)[0].className = "icon-arrow-up expand";
        if (me.util.getElementsByClassName('clearfix', 'ul', me._currentRouteTitle)[0]) {
            me.util.getElementsByClassName('clearfix', 'ul', me._currentRouteTitle)[0].style.display = "inline-block";
        }
        me._currentRouteOptenitem.style.display = "block";
        me._currentRouteTitleStatus = "open";

        var index = me._currentRouteTitle.getAttribute("index");
        me._currentRouteIndex = index; //记录当前换乘方案索引号
        if (me.options.map) {
            me.drawRouteOverlays(me._currentRouteIndex);
        }
    }
    me.view.closeCurrentPlanDiv = function() { //关闭当前打开的换乘方案panel
        var title = me.util.getElementsByClassName('icon-arrow-up expand', 'div', me._currentRouteTitle)[0];
        var fix = me.util.getElementsByClassName('clearfix', 'ul', me._currentRouteTitle)[0];
        if (title) {
            title.className = "icon-arrow-up";
        }
        if (fix) {
            fix.style.display = "none";
        }
        me._currentRouteOptenitem.style.display = "none";
        me._currentRouteTitleStatus = "close";
    }
    me.view.createInfowindowContent = function(data) { //创建点的infowindow内容
        var content = document.createElement('div');
        var div = document.createElement('div');
        div.className = 'amap-content-body';
        var c = [];
        c.push('<div class="amap-lib-infowindow">');
        c.push('    <div class="amap-lib-infowindow-title">' + data.name + '&nbsp;<a href=\"http://detail.amap.com/detail/' + data.id + '?spm=0.0.0.0.sWhSmy&citycode=' + data.citycode + '\" target=\"_blank\">详情»</a></div>');
        c.push('    <div class="amap-lib-infowindow-content">');
        c.push('        <div class="amap-lib-infowindow-content-wrap">');
        c.push('             <div>地址：' + data.address + '</div>');
        if (data.tel) {
            c.push('             <div>电话：' + data.tel + '</div>');
        }
        c.push('        </div>');
        c.push('    </div>');
        c.push('</div>');
        div.innerHTML = c.join('');

        var sharp = document.createElement('div');
        sharp.className = 'amap-combo-sharp';
        div.appendChild(sharp);

        var close = document.createElement('div');
        close.className = 'amap-combo-close';
        div.appendChild(close);
        close.href = 'javascript: void(0)';
        AMap.event.addDomListener(close, 'touchend', function(e) {
            me._infoWindow['close']();
        }, this);
        AMap.event.addDomListener(close, 'click', function(e) {
            me._infoWindow['close']();
        }, this);
        content.appendChild(div);
        content.appendChild(close);
        content.appendChild(sharp);
        return content;
    }
    me.view.createPanel = function(data) { //根据服务插件AMap.Driving的返回结果，生成panel
        if (!data.routes) {
            return "<div class='amap_lib_driving'>抱歉，没有合适的路线。</div>";
        }
        var result = [];
        result.push("<div class='amap-lib-driving' >");
        for (var i = 0, route; i < data.routes.length; i++) {
            route = data.routes[i] ;
            var
                //isDisplay=i==0?true:false,//默认打开第一个乘车方案
                isDisplay = false,
                title = me.getTitle(route);

            result = result.concat(me.view.createTitleDiv(i, title, route, !isDisplay));
            result = result.concat(me.view.createOpenitemDiv(i, title, data, isDisplay));
        }
        result.push("</div>");
        return result.join("");
    }
    me.view.createTitleDiv = function(index, title, route, isDisplay) { //创建标题div
        if (isDisplay) {
            isDisplay = "block";
        } else {
            isDisplay = "none";
        }
        var steps = route.steps,
            roadNameArray = [],
            roadNames = "",
            roadNames2 = "";
        var temp = "";
        for (var i = 0, road; i < steps.length; i++) {
            road = steps[i].road;
            if (road && road != temp) {
                roadNameArray.push(road);
                temp = road;
            }
        }
        roadNames = roadNameArray.join(">");
        if (roadNameArray.length > 4) {
            roadNameArray.splice(2, roadNameArray.length - 4, "......");
            roadNames2 = roadNameArray.join(">");
        } else {
            roadNames2 = roadNames;
        }
        var r = [];
        r.push("<div class=\"planTitle\" index=\"" + index + "\">");
        r.push("    <h3 title=\"" + roadNames + "\">");
        r.push("         <b>" + roadNames2 + "</b>");
        r.push("    </h3>");
        r.push("    <p>" + title + "</p>");
        //http://www.amap.com/?r=sy,sx,sname,dy,dx,dname,m,t,mcount,my,mx,mname
        var d = me.options.data;
        if (d.start.name !== '起点' && d.end.name !== '终点') {
            p = [];
            p.push(d.start.location.lat);
            p.push(d.start.location.lng);
            p.push(d.start.name);
            p.push(d.end.location.lat);
            p.push(d.end.location.lng);
            p.push(d.end.name);
            p.push("5"); //m
            if (route.policy) {
                p.push("0"); //t:驾车是'0'.公交是'1'.步行是'2'
            } else {
                p.push("2"); //t:驾车是'0'.公交是'1'.步行是'2'
            }
            p.push("0");
            r.push("	<ul class=\"clearfix\">");
            r.push("		<li><a target='_blank' class=\"blue\" href=\"http://www.amap.com/?r=" + p.join(",") + "\">在高德地图中查看</a></li>");
            r.push("	</ul>");
        }
        r.push("    <div class=\"sidebar\">");
        r.push("        <div class=\"icon-arrow-up\" href=\"javascript:void(0)\"></div>");
        r.push("    </div>                ");
        r.push("</div>");
        return r;
    }
    me.view.createOpenitemDiv = function(index, title, data, isDisplay) { //创建打开的div
        if (isDisplay) {
            isDisplay = "block";
        } else {
            isDisplay = "none";
        }
        var route = data.routes[index],
            steps = route.steps;
        var r = [];
        r.push("<dl class=\"plan plan-nobus dlview\">");
        r.push("    <dt class=\"start\"><div class='beforedt'></div><div class='afterdt'></div><b>" + data.start.name + "</b></dt>");
        //添加了a标签
        for (var i = 0, step; i < steps.length; i++) {
            step = steps[i];
            //IOS端让服务器去生成音频文件
            if (mobiledetect.os() == "iOS") {
                $.ajax({
                    type: "post",
                    data: {"message":step.instruction,"iplocation":iplocation,"index":i},
                    url: "/mobilephone/sounds",
                    dataType: "json",
                    success: function (msg) {
                        if (msg.success) {

                        } else {
                            console.log("msg is failec")
                        }
                    },
                    error: function (data) {
                    }
                });
                r.push(" <dt class=\"route turn-" + me.getSigns(step.action) + "\"  index=\"" + index + "\">                ");
                r.push("  <div class='beforedt'></div><div class='afterdt'></div> <a id=\"startaudio_"+i+" \">     " + step.instruction+"</a>");
                r.push(" <audio id=\"audio_"+i+"\" autoplay=\"autoplay\" >" +
                    "                            <source  src='/mobilephone/getmusic?fileName="+"output_"+iplocation+"_"+i+".mp3'" +
                    "                                    type=\"audio/mp3\">\n" +
                    "                        </audio>");
                r.push(" </dt>");
            }else{
                r.push(" <dt class=\"route turn-" + me.getSigns(step.action) + "\"  index=\"" + index + "\">                ");
                r.push("  <div class='beforedt'></div><div class='afterdt'></div> <a id=\"startaudio_"+i+" \">     " + step.instruction+"</a>");
                r.push(" </dt>");
            }

        }
        //原声的代码
        // for (var i = 0, step; i < steps.length; i++) {
        //     step = steps[i];
        //     r.push(" <dt class=\"route turn-" + me.getSigns(step.action) + "\"  index=\"" + index + "\">                ");
        //     r.push("  <div class='beforedt'></div><div class='afterdt'></div>       " + step.instruction);
        //     r.push(" </dt>");
        // }
        r.push("    <dt class=\"end\"><div class='beforedt'></div><b>" + data.end.name + "</b></dt>");
        r.push("</dl>");
        r.push("<div style='color:#8b1014' align=\"center\">貌似可以滚动哦！</div>")
        return r;
    }
    me.getSigns = function(action) {
        var signs = '';
        var routeSign = {
            '左转': 'left',
            '右转': 'right',
            '靠左': 'keepleft',
            '靠右': 'keepright',
            '向左前方行驶': 'leftup',
            '向左后方行驶': 'leftdown',
            '向右前方行驶': 'rightup',
            '向右后方行驶': 'rightdown',
            '左转调头': 'leftback',
            '右转调头': 'rightback',
            '进入环岛': 'enterRing',
            '离开环岛': 'leaveRing',
            '减速行驶': 'slow',
            '向左前方行走': 'leftup',
            '向左后方行走': 'leftdown',
            '向右前方行走': 'rightup',
            '向右后方行走': 'rightdown',
            '通过人行横道': 'crosswalk',
            '通过过街天桥': 'overpass',
            '通过地下通道': 'underpass',
            '通过广场': 'squarepass'
        }
        signs = routeSign[action];
        if (!signs) {
            signs = "advance";
        }
        return signs;
    }
    /*  //生成infowindow基本结构（上中下）
     me.view.createInfowindowDom=function(content, type) {
     var self = this;
     //定义infowindow容器
     var infowindow_wrap = document.createElement("div");
     infowindow_wrap.className = "infowindow-wrap J_infoWin";
     $(infowindow_wrap).on('mousemove', function(e) {
     e.stopPropagation();
     });
     //定义infowindow上部内容
     var infowindow_header = document.createElement("div");
     infowindow_header.className = "infowindow-header";
     infowindow_wrap.appendChild(infowindow_header);
     //定义infowindow内容区
     var infowindow_body = document.createElement("div");
     infowindow_body.className = "infowindow-body";
     infowindow_body.innerHTML = content;
     infowindow_wrap.appendChild(infowindow_body);
     //定义infowindow下部arrow
     var infowindow_foot = document.createElement("div");
     infowindow_foot.className = "infowindow-foot";
     var infowindow_arrow = document.createElement("div");
     var className;
     */
    /*    if (type == "marker-poi" || type == "marker-sub" || type == "marker-poi-geo" || type == "marker-station" || type == "marker-busStop" || type == "marker-single" || type == "marker-fav-single" || type == "marker-fav" || type == "marker-tmp" || type == "marker-tmp-sign" || type == "marker-hotspot" || type == "marker-placeSearch") {
     className = "infowindow-arrow marker-iw";
     } else if (type === "poly-drive" || type === "poly-walk" || type == "marker-drive-from" || type == "marker-drive-to" || type == "marker-plan-poi" || type == "marker-walk-from" || type == "marker-walk-to" || type == "marker-bus-from" || type == "marker-bus-to" || type == "marker-bus-busStop" || type == "marker-bus-subwayStop" || type == "marker-favdrive-from" || type == "marker-favdrive-to") {
     className = "infowindow-arrow poly-iw";
     }*/
    /*
     className = "infowindow-arrow poly-iw";
     infowindow_arrow.className = className;
     infowindow_foot.appendChild(infowindow_arrow);
     infowindow_wrap.appendChild(infowindow_foot);
     return infowindow_wrap;
     }*/
}

//浏览器大小发生改变时触发的函数
function Change(){
    var browserWidth = window.screen.width;//获取屏幕分辨率的宽度。
        if(browserWidth<1024){//当浏览器可视区域小于1024时触发
            if(browserWidth<625){
                $("#panel").css("background-color","rgba(56,56,56,0.15)");
                $(".amap-lib-driving").css("background-color","rgba(0,0,0,0.15)");
                $(".dlview a").css("color","white");
                $(".dlview").css("height","100px");
                $(".dlview").css("overflow-y","scroll");
            }
            $("#panel").css("background-color","rgba(56,56,56,0.15)");
            $(".amap-lib-driving").css("background-color","rgba(0,0,0,0.15)");
            $(".dlview a").css("color","white");
            $(".dlview").css("height","100px");
            $(".dlview").css("overflow-y","scroll");
    }else{
        $("#panel").css("background-color","rgba(56,56,56,0.15)");
        $(".amap-lib-driving").css("background-color","rgba(0,0,0,0.15)");
        $(".dlview a").css("color","white");
        $(".dlview").css("height","100px");
        $(".dlview").css("overflow-y","scroll");

    }

}

