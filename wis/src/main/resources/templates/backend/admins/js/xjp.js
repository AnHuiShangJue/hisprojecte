var dialog = {
    // 错误弹出层
    error: function (message) {
        layer.open({
            content: message,
            icon: 2,
            title: '错误提示',
        });
    },
    // 错误弹出层
    warn: function (message, callback) {
        // layer.open({
        //     content:message,
        //     icon:0,
        //     title : '警告提示',
        //     yes : function(index){
        //         if (callback != null && typeof callback === "function" ) {
        //             callback();
        //         }
        //         layer.close(index);
        //     }
        // });
        swal({
            title: message,
            icon: 'warning'
        })
    },
    //成功弹出层
    success: function (message, confirmCallback) {
        // layer.open({
        //     content : message,
        //     icon : 1,
        //     yes : function(index){
        //         if (callback != null && typeof callback === "function" ) {
        //             callback();
        //         }
        //         layer.close(index);
        //     }
        // });
        swal({
            title: "操作成功！",
            text: message,
            icon: "success",
            buttons: {
                delete: '确定!'
            },
            dangerMode: true
        }).then(function (willDelete) {
            if (willDelete) {
                if (confirmCallback != null && typeof confirmCallback === "function") {
                    confirmCallback();
                }
                swal.close();
            } else {

            }
        });
    },

    // 确认弹出层
    confirm: function (message, confirmCallback, dangerMode) {
        dangerMode = (dangerMode == null || dangerMode == "" || typeof (dangerMode) == "undefined") ? true : dangerMode;
        swal({
            title: "确定吗？",
            text: message,
            icon: "warning",
            buttons: {
                cancel: '取消!',
                delete: '确定!'
            },
            dangerMode: dangerMode
        }).then(function (willDelete) {
            if (willDelete) {
                if (confirmCallback != null && typeof confirmCallback === "function") {
                    confirmCallback();
                }
                swal.close();
            } else {

            }
        });
        // layer.open({
        //     content : message,
        //     icon:3,
        //     title:"确认",
        //     btn : ['是','否'],
        //     yes : function(index){
        //         if (callback != null && typeof callback === "function" ) {
        //             callback();
        //         }
        //         layer.close(index);
        //     },
        // });
    },

    //无需跳转到指定页面的确认弹出层
    toconfirm: function (message) {
        layer.open({
            content: message,
            icon: 3,
            btn: ['确定'],
        });
    },
    show: function (url, name, okBtn, onlyClose, callback) {
        var index;
        if (okBtn) {
            if (onlyClose) {
                index = layer.open({
                    type: 2,
                    fixed: true,
                    title: name,
                    shadeClose: true,
                    maxmin: false,
                    area: ["98%", "96%"],
                    content: url,
                    btn: ['关闭']
                });
            } else {
                index = layer.open({
                    type: 2,
                    fixed: true,
                    title: name,
                    shadeClose: true,
                    maxmin: false,
                    area: ["98%", "96%"],
                    content: url,
                    btn: ['确定', '关闭'],
                    yes: function (index, layero) {
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        if (iframeWin && iframeWin != null) {
                            iframeWin.doOk();
                        }
                    }
                });
            }
        } else {
            if (callback != null && typeof callback === "function") {
                callback();
                index = layer.open({
                    type: 2,
                    fixed: true,
                    title: name,
                    shadeClose: true,
                    maxmin: false,
                    area: ["98%", "98%"],
                    content: url,
                    success: callback()
                });
            } else {
                index = layer.open({
                    type: 2,
                    fixed: true,
                    title: name,
                    shadeClose: true,
                    maxmin: false,
                    area: ["98%", "98%"],
                    content: url
                });

            }
        }
        layer.full(index);
    },
    showDialog: function (url, name, width, height, okCallback) {
        layer.open({
            type: 2,
            fixed: true,
            title: name,
            shadeClose: true,
            maxmin: false,
            area: [width, height],
            content: url,
            btn: ['确定', '关闭'],
            yes: function (index, layero) {
                if (okCallback != null && typeof okCallback === "function") {
                    okCallback();
                }
                var iframeWin = window[layero.find('iframe')[0]['name']];
                if (iframeWin && iframeWin != null) {
                    iframeWin.doOk();
                }
            }

        });
    },
    showDialogNoBtn: function (url, name, width, height) {
        layer.open({
            type: 2,
            fixed: true,
            title: name,
            shadeClose: true,
            maxmin: false,
            area: [width, height],
            content: url
        });
    },
    close: function (isRefresh) {
        if (isRefresh) {
            parent.location.reload(); // 父页面刷新
        }
        layer.close(layer.index);
    },
    parentclose: function (isRefresh) {
        if (isRefresh) {
            parent.location.reload(); // 父页面刷新
        }
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    },
    parentcloseAll: function (isRefresh) {
        if (isRefresh) {
            parent.location.reload(); // 父页面刷新
        }
        parent.layer.closeAll(parent.layer.getFrameIndex(window.name));
    }
}

