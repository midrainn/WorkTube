<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.jdbc.Work" %>
<%@ page import="package1.Table.WorkBean" %>
<%@page import="package1.JavaBean.likeBean" %>
<%@ page import="package1.DAO.DAOImp.workDAOImp1" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2018/10/16
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <%
        if(session.getAttribute("ProgramType")!=null)
        {
            pageContext.setAttribute("ProgramType",session.getAttribute("ProgramType"));
        }
        else {
            pageContext.setAttribute("ProgramType","all");
        }

    %>
    <%if(session.getAttribute("MaxPageNum")==null)
    {
        out.println("<script>window.location.href=\"index\"</script>");
    }
    if(session.getAttribute("WorkList")==null)
    {
//        out.println("<script>alert(\"List空\");window.location.href=\"error\"</script>");
        out.println("<script>window.location.href=\"index\"</script>");
    }
    %>
    <meta charset="utf-8">
    <title>主页面</title>
    <link type="text/css" rel="stylesheet" href="style/reset.css">
    <link type="text/css" rel="stylesheet" href="style/main.css">
    <%--<link rel="stylesheet" href="style/BootStrap/bootstrap.css">--%>
    <%--<link rel="stylesheet" href="style/BootStrap/bootstrap-theme.css">--%>
    <script src="js/jQuery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/js1.js"></script>
    <script src="js/js2.js"></script>
    <!--[if IE 6]>
    <!--<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js"></script>-->
    <!--<script type="text/javascript" src="js/ie6Fixpng.js"></script>-->
    <![endif]-->
</head>
<body>
<div class="headerBar">
    <div class="topBar">
        <div class="comWidth">
            <div class="leftArea">
                <a href="#" class="collection">收藏网站</a>
            </div>
            <div class="rightArea">
                <%
				if(session.getAttribute("name")==null)
				{
					String str="欢迎来到作品观看系统！<a href=\"comLogin\">[登录]</a><a href=\"register\">[注册]</a>";
                    out.println(str);
                }
                else
                {
                    String str="用户名:"+session.getAttribute("name")+"<a href=\"comPersonalindex\">&nbsp&nbsp[个人中心]</a> <a href=\"#\" onclick=\"loginout()\">&nbsp&nbsp[退出登陆]</a>";
                    out.println(str);
                }
                %>

            </div>
        </div>
    </div>
    <div class="logoBar">
        <div class="comWidth">
            <div class="logo fl">
            </div>
            <div class="search_box fl">
                <input type="text" class="search_text fl">
                <input type="button" value="搜 索" class="search_btn fr">
            </div>
        </div>
    </div>
    <div class="navBox">
        <div class="comWidth clearfix">
            <div class="shopClass fl">
                <h3>全部作品分类<i class="shopClass_icon"></i></h3>
                <div class="shopClass_show hide">
                    <dl class="shopClass_item">
                    </dl>
                </div>
                <div class="shopClass_list hide">
                    <div class="shopClass_cont">
                        <dl class="shopList_item">
                        </dl>
                    </div>
                </div>
            </div>
            <ul class="nav fl">
                <li><a href="#" class="active">JAVA</a></li>
                <li><a href="#">C/C++</a></li>
                <li><a href="#">WEB</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="hr_15"></div>
