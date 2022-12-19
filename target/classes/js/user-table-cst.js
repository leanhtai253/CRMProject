function roleIdComparer(a, b)
{
    if(parseInt(a.roleID) < parseInt(b.roleID))
        return -1;
    if(parseInt(a.roleID) > parseInt(b.roleID))
        return 1;

    return 0;
}

$(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/members"
    }).done(function(data){
        let da = data.data
        var sortedDa = da.sort(roleIdComparer)
        let index = 0;
        $.get("templateMembersList.html", function(content){
            $.each(sortedDa, function(key,value){
                $("#membersTableBody").append(content)
                $("#membersTableBody").find(".memberRow").last().attr("userid",value.userID)
                $("#membersTableBody").find(".memberRow").last().attr("roleid",value.roleID)

                $(".memberRow").last().find(".memberNo").text(++index)
                $(".memberRow").last().find(".firstName").text(value.firstName)
                $(".memberRow").last().find(".lastName").text(value.lastName)
                $(".memberRow").last().find(".username").text('@'+value.username)
                $(".memberRow").last().find(".roleDesc").text(value.role)
            })
        })
    })
    $(document).on("click", ".memberViewBtn", function(){
        let userId = $(this).closest(".memberRow").attr("userid")
        var requestData = {
            userid: userId
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/userDetails",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            if (data.isSuccess) {
                window.location.href="http://localhost:8080/userDetails"
            }
        })
    })
    $(document).on("click", ".memberUpdateBtn", function(){
        let userId = $(this).closest(".memberRow").attr("userid")
        var requestData = {
            userid: userId
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/userDetails",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            if (data.isSuccess) {
                window.location.href="http://localhost:8080/editUser"
            }
        })
    })

    $(document).on("click", "#addMemBtn", function() {
        window.location.href="http://localhost:8080/newUser"
    })
    $(document).on("click", ".memberUpdateBtn", function() {
        window.location.href="http://localhost:8080/editUser"
    })
    $(document).on("click", ".memberDeleteConfirmBtn", function() {
        window.location.href="http://localhost:8080/api/deleteUser"
    })
    $(document).on("click", ".memberDeleteBtn", function() {
        $(this).addClass("hidden")
        $(this).closest("td").find(".memberDeleteConfirmBtn").removeClass("hidden")
        $(this).closest("td").find(".memberDeleteCancelBtn").removeClass("hidden")
    })
    $(document).on("click", ".memberDeleteCancelBtn", function() {
        $(this).addClass("hidden")
        $(this).closest("td").find(".memberDeleteConfirmBtn").addClass("hidden")
        $(this).closest("td").find(".memberDeleteBtn").removeClass("hidden")
    })
})