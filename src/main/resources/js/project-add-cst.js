$(document).ready(function(){

    $.ajax({
        method: "GET",
        url: ""
    })

    $(document).on("click","#addPrjBtn",function(){
        let requestData = {
            name: $("#prNa").val(),
            start: $("#prSt").val(),
            end: $("#prEn").val()
        }
        if (requestData.name != '') {
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/api/addProject",
                data: JSON.stringify(requestData)
            }).done(function(data){
                if (data.isSuccess) {
                    window.location.href="http://localhost:8080/projects"
                } else {
                    alert("Failed to add project")
                }
            })
        } else {
            alert("Project name cannot be blank")
        }
    })
    $(document).on("click","#returnBtn",function(){
        window.location.href="http://localhost:8080/projects"
    })
})