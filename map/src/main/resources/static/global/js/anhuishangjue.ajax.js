$(function(){
    jQuery.kcppcajax = function(url, data, successfn, async, type, dataType, errorfn) {
        async = (async==null || async=="" || typeof(async)=="undefined")? "true" : async;
        type = (type==null || type=="" || typeof(type)=="undefined")? "post" : type;
        dataType = (dataType==null || dataType=="" || typeof(dataType)=="undefined")? "json" : dataType;
        data = (data==null || data=="" || typeof(data)=="undefined")? {"date": new Date().getTime()} : data;
        layer.load();
        $.ajax({
            type: type,
            async: async,
            data: data,
            url: url,
            dataType: dataType,
            success: function(d){
                //在这里绘制加载动画
                successfn(d);
            },
            error: function(data){
                //在这里绘制加载动画
                if (typeof errorfn === "function") {
                    errorfn(data);
                    return;
                }
                if (data.responseText=='logout') {
                    var toastHTML = '<span>您尚未登录或登录时间过长,请重新登录!</span><button class="btn-flat toast-action" onclick="toLoginLayout()">确定</button>';
                    Materialize.toast( toastHTML);
                } else
                    if (data.responseText=='nopemission') {
                        var toastHTML = '<span>您没有足够的权限执行该操作!</span><button class="btn-flat toast-action" onclick="toLoginLayout()">确定</button>';
                        Materialize.toast( toastHTML);

                } else {
                        Materialize.toast('<span>发生异常，请联系管理员!</span>');
                }
            }
        });
    };
});

function toLoginLayout(){
    parent.window.location.href = "/backend/login.html";
    return;
}

function isNull( str ){
    if ( str == "" ) return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}
function Toast( message){
    if(!isNull(message)){
        Materialize.toast({html: message})
    }
}
function ToastWithClasses(message ,classes){
    if(!isNull(message)){
        Materialize.toast({html: message, classes: classes});
    }
}
