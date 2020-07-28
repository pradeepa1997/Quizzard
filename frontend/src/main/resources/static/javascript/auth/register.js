$(document).ready(
    function(){
        $("#registerForm").submit(function(event){
            event.preventDefault();
            ajaxPost();
        });
    

    function ajaxPost(){
        var formData = {
            username : "hasantha",
            password : "1234",
            email : "malshanh09@gmail.com"
        }

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:8081/auth/register",
            data : JSON.stringify(formData),
            dataType : "json",
            success : function(res){
                console.log(res);
            },
            error : function(err){
                console.log(err);
            }
        })
    }
}
)