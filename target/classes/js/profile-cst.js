$(document).ready(function() {
    // User API
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/login"
    }).done(function(data) {
        let da = data.data
        $(da).each(function(index,item) {
            $("#profileName").text(item.firstName+" "+item.lastName)
            $("#profileEmail").text(item.email)
            $("#profileAvatar").attr("src",item.avatar)
            $("#profileBgImg").attr("src",item.bgImg)
        })
    })
    // Dashboard API
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/dashboard"
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
})