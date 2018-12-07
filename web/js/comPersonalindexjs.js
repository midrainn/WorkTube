var pagenum=1;
var maxpage=1;
var clock=false;
getAllWork=function () {

    // $("#mainpanel").html("aaaa");
    $.ajax({
        url:"mywork",
        dataType:"json",
        data:{
            pagenum:pagenum,
            act:"getmywork"
        },
        success:function (data) {
            // alert(data.length);
            var str="<div class=\"row\">";
            var i=0;
            for(;i<data.length-1;i++) {
                str+=" <div class=\"col-sm-6 col-md-4\" style=\"width: 250px\">\n" +
                    "                <div class=\"thumbnail\">\n" +
                    "                    <div style=\"width: 210px;height: 170px\">\n" +
                    "                        <img style=\"width: 210px;height: 170px\" src=\""+data[i].url+"\" alt=\"...\">\n" +
                    "                    </div>\n" +
                    "                    <div class=\"caption\">\n" +
                    "                        <h3>"+data[i].name+"</h3>\n" +
                    "                        <p>点赞量:"+data[i].likenum+"</p>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>";
            }
            var maxnum=data[i].maxpagenum;
            maxpage=maxnum;
            str+="</div>";
            $("#mainpanel").html(str);
            str="";
            if(maxnum>0)
            {
                str+="<ul class=\"pagination\" id=\"pagenav1\">\n" +
                    "                <li>\n" +
                    "                    <a style='cursor: pointer' onclick='lastpage()' aria-label=\"Previous\">\n" +
                    "                        <span aria-hidden=\"true\">上一页</span>\n" +
                    "                    </a>\n" +
                    "                </li>";

                for (var j=1;j<=maxnum;j++)
                {
                    if(j!=pagenum)
                    str+="<li><a style='cursor: pointer' onclick=\"getval(this)\">"+(j)+"</a></li>";
                    else str+="<li class='active'><a style='cursor: pointer' onclick=\"getval(this)\">"+(j)+"</a></li>";
                }
                str+="<li>\n" +
                    "                    <a style='cursor: pointer' onclick=\"nextpage()\" aria-label=\"Next\">\n" +
                    "                        <span aria-hidden=\"true\">下一页</span>\n" +
                    "                    </a>\n" +
                    "                </li>" +
                    "</ul>";
                $("#pagenav1").html(str);
            }
            else
            {
                str="<h1><small>你暂时没有正在展示的作品哦</small></h1>"
                $("#mainpanel").html(str);
                $("#pagenav1").html("");
            }
        },
        error:function () {
            // alert("异常");
        }
    })
}
getval=function (a) {
    // alert($(a).text());
    pagenum=$(a).text();
    getAllWork();
}
lastpage=function () {
    if(pagenum>0)
    {
        pagenum--;
        getAllWork();
    }
}
nextpage=function () {
    if(pagenum<maxpage)
    {
        pagenum++;
        getAllWork();
    }
}
