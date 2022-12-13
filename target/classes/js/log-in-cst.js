
$(document).ready(function(){
    alert("Javascript working")
    $("#loginBtn").on("click", loginFunction)
    $(document).on("keypress", function(event){
        if (event.which == 13) {
            loginFunction()
        }
    })

    var loginFunction = function() {
        console.log("Da nhan nut")
        loginData = {
            email: $("#emailInput").val(),
            password: $("#pwdInput").val()
        }
        console.log(loginData)
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/login",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=UTF-8;"
        }).done(function(data){
            // console.log(data)
            if (data.isSuccess) {
                console.log("OKE")
                window.location.href="http://localhost:8080/dashboard"
            } else {
                console.log(data)
            }
        })
    }
})

