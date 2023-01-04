$(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/taskInfo"
    }).done(function (data) {
        let da = data.data
        $("#inputTaskName").attr("value", da.task)
        $("#inputProjectName").attr("value", da.project)
        $("#inputStartD").attr("value", da.startD)
        $("#inputEndD").attr("value", da.endD)
        $("#inputTaskMember").attr("value",da.member)
        // $("#statusSelection").attr("value", da.status)
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/status"
        }).done(function (data){
            let st = data.data;
            $(st).each(function(index,item){
                if (item.name == da.status) {
                    $("#statusSelection").append(
                        `<option class="statusOption" selected="selected"></option>`
                    )
                } else {
                    $("#statusSelection").append(
                        `<option class="statusOption"></option>`
                    )
                }
                $(".statusOption").last().attr("statusid", item.statusID)
                $(".statusOption").last().val(item.name)
                $(".statusOption").last().text(item.name)
            })
        })
    })
    $(document).on("click","#updateSubmitBtn",function(){
        var reqData = {
            name : $("#inputTaskName").val(),
            start: $("#inputStartD").val(),
            end: $("#inputEndD").val(),
            status: $("#statusSelection").find(":selected").attr("statusid")
        }
        console.log(reqData)
        $.ajax({
            method:"POST",
            url:"http://localhost:8080/api/updateTask",
            data: JSON.stringify(reqData),
        }).done(function(data){
            console.log(data)
            // if (data.isSuccess) {
            //     window.location.href = "http://localhost:8080/tasks";
            // } else {
            //     alert("Failed to edit task")
            // }
        })
    })
    $("#returnBtn").click(function(){
        window.location.href = "http://localhost:8080/tasks";
    })
})