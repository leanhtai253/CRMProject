$(document).ready(function(){

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/projects"
    }).done(function(data){
        let da = data.data
        $(da).each(function(key,value){
            $("#projectList").append(`
            <option id="${value.projectID}">${value.name}</option>
            `)
        })
    })

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/members"
    }).done(function(data){
        let da = data.data
        console.log(da)
        $(da).each(function(key,value){
            $("#memberList").append(`
            <option id=${value.userID}>${value.firstName + " " + value.lastName}</option>
            `)
        })
    })

    $(document).on("click", "#returnBtn", function() {
        window.location.href="http://localhost:8080/tasks"
    })
    $(document).on("click", "#addTaskBtn", function() {
        let prj = document.querySelector("#projectList")
        let mbr = document.querySelector("#memberList")
        let reqData = {
            project: prj.options[prj.selectedIndex].id,
            member: mbr.options[mbr.selectedIndex].id,
            task: $("#formTask").val(),
            start: $("#formSt").val(),
            end: $("#formEn").val(),
        }
        console.log(reqData)
        if (reqData.task == '') {
            alert("Task cannot be blank.")
        } else {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/p/api/addTask",
                data: JSON.stringify(reqData)
            }).done(function(data){
                if (data.status == 200) {
                    if (data.isSuccess) {
                        window.location.href="http://localhost:8080/tasks"
                    } else {
                        alert("Failed to add task")
                    }
                } else {
                    window.location.href="http://localhost:8080/notAllowed"
                }
            })
        }
    })

})
