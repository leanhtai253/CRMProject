
$(document).ready(function() {
    $.ajax({
        method:"GET",
        url:'http://localhost:8080/api/completeTasks',
        cache: false
    }).done(function (data) {
        let da = data.data
        var taskRows = setUpData(da)
        console.log(taskRows)
        $(taskRows).each(function (key, value) {
            console.log("append" + value)
            $("#taskRows").append(value)
        })
    })
    $(document).on("click", "#addTaskBtn", function() {
        window.location.href="http://localhost:8080/newTask"
    })
    $(document).on("click", ".taskEditBtn", function() {
        let id = $(this).parents(".taskRow").find(".taskNo").attr("taskid")
        var requestData = {
            taskid: id
        }
        console.log(id)
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/editTask",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            window.location.href="http://localhost:8080/taskInfo"
        })
    })
    $(document).on("click", ".deleteConfirmBtn", function() {
        let id = $(this).parents(".taskRow").find(".taskNo").attr("taskid")
        var requestData = {
            taskid: id
        }
        console.log(id)
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/editTask",
            data: JSON.stringify(requestData),
            contentType: "application/json; charset=utf-8"
        }).done(function(data){
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/p/api/deleteTask"
            }).done(function(data){
                if (data.status == 200) {
                    if (data.isSuccess) {
                        window.location.href="http://localhost:8080/tasks"
                    } else {
                        alert("Failed to delete task")
                    }
                } else {
                    window.location.href="http://localhost:8080/notAllowed"
                }
            })
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

function setUpData(da) {
    var taskRows = []
    $.get("templateTaskCompleteRow.html", function(content){
        // var taskRow = $(content)
        da.forEach(function(item,index){
            let taskRow = $(content)
            taskRow.find(".taskNo").attr("taskid", item.taskID)
            taskRow.find(".taskNo").text(index + 1)
            taskRow.find(".taskName").text(item.task)
            taskRow.find(".taskProject").text(item.project)
            taskRow.find(".taskMember").text(item.member)
            taskRow.find(".taskStart").text(item.startD)
            taskRow.find(".taskEnd").text(item.endD)
            taskRow.find(".taskStatus").text(item.status)
            taskRows.push(taskRow)
            $("#taskRows").append(taskRow)
        })
    })

    return taskRows
}

function compareById(a, b)
{
    if(parseInt(a.taskID) < parseInt(b.taskID))
        return -1;
    if(parseInt(a.taskID) > parseInt(b.taskID))
        return 1;

    return 0;
}