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
                // if (data.responseText == 'logout') {
                //     dialog.confirm("您尚未登录或登录时间过长,请重新登录!",function () {
                //         toLoginLayout();
                //     });
                //
                // } else if (data.responseText == 'nopemission') {
                //     dialog.confirm("您没有足够的权限执行该操作",function () {
                //         toLoginLayout();
                //     });
                // }
                // else if (data.responseText == 'exception') {
                //     dialog.warn("发生异常，请联系管理员!");
                //     // M.toast({html: "<span>发生异常，请联系管理员!</span>"});
                // }
                dialog.warn("发生异常，请联系管理员!");

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
