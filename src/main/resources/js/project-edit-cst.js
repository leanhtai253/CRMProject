$(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/projectInfo"
    }).done(function (data) {
        let da = data.data
        console.log(da)
        $("#prNa").attr("value", da.name)
        $("#prSt").attr("value", da.startD)
        $("#prEn").attr("value", da.endD)
    })
    $(document).on("click","#returnBtn",function(){
        window.location.href="http://localhost:8080/projects"
    })
    $(document).on("click","#editPrjBtn",function(){
        let requestData = {
            name: $("#prNa").val(),
            start: $("#prSt").val(),
            end: $("#prEn").val()
        }
        if (requestData.name != '') {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/p/api/updateProject",
                data: JSON.stringify(requestData)
            }).done(function(data){
                if (data.status == 200) {
                    if (data.isSuccess) {
                        window.location.href="http://localhost:8080/projects"
                    } else {
                        alert("Failed to edit project")
                    }
                } else {
                    window.location.href="http://localhost:8080/notAllowed"
                }
            })
        } else {
            alert("Project name cannot be blank")
        }
    })
})