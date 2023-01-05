$(document).ready(function () {

    var contextPath = "http://localhost:8080"
    var profilePage = contextPath + "/public/profile"
    var taskSummaryPage = contextPath + "/public/taskSummary"
    var logOutPage = contextPath + "/public/api/logout"
    var dashboardPage = contextPath + "/public/dashboard"
    var rolesPage = contextPath + "/public/roles"
    var usersPage = contextPath + "/public/users"
    var tasksPage = contextPath + "/public/tasks"
    var projectsPage = contextPath + "/public/projects"

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