var pagenum=1;
var maxpage=1;
var clock=false;
var tipclock=true;
var PointNum=2;
var GetNum=0;
OutTip=function()
{

    if(tipclock)
    {
        return;
    }
    else
    {
        var TipStr="读取数据中";
        for(var i=0;i<PointNum;++i)
        {
            TipStr+='.';
        }
        $("#mainpanel1").html("<h1>"+TipStr+"</h1>");
        PointNum++;
        GetNum++;
        if (PointNum>5)PointNum=0;
        if(GetNum>20)
        {
            getWaitPassWork();
        }
        setTimeout(function () {
            OutTip();
        },500)
    }
}
getWaitPassWork=function () {
    if(clock)return;
    clock=true;
    tipclock=false;
    GetNum=0;
    OutTip();

    $.ajax({
        url:"updatework",
        type:"POST",
        dataType:"json",
        data:
            {
                act:"getwaitwork"
            },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
            tipclock=true;
            var str="<table class=\"table\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                    <thead>\n" +
                "                    <tr>\n" +
                "                        <th width=\"25%\">作品ID</th>\n" +
                "                        <th width=\"10%\">作品名称</th>\n" +
                "                        <th width=\"15%\">作者</th>\n" +
                "                        <th width=\"10%\">类型</th>\n" +
                "                        <th width=\"20%\">操作</th>\n" +
                "                    </tr>\n" +
                "                    </thead>\n" +
                "                    <tbody>";
            var i=0;
            for(;i<data.length-1;i++) {
                    str+="<tr>";
                    str+="<td><a style='cursor: pointer' onclick='Work(this)' id='"+data[i].id+"'>"+"查看作品"+"</a></td>";
                    str+="<td>"+data[i].workname+"</td>";
                    str+="<td>"+data[i].writter+"</td>";
                    str+="<td>"+data[i].type+"</td>";
                    str+="<td align='center'>" +
                        "<input type='button'value='通过审核' class='btn btn-success' onclick='passwork(this)'>" +
                        "<input type='button'value='不通过审核' class='btn btn-danger' onclick='nopasswork(this)'>" +
                        " </td>";
                    str+="</tr>";
            }
            str+="</tbody>\n" +
                "                </table>";
            var maxnum=data[i].maxpagenum;
            maxpage=maxnum;
            if(maxnum>0)
            {
                str+="<ul class=\"pagination\" id=\"pagenav1\">\n" +
                    "                <li>\n" +
                    "                    <a style='cursor: pointer' onclick='WaitPasslastpage()' aria-label=\"Previous\">\n" +
                    "                        <span aria-hidden=\"true\">上一页</span>\n" +
                    "                    </a>\n" +
                    "                </li>";

                for (var j=1;j<=maxnum;j++)
                {
                    if(j!=pagenum)
                        str+="<li><a style='cursor: pointer' onclick=\"WaitPassgetval(this)\">"+(j)+"</a></li>";
                    else str+="<li class='active'><a style='cursor: pointer' onclick=\"WaitPassgetval(this)\">"+(j)+"</a></li>";
                }
                str+="<li>\n" +
                    "                    <a style='cursor: pointer' onclick=\"WaitPassnextpage()\" aria-label=\"Next\">\n" +
                    "                        <span aria-hidden=\"true\">下一页</span>\n" +
                    "                    </a>\n" +
                    "                </li>" +
                    "</ul>";
                $("#mainpanel1").html(str);
            }
            else
            {
                str="<h1><small>暂时没有审核中的作品</small></h1>"
                $("#mainpanel1").html(str);
            }
            clock=false;
        },
        error:function () {
            // alert("异常");
            clock=false;
            tipclock=true;
        }
    })
}
WaitPassgetval=function (a) {
    // alert($(a).text());
    pagenum=$(a).text();
    getWaitPassWork();
}
WaitPasslastpage=function () {
    if(pagenum>0)
    {
        pagenum--;
        getWaitPassWork();
    }
}
WaitPassnextpage=function () {
    if(pagenum<maxpage)
    {
        pagenum++;
        getWaitPassWork();
    }
}

getAllWorkList=function()
{
    if(clock)return;
    clock=true;
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
            clock=false;
        }
    })
}
passwork=function(btn)
{
    if(clock)return;
    clock=true;
    // alert($(btn).parent().parent().children("td").eq(0).text());
    $.ajax({
        url:"updatework",
        type:"POST",
        dataType:"text",
        data:{
            act:"PassWorkByID",
            workid:$(btn).parent().parent().children("td").eq(0).text()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
            alert(data);
            clock=false;
            getWaitPassWork();
        }
    })
}
nopasswork=function(btn)
{
    if(clock)return;
    clock=true;
    // alert($(btn).parent().parent().children("td").eq(0).text());
    $.ajax({
        url:"updatework",
        type:"POST",
        dataType:"text",
        data:{
            act:"noPassWorkByID",
            workid:$(btn).parent().parent().children("td").eq(0).text()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
            alert(data);
            clock=false;
            getWaitPassWork();
        }
    })
}