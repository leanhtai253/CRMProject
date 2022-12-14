$(document).ready(function(){
    $(document).on("click","#addRoleBtn",function(){
        let requestData = {
            roleName: $("#roleName").val(),
            roleDescr: $("#roleDescr").val()
        }
        if (requestData.roleName != '') {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/p/api/addRole",
                data: JSON.stringify(requestData)
            }).done(function(data){
                if (data.status == 200) {
                    if (data.isSuccess) {
                        window.location.href="http://localhost:8080/roles"
                    } else {
                        alert("Failed to add role")
                    }
                } else {
                    window.location.href="http://localhost:8080/notAllowed"
                }
            })
        } else {
            alert("Role name cannot be blank")
        }
    })
    $(document).on("click","#returnBtn",function(){
        window.location.href="http://localhost:8080/roles"
    })
})