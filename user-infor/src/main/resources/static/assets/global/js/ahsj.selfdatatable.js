/** *@Description 自定义的表格插件
 *  *@Params
 *  *@return
 *  *@Author chenzhicai
 *  *@Date   2019-6-28
 *  *@Time  8：39
 *  **/
var AHSJDT = function () {

    var tableOptions;
    var theads;
    var the;
    var src;
    var datas;
    var haveCheck;
    var table;
    var ids;

    return {
        init: function (options) {
            the = this;
            tableOptions = $.extend(true, {
                src: "",
                theads: [{}],
                datas: [{}],
                haveCheck: true,
            }, options);
            table = $(options.src);
            draw();
        },
        addRow: function (data) {
            addRowAndDraw(data);
        },
        removeRow: function (index) {
            removeRowAndDraw(index);
        },
        getSelectedRows: function () {
            var rows = [];
            $('tbody > tr > td > label > input[type="checkbox"]:checked', table).each(function () {
                rows.push($(this).val());
            });

            return rows;
        },
        getSelectedRowsCount: function () {
            return $('tbody > tr > td > label > input[type="checkbox"]:checked', table).size();

        },
        removeRows: function (ids) {
            removeRowsAndDraw(ids);
        },
        addRows: function (datas) {
            addRowsAndDraw(datas);
        },
        getIds: function () {
            ids = [];
            var i = 0;
            $('tbody > tr > td > label > input[type="checkbox"]', table).each(function () {
                ids[i] = $(this).attr("value");
                i++;
            });
            return ids;
        },
        sendData: function (url, succesfn, errorfn) {
            $.ahsjajax(url,
                {
                    ids: getIds(),
                    numbers: gernumbers()
                },
                succesfn(msg),
                errorfn(msg))
        },
        gernumbers:function () {
            numbers = [];
            var i = 0;
            $('tbody > tr > td  > input[name="enterCountPlan"]', table).each(function () {
                numbers[i] = $(this).val();
                i++;
            });
            return numbers;
        },
        getPrices:function () {
            prices=[];
            var i=0;
            $('tbody > tr > td  > input[name="unitPrice"]', table).each(function () {
                prices[i] = $(this).val();
                i++;
            });
            return prices;
        },
        getvalidityPeriods:function () {
            validityPeriods=[];
            var i=0;
            $('tbody > tr > td  > input[name="validityPeriod"]', table).each(function () {
                validityPeriods[i] = $(this).val();
                i++;
            });
            return validityPeriods;
        }
    };

    /** *@Description 绘制
     *  *@Params
     *  *@return
     *  *@Author chenzhicai
     *  *@Date   2019-6-28
     *  *@Time  8：39
     *  **/
    function draw() {
        var tableHtml = "";
        tableHtml += "   <thead>\n" +
            "    <tr>";
        if (tableOptions.haveCheck) {
            tableHtml += "<th><label><input type='checkbox' class='select-all' id='checkAll'><span></span></label></lable></th>";
        }
        for (var i = 0; i < tableOptions.theads.length; i++) {
            var header = tableOptions.theads[i];
            tableHtml += "   <th>" + header.title + "</th>";
        }
        tableHtml += " </tr>\n" +
            "    </thead>";
        tableHtml += "<tbody>";
        if (tableOptions.datas !== null && tableOptions.datas.length !== 0 && tableOptions.datas!== undefined
            && null !== tableOptions.datas[0].id && undefined !== tableOptions.datas[0].id && '' !== tableOptions.datas[0].id) {
            for (var i = 0; i < tableOptions.datas.length; i++) {
                var enterConsumable = tableOptions.datas[i];
                tableHtml += "<tr> ";
                if (tableOptions.haveCheck) {
                    tableHtml += "<td><label><input type='checkbox' name='id' value='" + enterConsumable.id + "'><span></span></label></lable></td>";
                }
                for (var j = 0; j < tableOptions.theads.length; j++) {
                    var header = tableOptions.theads[j];
                    if (header.hasOwnProperty("data")) {
                        tableHtml += "<td>" + enterConsumable[header.data] + "</td>";
                    } else if (header.hasOwnProperty("render")) {
                        tableHtml += "<td ";
                        if (header.hasOwnProperty("className")) {
                            tableHtml += "class='" + header.className + "'";
                        }
                        if (header.hasOwnProperty("width")) {
                            tableHtml += " style='width:" + header.width + ";'";
                        }
                        if (header.hasOwnProperty("data")) {
                            tableHtml += " name='" + header.data + "'";
                        }
                        tableHtml += ">" + header.render(enterConsumable) + "</td>";
                    }
                }
                tableHtml += "</tr>";
            }
        }
        tableHtml += "</tbody>";
        table.append(tableHtml);
        if (tableOptions.haveCheck) {
            initCheckBox();
        }
    }

    /** *@Description 添加一行并且绘制
     *  *@Params
     *  *@return
     *  *@Author chenzhicai
     *  *@Date   2019-6-28
     *  *@Time  8：39
     *  **/
    function addRowAndDraw(data) {
        var tableHtml = "";
        var tableId = table.attr("id");
        var tmp = $("#" + tableId + " tbody");
        if (undefined !== data && '' !== data && null != data) {
            tableHtml += "<tr> ";
            if (tableOptions.haveCheck) {
                tableHtml += "<td><label><input name='id'  type='checkbox' value='" + data.id + "'><span></span></label></lable></td>";
            }
            for (var j = 0; j < tableOptions.theads.length; j++) {
                var header = tableOptions.theads[j];
                if (header.hasOwnProperty("data")) {
                    tableHtml += "<td>" + data[header.data] + "</td>";
                } else if (header.hasOwnProperty("render")) {
                    tableHtml += "<td ";
                    if (header.hasOwnProperty("className")) {
                        tableHtml += "class='" + header.className + "'";
                    }
                    if (header.hasOwnProperty("width")) {
                        tableHtml += " style='width:" + header.width + ";'";
                    }
                    if (header.hasOwnProperty("data")) {
                        tableHtml += " name='" + header.data + "'";
                    }
                    tableHtml += ">" + header.render(data) + "</td>";

                }
            }

            tableHtml += "</tr>";
        }
        tmp.append(tableHtml);
        if (tableOptions.haveCheck) {
            initCheckBox();
        }
    }

    function addRowsAndDraw(datas) {
        var tableHtml = "";
        var tableId = table.attr("id");
        var tmp = $("#" + tableId + " tbody");
        for (var i = 0; i < datas.length; i++) {
            var data = datas[i];
            if (undefined !== data && '' !== ids && null != ids) {
                tableHtml += "<tr> ";
                if (tableOptions.haveCheck) {
                    tableHtml += "<td><label><input type='checkbox' name='id' value='" + data.id + "'><span></span></label></lable></td>";
                }
                for (var j = 0; j < tableOptions.theads.length; j++) {
                    var header = tableOptions.theads[j];
                    if (header.hasOwnProperty("data")) {
                        tableHtml += "<td>" + data[header.data] + "</td>";
                    } else if (header.hasOwnProperty("render")) {
                        tableHtml += "<td ";
                        if (header.hasOwnProperty("className")) {
                            tableHtml += "class='" + header.className + "'";
                        }
                        if (header.hasOwnProperty("width")) {
                            tableHtml += " style='width:" + header.width + ";'";
                        }
                        if (header.hasOwnProperty("data")) {
                            tableHtml += " name='" + header.data + "'";
                        }
                        tableHtml += ">" + header.render(data) + "</td>";

                    }
                }

                tableHtml += "</tr>";
            }
        }
        tmp.append(tableHtml);
        if (tableOptions.haveCheck) {
            initCheckBox();
        }
    }

    /** *@Description 移除并且绘制
     *  *@Params
     *  *@return
     *  *@Author chenzhicai
     *  *@Date   2019-6-28
     *  *@Time  8：39
     *  **/
    function removeRowAndDraw(index) {
        // var str = "  tbody > tr > td:first > label > input[type=\"checkbox\"][value="+index+"]";
        // console.log(str);
        // var tmp = $(str,table).parent().parent().parent().parent();
        $('tbody > tr > td > label > input[type="checkbox"]', table).each(function () {
            if ($(this).attr("value") == index) {
                $(this).parent().parent().parent().empty();
            }
        });
        if (tableOptions.haveCheck) {
            initCheckBox();
        }
    }

    function removeRowsAndDraw(indexs) {
        for (var i = 0; i < indexs.length; i++) {
            removeRowAndDraw(indexs[i]);
        }
        var tableId = table.attr("id");
        selectAll = $('#' + tableId + ' #checkAll');
        var checked = selectAll.attr("checked");
        selectAll.attr('checked', !checked);
        selectAll.prop('checked', !checked);
    }

    function initCheckBox() {
        //set Check
        var tableId = table.attr("id");
        selectAll = $('#' + tableId + ' #checkAll');
        var checkbox = $('#' + tableId + ' tbody tr td label input[type=\'checkbox\']');
        selectAll.on('click', function () {
            var checked = $(this).attr("checked");
            $(this).attr('checked', !checked);
            $(this).prop('checked', !checked);
            checkbox.attr('checked', !checked);
            checkbox.prop('checked', !checked);

        });
        checkbox.on('click', function () {
            var checked = $(this).attr("checked");
            if ($(this).attr("checked")) {
                $(this).attr('checked', !checked);
                $(this).prop('checked', !checked);
            } else {
                $(this).attr('checked', !checked);
                $(this).prop('checked', !checked);
            }
        });
        onDrawCallback();
    }

    function gernumbers() {
        numbers = [];
        var i = 0;
        $('tbody > tr > td  > input[type="number"]', table).each(function () {
            numbers[i] = $(this).attr("value");
            i++;
        });
        return numbers;
    }

    function getIds() {
        ids = [];
        var i = 0;
        $('tbody > tr > td > label > input[type="checkbox"]', table).each(function () {
            ids[i] = $(this).val();
            i++;
        });
        return ids;
    }

    function onDrawCallback() {
        if($(".datepicker").length !== 0){
            $(".datepicker").datepicker({
                autoclose: true,
                format: "yyyy/mm/dd",
                formatSubmit: 'yyyy/mm/dd',
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
        }
    }
};