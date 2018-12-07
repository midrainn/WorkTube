
getAllWorkList=function()
{
    $.ajax({
        url:"workaction",
        type:"POST",
        dataType:"text",
        data:{
            loginaction:"getWorkByPage"
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            if(data=="true")
            {
                // alert("刷新成功");
                // window.location.href="index";
                window.location.reload();
            }
            else
            {
                alert("刷新失败");
            }
        }
    })
}