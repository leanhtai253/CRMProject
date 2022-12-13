
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
    $(document).on("click", "#addRoleBtn", function(){
        window.location.href="http://localhost:8080/newRole"
    })
})
