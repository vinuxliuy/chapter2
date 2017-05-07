<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/5/7
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>客户列表</title>
</head>
<body>
    <table>
        <tr>
            <th>客户名称</th>
            <th>联系人</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
            <th>创建时间</th>
        </tr>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <th>${customer.name}</th>
                <th>${customer.contect}</th>
                <th>${customer.telephone}</th>
                <th>${customer.email}</th>
                <th>${customer.cdate}</th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
