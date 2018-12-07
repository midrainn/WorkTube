var aaa=1;
subfile=function () {
    getuploadstatus();
    var fromdata=new FormData();

    var file=$("#faceimage").prop("files")[0];
    fromdata.append("file1",file);
    file=$("#showimage1").prop("files")[0];
    fromdata.append("file2",file);
    $.ajax({
        url:"uploadfile",
        type:"POST",
        dataType:"text",
        contentType: false,
        processData: false,
        data:fromdata,
        // data:{file2FileName:"111"},
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
                alert("成功");
            // fromdata=null;
        },
        error:function () {
            alert("异常");
        }
    })
}

getuploadstatus=function () {
    // $("#uploadstatus").html("<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\"60\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "+aaa+"%;\">\n" +
    //     aaa +
    //     "            </div>");
    $("#uploadstatus").css("display","block");
    $.ajax({
        url:"uploadstatus",
        type:"POST",
        dataType:"text",
        data:{
            act:"getstatus"
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
            // alert(data);
            $("#uploadstatus").html("<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\""+data+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "+data+"%;\">\n" +
                data +
                "%            </div>");
            aaa=data;
            if(aaa<100)
            {
                setTimeout(function () {
                    getuploadstatus();
                },500)

            }
            else
            {
                aaa=0;
                $("#uploadstatus").html("<div class=\"progress-bar-success\" role=\"progressbar\" aria-valuenow=\"100\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 100%;\">\n" +
                    "上传成功            </div>");
                aaa=data;
                // setTimeout(function () {
                //     $("#uploadstatus").css("display","none");
                // },3000);
            }
        }
    })
}
var pagenum=1;
var maxpage=1;
getAllLog=function () {
    // $("#mainpanel").html("aaaa");
    $.ajax({
        url:"test",
        dataType:"json",
        data:{
            pagenum:pagenum,
            act:"getlog"
        },
        success:function (data) {
            // alert(data.length);
            var str="";
            var i=0;
            for(;i<data.length-1;i++) {
                str+="<tr>";
                str+="<td>"+data[i].name+"</td>";
                str+="<td>"+data[i].log+"</td>";
                str+="<td>"+data[i].mark+"</td>";
                str+="</tr>";
            }
            $("#bodytr1").html(str);
            var maxnum=data[i].maxpagenum;
            maxpage=maxnum;
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
Loggetval=function (a) {
    // alert($(a).text());
    pagenum=$(a).text();
    getAllLog();
}
Loglastpage=function () {
    if(pagenum>0)
    {
        pagenum--;
        getAllLog();
    }
}
Lognextpage=function () {
    if(pagenum<maxpage)
    {
        pagenum++;
        getAllLog();
    }
}
