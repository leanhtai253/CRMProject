$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/roleDetails"
    }).done(function(data){
        let da = data.data
        console.log(da)
        $("#roleName").val(da.name)
        $("#roleDescr").val(da.descr)
    })
    $(document).on("click","#returnBtn", function(){
        window.location.href="http://localhost:8080/roles"
    })
    $(document).on("click", "#updateRoleBtn", function(){
        let dataToSend = {
            name: $("#roleName").val(),
            descr: $("#roleDescr").val()
        }
        if (dataToSend.name != '') {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/p/api/updateRole",
                data: JSON.stringify(dataToSend)
            }).done(function(data){
                if (data.status == 200) {
                    if (data.isSuccess) {
                        window.location.href="http://localhost:8080/roles"
                    } else {
                        alert("Failed to modify role")
                    }
                } else if (data.status==403){
                    window.location.href="http://localhost:8080/notAllowed"
                }
            })
        }
        else {
            alert("Role name cannot be blank")
        }
    })

})