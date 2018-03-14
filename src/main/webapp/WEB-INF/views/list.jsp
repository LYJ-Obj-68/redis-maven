<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>用户列表</title>
</head>
<body>
<form action="" method="post">
    <a href="${pageContext.request.contextPath}/user/goAddPage">增加</a>
    <table border="1px" cellspacing="0px" style="border-collapse: collapse">
        <thead style="background-color: lightblue">
            <tr align="center">
                <td>编号</td>
                <td>姓名</td>
                <td>电话</td>
                <td>性别</td>
                <td>操作</td>
            </tr>
        </thead>
        <c:forEach items="${listUser}" var="u" begin="0" end="${listUserSize}">
            <tr>
                <td><c:out value="${u.uid }"></c:out></td>
                <td><c:out value="${u.uname }"></c:out></td>
                <td><c:out value="${u.uphone }"></c:out></td>
                <td><c:out value="${u.ugender }"></c:out></td>
                <td>
                    <a href="/user/getUser/${u.uid}">编辑</a>&nbsp;&nbsp;
                    <a href="/user/goDelete/${u.uid}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
