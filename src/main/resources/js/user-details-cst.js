$(document).ready(function() {
    $.ajax({
        method:"GET",
        url: "http://localhost:8080/api/userApi"
    }).done(function(data){
        let da = data.data
        console.log(data)
        $("#viewUserAvatar").attr("src",da.avatar)
        $("#viewUserBgImg").attr("src", da.bgImg)
        $("#viewUserName").text(da.firstName+" "+da.lastName)
        $("#viewUserEmail").text(da.email)
    })
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/memberDashboard"
    }).done(function (data) {
        let da = data.data
        let totalTasks = 0;
        $(da).each(function (index, value) {
            totalTasks = totalTasks + value.count
        })
        $.get("templateDashboardProfile.html", function (content) {
            $(da).each(function (index, value) {
                let db = $(content)
                let taskPercent = value.count * 100.0 / totalTasks
                db.find(".statusName").text(value.status)
                db.find(".taskCountPercent").text(taskPercent.toFixed(0) + "%")
                db.find(".progressCount").attr("aria-valuenow", value.count)
                db.find(".progressCount").attr("aria-valuemin", 0)
                db.find(".progressCount").attr("aria-valuemax", totalTasks)
                db.find(".progressCount").css("width", String(taskPercent) + "%")
                switch (value.statusId) {
                    case 1:
                        db.find(".progressCount").addClass("progress-bar-danger")
                        db.find(".taskCountPercent").addClass("text-danger")
                        break;
                    case 2:
                        db.find(".progressCount").addClass("progress-bar-megna")
                        db.find(".taskCountPercent").addClass("text-megna")
                        break;
                    case 3:
                        db.find(".progressCount").addClass("progress-bar-primary")
                        db.find(".taskCountPercent").addClass("text-primary")
                        break;
                }
                $("#summaryTasks").append(db)
            })
        })
    })
    // Get Tasks Based On Status
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/tasksByStatus"
    }).done(function(data) {
        let da = data.data
        console.log(da)
        $.get("templateTaskView.html", function(taskViewContent){
            $.get("templateTaskViewElement.html", function(taskViewElement) {
                $(da).each(function(key,value){
                    var taskView = $(taskViewContent)
                    taskView.find(".viewStatusName").text(value.status)
                    $(value.tasks).each(function(taskIndex,task){
                        var taskElement = $(taskViewElement)
                        taskElement.find(".viewTaskName").text(task.task)
                        taskElement.find(".viewTaskStart").append(task.start_date)
                        taskElement.find(".viewTaskEnd").append(task.end_date)
                        taskElement.find(".viewTaskProject").append(task.project)

                        taskView.find(".taskViewList").append(taskElement)
                    })
                    $("#taskLists").append(taskView)
                })
            })
        })
    })
})

function compareStatusId(a, b)
{
    if(parseInt(a.statusId) < parseInt(b.statusId))
        return -1;
    if(parseInt(a.statusId) > parseInt(b.statusId))
        return 1;

    return 0;
}