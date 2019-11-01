var sweetLayer = {
    //错误提示框
    error: function (message) {
        swal({
            title: message,
            icon: 'error'
        })
    },
    // 确认弹出层
    confirm: function (message, confirmCallback) {
        swal({
            title: "确定吗？",
            text: message,
            icon: "warning",
            buttons: {
                cancel: '取消!',
                delete: '确定!'
            },
            dangerMode: true
        }).then(function (willDelete) {
            if (willDelete) {
                if (confirmCallback != null && typeof confirmCallback === "function" ) {
                    confirmCallback();
                }
                swal.close();
            } else {

            }
        });
    },
}