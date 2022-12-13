$(document).ready(function(){
    $(document).on("click","#addRoleBtn",function(){
        let requestData = {
            roleName: $("#roleName").val(),
            roleDescr: $("#roleDescr").val()
        }
        if (requestData.roleName != '') {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/api/addRole",
                data: JSON.stringify(requestData)
            }).done(function(data){
                if (data.isSuccess) {
                    window.location.href="http://localhost:8080/api/roles"
                } else {
                    alert("Failed to add role")
                }
            })
        } else {
            alert("Role name cannot be blank")
        }
    })
})