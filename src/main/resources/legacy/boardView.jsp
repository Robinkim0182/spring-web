<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    // 사용할 객체 초기화
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    // 파라미터
    String num = request.getParameter("num");
    String pageNum = request.getParameter("pageNum");
    String searchType = request.getParameter("searchType");
    String searchText = request.getParameter("searchText");
    try {
        // 데이터베이스 객체 생성
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/stone", "root", "1234");
        // 조회수 증가 쿼리 실행
        pstmt = conn.prepareStatement("UPDATE BOARD SET HIT = HIT + 1 WHERE NUM = ?");
        pstmt.setString(1, num);
        pstmt.executeUpdate();
        // 게시물 상세 조회 쿼리 실행
        pstmt = conn.prepareStatement(
                "SELECT NUM, SUBJECT, CONTENTS, WRITER, HIT, REG_DATE FROM BOARD "+
                        "WHERE NUM = ?");
        pstmt.setString(1, num);
        rs = pstmt.executeQuery();
        rs.next();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>게시판 상세보기</title>
    <style type="text/css">
        * {font-size: 9pt;}
        .btn_align {width: 600px; text-align: right;}
        table tbody tr th {background-color: gray;}
    </style>
    <script type="text/javascript">
        function goUrl(url) {
            location.href=url;
        }
        // 삭제 체크
        function deleteCheck(url) {
            if (confirm('정말 삭제하시겠어요?')) {
                location.href=url;
            }
        }
    </script>
</head>
<body><%=searchText%>
<table border="1" summary="게시판 상세조회">
    <caption>게시판 상세조회</caption>
    <colgroup>
        <col width="100" />
        <col width="500" />
    </colgroup>
    <tbody>
    <tr>
        <th align="center">제목</th>
        <td><%=rs.getString("SUBJECT") %></td>
    </tr>
    <tr>
        <th align="center">작성자/조회수</th>
        <td><%=rs.getString("WRITER") %> / <%=rs.getInt("HIT") %></td>
    </tr>
    <tr>
        <th align="center">등록 일시</th>
        <td><%=rs.getString("REG_DATE") %></td>
    </tr>
    <tr>
        <td colspan="2"><%=rs.getString("CONTENTS") %></td>
    </tr>
    </tbody>
</table>
<p class="btn_align">
    <input type="button" value="목록" onclick="goUrl('boardList.jsp?pageNum=<%=pageNum%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>');" />
    <input type="button" value="수정" onclick="goUrl('boardModifyForm.jsp?num=<%=num%>&amp;pageNum=<%=pageNum%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>');" />
    <input type="button" value="삭제" onclick="deleteCheck('boardProcess.jsp?mode=D&amp;num=<%=num%>&amp;pageNum=<%=pageNum%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>');" />
</p>
</body>
</html>
<%
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    }
%>