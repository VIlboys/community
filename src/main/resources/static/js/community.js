function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: "/comment",
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success: function (response) {
            if(response.code == 200){
                $("#comment_section").hide();
            }
            else{
                if(response.code == 2003){
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=ab964e0524966e490960&redirect_uri=http://localhost:8001/callback&scope=user&state=1");
                        window.localStorage.setItem("closable","true");//跳转页面后存入一个变量名为closable设置为true
                    }
                }else {
                    alert(response.message)
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
}