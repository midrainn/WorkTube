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
    <%
        if((String)session.getAttribute("name")!=null)
        {
            out.println("<script>alert('已经登录');window.location.href='index'</script>");
        }
    %>
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
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div style="width: 40%;float: left"><h1><a href="index" style="cursor: pointer">LOGO</a></h1></div>
        <div style="width: 60%;float: left">
            <h1><font color="#c0c0c0">欢迎登陆</font> <small>Welcome</small></h1>
        </div>
    </div>
    <br><br><br><br><br>
    <div style="height: 300px">
        <div id="mainpanel1" style="margin-top: 100px" >
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
                    <div class="col-sm-offset-2 col-sm-10" style="margin-left:40%;">
                        <button  type="button" class="btn btn-success" onclick="commlogin(this)">登陆</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


<div class="hr_25"></div>
<div class="footer">
    <a style="font-weight:bold;font-size:18px">Copyright ©2018 csxc</a>
</div>
</body>
</html>


