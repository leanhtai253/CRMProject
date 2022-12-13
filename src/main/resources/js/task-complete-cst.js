
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