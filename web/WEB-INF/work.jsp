<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2018/11/9
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%
        if(session.getAttribute("adminname")==null&&session.getAttribute("Pass")==null)
        {

            out.print("<script>alert(\"作品未审核通过\");window.location.href=\"./index\";</script>");
        }
    %>
    <link rel="stylesheet" href="/style/BootStrap/bootstrap.min.css">
    <link rel="stylesheet" href="/style/BootStrap/bootstrap-theme.min.css">
    <script src="js/jQuery/jquery-3.3.1.min.js"></script>
    <script src="js/workjs.js"></script>
    <title id="title">作品信息</title>
</head>
<body onload="OnloadPage()">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index">
                LOGO
                <%--<img alt="Brand" src="">--%>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="">
            <ul class="nav navbar-nav" style="width: 90%">
                <li style="margin-left: 5%"></li>
            </ul>
        </div>
    </div>
</nav>
<div style="width: 90%;height:600px;margin-left: 5%;margin-top: 2%;border: 1px solid">
    <div style="width: 400px;height: 300px;margin-left: 5%;margin-top: 5%;float: left">
        <img id="maximg1" src="#" style="width: 100%;height: 100%;border:1px solid black">
    </div>
    <div style="margin-left: 40%;margin-top: 5%">
        <h1>作品名称：<font id="workname">读取数据中</font></h1><br>
        <h1>作者：<font id="writter">读取数据中</font></h1><br>
        <h1 id="video1">
            读取数据中
        </h1><br>
        <h1 id="program1">
            读取数据中
        </h1><br>
    </div>
    <br>
    <div class="row" style="margin-left: 8%;width: 400px" id="imgpanel">
        <div class="col-xs-6 col-md-3"  style="width: 100px;height: 75px;padding: 5px;">
            <a  class="thumbnail" style="cursor: pointer" onclick="exchangeimg(this)">
                <img src="#" style="width: 90px;height: 70px;border:1px solid black">
            </a>
        </div>
        <div class="col-xs-6 col-md-3"  style="width: 100px;height: 75px;padding: 5px;">
            <a  class="thumbnail" style="cursor: pointer" onclick="exchangeimg(this)">
                <img src="#" style="width: 90px;height: 70px;border:1px solid black">
            </a>
        </div>
        <div class="col-xs-6 col-md-3"  style="width: 100px;height: 75px;padding: 5px;">
            <a  class="thumbnail" style="cursor: pointer" onclick="exchangeimg(this)">
                <img src="#" style="width: 90px;height: 70px;border:1px solid black">
            </a>
        </div>
    </div>
</div>

</body>  
</html>
