function login() {
    $.ajax({
        url: context + "oauth/loginAction",
        data:{username:$("#username").val(),password:$("#password").val()},
        success:function(result){
            $("#t1").val(result.data.accessToken);
            $("#t2").val(result.data.refreshToken);
            $("#t3").val(result.data.jti);
        }
    });
}

function refreshToken() {
    $.ajax({
        url: context + "oauth/refreshToken",
        data:{refreshToken:$("#t2").val()},
        success:function(result){
            $("#t1").val(result.data.accessToken);
            $("#t2").val(result.data.refreshToken);
            $("#t3").val(result.data.jti);
        }
    });
}

function checkToken() {
    $.ajax({
        url: context + "oauth/check_token?token="+$("#t1").val(),
        success:function(result){
            $("#t4").val(JSON.stringify(result));

        }
    });
}

function getApi() {
    $.ajax({
        url: context + "oauth/getApi?accressToken="+$("#t1").val(),
        success:function(result){
            $("#t5").val(JSON.stringify(result));
        },
        error:function (result) {
            $("#t5").val(JSON.stringify(result));
        }
    });
}