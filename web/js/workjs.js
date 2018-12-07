exchangeimg=function (a) {
    var path=($(a).find('img')).attr('src');
    $("#maximg1").attr('src',path);
}
OnloadPage=function()
{
    $.ajax({
        url:"workindex",
        type:"POST",
        dataType:"json",
        // contentType: false,
        // processData: false,
        data:{
            act:"GetWork"},
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
            if(data=="ERROR")alert(data);
            else
            {
                $("#workname").html(data[0].name);
                $("#writter").html(data[0].writter);
                var str="";
                if(data[0].Showimage1!=null)
                {
                    str+="  <div class=\"col-xs-6 col-md-3\"  style=\"width: 100px;height: 75px;padding: 5px;\">\n" +
                        "            <a  class=\"thumbnail\" style=\"cursor: pointer\" onclick=\"exchangeimg(this)\">\n" +
                        "                <img src=\"WorkFile/Images/"+data[0].Showimage1+"\" style=\"width: 90px;height: 70px;border:1px solid black\">\n" +
                        "            </a>\n" +
                        "        </div>";
                }
                if(data[0].Showimage2!=null)
                {
                    str+= "  <div class=\"col-xs-6 col-md-3\"  style=\"width: 100px;height: 75px;padding: 5px;\">\n" +
                        "            <a  class=\"thumbnail\" style=\"cursor: pointer\" onclick=\"exchangeimg(this)\">\n" +
                        "                <img src=\"WorkFile/Images/"+data[0].Showimage2+"\" style=\"width: 90px;height: 70px;border:1px solid black\">\n" +
                        "            </a>\n" +
                        "        </div>";
                }
                if(data[0].Showimage3!=null)
                {
                    str+="  <div class=\"col-xs-6 col-md-3\"  style=\"width: 100px;height: 75px;padding: 5px;\">\n" +
                        "            <a  class=\"thumbnail\" style=\"cursor: pointer\" onclick=\"exchangeimg(this)\">\n" +
                        "                <img src=\"WorkFile/Images/"+data[0].Showimage3+"\" style=\"width: 90px;height: 70px;border:1px solid black\">\n" +
                        "            </a>\n" +
                        "        </div>";
                }
                $("#imgpanel").html(str);
                if(data[0].VideoSRC!=null)
                {
                    $("#video1").html("作品演示视频：<input type=\"button\" value=\"观看视频\" class=\"btn btn-success\" onclick=\"PlayVideo(this)\">");
                }
                else
                {
                    $("#video1").html("暂无演示视频");
                }
                if(data[0].ProgramSRC!=null)
                {
                    $("#program1").html("作品源码：<input id='"+data[0].ProgramSRC+"' type=\"button\" value=\"下载源码\" class=\"btn btn-success\" onclick=\"DwonloadProgram(this)\">")
                }
                else
                {
                    $("#program1").html("暂无源码");
                }
            }
        },
        error:function () {
        }
    })
}
PlayVideo=function (btn) {
    // alert($(btn).attr('id'));
    window.location.href="playvideo";
}
DwonloadProgram=function(btn) {
    window.location.href="/WorkFile/programs/"+$(btn).attr('id');
}