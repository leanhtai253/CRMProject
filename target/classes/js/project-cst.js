$(document).ready(function(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/projects",
        cache:false
    }).done(function (data){
        console.log(data)
        let da = data.data
        $.get("templateProjectRow.html",function(content){
            // let projectRow = $(content)
            $(da).each(function(key,value){
                let projectRow = $(content)
                projectRow.find(".projectNo").text(key+1)
                projectRow.find(".projectName").text(value.name)
                projectRow.find(".projectStart").text(value.startD)
                projectRow.find(".projectEnd").text(value.endD)
                projectRow.attr("projectid", value.projectID)
                $("#projectRows").append(projectRow)
            })
        })

    })
    $(document).on("click", ".projectViewBtn", function(){
        let projectID = $(this).closest(".projectRow").attr("projectid")
        console.log(projectID)
        var requestData = {
            projectid: projectID
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/projectDashboard",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            if (data.isSuccess) {
                window.location.href="http://localhost:8080/projectDetails"
            }
        })
    })
    $(document).on("click", ".deleteConfirmBtn", function() {
        let projectID = $(this).closest(".projectRow").attr("projectid")
        console.log(projectID)
        var requestData = {
            projectid: projectID
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/projectDashboard",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            if (data.isSuccess) {
                $.ajax({
                    method: "POST",
                    url: "http://localhost:8080/p/api/deleteProject"
                }).done(function(data){
                    if (data.status == 200) {
                        if (data.isSuccess) {
                            window.location.href="http://localhost:8080/projects"
                        } else {
                            alert("Failed to remove project")
                        }
                    } else {
                        window.location.href="http://localhost:8080/notAllowed"
                    }
                })
            }
        })
    })
    $(document).on("click", "#addPrjBtn", function(){
        window.location.href="http://localhost:8080/newProject"
    })
    $(document).on("click",".updateBtn", function(){
        let projectID = $(this).closest(".projectRow").attr("projectid")
        console.log(projectID)
        var requestData = {
            projectid: projectID
        }
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/projectDashboard",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            if (data.isSuccess) {
                window.location.href="http://localhost:8080/projectInfo"
            } else {
                alert("Error editing project")
            }
        })
    })

    $(document).on("click", ".deleteBtn", function() {
        $(this).addClass("hidden")
        $(this).closest("td").find(".deleteConfirmBtn").removeClass("hidden")
        $(this).closest("td").find(".deleteCancelBtn").removeClass("hidden")
    })
    $(document).on("click", ".deleteCancelBtn", function() {
        $(this).addClass("hidden")
        $(this).closest("td").find(".deleteConfirmBtn").addClass("hidden")
        $(this).closest("td").find(".deleteBtn").removeClass("hidden")
    })

})