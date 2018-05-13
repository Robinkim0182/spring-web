<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    // 사용할 객체 초기화
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int pageNumTemp = 1;
    int listCount = 10;
    int pagePerBlock = 10;
    String whereSQL = "";
    // 파라미터
    String pageNum = request.getParameter("pageNum");
    String searchType = request.getParameter("searchType");
    String searchText = request.getParameter("searchText");
    // 파라미터 초기화
    if (searchText == null) {
        searchType = "";
        searchText = "";
    }
    if (pageNum != null) {
        pageNumTemp = Integer.parseInt(pageNum);
    }
    // 한글파라미터 처리
    String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
    // 데이터베이스 커넥션 및 질의문 실행
    // 검색어 쿼리문 생성
    if (!"".equals(searchText)) {
        if ("ALL".equals(searchType)) {
            whereSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') OR WRITER LIKE CONCAT('%',?,'%') OR CONTENTS LIKE CONCAT('%',?,'%') ";
        } else if ("SUBJECT".equals(searchType)) {
            whereSQL = " WHERE SUBJECT LIKE CONCAT('%',?,'%') ";
        } else if ("WRITER".equals(searchType)) {
            whereSQL = " WHERE WRITER LIKE CONCAT('%',?,'%') ";
        } else if ("CONTENTS".equals(searchType)) {
            whereSQL = " WHERE CONTENTS LIKE CONCAT('%',?,'%') ";
        }
    }
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/stone", "root", "1234");
        // 게시물의 총 수를 얻는 쿼리 실행
        pstmt = conn.prepareStatement("SELECT COUNT(NUM) AS TOTAL FROM BOARD" + whereSQL);
        if (!"".equals(whereSQL)) {
            if ("ALL".equals(searchType)) {
                pstmt.setString(1, searchTextUTF8);
                pstmt.setString(2, searchTextUTF8);
                pstmt.setString(3, searchTextUTF8);
            } else {
                pstmt.setString(1, searchTextUTF8);
            }
        }
        rs = pstmt.executeQuery();
        rs.next();
        int totalCount = rs.getInt("TOTAL");
        // 게시물 목록을 얻는 쿼리 실행
        pstmt = conn.prepareStatement("SELECT NUM, SUBJECT, WRITER, REG_DATE, HIT FROM BOARD "+whereSQL+" ORDER BY NUM DESC LIMIT ?, ?");
        if (!"".equals(whereSQL)) {
            // 전체검색일시
            if ("ALL".equals(searchType)) {
                pstmt.setString(1, searchTextUTF8);
                pstmt.setString(2, searchTextUTF8);
                pstmt.setString(3, searchTextUTF8);
                pstmt.setInt(4, listCount * (pageNumTemp-1));
                pstmt.setInt(5, listCount);
            } else {
                pstmt.setString(1, searchTextUTF8);
                pstmt.setInt(2, listCount * (pageNumTemp-1));
                pstmt.setInt(3, listCount);
            }
        } else {
            pstmt.setInt(1, listCount * (pageNumTemp-1));
            pstmt.setInt(2, listCount);
        }
        rs = pstmt.executeQuery();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>게시판 목록</title>
    <style type="text/css">
        * {font-size: 9pt;}
        p {width: 600px; text-align: right;}
        table thead tr th {background-color: gray;}
    </style>
    <script type="text/javascript">
        function goUrl(url) {
            location.href=url;
        }
        // 검색 폼 체크
        function searchCheck() {
            var form = document.searchForm;
            if (form.searchText.value == '') {
                alert('검색어를 입력하세요.');
                form.searchText.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form name="searchForm" action="boardList.jsp" method="get" onsubmit="return searchCheck();" >
    <p>
        <select name="searchType">
            <option value="ALL" selected="selected">전체검색</option>
            <option value="SUBJECT" <%if ("SUBJECT".equals(searchType)) out.print("selected=\"selected\""); %>>제목</option>
            <option value="WRITER" <%if ("WRITER".equals(searchType)) out.print("selected=\"selected\""); %>>작성자</option>
            <option value="CONTENTS" <%if ("CONTENTS".equals(searchType)) out.print("selected=\"selected\""); %>>내용</option>
        </select>
        <input type="text" name="searchText" value="<%=searchTextUTF8%>" />
        <input type="submit" value="검색" />
    </p>
</form>
<table border="1" summary="게시판 목록">
    <caption>게시판 목록</caption>
    <colgroup>
        <col width="50" />
        <col width="300" />
        <col width="80" />
        <col width="100" />
        <col width="70" />
    </colgroup>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록 일시</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (totalCount == 0) {
    %>
    <tr>
        <td align="center" colspan="5">등록된 게시물이 없습니다.</td>
    </tr>
    <%
    } else {
        int i = 0;
        while (rs.next()) {
            i++;
    %>
    <tr>
        <td align="center"><%=totalCount - i + 1 - (pageNumTemp - 1) * listCount %></td>
        <td><a href="boardView.jsp?num=<%=rs.getInt("NUM")%>&amp;pageNum=<%=pageNumTemp%>&amp;searchType=<%=searchType%>&amp;searchText=<%=searchText%>"><%=rs.getString("SUBJECT") %></a></td>
        <td align="center"><%=rs.getString("WRITER") %></td>
        <td align="center"><%=rs.getString("REG_DATE").substring(0, 10) %></td>
        <td align="center"><%=rs.getInt("HIT") %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
    <tfoot>
    <tr>
        <td align="center" colspan="5">
            <%
                // 페이지 네비게이터
                if(totalCount > 0) {
                    int totalNumOfPage = (totalCount % listCount == 0) ?
                            totalCount / listCount :
                            totalCount / listCount + 1;

                    int totalNumOfBlock = (totalNumOfPage % pagePerBlock == 0) ?
                            totalNumOfPage / pagePerBlock :
                            totalNumOfPage / pagePerBlock + 1;

                    int currentBlock = (pageNumTemp % pagePerBlock == 0) ?
                            pageNumTemp / pagePerBlock :
                            pageNumTemp / pagePerBlock + 1;

                    int startPage = (currentBlock - 1) * pagePerBlock + 1;
                    int endPage = startPage + pagePerBlock - 1;

                    if(endPage > totalNumOfPage)
                        endPage = totalNumOfPage;
                    boolean isNext = false;
                    boolean isPrev = false;
                    if(currentBlock < totalNumOfBlock)
                        isNext = true;
                    if(currentBlock > 1)
                        isPrev = true;
                    if(totalNumOfBlock == 1){
                        isNext = false;
                        isPrev = false;
                    }
                    StringBuffer sb = new StringBuffer();
                    if(pageNumTemp > 1){
                        sb.append("<a href=\"").append("boardList.jsp?pageNum=1&amp;searchType="+searchType+"&amp;searchText="+searchText);
                        sb.append("\" title=\"<<\"><<</a>&nbsp;");
                    }
                    if (isPrev) {
                        int goPrevPage = startPage - pagePerBlock;
                        sb.append("&nbsp;&nbsp;<a href=\"").append("boardList.jsp?pageNum="+goPrevPage+"&amp;searchType="+searchType+"&amp;searchText="+searchText);
                        sb.append("\" title=\"<\"><</a>");
                    } else {

                    }
                    for (int i = startPage; i <= endPage; i++) {
                        if (i == pageNumTemp) {
                            sb.append("<a href=\"#\"><strong>").append(i).append("</strong></a>&nbsp;&nbsp;");
                        } else {
                            sb.append("<a href=\"").append("boardList.jsp?pageNum="+i+"&amp;searchType="+searchType+"&amp;searchText="+searchText);
                            sb.append("\" title=\""+i+"\">").append(i).append("</a>&nbsp;&nbsp;");
                        }
                    }
                    if (isNext) {
                        int goNextPage = startPage + pagePerBlock;

                        sb.append("<a href=\"").append("boardList.jsp?pageNum="+goNextPage+"&amp;searchType="+searchType+"&amp;searchText="+searchText);
                        sb.append("\" title=\">\">></a>");
                    } else {

                    }
                    if(totalNumOfPage > pageNumTemp){
                        sb.append("&nbsp;&nbsp;<a href=\"").append("boardList.jsp?pageNum="+totalNumOfPage+"&amp;searchType="+searchType+"&amp;searchText="+searchText);
                        sb.append("\" title=\">>\">>></a>");
                    }
                    out.print(sb.toString());
                }
            %>
        </td>
    </tr>
    </tfoot>
</table>
<p>
    <input type="button" value="목록" onclick="goUrl('boardList.jsp');" />
    <input type="button" value="글쓰기" onclick="goUrl('boardWriteForm.jsp');" />
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