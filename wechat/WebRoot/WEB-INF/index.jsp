<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>显示用户信息</title>
        <style type="text/css">
            table,td{
                border: 1px solid;
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <table>
            <tr>
            <td>ID</td>
                <td>用户ID</td>
                <td>用户状态</td>
            </tr>
            <%--遍历lstUsers集合中的User对象 --%>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.userId}</td>
                    <td>${user.remindSetting}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>