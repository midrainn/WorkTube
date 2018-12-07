<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2018/10/16
  Time: 8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <%session.setAttribute("LoginAction","login");%>
    <meta charset="utf-8">
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="style/reset.css">
    <link type="text/css" rel="stylesheet" href="style/main.css">
    <link rel="stylesheet" href="style/BootStrap/bootstrap.css">
    <link rel="stylesheet" href="style/BootStrap/bootstrap-theme.css">
    <script src="js/jQuery/jquery-3.3.1.min.js"></script>
    <script src="js/js2.js"></script>
</head>

<body>
    <%--<div class="headerBar">--%>
        <%--<div class="logoBar login_logo">--%>
            <%--<div class="comWidth">--%>
                <%--<div class="logo fl">--%>
                <%--</div>--%>
                <%--<h3 class="welcome_title">欢迎登陆作品后台管理系统</h3>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--<form action="login" accept-charset="UTF-8">--%>
    <%--<div class="loginBox">--%>
        <%--<div class="login_cont">--%>
            <%--<ul class="login">--%>
                <%--<li class="l_tit">用户名</li>--%>
                <%--<li class="mb_10"><input type="text" class="login_input user_icon" name="username" placeholder="请输入用户名"></li>--%>
                <%--<li class="l_tit">密码</li>--%>
                <%--<li class="mb_10"><input type="text" class="login_input user_icon" name="password" placeholder="请输入密码"></li>--%>
                <%--<li class="autoLogin"><input type="checkbox" id="a1" class="checked"><label for="a1">自动登录</label></li>--%>
                <%--&lt;%&ndash;<li><s:submit  name="" key="" class="login_btn"></s:submit></li>&ndash;%&gt;--%>
                <%--<li><input type="submit" value="" class="login_btn"></li>--%>
            <%--</ul>--%>

        <%--</div>--%>
    <%--</div>--%>
<%--</form>--%>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div style="margin-left:32%">
            <h1><font color="#c0c0c0">欢迎登陆后台管理系统</font> <small>Welcome</small></h1>
        </div>
    </div>
    <br><br><br><br><br>
<form accept-charset="UTF-8" class="form-horizontal">
    <br>
    <div class="form-group">
        <label for="username1" class="col-sm-2 control-label" style="margin-left:23%">账号</label>
        <div class="col-sm-10" style="width: 20%">
            <input name="username" type="text" class="form-control" id="username1" placeholder="账号">
        </div>
    </div>

    <div class="form-group">
        <label for="password1" class="col-sm-2 control-label" style="margin-left:23%">密码</label>
        <div class="col-sm-10" style="width: 20%">
            <input name="password" type="password" class="form-control" id="password1" placeholder="密码">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10" style="margin-left:40%">
            <div class="checkbox" >
                <label>
                    <input name="checkbox1" type="checkbox" value="selected"> 自动登陆
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10" style="margin-left:40%">
            <button type="button" class="btn btn-default" onclick="adminlogin()">登陆</button>
        </div>
    </div>
</form>
<div class="hr_25"></div>
<div class="footer">
    <a style="font-weight:bold;font-size:18px">Copyright ©2018 csxc</a>
</div>
</body>
</html>


