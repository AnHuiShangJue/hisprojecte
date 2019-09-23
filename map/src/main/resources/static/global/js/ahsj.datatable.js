var Datatable = function () {

    var tableOptions; // main options
    var dataTable; // datatable object
    var table; // actual table jquery object
    var tableContainer; // actual table container object
    var tableWrapper; // actual table wrapper jquery object
    var tableInitialized = false;
    var ajaxParams = {}; // set filter mode
    var the;

    //计算被选中的的记录
    var countSelectedRecords = function () {
        var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        var text = tableOptions.dataTable.language.metronicGroupActions;
        if (selected > 0) {
            // $('.table-group-actions > span', tableWrapper).text(text.replace("_TOTAL_", selected));
        } else {
            $('.table-group-actions > span', tableWrapper).text("");
        }
    };

    return {

        //main function to initiate the module
        init: function (options) {

            if (!$().dataTable) {
                return;
            }

            the = this;

            // default settings
            options = $.extend(true, {
                src: "", // actual table
                resetGroupActionInputOnSuccess: true,
                loadingMessage: 'Loading...',
                dataTable: {
                    // "dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r><'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>", // datatable layout
                    "dom": "<'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
                    "pageLength": 10, // default records per page
                    language: {
                        "lengthMenu": "每页显示 _MENU_ 条记录",
                        "zeroRecords": "没有检索到数据",
                        "infoEmpty": "没有数据",
                        "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                        "emptyTable": "没有数据,请修改查询条件重新查询",
                        "paginate": {
                            "first": "第一页",
                            "last": "最后一页",
                            "previous": "上一页",
                            "next": "下一页"
                        }
                    },

                    // "orderCellsTop": true,
                    // "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                    //     'orderable': false,
                    //    'targets': [0]
                    //}],

                    "pagingType": "bootstrap_full_number", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
                    "autoWidth": false, // disable fixed width and enable fluid table
                    "processing": false, // enable/disable display message box on record load
                    "serverSide": true, // enable/disable server side ajax loading
                    "ordering": true,
                    "ajax": { // define ajax settings
                        "url": "", // ajax URL
                        "type": "POST", // request type
                        "timeout": 20000,
                        "data": function (data) { // add request parameters before submit
                            $.each(ajaxParams, function (key, value) {
                                data[key] = value;
                            });
                            var order = " ";
                            for (var i = 0; i < data.order.length; i++) {
                                var dataCol = data.columns[data.order[i].column].data;
                                var name = data.columns[data.order[i].column].name;
                                if (name != null && name != "") {
                                    order = order + name + " " + data.order[i].dir;
                                } else {
                                    order = order + dataCol + " " + data.order[i].dir;
                                }
                                if (i < data.order.length - 1) {
                                    order = order + " , "
                                }
                            }
                            data["orders"] = order;
                            layer.load();
                        },
                        "dataSrc": function (res) { // Manipulate the data returned from the server

                            if (tableOptions.onSuccess) {
                                tableOptions.onSuccess.call(undefined, the, res);
                            }

                            layer.closeAll('loading');
                            return res.data;
                        },
                        "error": function (data) { // handle general connection errors
                            if (tableOptions.onError) {
                                tableOptions.onError.call(undefined, the);
                            }
                            // if (data.responseText=='logout') {
                            //     dialog.warn("您尚未登录或登录时间过长,请重新登录!",function() {
                            //         parent.window.location.href =ctx + "/view/login.jsp";
                            //         return;
                            //     });
                            // } else if (data.responseText=='nopemission') {
                            //     dialog.warn("您没有足够的权限执行该操作!",function() {
                            //         parent.window.location.href =ctx + "/view/login.jsp";
                            //         return;
                            //     });
                            // } else {
                            //
                            //     dialog.error("发生异常，请联系管理员");
                            // }
                            layer.closeAll('loading');
                        }
                    },

                    "drawCallback": function (oSettings) { // run some code on table redraw
                        if (tableInitialized === false) { // check if table has been initialized
                            tableInitialized = true; // set table initialized
                            table.show(); // display table
                        }

                        countSelectedRecords(); // reset selected records indicator

                        // callback for ajax data load
                        if (tableOptions.onDataLoad) {
                            tableOptions.onDataLoad.call(undefined, the);
                        }
                        $('input').iCheck({
                            checkboxClass: 'icheckbox_flat-orange ',
                            radioClass: 'iradio_flat-orange',
                            handle: 'checkbox'
                        });
                        $('#checkAll').iCheck('uncheck');
                        $('#checkAll', table).on('ifChecked', function (event) {
                            $('.icheck').iCheck('check');
                            countSelectedRecords();
                        });

                        $('#checkAll', table).on('ifUnchecked', function (event) {
                            $('.icheck').iCheck('uncheck');
                            countSelectedRecords();
                        });
                    }
                }
            }, options);

            tableOptions = options;

            // create table's jquery object
            table = $(options.src);
            tableContainer = table.parents(".table-container");

            // apply the special class that used to restyle the default datatable
            var tmp = $.fn.dataTableExt.oStdClasses;

            $.fn.dataTableExt.oStdClasses.sWrapper = $.fn.dataTableExt.oStdClasses.sWrapper;
            $.fn.dataTableExt.oStdClasses.sFilterInput = "form-control input-small input-sm input-inline";
            $.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";

            // initialize a datatable
            dataTable = table.DataTable(options.dataTable);

            // revert back to default
            $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
            $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
            $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

            // get table wrapper
            tableWrapper = table.parents('.dataTables_wrapper');

            // build table group actions panel
            if ($('.table-actions-wrapper', tableContainer).size() === 1) {
                $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html()); // place the panel inside the wrapper
                $('.table-actions-wrapper', tableContainer).remove(); // remove the template container
            }
        },

        search: function () {
            // the.setAjaxParam("action", tableOptions.filterApplyAction);
            var searchForm = $('#searchForm');
            // get all typeable inputs
            $('textarea, select, input:not([type="radio"],[type="checkbox"])', searchForm).each(function () {
                var attrname = $(this).attr("name");
                var attrvalue = $(this).val();
                if (typeof attrname !== "undefined" && attrname !== null && attrname !== ""
                    && attrvalue !== "undefined" && attrvalue !== null && attrvalue !== "") {
                    the.setAjaxParam($(this).attr("name"), $(this).val());
                }
            });

            // get all checkboxes
            $('input.form-filter[type="checkbox"]:checked', searchForm).each(function () {
                the.addAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all radio buttons
            $('input.form-filter[type="radio"]:checked', searchForm).each(function () {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            dataTable.ajax.reload();
        },

        searchPageNotChange: function () {
            // the.setAjaxParam("action", tableOptions.filterApplyAction);
            var searchForm = $('#searchForm');
            // get all typeable inputs
            $('textarea, select, input:not([type="radio"],[type="checkbox"])', searchForm).each(function () {
                var attrname = $(this).attr("name");
                if (typeof attrname !== "undefined" && attrname !== null && attrname !== "") {
                    the.setAjaxParam($(this).attr("name"), $(this).val());
                }
            });

            // get all checkboxes
            $('input.form-filter[type="checkbox"]:checked', searchForm).each(function () {
                the.addAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all radio buttons
            $('input.form-filter[type="radio"]:checked', searchForm).each(function () {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            dataTable.ajax.reload(null, false);
        },

        reset: function (data) {
            var searchForm = $('#searchForm');
            $('textarea, select, input', searchForm).each(function () {
                $(this).val("");
                $(this).select2("val", "");
            });
            $('input[type="checkbox"]', searchForm).each(function () {
                $(this).attr("checked", false);
            });

            the.clearAjaxParams();

            if (data != undefined && data != null && data != "") {
                ajaxParams = data;
            }

            dataTable.ajax.reload();
        },

        getSelectedRowsCount: function () {
            return $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        },

        getSelectedRows: function () {
            var rows = [];
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function () {
                rows.push($(this).val());
            });

            return rows;
        },
        getSelectedRowsAttr: function (attr) {
            var rows = [];
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function () {
                rows.push($(this).attr(attr));
            });

            return rows;
        },

        setAjaxParam: function (name, value) {
            ajaxParams[name] = value;
        },

        addAjaxParam: function (name, value) {
            if (!ajaxParams[name]) {
                ajaxParams[name] = [];
            }

            skip = false;
            for (var i = 0; i < (ajaxParams[name]).length; i++) { // check for duplicates
                if (ajaxParams[name][i] === value) {
                    skip = true;
                }
            }

            if (skip === false) {
                ajaxParams[name].push(value);
            }
        },

        clearAjaxParams: function (name, value) {
            ajaxParams = {};
        },

        getDataTable: function () {
            return dataTable;
        },

        getTableWrapper: function () {
            return tableWrapper;
        },

        gettableContainer: function () {
            return tableContainer;
        },

        getTable: function () {
            return table;
        }

    };

};