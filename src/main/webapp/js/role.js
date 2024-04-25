$(document).ready(function () {
    var str = null
    $(".btn-delete").click(function () {
        var id = $(this).attr("roleId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8082/project_crm/api/removeRole?id=" + id,
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
        var id = $(this).attr("roleId");
        $('#myModal').modal('show');

        $("#myform").submit(function (event) {
            event.preventDefault()
            var role_name = $(".roleName-value").val()
            var desc = $(".desc-value").val()
            $.ajax({
                method: "get",
                url: "http://localhost:8082/project_crm/api/editRole?id=" + id + "&name=" + role_name + "&description=" + desc,
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

        // $(".btn-edit-confirm").click(function () {
        //     var role_name = $(".roleName-value").val()
        //     var desc = $(".desc-value").val()
        //     $.ajax({
        //         method: "get",
        //         url: "http://localhost:8082/project_crm/api/editRole?id=" + id + "&name=" + role_name + "&description=" + desc,
        //         // data: {}
        //     }).done(function (data) {
        //         if (data.success) {
        //             //Sua thanh cong
        //             alert("Sua thanh cong");
        //         } else {
        //             //Sua that bai
        //             alert("Sua that bai")
        //         }
        //         console.log(data)
        //         //alert( "Data Saved: " + data );
        //     });


            //
            //
            //         // $.ajax({
            //         //     method: "get",
            //         //     url: "http://localhost:8082/project_crm/api/removeRole?id=" + id,
            //         //     // data: {}
            //         // }).done(function (data){
            //         //     if (data.success){
            //         //         //Xoa thanh cong
            //         //         This.closest("tr").remove()
            //         //     }else {
            //         //         //Xoa that bai
            //         //         alert("Xoa that bai")
            //         //     }
            //         //     console.log(data)
            //         //     //alert( "Data Saved: " + data );
            //         // });
            //     })
            // })


        })
    })
})