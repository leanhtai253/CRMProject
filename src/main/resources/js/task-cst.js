$(document).ready(function() {
    $.ajax({
        method:"GET",
        url:'http://localhost:8080/api/tasks',
        cache: false
    }).done(function (data) {
        let da = data.data
        $.get("templateTaskList.html", function(content) {
            $(da).each(function(index,item){
                let taskList = $(content)

                taskList.find(".taskNo").attr("taskid",item.id)
                taskList.find(".taskNo").text(index+1)
                taskList.find(".taskName").text(item.task)
                taskList.find(".taskProject").text(item.project)
                taskList.find(".taskStart").text(item.start_date)
                taskList.find(".taskEnd").text(item.end_date)
                taskList.find(".taskStatus").text(item.status)

                $("#taskListBody").append(taskList)
            })
        })
    })
    $(document).on("click", ".editTaskBtn", function() {
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
            window.location.href="http://localhost:8080/editTask"
        })
    })

})