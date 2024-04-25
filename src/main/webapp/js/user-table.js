//Khi nào html nội dung đã đc nạp vào trình duyệt thì sẽ chạy code trên function
$(document).ready(function(){
    //Lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-user").click(function(){
        var id = $(this).attr("userid")
        var This = $(this)
        $.ajax({
          method: "GET",
          url: "http://localhost:8080/demoservelet/user/delete?id=" + id,
//          data: { name: "John", location: "Boston" }
        }).done(function( result ) {
            This.closest("tr").remove();
            console.log("Ket qua",result)
          });
    })
})