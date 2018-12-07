Work=function (a) {
    //alert($(a).attr("id"));
    $.ajax({
        url:"workindex",
        type:"POST",
        dataType:"text",
        // contentType: false,
        // processData: false,
        data:{
            act:"SetWork",
            WorkID:$(a).attr("id")
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
            //alert(data);
            if("Success"==data)
            {
                window.location.href="work";
            }
            else
            {
                alert(data);
            }
        },
        error:function () {
        }
    })
}