/**
 * Created by zhuzhaolin on 2017/12/3.
 */

function updateUsername() {
    var nickname = $("#nickname").val();
    var signature = $("#signature").val();
    $.ajax({
        type: "post",
        url: "profile",
        data: {name: nickname, signature: signature},
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {

            $.each(data, function (idx, item) {
                //输出
                //alert(item.message + "哈哈");
                if (item.code == 0) {
                    $("#updateSuccess").html(item.message).fadeIn(1000);
                    setTimeout(function () {
                        $("#updateSuccess").fadeOut(3000);
                    }, 1000);
                } else {
                    $("#updateFail").show().html(item.message);
                    setTimeout(function () {
                        $("#updateFail").fadeOut(3000);
                    }, 1000);
                }
            });

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(errorThrown);
        }
    });
}

function updatePassword() {
    var oldPassword = $("#oldPassword").val();
    var password = $("#password").val();
    $.ajax({
        type: "post",
        url: "password",
        data: {oldPassword: oldPassword, password: password},
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {

            $.each(data, function (idx, item) {
                //输出
                //alert(item.message + "哈哈");
                if (item.code == 0) {
                    $("#updateSuccess").html(item.message).fadeIn(1000);
                    setTimeout(function () {
                        $("#updateSuccess").fadeOut(3000);
                    }, 1000);
                } else {
                    $("#updateFail").show().html(item.message);
                    setTimeout(function () {
                        $("#updateFail").fadeOut(3000);
                    }, 1000);
                }
            });

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(errorThrown);
        }
    });

}

function updateEmail() {
    var email = $("#newEmail").val();
    $.ajax({
        type: "post",
        url: "email",
        data: {email: email},
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                $("#updateSuccess").html(data.message).fadeIn(1000);
                setTimeout(function () {
                    $("#updateSuccess").fadeOut(3000);
                }, 1000);
            } else {
                $("#updateFail").show().html(data.message);
                setTimeout(function () {
                    $("#updateFail").fadeOut(3000);
                }, 1000);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(errorThrown);
        }
    });

}

function updateAvatar() {
    var x = $("#x").val();
    var y = $("#y").val();
    var width = $("#width").val();
    var height = $("#height").val();
    var file = document.getElementById("upload_btn").files[0];

    //原生ajax实现文件上传
    var form = new FormData();
    form.append("x", x); // 可以增加表单数据
    form.append("y", y);
    form.append("width", width);
    form.append("height", height);
    if (file) {
        form.append("file", file);
    }

    $.ajax({
        type: "post",
        url: "avatar",
        data: form,
        contentType: false,
        mimeType: "multipart/form-data",
        processData: false,
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                $("#updateSuccess").html(data.message).fadeIn(1000);
                setTimeout(function () {
                    $("#updateSuccess").fadeOut(3000);
                }, 1000);
            } else {
                $("#updateFail").show().html(data.message);
                setTimeout(function () {
                    $("#updateFail").fadeOut(3000);
                }, 1000);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(errorThrown);
        }
    });
}

//判断浏览器是否支持FileReader接口
if (typeof FileReader == 'undefined') {
    document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
    //使选择控件不可操作
    document.getElementById("upload_btn").setAttribute("disabled", "disabled");
}

function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.readAsDataURL(input.files[0]);
        reader.onload = function (e) {
            console.log("成功读取....");

            //$('#upload_btn').removeAttr('src');
            $('#upload_btn').attr('src', e.target.result);
            // $('#target').removeAttr('src');
            $('#target').attr('src', e.target.result);


        }
    }


}