$(function () {
    jQuery.ahsjajax = function (url, data, successfn, async, type, dataType, errorfn,contentType) {
        async = (async == null || async == "" || typeof(async) == "undefined") ? "true" : async;
        type = (type == null || type == "" || typeof(type) == "undefined") ? "post" : type;
        contentType = (contentType == null || contentType == "" || typeof(contentType) == "undefined") ? "application/x-www-form-urlencoded; charset=UTF-8" : contentType;
        dataType = (dataType == null || dataType == "" || typeof(dataType) == "undefined") ? "json" : dataType;
        data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
        layer.load();
        $.ajax({
            type: type,
            async: async,
            data: data,
            url: url,
            dataType: dataType,
            headers: {
                "Authorization": "Bearer " + TOKEN
            },
            contentType:contentType,
            success: function (d) {
                //在这里绘制加载动画
                layer.closeAll('loading');
                successfn(d);
            },
            error: function (data) {
                //在这里绘制加载动画
                layer.closeAll('loading');
                if (typeof errorfn === "function") {
                    errorfn(data);
                    return;
                }
                if (data.responseText == 'logout') {
                    dialog.confirm("您尚未登录或登录时间过长,请重新登录!",function () {
                        toLoginLayout();
                    });

                } else if (data.responseText == 'nopemission') {
                    dialog.confirm("您没有足够的权限执行该操作",function () {
                        toLoginLayout();
                    });
                } else if (data.responseText == 'exception') {
                    dialog.warn("发生异常，请联系管理员!");
                    // M.toast({html: "<span>发生异常，请联系管理员!</span>"});
                }
            }
        });
    };

    jQuery.ahsjajaxGet = function (url, data, successfn, async, type, dataType, errorfn,contentType) {
        async = (async == null || async == "" || typeof(async) == "undefined") ? "true" : async;
        type = (type == null || type == "" || typeof(type) == "undefined") ? "get" : type;
        contentType = (contentType == null || contentType == "" || typeof(contentType) == "undefined") ? "application/x-www-form-urlencoded; charset=UTF-8" : contentType;
        dataType = (dataType == null || dataType == "" || typeof(dataType) == "undefined") ? "json" : dataType;
        data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
        layer.load();
        $.ajax({
            type: type,
            async: async,
            data: data,
            url: url,
            dataType: dataType,
            headers: {
                "Authorization": "Bearer " + TOKEN
            },
            contentType:contentType
            /*   success: function (d) {
                   //在这里绘制加载动画
                   layer.closeAll('loading');
                   successfn(d);
               },
               error: function (data) {
                   //在这里绘制加载动画
                   layer.closeAll('loading');
                   if (typeof errorfn === "function") {
                       errorfn(data);
                       return;
                   }
                   if (data.responseText == 'logout') {
                       dialog.confirm("您尚未登录或登录时间过长,请重新登录!",function () {
                           toLoginLayout();
                       });

                   } else if (data.responseText == 'nopemission') {
                       dialog.confirm("您没有足够的权限执行该操作",function () {
                           toLoginLayout();
                       });
                   } else if (data.responseText == 'exception') {
                       dialog.warn("发生异常，请联系管理员!");
                       // M.toast({html: "<span>发生异常，请联系管理员!</span>"});
                   }
               }*/
        });
    };
});

jQuery.ahsjajaxForValidate = function (form,url, data, successfn, async, type, dataType, errorfn) {
    async = (async == null || async == "" || typeof(async) == "undefined") ? "true" : async;
    type = (type == null || type == "" || typeof(type) == "undefined") ? "post" : type;
    dataType = (dataType == null || dataType == "" || typeof(dataType) == "undefined") ? "json" : dataType;
    data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
    if(from == null || typeof(data) == "undefined"){
        M.toast({
            html: "Form 为空！请联系管理员"
        });
        return;
    }
    layer.load();
    $(form).ajaxSubmit({
        type: type,
        async: async,
        data: data,
        url: url,
        dataType: dataType,
        headers: {
            "Authorization": "Bearer " + TOKEN
        },
        success: function (d) {
            //在这里绘制加载动画
            layer.closeAll('loading');
            successfn(d);
        },
        error: function (data) {
            //在这里绘制加载动画
            layer.closeAll('loading');
            if (typeof errorfn === "function") {
                errorfn(data);
                return;
            }
            if (data.responseText == 'logout') {
                var toastHTML = '<span>您尚未登录或登录时间过长,请重新登录!</span><button class="btn-flat toast-action" onclick="toLoginLayout()">确定</button>';
                M.toast({
                    html: toastHTML
                });
            } else if (data.responseText == 'nopemission') {
                var toastHTML = '<span>您没有足够的权限执行该操作!</span><button class="btn-flat toast-action" onclick="toLoginLayout()">确定</button>';
                M.toast({
                    html: toastHTML
                });
            } else {
                M.toast({html: "<span>发生异常，请联系管理员!</span>"});
            }
        }
    });
};


function toLoginLayout() {
    parent.window.location.href = "/accounts/his/back/login/index.ahsj";
    return true;
}

function isNull(str) {
    if (str == "") return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}

function Toast(message) {
    if (!isNull(message)) {
        Materialize.toast({html: message})
    }
}

function ToastWithClasses(message, classes) {
    if (!isNull(message)) {
        Materialize.toast({html: message, classes: classes});
    }
}
