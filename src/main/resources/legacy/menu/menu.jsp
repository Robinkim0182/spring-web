<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    List<String> breakfast = new ArrayList<>();
    List<String> launch = new ArrayList<>();
    List<String> dinner = new ArrayList<>();
    // 파라미터
    String today = request.getParameter("today");
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        pstmt = conn.prepareStatement("SELECT * FROM menu WHERE today = ?");
        pstmt.setString(1, today);

        rs = pstmt.executeQuery();

        while(rs.next()) {
            if (rs.getString("meal").equals("breakfast")) {
                breakfast.add(rs.getString("menuName"));
            } else if (rs.getString("meal").equals("breakfast")) {
                launch.add(rs.getString("menuName"));
            } else if (rs.getString("meal").equals("dinner")) {
                dinner.add(rs.getString("menuName"));
            }
        }
%>
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
            <th></th>
            <th>아침</th>
            <th>점심</th>
            <th>저녁</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%=today%></td>
            <td>
                <ul>
                    <%
                        for (int i = 0 ; i < breakfast.size() ; i++) {
                    %>
                        <li><%=breakfast.get(i)%></li>
                    <%
                        }
                    %>
                </ul>
            </td>
            <td>
                <ul>
                    <%
                        for (int i = 0 ; i < launch.size() ; i++) {
                            System.out.print(launch.get(i));
                        }
                    %>
                </ul>
            </td>
            <td>
                <ul>
                    <%
                        for (int i = 0 ; i < dinner.size() ; i++) {
                            System.out.print(launch.get(i));
                        }
                    %>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>