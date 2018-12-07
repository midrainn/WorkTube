<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2018/10/30
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if((String)session.getAttribute("name")==null)
        {
            out.println("<script>alert('请先登陆');window.location.href='comLogin'</script>");
        }
    %>
    <title>个人中心</title>
    <link rel="stylesheet" href="style/BootStrap/bootstrap.css">
    <link rel="stylesheet" href="style/BootStrap/bootstrap-theme.css">
    <script src="js/jQuery/jquery-3.3.1.min.js"></script>
    <script src="js/comPersonalindexjs.js"></script>
</head>
<body onload="getAllWork()">
<div class="navbar navbar-inverse navbar-fixed-top" style="background-image: linear-gradient(to bottom, #3c3c30 0%, #4a1b42 100%);">
    <div style="margin-left:1%;float:left">
        <h2><a href="index" style="color: #c0c0c0;">LOGO</a></h2>
    </div>
    <div style="margin-left:2%;width: 20%;float:left">
        <h2><font color="#c0c0c0">个人中心</font> <small>Personal</small></h2>
    </div>
    <div class="navbar-collapse collapse" role="navigation" style="width: 100%">
        <ul class="nav navbar-nav" style="padding-top: 1%;border: 0px solid;height: 80%;float: left;margin-left: 5%">
                <li style="">
                    <a style="cursor: pointer;">个人作品</a>
                </li>
        </ul>
        <ul class="nav navbar-nav" style="padding-top: 1%;border: 0px solid;height: 80%;float: left;margin-left: 5%">
            <li style="">
                <a style="cursor: pointer;">个人资料</a>
            </li>
        </ul>
        <ul class="nav navbar-nav" style="padding-top: 1%;border: 0px solid;height: 80%;float: left;margin-left: 5%">
            <li style="">
                <a style="cursor: pointer;">我的关注</a>
            </li>
        </ul>
        <ul class="nav navbar-nav" style="padding-top: 1%;border: 0px solid;height: 80%;float: left;margin-left: 5%">
            <li style="">
                <a style="cursor: pointer;">隐私设置</a>
            </li>
        </ul>
    </div>
</div>
<br><br><br>
<div class="page-header" style="margin: 0">
    <div style="margin-left: 25%">
        <h1>我的作品<small>My works</small></h1>
    </div>

</div>
<div class="panel panel-default" style="margin-left: 12%;width: 15%;float: left ">
    <div class="panel-heading">
        <h3 class="panel-title">&nbsp</h3>
    </div>
    <div class="panel-body" style="text-align: center">
        我的作品
    </div>
    <div class="panel-body" style="text-align: center">
        <a href="submitwork" style="cursor: pointer">上传作品</a>
    </div>
    <div class="panel-body" style="text-align: center">
        <a href="workslog" style="cursor: pointer">作品记录</a>
    </div>
</div>
<div style="width: 100%;height: 1300px" >
    <div style="margin-left: 0px;width: 70%;height:1220px;float: left" id="mainpanel" >
        <div class="row">
            <div class="col-sm-6 col-md-4" style="width: 250px">
                <div class="thumbnail">
                    <img src="WorkFile/Images/C++.jpg" alt="...">
                    <div class="caption">
                        <a href="#" style=""><h3 style="color: black">数据查询中</h3></a>
                        <p>...</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4" style="width: 250px">
                <div class="thumbnail">
                    <img src="WorkFile/Images/C++.jpg" alt="...">
                    <div class="caption">
                        <h3>数据查询中</h3>
                        <p>...</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4" style="width: 250px">
                <div class="thumbnail">
                    <img src="WorkFile/Images/C++.jpg" alt="...">
                    <div class="caption">
                        <h3>数据查询中</h3>
                        <p>...</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4" style="width: 250px">
                <div class="thumbnail">
                    <div style="width: 210px;height: 170px">
                        <img style="width: 210px;height: 170px" src="WorkFile/Images/C++.jpg" alt="...">
                    </div>
                    <div class="caption">
                        <h3>数据查询中</h3>
                        <p>...</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div  style="width: 100%">
        <nav aria-label="Page navigation" style="margin-left: 27%;">
            <ul class="pagination" id="pagenav1" style="margin: 0">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">上一页</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">下一页</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<footer class="panel-footer" style="background-color: #c0c0c0 ;">
    <div style="text-align: center;padding: 2% 0;">
        <a style="font-weight:bold;font-size:18px">Copyright ©2018 csxc</a>
    </div>
</footer>
</body>
</html>
