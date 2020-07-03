function login(){
    $.ajax({
        url: "../../login",
        type: 'POST',
        async: true,
        dataType: "json",
        contentType: "application/json",
        //要发送到服务器的数据
        data: {},
        beforeSend: function (XMLHttpRequest) {

        },
        //当请求失败时调用的函数
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        },
        //当请求成功时调用的函数
        success: function (data, textStatus) {
            alert(JSON.stringify(data));

        },
        //当请求完成时调用的函数
        complete: function (XMLHttpRequest, textStatus) {

        }
    });
}

function logout() {
    $.ajax({
        url: "../../logout",
        type: 'POST',
        async: true,
        dataType: "json",
        contentType: "application/json",
        //要发送到服务器的数据
        data: {},
        beforeSend: function (XMLHttpRequest) {

        },
        //当请求失败时调用的函数
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        },
        //当请求成功时调用的函数
        success: function (data, textStatus) {
            alert(JSON.stringify(data));
        },
        //当请求完成时调用的函数
        complete: function (XMLHttpRequest, textStatus) {

        }
    });
}