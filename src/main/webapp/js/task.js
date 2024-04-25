$(document).ready(function () {
    var str = null
    $(".btn-delete").click(function () {
        var id = $(this).attr("taskId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8082/project_crm/api/removeTask?id=" + id,
            // data: {}
        }).done(function (data) {
            if (data.success) {
                //Xoa thanh cong
                This.closest("tr").remove()
                window.location.reload()
            } else {
                //Xoa that bai
                alert("Xoa that bai")
            }
            console.log(data)
            //alert( "Data Saved: " + data );
        });
    })

    $(".btn-update").click(function () {
        var id = $(this).attr("taskId");
        $('#myModal').modal('show');

        $("#myform").submit(function (event) {
            event.preventDefault()
            var task_name = $(".taskname-value").val()
            var user_id = $(".username-value").val()
            var job_id = $(".jobname-value").val()
            var status_id = $(".status-value").val()
            $.ajax({
                method: "get",
                // http://localhost:8082/project_crm/api/editTask?id=6&task_name=code&user_id=2&job_id=2&status_id=2
                url: "http://localhost:8082/project_crm/api/editTask?id=" + id + "&task_name=" + task_name + "&user_id=" + user_id + "&job_id=" + job_id + "&status_id=" + status_id,
                // data: {}
            }).done(function (data) {
                if (data.success) {
                    //Sua thanh cong
                    alert("Sua thanh cong");
                    window.location.reload();

                } else {
                    //Sua that bai
                    alert("Sua that bai")
                    console.log(data)
                }

            })

        })
    })
})