<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.jdbc.Work" %>
<%@ page import="package1.Table.WorkBean" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2018/10/16
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%
    if((String)session.getAttribute("adminname")==null)
    {
        out.println("<script>alert('请先登陆');window.location.href='Login'</script>");
    }
    else
    {
    session.setAttribute("LoginAction","loginout");
    session.setAttribute("WorkAction","getWorkByPage");
    }
%>
<html>
<head>
    <meta charset="utf-8">
    <title>作品后台管理系统</title>
    <link rel="stylesheet" href="style/backstage.css">
    <link rel="stylesheet" href="style/BootStrap/bootstrap.css">
    <link rel="stylesheet" href="style/BootStrap/bootstrap-theme.css">
    <%--<link type="text/css" rel="stylesheet" href="style/main.css">--%>
    <link type="text/css" rel="stylesheet" href="style/reset.css">
    <script src="js/jQuery/jquery-3.3.1.min.js"></script>
    <script src="js/js2.js"></script>
    <script src="js/hpage1js.js"></script>
    <script src="js/js1.js"></script>
</head>

<body>
<div class="head">
    <h3 class="head_text fr">后台管理系统</h3>
</div>
<div class="operation_user clearfix">
    <div class="link fl">当前目录</div>
    <div class="link fr">
        <b>当前用户:<%=(String)session.getAttribute("adminname")%></b>&nbsp;&nbsp;<a href="#" class="icon icon_i">首页</a><span></span><a href="#" class="icon icon_j">前进</a><span></span><a href="#" class="icon icon_t">后退</a><span></span><a href="#" class="icon icon_n" onclick="getAllWorkList()">刷新</a><span></span><a href="#" class="icon icon_e" onclick="loginout()">退出</a>
    </div>
</div>
<div class="content clearfix">
    <div class="main">
        <!--右侧内容-->
        <div class="cont">
            <div class="title">作品信息查看</div>
            <div class="details" id="mainpanel1">
                <%--<div class="details_operation clearfix">--%>
                    <%--<div class="bui_select">--%>
                        <%--<!--  <input type="button" value="添&nbsp;&nbsp;加" class="add">-->--%>
                    <%--</div>--%>
                    <%--<div class="fr">--%>
                        <%--<div class="text">--%>
                            <%--<span>测试功能1：</span>--%>
                            <%--<div class="bui_select">--%>
                                <%--<select name="" id="s1" class="select">--%>
                                    <%--<option value="1">测试内容</option>--%>
                                    <%--<option value="1">测试内容</option>--%>
                                    <%--<option value="1">测试内容</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="text">--%>
                            <%--<span>测试功能2：</span>--%>
                            <%--<div class="bui_select">--%>
                                <%--<select name="" id="s2" class="select">--%>
                                    <%--<option value="1">测试内容</option>--%>
                                    <%--<option value="1">测试内容</option>--%>
                                    <%--<option value="1">测试内容</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="text">--%>
                            <%--<span>测试点3</span>--%>
                            <%--<input type="text" value="" class="search">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <!--表格-->
                <table class="table" cellspacing="0" cellpadding="0">
                    <thead>
                    <tr>
                        <th width="15%">作品ID</th>
                        <th width="25%">作品名称</th>
                        <th width="35%">作者</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<WorkBean> workList=(List<WorkBean>)session.getAttribute("WorkList");
                        if(workList!=null)
                        {
                            int i=1;
                            for (WorkBean wk:
                                 workList) {
                                out.println("<tr>\n" +
                                        "<td>"+wk.getId()+"</td>\n" +
                                        "<td>"+wk.getName()+"</td>\n" +
                                        "<td>"+wk.getWritter()+"</td>\n" +
                                        "<td align=\"center\"><input type=\"button\" value=\"查看\" class=\"btn\"><input type=\"button\" value=\"删除\" class=\"btn\"></td>\n" +
                                        "</tr>");
                            }
                        }
                       %>
                    </tbody>
                </table>
                <nav aria-label="...">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="上一页">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li>
                            <a href="#" aria-label="下一页">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <!--左侧列表-->
    <div class="menu">
        <div class="cont">
            <div class="title">菜单</div>
            <ul class="mList">
                <li>
                    <h3><span>-</span>个人信息</h3>
                    <dl>
                        <dd><a href="#">修改密码</a></dd>
                        <dd><a href="#">修改详细信息</a></dd>
                    </dl>
                </li>
                <li>
                    <h3><span>+</span>作品管理</h3>
                    <dl>
                        <dd><a style="cursor: pointer" onclick="getWaitPassWork()">审核作品</a></dd>
                        <dd><a href="#">查看作品</a></dd>
                    </dl>
                </li>
                <li>

                </li>
            </ul>
        </div>
    </div>

</div>
<div class="footer">
    <a style="font-weight:bold;font-size:18px;">Copyright ©2018 csxc</a>
</div>
</body>
</html>

