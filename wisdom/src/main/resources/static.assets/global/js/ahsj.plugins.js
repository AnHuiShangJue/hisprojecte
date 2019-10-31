$(document).ready(function () {
    $("input[type='number']").bind("input propertychange",function () {
        numberCheck()
    });

    $(".datepicker").datepicker({
        autoclose: true,
        format: "yyyy-mm-dd",
        formatSubmit: 'yyyy-mm-dd',
        i18n: {
            cancel: '取消',
            clear: '清除',
            done: '确认',
            previousMonth: '‹',
            nextMonth: '›',
            months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            monthsShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            weekdays: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            weekdaysShort: ['日', '一', '二', '三', '四', '五', '六'],
            weekdaysAbbrev: ['日', '一', '二', '三', '四', '五', '六']
        }
    });

    $(".timepicker").timepicker({
        default: "now", // Set default time
        fromnow: 0, // set default time to * milliseconds from now (using with default = 'now')
        twelvehour: false, // Use AM/PM or 24-hour format
        donetext: "确认", // text for done-button
        cleartext: "清除", // text for clear-button
        canceltext: "取消", // Text for cancel-button
        autoclose: false, // automatic close timepicker
        ampmclickable: true, // make AM PM clickable
        aftershow: function () {
        } //Function for after opening timepicker
    });

    (function ($) {
        $.fn.serializeJson = function () {
            var serializeObj = {};
            var array = this.serializeArray();
            var str = this.serialize();
            $(array).each(function () {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [serializeObj[this.name], this.value];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
            return serializeObj;
        };
    })(jQuery);

    numberCheck();
    initTextareaAutoResize();

    changeLabel();

    $(document).ready(function(){
        $('input.autocomplete').autocomplete({
            data: {
                "发热，": null,
                "头痛，": null,
                "咽痛，": null,
                "鼻塞，": null,
                "流涕，": null,
                "咳嗽，": null,
                "呼吸困难，": null,
                "腹痛，": null,
                "腹胀，": null,
                "恶心，": null,
                "呕吐，": null,
                "反酸，": null,
                "便秘，": null,
                "胸痛，": null,
                "心悸，": null,
                "腰背痛，": null,
                "关节痛，": null,
                "尿频，": null,
                "尿急，": null,
                "尿痛，": null,
                "尿多，": null,
                "排尿困难，": null,
                "水肿，": null,
                "皮疹，": null,
            },
        });
    });

});

// function numberCheck() {
//     var numInput = $("input[type='number']");
//     if(numInput.length >0){
//         numInput.each(function () {
//             var tmp = $(this);
//             if ('' == tmp.attr("min") ) {
//                 tmp.attr("min", 0);
//             }
//
//             if (tmp.val() < 0) {
//                 tmp.attr("value", 0);
//             }
//         });
//     }
// }

function numberCheck() {
    var numInput = $("input[type='number']");
    if(numInput.length >0){
        for(var i=0 ;i<=numInput.length;i++){

            if(!isNullOrEmpty(numInput[i].min)){
                numInput[i].min=0;
            }
            // if(numInput[i].min==""){
            //numInput[i].min=0;
            // }
            if (numInput[i].value < 0) {
                numInput[i].value=0;
            }
        }
    }
}

function changeInputValue(targeInputId,value) {
    var input = $("#"+targeInputId);
    input.text(value);
    input.attr("value",value);
    input.next().attr("class","active");
    initTextareaAutoResize();
}

// function changeSelectValue(targeSelectId,value){
//     var select = $("#"+targeSelectId);
//     $.each(select,function () {
//         if($(this).val() == value){
//             $(this).attr("select",true);
//         }
//     });
//     initTextareaAutoResize();
// }
function changeSelectValue(targeSelectId,value) {
    var select = $("#"+targeSelectId);
    select.find("option").attr('selected', false);
    select.find('option[value="'+value+'"]').attr('selected', true);
    select.formSelect();
}

function changeLabel() {
    var input=$("input[type='text']");
    if(input.length >0) {
        for(var i=0 ;i<=input.length;i++) {
            if (input[i].value) {
                document.querySelector('label[for="'+input[i].id+'"]').setAttribute("class", "active");
            }
        }
    }
}

function initTextareaAutoResize() {
    var textareas =$('textarea');
    if(0 !== textareas.length ){
        M.textareaAutoResize($('textarea'));
    }
    M.updateTextFields();
}

function isNullOrEmpty(data) {
    if(undefined == data || '' == data || null == data || "undefined" == data || "null" == data) return true;else return false;
}

function strToObj(str) {
    str = str.replace(/&/g, "','");
    str = str.replace(/=/g, "':'");
    str = "({'" + str + "'})";
    obj = eval(str);
    return obj;
}

function handleTextArea(){
    var textareas =$('textarea');
    if(0 !== textareas.length ){
        $.each(textareas,function () {
            var the = $(this);
            var textName = the.data("name");
            var textData = the.val();
            var str;
            if(!isNullOrEmpty(textName)){
                str = "<input type='hidden' name='"+textName+"' value='"+textData+"'/>"
            }
            the.append(str);
            the.attr("name" , "");

        })
    }
}
//缓存ID值 checkBox代表的就是复选框的选中状态  为true代表选中了  为false代表未选中
//缓存ID
 function cacheId(checkBox,id,cacheIds) {
    //若被选中数组中要保存此值
    var check = $(checkBox).is(":checked");
    if(check){
        var index = cacheIds.indexOf(id);
        //-1代表数组中无此值 ，只有无此值时才能添加
        if(index == -1){
            cacheIds.push(id);
        }
    }
    //若未被选中 要判断数组中是否有ID 若有需要移除
    else {
        var index = cacheIds.indexOf(id);
        //不等于-1 说明id在数组中存在
        if(index != -1){
            //splice方法可以向数组中删除，插入，替换任意数量的项
            //删除: 接受两个参数，第一个参数表示要从哪里开始删除，第二个项表示删除的个数 color.splice(0, 2);//从第0个开始删除两个
            cacheIds.splice(index,1);
        }
    }
}

