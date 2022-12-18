$(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/userApi"
    }).done(function(data){
        let da = data.data
        console.log(da)
        $("#formFirstN").val(da.firstName)
        $("#formLastN").val(da.lastName)
        $("#formUsername").val(da.username)
        $("#formEmail").val(da.email)
        $("#formPhone").val(da.phone)

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/countries"
        }).done(function (data){
            let countries = data.data
            $(countries).each(function(key,value){
                if (value.name == da.country) {
                    $("#countryOptions").append(
                        `<option value="${value.name}" selected>${value.name}</option>`
                    )
                } else {
                    $("#countryOptions").append(
                        `<option value="${value.name}">${value.name}</option>`
                    )
                }
            })
        })
    })

    $(document).on("click", "#updateMemBtn", function(){
        let dataToSend = {
            firstName: $("#formFirstN").val(),
            lastName: $("#formLastN").val(),
            email: $("#formEmail").val(),
            phone: $("#formPhone").val(),
            username: $("#formUsername").val(),
            country: $("#countryOptions").val()
        }
        if (dataToSend.email != '') {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/api/updateUser",
                data: JSON.stringify(dataToSend)
            }).done(function(data){
                if (data.isSuccess) {
                    window.location.href = "http://localhost:8080/users"
                } else {
                    alert("Failed to add user")
                }
            })
        }
        else {
            alert("Email cannot be blank")
        }
    })
    $(document).on("click", "#returnBtn", function(){
        window.location.href="http://localhost:8080/users"
    })
})