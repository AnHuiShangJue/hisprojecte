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