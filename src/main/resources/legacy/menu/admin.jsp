<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<span>학식 메뉴입니다.</span>
<div>
    <form method="post" action="/menu/adminAction.jsp">
        <label for="today">날짜</label>
        <input type="text" id="today" name="today" placeholder="0501, 0502, . . . " />

        <label for="meal">시간</label>
        <select id="meal" name="meal">
            <option value="breakfast">아침</option>
            <option value="launch">점심</option>
            <option value="dinner">저녁</option>
        </select>

        <label for="menu">메뉴</label>
        <input type="text" id="menu" name="menu" placeholder="메뉴" />
        <input type="submit"/>
    </form>
</div>
</body>
</html>