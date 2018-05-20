<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        table {border-collapse: collapse}
        table th, td {border: 1px solid black; padding: 0px;}
    </style>
</head>
<body>
<div>
    <table>
        <thead>
        <tr>
            <th>${today}</th>
            <th>아침</th>
            <th>점심</th>
            <th>저녁</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td>
                <ul>
                    <c:forEach items="${breakfast}" var="menu">
                        <li>${menu}</li>
                    </c:forEach>
                </ul>
            </td>
            <td>
                <ul>
                    <c:forEach items="${launch}" var="menu">
                        <li>${menu}</li>
                    </c:forEach>
                </ul>
            </td>
            <td>
                <ul>
                    <c:forEach items="${dinner}" var="menu">
                        <li>${menu}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>