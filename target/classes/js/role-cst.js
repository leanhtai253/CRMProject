
$(document).ready(function(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/roles",
        cache: false
    }).done(function(data){
        let da = data.data
        $.get("templateRoleRow.html", function(content){
            $(da).each(function(key,value){
                let rowElement = $(content)
                rowElement.find(".roleNo").text(key+1)
                rowElement.find(".roleName").text(value.name)
                rowElement.find(".roleDescr").text(value.descr)
                rowElement.find(".roleNo").attr("roleid",value.roleID)

                $(".roleRows").append(rowElement)
            })
        })
    })
    $(document).on("click", ".roleEditBtn", function(){
        let roleId = $(this).closest(".roleElement").find('.roleNo').attr("roleid")
        var requestData = {
            roleid: roleId
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/roleDetails",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            if (data.isSuccess) {
                window.location.href="http://localhost:8080/editRole"
            }
        })
    })
    $(document).on("click", ".roleDeleteConfirmBtn", function() {
        let roleId = $(this).closest(".roleElement").find('.roleNo').attr("roleid")
        var requestData = {
            roleid: roleId
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/roleDetails",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            if (data.isSuccess) {
                $.ajax({
                    method: "POST",
                    url: "http://localhost:8080/p/api/deleteRole"
                }).done(function(data){
                    if (data.status == 200) {
                        if (data.isSuccess) {
                            window.location.href="http://localhost:8080/roles"
                        } else {
                            alert("Failed to remove role")
                        }
                    } else {
                        window.location.href="http://localhost:8080/notAllowed"
                    }
                })
            }
        })

    })
    $(document).on("click", "#addRoleBtn", function(){
        window.location.href="http://localhost:8080/newRole"
    })

    $(document).on("click", ".roleDeleteBtn", function() {
        $(this).addClass("hidden")
        $(this).closest("td").find(".roleDeleteConfirmBtn").removeClass("hidden")
        $(this).closest("td").find(".roleDeleteCancelBtn").removeClass("hidden")
    })
    $(document).on("click", ".roleDeleteCancelBtn", function() {
        $(this).addClass("hidden")
        $(this).closest("td").find(".roleDeleteConfirmBtn").addClass("hidden")
        $(this).closest("td").find(".roleDeleteBtn").removeClass("hidden")
    })
})
