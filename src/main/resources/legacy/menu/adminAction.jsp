<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    Connection conn = null;
    PreparedStatement pstmt = null;

    // 파라미터
    String today = request.getParameter("today");
    String meal = request.getParameter("meal");
    String menu = request.getParameter("menuName");
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        pstmt = conn.prepareStatement("INSERT INTO menu (today, meal, menu) VALUES (?, ?, ?)");
        pstmt.setString(1, today);
        pstmt.setString(2, meal);
        pstmt.setString(3, menu);

        pstmt.execute();
    } catch (Exception e) {
        ;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<script type="text/javascript">
    alert('OK');
    document.location.href="/static-menu/admin.jsp";
</script>
<body>
</body>
</html>