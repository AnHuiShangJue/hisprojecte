$(document).ready(function () {
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
        aftershow: function() {} //Function for after opening timepicker
    });
});