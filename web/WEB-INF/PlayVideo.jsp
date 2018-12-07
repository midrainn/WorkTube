<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2018/11/29
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/style/BootStrap/bootstrap.min.css">
    <link rel="stylesheet" href="/style/BootStrap/bootstrap-theme.min.css">
    <script src="js/jQuery/jquery-3.3.1.min.js"></script>
    <title>播放页</title>
</head>
<body>
    <div style="height: 10%"></div>

    <div style="height:70%;width: 60%;margin-left: 20%">
        <h1>name</h1>
        <video style="width: 100%;height: 100%" controls>
            <%--<source src="./WorkFile/videos/testvideo.Ogg" type="video/ogg">--%>
            <source src="./WorkFile/videos/testvideo.mp4" type="video/mp4">
                <source src="<%if(session.getAttribute("VideoSRC")!=null)out.print((String)session.getAttribute("VideoSRC"));
                                else out.print("#");%>" type="video/mp4">
                <source src="<%if(session.getAttribute("VideoSRC")!=null)out.print((String)session.getAttribute("VideoSRC"));
                                else out.print("#");%>" type="video/ogg">
            视频无法播放
        </video>
    </div>
</body>
</html>
