<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2018/10/29
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if(request.getParameter("act")!=null)
        {
            String act=(String)request.getParameter("act");
            switch (act)
            {
                case "getpasserror":
                    out.print("<script>alert(\"获取验证码失败,请稍后再试\")</script>");
                    break;
                case "usepasserror":
                    out.print("<script>alert(\"验证码错误\")</script>");
                    break;
                case "getpasssuccess":
                    out.print("<script>alert(\"获取验证码成功\")</script>");
                    break;
                case "registererror":
                    out.print("<script>alert(\"注册失败\")</script>");
                    break;
                case "registersuccess":
                    out.print("<script>alert(\"注册成功\")</script>");
                    break;
            }
        }
    %>
    <link rel="stylesheet" href="style/BootStrap/bootstrap.css">
    <link rel="stylesheet" href="style/BootStrap/bootstrap-theme.css">
    <script src="js/jQuery/jquery-3.3.1.min.js"></script>
    <script src="js/js2.js"></script>
    <script></script>
    <title>账号注册</title>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">

        <div style="width: 40%;float: left"><h1><a href="index" style="cursor: pointer">LOGO</a></h1></div>
        <div style="width: 60%;float: left">
            <h1><font color="#c0c0c0">账号注册</font> <small>Create Account</small></h1>
        </div>
</div>
<br><br><br><br><br>
<form accept-charset="UTF-8" class="form-horizontal">
    <br>
    <div class="form-group">
        <label for="name1" class="col-sm-2 control-label" style="margin-left:23%">昵称</label>
        <div class="col-sm-10" style="width: 20%">
            <input name="name1" type="text" class="form-control" id="name1" placeholder="昵称" onkeyup="isName()">
        </div>
        <label id="tip5" for="phonenumber" class="control-label" style="" ></label>
    </div>
    <div class="form-group">
        <label for="username1" class="col-sm-2 control-label" style="margin-left:23%">账号</label>
        <div class="col-sm-10" style="width: 20%">
            <input name="username" type="text" class="form-control" id="username1" placeholder="账号" onkeyup="isUserName()">
        </div>
        <label id="tip1" for="phonenumber" class="control-label" style="" ></label>
    </div>

    <div class="form-group">
        <label for="password1" class="col-sm-2 control-label" style="margin-left:23%">密码</label>
        <div class="col-sm-10" style="width: 20%">
            <input name="password" type="password" class="form-control" id="password1" placeholder="密码" onkeyup="isPassword()">
        </div>
        <label id="tip2" for="phonenumber" class="control-label" style="" ></label>
    </div>
    <div class="form-group">
        <label for="password2" class="col-sm-2 control-label" style="margin-left:23%">确认密码</label>
        <div class="col-sm-10" style="width: 20%">
            <input type="password" class="form-control" id="password2" placeholder="确认密码" onkeyup="passwordequals()">
        </div>
        <label id="tip3" for="phonenumber" class="control-label" style="" ></label>
    </div>
    <div class="form-group">
        <label for="phonenumber" class="col-sm-2 control-label" style="margin-left:23%">手机号</label>
        <div class="col-sm-10" style="width: 20%">
            <input name="phonenumber" type="text" class="form-control" id="phonenumber" placeholder="手机号" onkeyup="isPhoneNumber()">
        </div>
        <label id="tip4" for="phonenumber" class="control-label" style="" ></label>
    </div>
    <div class="form-group">
    <label for="invitenum" class="col-sm-2 control-label" style="margin-left:23%">邀请码</label>
    <div class="col-sm-10" style="width: 20%">
        <input name="invitenum" type="text" class="form-control" id="invitenum" placeholder="邀请码" onkeyup="isInviteNum()">
    </div>
    <label id="tip6" for="phonenumber" class="control-label" style="" ></label>
    </div>
    <div class="form-group">
        <label for="passnum" class="col-sm-2 control-label" style="margin-left:23%">验证码</label>
        <div class="col-sm-10" style="width: 20%">
            <input name="passnum" type="text" class="form-control" id="passnum" placeholder="验证码">
        </div>
        <button type="button" class="btn btn-default" onclick="getpass(this)">获取验证码</button>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10" style="margin-left:40%">
            <div class="checkbox" >
                <label>
                    <input name="checkbox1" type="checkbox" value="selected"> 我同意<a href="#">《相关条款》</a>
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10" style="margin-left:45%">
            <button type="button" class="btn btn-default" onclick="submitrespons()">注册账号</button>
        </div>
    </div>
</form>
<div class="hr_25"></div>
<footer class="panel-footer" style="background-color: #c0c0c0 ;">
    <div style="text-align: center;padding: 2% 0;">
        <a style="font-weight:bold;font-size:18px">Copyright ©2018 csxc</a>
    </div>

</footer>
</body>
</html>
