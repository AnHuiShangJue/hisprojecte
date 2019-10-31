
/**
 * *@Description 体温控件的绘制       www.anhuishangjue.com
 * *@Params
 * *@return
 * *@Author chenzhicai
 * *@Date  2019-08-08
 * *@Time **/
var Templates = function(){

    var mOptions;
    //画布
    var canvas;
    //画笔
    var mPaint;
    //体温点数据
    var datas;

    //内边距离
    var paddingLeft = 10;
    var paddingTop = 10;
    var paddingRight = 10;
    var paddingBottom = 10;

    //top的高度
    var topHeight ;
    var topWidth;

    //内容区域的宽高
    var contentWidth ;
    var contentHeight ;

    //父容器宽高
    var parentWidth;
    var parentHeight;

    //中心的宽高
    var centerHeight;
    var centetrWidth;

    //画笔的宽度
    var mPaintWidth;

    //字体变量
    var mPaintFont1;
    var mPaintFont2;
    var mPaintFont3;
    var mPaintFont4;
    var mPaintFont5;
    var mPaintFont6;

    return {
        init: function (options) {
            the = this;
            mOptions = $.extend(true, {
                src: "",
                datas: [],
                paddingLeft:10,
                paddingTop:10,
                paddingRight:10,
                paddingBottom:10,
                mPaintWidth:1,
            }, options);
            canvas = mOptions.src;
            datas = mOptions.datas;

            //画布是否存在
            // if(isNullOrEmpty(canvas)){
            //     console.log("画布不存在");
            //     dialog.warn("画布不存在");
            //     return ;
            // }
            //测量尺寸数据
            onMesureSize();
            //初始化画笔
            initmPaint();
            //开始绘制
            onDraw();
        },
        setmPaintWidth:function (mPaintWidth) {
            if(!isNullOrEmpty(Templates)){
                console.log("类不存在");
                dialog.warn("类不存在");
            }
            Templates.mPaintWidth =mPaintWidth;
        },
        getmPaintWidth:function () {
            return mPaintWidth;
        }
    };


    /** *@Description 初始化画笔
     *  *@Params
     *  *@return
     *  *@Author chenzhicai
     *  *@Date
     *  *@Time **/
    function initmPaint() {
        //初始化画笔
        mPaint = canvas.getContext("2d");
        mPaint.lineWidth = mPaintWidth;
        mPaint.textAlign="center";
        mPaint.textBaseline="middle";

        if(400<=parentWidth<600){
            mPaintFont1="12px 宋体";
            mPaintFont2="6px 宋体";
            mPaintFont3="6px 宋体";
            mPaintFont4="6px 宋体";
            mPaintFont5="6px 宋体";
            mPaintFont6="4px 宋体";
        }
        if(600<=parentWidth<700){
            mPaintFont1="12px 宋体";
            mPaintFont2="10px 宋体";
            mPaintFont3="6px 宋体";
            mPaintFont4="8px 宋体";
            mPaintFont5="8px 宋体";
            mPaintFont6="6px 宋体";
        }
        if(parentWidth>=700){
            mPaintFont1="15px 宋体";
            mPaintFont2="12px 宋体";
            mPaintFont3="10px 宋体";
            mPaintFont4="10px 宋体";
            mPaintFont5="12px 宋体";
            mPaintFont6="6px 宋体";
        }
    }

    /** *@Description 测量尺寸数据
     *  *@Params
     *  *@return
     *  *@Author chenzhicai
     *  *@Date
     *  *@Time **/
    function onMesureSize(){
        // if(isNullOrEmpty(canvas)){
        //     console.log("画布不存在");
        //     dialog.warn("画布不存在");
        //     return ;
        // }
        //父容器宽高
        parentWidth = canvas.offsetWidth;
        parentHeight = canvas.offsetHeight;


        //内容器宽高
        contentWidth =   numeral(parentWidth - paddingLeft - paddingRight).value();
        contentHeight = numeral(parentHeight - paddingTop - paddingBottom).value();

        //top的高度
        topHeight = numeral(contentHeight).divide(38).value();
        topWidth = contentWidth / 8;


        //中间区域高度
        centerHeight = topHeight*3/5*2;
        centetrWidth = topWidth / 6;

        mPaintWidth = mOptions.mPaintWidth;
        paddingLeft = mOptions.paddingLeft;
        paddingTop = mOptions.paddingTop;
        paddingRight = mOptions.paddingRight;
        paddingBottom = mOptions.paddingBottom;
    }


    /** *@Description 绘制方法
     *  *@Params
     *  *@return
     *  *@Author chenzhicai
     *  *@Date
     *  *@Time **/
    function onDraw() {
        mPaint.strokeStyle = "black";
        //绘制外边边框
        mPaint.strokeRect(paddingLeft, paddingTop, contentWidth, contentHeight);

        //开始绘制头部区域
        for (var i = 1; i < 5; i++) {
            mPaint.beginPath();
            mPaint.moveTo(paddingLeft - (mPaintWidth / 2), paddingTop + i * topHeight - (mPaintWidth / 2));
            mPaint.lineTo(paddingLeft + contentWidth, paddingTop+ i * topHeight - (mPaintWidth / 2));
            mPaint.stroke();
            mPaint.font = mPaintFont1;
            switch (i) {
                case 1:
                    mPaint.strokeText("日期", paddingLeft- (mPaintWidth / 2) + topWidth/2, paddingTop+ (2*i -1)/2 * (topHeight  - (mPaintWidth / 2)));
                    break;
                case 2:
                    mPaint.strokeText("住院天数", paddingLeft- (mPaintWidth / 2) + topWidth/2, paddingTop+(2*i -1)/2 * (topHeight  - (mPaintWidth / 2)));
                    break;
                case 3:
                    mPaint.strokeText("术后天数", paddingLeft- (mPaintWidth / 2) + topWidth/2, paddingTop+(2*i -1)/2 * (topHeight   - (mPaintWidth / 2)));
                    break;
                case 4:
                    mPaint.strokeText("时间", paddingLeft- (mPaintWidth / 2) + topWidth/2, paddingTop+(2*i -1)/2 * (topHeight  - (mPaintWidth / 2)));
                    break;
            }

        }

        //开始绘制时间的表格内容
        mPaint.strokeStyle = "black";
        mPaint.beginPath();
        mPaint.moveTo(paddingLeft - (mPaintWidth / 2) + topWidth,paddingTop+ (7 / 2) * topHeight  - (mPaintWidth / 2));
        mPaint.lineTo(contentWidth+paddingRight, paddingTop + (7 / 2) * topHeight  - (mPaintWidth / 2));
        mPaint.stroke();

        //开始绘制中间区域
        mPaint.strokeStyle = "black";
        for (var i = 1; i < 43; i++) {

            if (i % 6 == 0) {
                continue;
            }

            if (i % 3 == 0) {
                mPaint.moveTo(paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * i,paddingTop + 3 * topHeight  - (mPaintWidth / 2));
                mPaint.lineTo(paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * i, paddingTop + contentHeight);
                mPaint.stroke();
                continue;
            }

            mPaint.moveTo(paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * i, paddingTop+ 4 * topHeight  - (mPaintWidth / 2));
            mPaint.lineTo(paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * i, paddingTop+ contentHeight);
            mPaint.stroke();
        }
        mPaint.font = mPaintFont2;

        //开始绘制中间文字
        for (var i = 1; i < 15; i++) {
            if(i %2 == 0){
                mPaint.strokeText("下午", paddingLeft - (mPaintWidth / 2) + topWidth + (topWidth/2) * (i - 1 + 1/2), paddingTop + (13 / 4) * topHeight - (mPaintWidth / 2));
                continue;
            }
            mPaint.strokeText("上午", paddingLeft - (mPaintWidth / 2) + topWidth + (topWidth/2) * (i - 1+ 1/2), paddingTop + (13 / 4) * topHeight - (mPaintWidth / 2));
        }

        mPaint.font = mPaintFont3;
        for (var i = 1; i < 43; i++) {
            if (i % 6 == 0) {
                mPaint.strokeText("22", paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * (i - 1+ 1/2), paddingTop+ (15 / 4) * topHeight - (mPaintWidth / 2));
                continue;
            }
            if(i % 5 == 0){
                mPaint.strokeText("18", paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * (i - 1+ 1/2), paddingTop+ (15 / 4) * topHeight - (mPaintWidth / 2));
                continue;
            }
            if(i % 4 == 0){
                mPaint.strokeText("14", paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * (i - 1+ 1/2), paddingTop+ (15 / 4) * topHeight - (mPaintWidth / 2));
                continue;
            }
            if(i % 3 == 0){
                mPaint.strokeText("10", paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * (i - 1+ 1/2), paddingTop+ (15 / 4) * topHeight - (mPaintWidth / 2));
                continue;
            }
            if(i % 2 == 0){
                mPaint.strokeText("6", paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * (i - 1+ 1/2), paddingTop+ (15 / 4) * topHeight - (mPaintWidth / 2));
                continue;
            }
            mPaint.strokeText("2", paddingLeft - (mPaintWidth / 2) + topWidth + centetrWidth * (i -1+ 1/2), paddingTop+ (15 / 4) * topHeight - (mPaintWidth / 2));

        }

        //绘制中间纵线
        for(var i = 1;i<46;i++){
            mPaint.strokeStyle = "black";
            mPaint.beginPath();
            mPaint.moveTo(paddingLeft - (mPaintWidth / 2) + topWidth , paddingTop + 4*topHeight + i/2 *centerHeight  - (mPaintWidth / 2));
            mPaint.lineTo(contentWidth+paddingRight,paddingTop +  4*topHeight + i/2 *centerHeight  - (mPaintWidth / 2));
            mPaint.stroke();
        }

        //开始绘制中间区域左边文字和横线
        mPaint.font = mPaintFont4;
        mPaint.strokeText("脉搏", paddingLeft - (mPaintWidth / 2) + topWidth/4  ,4*topHeight + 3/4*centerHeight - (mPaintWidth / 2));
        mPaint.strokeText("摄氏", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + 3/4*centerHeight - (mPaintWidth / 2));
        mPaint.moveTo(paddingLeft - (mPaintWidth / 2) + topWidth /2 , 4 * topHeight + paddingTop - (mPaintWidth / 2));
        mPaint.lineTo(paddingLeft - (mPaintWidth / 2) + topWidth/2, 4*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
        mPaint.stroke();

        //开始绘制中间左边区域的刻度
        for(var i = 1;i<46;i++){
            mPaint.font = mPaintFont5;
            if(i == 3){
                mPaint.strokeText("180", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("42", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
            if(i == 8){
                mPaint.strokeText("160", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("41", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
            if(i == 13){
                mPaint.strokeText("140", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("40", paddingLeft - (mPaintWidth / 2) + topWidth/4*3, 4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
            if(i == 18){
                mPaint.strokeText("120", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("39", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
            if(i == 23){
                mPaint.strokeText("100", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("38", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
            if(i == 28){
                mPaint.strokeText("80", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight+ paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("37", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
            if(i == 33){
                mPaint.strokeText("60", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight+ paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("36", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
            if(i == 38){
                mPaint.strokeText("40", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight+ paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("35", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
            if(i == 43){
                mPaint.strokeText("20", paddingLeft - (mPaintWidth / 2) + topWidth/4  , 4*topHeight + i/2 *centerHeight+ paddingTop - (mPaintWidth / 2));
                mPaint.strokeText("34", paddingLeft - (mPaintWidth / 2) + topWidth/4*3,  4*topHeight + i/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            }
        }

        //开始绘制纵线
        for (var i = 1; i < 8; i++) {
            mPaint.beginPath();
            if (i < 2) {
                mPaint.strokeStyle = "black";
            } else {
                mPaint.strokeStyle = "red";
            }
            mPaint.moveTo(i * topWidth + paddingLeft - (mPaintWidth / 2), paddingTop + (mPaintWidth / 2));
            mPaint.lineTo(i * topWidth + paddingLeft - (mPaintWidth / 2), contentHeight+paddingBottom);
            mPaint.stroke();
        }

        //开始绘制底部区域
        for (var i = 0 ; i<8 ; i++){
            mPaint.beginPath();
            mPaint.strokeStyle = "black";
            mPaint.moveTo( paddingLeft - (mPaintWidth / 2),  (4+i)*topHeight + 45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            mPaint.lineTo(contentWidth+paddingRight,   (4+i)*topHeight + 45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
            mPaint.stroke();
            switch (i) {
                case 1:
                    mPaint.strokeText("呼吸次数(次/分)", paddingLeft- (mPaintWidth / 2) + topWidth/2, (4+(2*i -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                    break;
                case 2:
                    mPaint.strokeText("血压mmHg", paddingLeft- (mPaintWidth / 2) + topWidth/2,(4+(2*i -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                    break;
                case 3:
                    mPaint.strokeText("体重 Kg", paddingLeft- (mPaintWidth / 2) + topWidth/2,(4+(2*i - 1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                    break;
                case 4:
                    mPaint.strokeText("吸氧流量 ml", paddingLeft- (mPaintWidth / 2) + topWidth/2,(4+(2*i -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                    break;
                case 5:
                    mPaint.strokeText("入量 ml", paddingLeft- (mPaintWidth / 2) + topWidth/2,(4+(2*i -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                    break;
                case 6:
                    mPaint.strokeText("出量 ml", paddingLeft- (mPaintWidth / 2) + topWidth/2,(4+(2*i -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                    break;
                case 7:
                    mPaint.strokeText("血氧饱和度 ml", paddingLeft- (mPaintWidth / 2) + topWidth/2,(4+(2*i -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
                    console.log("endH is"+((4+(2*i -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2)));
                    break;
            }
        }

        //绘制圆点和连线
        var temperatureArr=datas.temperatureArr;
        var tLength=temperatureArr.length+1
        for(var i = 1;i < tLength;i++){
            mPaint.beginPath();
            mPaint.strokeStyle = "black";
            mPaint.fillStyle="black";
            //温度在33.6-42.6
            var y1=(temperatureArr[i-1]-33.6)/(42.6-33.6)*45;
            var y2=(temperatureArr[i]-33.6)/(42.6-33.6)*45;
            var y=(45-y1) * (centerHeight/2) + topHeight * 4 + paddingTop ;
            mPaint.arc((topWidth + centetrWidth * i) + 1 , y ,topWidth / 24 , 0 , 2 * Math.PI );

            mPaint.moveTo((topWidth + centetrWidth * i)+1,y);
            mPaint.lineTo((topWidth + centetrWidth * (i+1))+1,(45-y2) * (centerHeight/2) + topHeight * 4 + paddingTop);
            mPaint.stroke();

            mPaint.fill();
        }

        //绘制日期，住院天数，术后天数
        var dataDay=datas.dataDay;
        var hospitalDay=datas.hospitalDay;
        var operativeDay=datas.operativeDay;
        var dLength=dataDay.length+1;
        var hospitalDayL=hospitalDay.length+1;
        var operativeDayL=operativeDay.length+1;
        for(var i = 1;i < dLength;i++){
            mPaint.font = mPaintFont1;
            mPaint.strokeText(dataDay[i-1], paddingLeft- (mPaintWidth / 2) + topWidth/2 + topWidth*i, paddingTop+ (2*1 -1)/2 * (topHeight  - (mPaintWidth / 2)));
        }
        for(var i = 1;i < hospitalDay.length+1;i++){
            mPaint.font = mPaintFont1;
            mPaint.strokeText(hospitalDay[i-1], paddingLeft- (mPaintWidth / 2) + topWidth/2 + topWidth*i, paddingTop+ (2*2 -1)/2 * (topHeight  - (mPaintWidth / 2)));
        }
        for(var i = 1;i < operativeDay.length+1;i++){
            mPaint.font = mPaintFont1;
            mPaint.strokeText(operativeDay[i-1], paddingLeft- (mPaintWidth / 2) + topWidth/2 + topWidth*i, paddingTop+ (2*3 -1)/2 * (topHeight  - (mPaintWidth / 2)));
        }

        //绘制底部呼吸次数，血压，体重，大便次数，入量，出量，尿量
        var breathing=datas.breathing;
        var blood=datas.blood;
        var weight=datas.weight;
        var number=datas.number;
        var inAmount=datas.inAmount;
        var outAmount=datas.outAmount;
        var output=datas.output;
        var bLength=breathing.length+1;

        for(var i = 1;i < bLength;i++){
            mPaint.font = mPaintFont3;
            mPaint.strokeText(breathing[i-1], paddingLeft- (mPaintWidth / 2) + topWidth +centetrWidth*i- centetrWidth/2 , (4+(2*1 -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
        }
        for(var i = 1;i < blood.length+1;i++) {
            mPaint.font = mPaintFont6;
            mPaint.strokeText(blood[i-1], paddingLeft- (mPaintWidth / 2) + topWidth +centetrWidth*i- centetrWidth/2 , (4+(2*2 -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));

        }
        for(var i = 1;i < weight.length+1;i++) {
            mPaint.font = mPaintFont3;
            mPaint.strokeText(weight[i-1], paddingLeft- (mPaintWidth / 2) + topWidth +centetrWidth*i- centetrWidth/2 , (4+(2*3 -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));
        }
        for(var i = 1;i < number.length+1;i++) {
            mPaint.font = mPaintFont3;
            mPaint.strokeText(number[i-1], paddingLeft- (mPaintWidth / 2) + topWidth +centetrWidth*i- centetrWidth/2 , (4+(2*4 -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));

        }
        for(var i = 1;i < inAmount.length+1;i++) {
            mPaint.font = mPaintFont3;
            mPaint.strokeText(inAmount[i-1], paddingLeft- (mPaintWidth / 2) + topWidth +centetrWidth*i- centetrWidth/2 , (4+(2*5 -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));

        }
        for(var i = 1;i < outAmount.length+1;i++) {
            mPaint.font = mPaintFont3;
            mPaint.strokeText(outAmount[i-1], paddingLeft- (mPaintWidth / 2) + topWidth +centetrWidth*i- centetrWidth/2 , (4+(2*6 -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));

        }
        for(var i = 1;i < output.length+1;i++) {
            mPaint.font = mPaintFont3;
            mPaint.strokeText(output[i-1], paddingLeft- (mPaintWidth / 2) + topWidth +centetrWidth*i- centetrWidth/2 , (4+(2*7 -1)/2)*topHeight +  45/2 *centerHeight + paddingTop - (mPaintWidth / 2));

        }
    }
}