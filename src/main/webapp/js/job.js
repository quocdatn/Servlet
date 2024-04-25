$(document).ready(function () {
    var str = null
    $(".btn-delete").click(function () {
        var id = $(this).attr("jobId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8082/project_crm/api/removeJob?id=" + id,
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
        var id = $(this).attr("jobId");
        $('#myModal').modal('show');

        $("#myform").submit(function (event) {
            event.preventDefault()
            var job_name = $(".jobname-value").val()
            var start_date = $(".startdate-value").val()
            var end_date = $(".enddate-value").val()

            $.ajax({
                method: "get",
                url: "http://localhost:8082/project_crm/api/editJob?id=" + id + "&job_name=" + job_name + "&start_date=" + start_date + "&end_date=" + end_date,
                // data: {}
            }).done(function (data) {
                if (data.success) {
                    str = data;
                    //Sua thanh cong
                    alert("Sua thanh cong");
                    window.location.reload();
                } else {
                    //Sua that bai
                    alert("Sua that bai")
                }
                // console.log(data)
            })

            console.log(str)

        })
    })
})