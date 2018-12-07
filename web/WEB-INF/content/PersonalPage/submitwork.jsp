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
    <%--<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>

    <script src="js/jQuery/jquery-3.3.1.min.js"></script>
    <script src="js/BootStrap/bootstrap.js"></script>
    <script src="js/submitworkjs.js"></script>
    <%--<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>  --%>
</head>
<body onload="">
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
        <h1>上传作品<small>Submit works</small></h1>
    </div>

</div>
<div class="panel panel-default" style="margin-left: 12%;width: 15%;float: left ">
    <div class="panel-heading">
        <h3 class="panel-title">&nbsp</h3>
    </div>
    <div class="panel-body" style="text-align: center">
        <a href="comPersonalindex" style="cursor: pointer">我的作品</a>
    </div>
    <div class="panel-body" style="text-align: center">
        上传作品
    </div>
    <div class="panel-body" style="text-align: center">
        <a href="workslog" style="cursor: pointer">作品记录</a>
    </div>
</div>
<div style="width: 100%;height: 1300px" >
    <div style="margin-left: 0px;width: 70%;height:1220px;float: left" id="mainpanel" >
        <form style="margin: 5%">
            <div class="dropdown">
                <label for="workname" >语言类型</label>
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    <span id="programtype">C/C++</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a style="cursor: pointer" onclick="selectprogramtype(this)">C/C++</a></li>
                    <li><a style="cursor: pointer" onclick="selectprogramtype(this)">java</a></li>
                    <li><a style="cursor: pointer" onclick="selectprogramtype(this)">python</a></li>
                    <li><a style="cursor: pointer" onclick="selectprogramtype(this)">PHP</a></li>
                    <li><a style="cursor: pointer" onclick="selectprogramtype(this)">C#</a></li>
                    <li role="separator" class="divider"></li>
                    <%--<li><a href="#">Separated link</a></li>--%>
                </ul>
            </div>
            <div class="form-group">
                <label for="workname">作品名称</label>
                <input style="width: 25%" type="email" class="form-control" id="workname" placeholder="作品名称">
            </div>
            <div class="form-group">
                <label for="faceimage">封面图</label>
                <input type="file" id="faceimage" accept="image/jpeg">
                <p class="help-block">选取一张图作为作品封面，如果未选择将使用系统默认封面</p>
            </div>
            <div class="form-group">
                <label for="showimage1">内容展示图1</label>
                <input type="file" id="showimage1" accept="image/jpeg">
                <p class="help-block">选取一张图作为作品展示图，如果未选择将为空</p>
            </div>
            <div class="form-group">
                <label for="showimage2">内容展示图2</label>
                <input type="file" id="showimage2" accept="image/jpeg">
                <p class="help-block">选取一张图作为作品展示图，如果未选择将为空</p>
            </div>
            <div class="form-group">
                <label for="showimage3">内容展示图3</label>
                <input type="file" id="showimage3" accept="image/jpeg">
                <p class="help-block">选取一张图作为作品展示图，如果未选择将为空</p>
            </div>
            <div class="form-group">
                <label for="video">视频文件</label>
                <input type="file" id="video" accept="audio/ogg">
                <p class="help-block">上传作品介绍视频，如果未选择将为空</p>
            </div>
            <div class="form-group">
                <label for="program">源代码</label>
                <input type="file" id="program" accept="application/zip">
                <p class="help-block">上传源代码压缩文件，如果未选择将为空</p>
            </div>
            <div class="checkbox">
                <label>
                    <div class="alert alert-danger" role="alert">
                        <strong>警告:</strong>请注意你上传的作品内容，如果为违规或无效的的作品，可能无法通过审核，甚至可能导致账号封禁。
                    </div>
                    <input type="checkbox" id="checkbox1" value="read"> 我已阅读警告
                </label>
            </div>
            <button type="button" class="btn btn-default" onclick="subfile()">确认上传</button>
        </form>
        <div class="progress" style="width: 70%;margin-left: 5%;display: none" id="uploadstatus">
            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                60%
            </div>
        </div>
    </div>
</div>
<footer class="panel-footer" style="background-color: #c0c0c0 ;">
    <div style="text-align: center;padding: 2% 0;">
        <a style="font-weight:bold;font-size:18px">Copyright ©2018 csxc</a>
    </div>
</footer>
</body>
</html>
