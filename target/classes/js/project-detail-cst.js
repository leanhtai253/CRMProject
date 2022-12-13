$(document).ready(function(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/projectDashboard",
        cache:false
    }).done(function (data){
        console.log(data)
        let da = data.data
        let totalTasks = 0;
        $(da).each(function (index, value) {
            totalTasks = totalTasks + value.count
        })
        $.get("templateDashboard.html", function (content) {
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
                $("#projectDashboard").append(db)
            })
        })
    })
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/projectDetails"
    }).done(function(data){
        console.log(data)
        let da = data.data
        $.get("templateUserBar.html", function(userContent) {
            $.get("templateTaskView.html", function (taskContent) {
                $.get("templateTaskViewElement.html", function (taskElContent) {
                    $(da).each(function (key, value) {
                        let userBar = $(userContent)
                        let member = value.member
                        let summaryList = value.summaryList
                        // set up user info
                        userBar.find("#accountName").text(member.firstName + " " + member.lastName)
                        userBar.find("img").attr("src", member.avatar)
                        // set up task list
                        $(summaryList).each(function (index, taskRecord) {
                            let taskView = $(taskContent)
                            taskView.find(".viewStatusName").text(taskRecord.status)
                            $(taskRecord.tasks).each(function (taskNo, task) {
                                let taskElement = $(taskElContent)
                                taskElement.find(".viewTaskName").text(task.task)
                                taskElement.find(".viewTaskStart").append(task.startD)
                                taskElement.find(".viewTaskEnd").append(task.endD)
                                taskElement.find(".viewTaskProject").css("visibility","hidden")

                                taskView.find(".taskViewList").append(taskElement)
                            })
                            userBar.find(".userRow").append(taskView)
                        })
                        $(".summaryListRow").append(userBar)
                    })
                })
            })
        })
    })
})