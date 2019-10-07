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
                theads: [],
                datas: [],
                haveCheck: true,
            }, options);
            table = tableOptions.src;
            datas = tableOptions.datas;
            draw();
        },
        getTableDatas:function(){
            return datas;
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
        gernumbers: function () {
            numbers = [];
            var i = 0;
            $('tbody > tr > td  > input[name="enterCountPlan"]', table).each(function () {
                numbers[i] = $(this).val();
                i++;
            });
            return numbers;
        },
        gernums: function () {
            nums = [];
            var i = 0;
            $('tbody > tr > td  > input[name="num"]', table).each(function () {
                nums[i] = $(this).val();
                i++;
            });
            return nums;
        },
        getNums: function () {
            getNums = [];
            var i = 0;
            $('tbody > tr > td  > input[name="hisProjectNum"]', table).each(function () {
                getNums[i] = $(this).val();
                i++;
            });
            return getNums;
        },

        hisProjectOrderNums: function () {
            hisProjectOrderNums = [];
            var i = 0;
            $('tbody > tr > td  > input[name="hisProjectOrderNum"]', table).each(function () {
                hisProjectOrderNums[i] = $(this).val();
                i++;
            });
            return hisProjectOrderNums;
        },

        getPrices: function () {
            prices = [];
            var i = 0;
            $('tbody > tr > td  > input[name="unitPrice"]', table).each(function () {
                prices[i] = $(this).val();
                i++;
            });
            return prices;
        },

        getDescription: function () {
            descriptions = [];
            var i = 0;
            $('tbody > tr > td  > input[name="description"]', table).each(function () {
                descriptions[i] = $(this).val();
                i++;
            });
            return descriptions;
        },
        getDosages: function () {
            dosages = [];
            var i = 0;
            $('tbody > tr > td  > input[name="dosage"]', table).each(function () {
                dosages[i] = $(this).val();
                i++;
            });
            return dosages;
        },

        getRemarks: function () {
            remark = [];
            var i = 0;
            $('tbody > tr > td  > input[name="remarks"]', table).each(function () {
                remark[i] = $(this).val();
                i++;
            });
            return remark;
        },

        getvalidityPeriods: function () {
            validityPeriods = [];
            var i = 0;
            $('tbody > tr > td  > input[name="validityPeriod"]', table).each(function () {
                validityPeriods[i] = $(this).val();
                i++;
            });
            return validityPeriods;
        },
        getReason: function () {
            reasons = [];
            var i = 0;
            $('tbody > tr > td  > input[name="reason"]', table).each(function () {
                reasons[i] = $(this).val();
                i++;
            });
            return reasons;
        },
        getDestoryType: function () {
            destoryTypes = [];
            var i = 0;
            $('tbody > tr > td  > input[name="destoryType"]', table).each(function () {
                destoryTypes[i] = $(this).val();
                i++;
            });
            return destoryTypes;
        },
        getSingledose:function () {
            singledoses = [];
            var i = 0;
            $('tbody > tr > td  > input[name="singledose"]', table).each(function () {
                singledoses[i] = $(this).val();
                i++;
            });
            return singledoses;
        },
        getCount:function () {
            counts = [];
            var i = 0;
            $('tbody > tr > td  > input[name="singledose"]', table).each(function () {
                counts[i] = $(this).val();
                i++;
            });
            return counts;
        },
        getAlreadyout:function () {
            alreadyouts = [];
            var i = 0;
            $('tbody > tr > td  > input[name="singledose"]', table).each(function () {
                alreadyouts[i] = $(this).val();
                i++;
            });
            return alreadyouts;
        },
        getSingleunit:function (){
            singleunits = [];
            var i = 0;
            $('tbody > tr > td  > input[name="singleunit"]', table).each(function () {
                singleunits[i] = $(this).val();
                i++;
            });
            return singleunits;
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
        if (tableOptions.datas !== null && tableOptions.datas.length !== 0 && tableOptions.datas !== undefined
            && null !== tableOptions.datas[0].id && undefined !== tableOptions.datas[0].id && '' !== tableOptions.datas[0].id) {
            for (var i = 0; i < tableOptions.datas.length; i++) {
                var data = tableOptions.datas[i];
                tableHtml = startHandleData(tableHtml,data);
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
        tableHtml = startHandleData(tableHtml,data);
        tmp.append(tableHtml);
        if (tableOptions.haveCheck) {
            initCheckBox();
        }
        datas.push(data);
    }

    function addRowsAndDraw(data) {
        var tableHtml = "";
        var tableId = table.attr("id");
        var tmp = $("#" + tableId + " tbody");
        for (var i = 0; i < data.length; i++) {
            var tmp1 = data[i];
            tableHtml = startHandleData(tableHtml,tmp1);
            datas.push(tmp1);
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
        removeArrayWithIndex(index);
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
        if ($(".datepicker").length !== 0) {
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
    //处理绘制
    function startHandleData(tableHtml,data) {
        if (undefined !== data && '' !== data && null != data) {
            tableHtml += "<tr> ";
            if (tableOptions.haveCheck) {
                tableHtml += "<td><label><input type='checkbox' name='id' value='" + data.id + "'><span></span></label></lable></td>";
            }
            for (var j = 0; j < tableOptions.theads.length; j++) {
                var header = tableOptions.theads[j];
                if (header.hasOwnProperty("data")) {
                    if (undefined !== data[header.data] && '' !== data[header.data] && null != data[header.data]) {
                        tableHtml += "<td";
                        if (header.hasOwnProperty("width") && undefined !== header.width && '' !== header.width && null != header.width) {
                            tableHtml += " style='width:" + header.width + ";'";
                        }
                        if(undefined !== data[header.data] && '' !== data[header.data] && null != data[header.data] && "null" != data[header.data]){
                            tableHtml += ">"+data[header.data] + "</td>";
                        }else{
                            tableHtml += "> </td>";

                        }
                    } else {
                        tableHtml += "<td ";
                        if (header.hasOwnProperty("width") && undefined !== header.width && '' !== header.width && null != header.width) {
                            tableHtml += " style='width:" + header.width + ";'";
                        }
                        tableHtml +=">"+ "</td>";
                    }
                } else if (header.hasOwnProperty("render")) {
                    tableHtml += "<td ";
                    if (header.hasOwnProperty("className") && undefined !== header.className && '' !== header.className && null != header.className) {
                        tableHtml += "class='" + header.className + "'";
                    }
                    if (header.hasOwnProperty("width") && undefined !== header.width && '' !== header.width && null != header.width) {
                        tableHtml += " style='width:" + header.width + ";'";
                    }
                    if (header.hasOwnProperty("data") && undefined !== header.data && '' !== header.data && null != header.data) {
                        tableHtml += " name='" + header.data + "'";
                    }
                    tableHtml += ">" + header.render(data) + "</td>";
                }
            }

            tableHtml += "</tr>";
        }
        return tableHtml;
    }

    //处理数组删除
    function removeArrayWithIndex(Index) {
        for(var i = 0 ; i < datas.length;i++){
            if(datas[i].id == Index){
                datas.splice(i,1);
                return i;
            }
        }
    }
};