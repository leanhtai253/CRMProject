$(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/countries"
    }).done(function (data){
        let da = data.data
        $(da).each(function(key,value){
            $("<option/>").val(value.name).text(value.name)
                .appendTo($("#countryOptions"))
        })
    })

    $(document).on("click", "#addMemBtn", function(){
        let dataToSend = {
            firstName: $("#formFirstN").val(),
            lastName: $("#formLastN").val(),
            email: $("#formEmail").val(),
            pwd: $("#formPwd").val(),
            phone: $("#formPhone").val(),
            country: $("#countryOptions").val()
        }
        if (dataToSend.email != '' && dataToSend.pwd!='') {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/p/api/addUser",
                data: JSON.stringify(dataToSend)
            }).done(function(data){
                if (data.status == 200) {
                    if (data.isSuccess) {
                        window.location.href="http://localhost:8080/users"
                    } else {
                        alert("Failed to add user")
                    }
                } else {
                    window.location.href="http://localhost:8080/notAllowed"
                }
            })
        }
        else {
            alert("Please fill in your email and password")
        }
    })
    $(document).on("click", "#returnBtn", function(){
        window.location.href="http://localhost:8080/users"
    })
})