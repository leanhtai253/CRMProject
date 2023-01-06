$(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/taskToEdit"
    }).done(function (data) {
        let da = data.data
        $("#inputTaskName").attr("value", da.task)
        $("#inputProjectName").attr("value", da.project)
        $("#inputStartD").attr("value", da.start_date)
        $("#inputEndD").attr("value", da.end_date)
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
        // $("#statusSelection").find(`option[value="${da.status}"]`).attr('selected','selected')
        // let newStatusId = $("#statusSelection").find(":selected").attr("statusid")
        // console.log(newStatusId)
        // $(`#statusSelection option`).each(function(){
        //     if($(this).val() == da.status) {
        //         $(this).prop("selected", true)
        //     }
        // })
    })
    $("#updateSubmitBtn").click(function(){
        let newStatusId = $("#statusSelection").find(":selected").attr("statusid")
        console.log(newStatusId)
        $.ajax({
            method:"POST",
            url:"http://localhost:8080/api/taskToEdit?statusId="+newStatusId
        }).done(function(data){})
        window.location.href = "http://localhost:8080/profile";
        return false;
    })
    // $(document).on("click","#updateSubmitBtn", function() {
    //     let newStatusId = $("#statusSelection").find(":selected").attr("statusid")
    //     console.log(newStatusId)
    //     $.ajax({
    //         method:"POST",
    //         url:"http://localhost:8080/api/taskToEdit?statusId="+newStatusId
    //     }).done(function(data){})
    // })
})