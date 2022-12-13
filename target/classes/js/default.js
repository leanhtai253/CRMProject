$(document).ready(function () {

    var contextPath = "http://localhost:8080"
    var profilePage = contextPath + "/profile"
    var taskSummaryPage = contextPath + "/taskSummary"
    var logOutPage = contextPath + "/api/logout"
    var dashboardPage = contextPath + "/dashboard"
    var rolesPage = contextPath + "/roles"
    var usersPage = contextPath + "/users"
    var tasksPage = contextPath + "/tasks"
    var projectsPage = contextPath + "/projects"

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/login"
    }).done(function (data) {
        let da = data.data
        $("#accountName").text(da.firstName+" "+da.lastName)
        $("#accountAvatar").attr("src", da.avatar)
        $("#navProfile").attr("href",profilePage)
        $("#sideDashboard").attr("href", dashboardPage)
        $("#toDashboardBtn").attr("href", dashboardPage)
        $("#sideMembers").attr("href",usersPage)
        $("#sideRoles").attr("href", rolesPage)
        $("#sideTasks").attr("href",tasksPage)
        $("#sideProjects").attr("href", projectsPage)
    })

})