<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>新增用户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <!-- form表单提交数据 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
    <!-- 美化按钮  -->
    <link href="${pageContext.request.contextPath}/static/css/beautify-bar.css" rel="stylesheet" />
</head>

<body>
<form action="${pageContext.request.contextPath}/user/goAdd" method="post">
    <table border="1px" cellspacing="0px" style="border-collapse: collapse">
        <tr>
            <th style="background-color: lightcyan;">姓名:</th>
            <td><input type="text" name="uname"/></td>
        </tr>
        <tr>
            <th style="background-color: lightcyan;">电话:</th>
            <td><input type="text" name="uphone"></td>
        </tr>
        <tr>
            <th style="background-color: lightcyan;">性别:</th>
            <td>
                <input type="radio" id="male" name="ugender" value="M" /><label for="male">男</label>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" id="female" name="ugender" value="W" /><label for="female">女</label>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="确定"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