<div class="comWidth clearfix products">
    <div class="leftArea">
        <div class="leftNav vertical">
            <h3 class="nav_title">作品</h3>
            <div class="nav_cont">
                <h3>前端</h3>
                <ul class="navCont_list clearfix">
                    <li><a href="#">HTML/CSS</a></li>
                    <li><a href="#">JS</a></li>
                </ul>
            </div>
            <div class="nav_cont">
                <h3>后端</h3>
                <ul class="navCont_list clearfix">
                    <li><a href="#">java</a></li>
                    <li><a href="#">C/C++</a></li>
                    <li><a href="#">Python</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="rightArea">
        <div class="screening_box">
            <dl class="screening clearfix">
                <dt>类型</dt>
                <dd class="limit"><a href="#" class="active">不限</a></dd>
                <dd class="screening_list">
                    <ul class="clearfix">
                        <li><a href="#">前端</a></li>
                        <li><a href="#">后端</a></li>
                    </ul>
                </dd>
            </dl>
            <dl class="screening clearfix">
                <dt>语言</dt>
                <dd class="limit"><a href="index" <%if(((String)pageContext.getAttribute("ProgramType")).equals("all"))out.print("class=\"active\"");%>>不限</a></dd>
                <dd class="screening_list">
                    <ul class="clearfix">
                        <li><a href="index?programtype=java" <%if(((String)pageContext.getAttribute("ProgramType")).equals("java"))out.print("class=\"active\"");%> >Java</a></li>
                        <li><a href="index?programtype=C/Cplus" <%if(((String)pageContext.getAttribute("ProgramType")).equals("C/C++"))out.print("class=\"active\"");%>>C/C++</a></li>
                        <li><a href="index?programtype=python" <%if(((String)pageContext.getAttribute("ProgramType")).equals("python"))out.print("class=\"active\"");%>>Python</a></li>
                        <li><a href="index?programtype=PHP" <%if(((String)pageContext.getAttribute("ProgramType")).equals("PHP"))out.print("class=\"active\"");%>>PHP</a></li>
                        <li><a href="index?programtype=Csharp" <%if(((String)pageContext.getAttribute("ProgramType")).equals("C#"))out.print("class=\"active\"");%>>C#</a></li>.
                    </ul>
                </dd>
            </dl>
        </div>
        <div class="hr_15"></div>
        <div class="addInfo">
            <div class="address">
            </div>
            <div class="fr screen_text">
            </div>
        </div>
        <div class="products_list screening_list_more clearfix">
            <%
                List<WorkBean> workBeanList=(List<WorkBean>)session.getAttribute("WorkList");
                if(workBeanList!=null)
                {
                    String URL="#";
                    String WorkName="";
                    String Writter="";
                    String type="";
                    int LikeNum=0;

                    for (WorkBean wk:workBeanList)
                    {
                        type=wk.getType();
                        if(type.equals("C/Cplus"))type="C/C++";
                        String id=wk.getId();
                        URL=wk.getUrl();
                        WorkName=wk.getName();
                        Writter=wk.getWritter();
                        LikeNum=wk.getLike_num();
                        String htmlmsg="";
                        if(LikeNum<9000)
                        {
                            htmlmsg=" " +
                                    "<div class='item'> " +
                                    "<div class='item_cont'> " +
                                    "<div class='img_item'> " +
                                    "<a style=\"cursor: pointer\" id='"+id+"' onclick='Work(this)'><img src='"+URL+"' alt=''></a> " +
                                    "</div> " +
                                    "<p><a style=\"cursor: pointer\" id='"+id+"' onclick='Work(this)'>"+WorkName+"</a></p> " +
                                    "<p class='money'>作者:"+Writter+"</p> " +
                                    "<p>点赞量:"+LikeNum+" &nbsp&nbsp"+ type+"</p>"+
                                    "<p><a href='like?id="+id+"' class='addCar'>点赞</a></p> " +
                                    "</div> " +
                                    "</div>";
                        }
                        else
                        {
                            htmlmsg=" " +
                                    "<div class='item'> " +
                                    "<div class='item_cont'> " +
                                    "<div class='img_item'> " +
                                    "<a style=\"cursor: pointer\" id='"+id+"'onclick='Work(this)'><img src='"+URL+"' alt=''></a> " +
                                    "</div> " +
                                    "<p><a style=\"cursor: pointer\" id='"+id+"' onclick='Work(this)'>"+WorkName+"</a></p> " +
                                    "<p class='money'>作者:"+Writter+"</p> " +
                                    "<p>点赞量:"+LikeNum+" &nbsp&nbsp"+type +"</p>"+
                                    "<p><a href='like?id="+id+"' class='addCar'>点赞</a></p> " +
                                    "</div> " +
                                    "<div class='hot'> " +
                                    "</div> " +
                                    "</div>";
                        }
                        out.println(htmlmsg);
                    }
                }
            %>
        </div>
        <div class="hr_25"></div>
        <div class="page">
            <%
                if(session.getAttribute("MaxPageNum")!=null)
                {
                    int MaxPageNum=(int)session.getAttribute("MaxPageNum");

                    String htmlmsg="<a href='/index?act1=LastPage'>上一页</a>";
                    int pagenum=(int)session.getAttribute("PageNum");
                    if(pagenum%9==0)
                    {
                        pagenum/=9;
                    }
                    else
                    {
                        pagenum=pagenum/9+1;
                    }
                    if(MaxPageNum%9==0)
                    {
                        MaxPageNum=MaxPageNum/9;
                    }
                    else
                    {
                        MaxPageNum=MaxPageNum/9+1;
                    }
                    if(pagenum<6)
                    {
                        for(int i=1;i<=MaxPageNum;i++)
                        {
                            if(i==pagenum+1)
                            {
                                htmlmsg=htmlmsg+"<strong href=\"index?ChangePage="+(i-1)+"\">"+i+"</strong>";
                            }
                            else if(i<pagenum+6)
                            {
                                htmlmsg=htmlmsg+"<a href=\"index?ChangePage="+(i-1)+"\">"+i+"</a>";
                            }
                            else
                            {
                                htmlmsg=htmlmsg+"<span class='h1'>......</span><a href=\"index?ChangePage="+(MaxPageNum-1)+"\""+MaxPageNum+"</a>";
                                break;
                            }
                        }
                    }
                    else
                    {
                        for(int i=pagenum+1;i<=MaxPageNum;i++)
                        {
                            if(i==pagenum+1)
                            {
                                htmlmsg=htmlmsg+"<strong href=\"index?ChangePage="+(i-1)+"\">"+i+"</strong>";
                            }
                            else if(i<pagenum+6)
                            {
                                htmlmsg=htmlmsg+"<a href=\"index?ChangePage="+(i-1)+"\">"+i+"</a>";
                            }
                            else
                            {
                                htmlmsg=htmlmsg+"<span class='h1'>......</span><a href=\"index?ChangePage="+(MaxPageNum-1)+"\""+MaxPageNum+"</a>";
                            }
                        }
                    }
                    htmlmsg=htmlmsg+"<a href='/index?act1=NextPage'>下一页</a><span class='morePage'>共"+MaxPageNum+"页,到第</span><input type='text' class='pageNum'><span class='ye'>页</span><input type='button' value='确定' class='page_btn'";
                    out.println(htmlmsg);
                }
    %>
        </div>
    </div>
</div>
</div>
<div class="hr_25"></div>
<div class="footer">
    <p>Copyright &copy; 2018 csxc</p>
</div>
</body>
</html>

