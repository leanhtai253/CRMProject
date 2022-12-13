$(document).ready(function () {

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/dashboard"
    }).done(function (data) {
        let da = data.data
        let totalTasks = 0;
        $(da).each(function(index,value) {
            totalTasks = totalTasks + value.count
        })
        $.get("templateDashboard.html", function(content) {
            $(da).each(function (index, value) {
                let db = $(content)
                db.find(".statusName").text(value.status)
                db.find(".taskCount").text(value.count)
                db.find(".progressCount").attr("aria-valuenow", value.count)
                db.find(".progressCount").attr("aria-valuemin", 0)
                db.find(".progressCount").attr("aria-valuemax", totalTasks)
                db.find(".progressCount").css("width", String(value.count * 100.0 / totalTasks) + "%")

                switch (value.statusId) {
                    case 1:
                        db.find(".progressCount").addClass("progress-bar-danger")
                        db.find(".taskCount").addClass("text-danger")
                        break;
                    case 2:
                        db.find(".progressCount").addClass("progress-bar-megna")
                        db.find(".taskCount").addClass("text-megna")
                        break;
                    case 3:
                        db.find(".progressCount").addClass("progress-bar-primary")
                        db.find(".taskCount").addClass("text-primary")
                        break;
                }
                $("#summaryTasks").append(db)
            })
        })
    })
})