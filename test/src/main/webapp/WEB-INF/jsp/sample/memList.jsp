<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 회원목록 </title>
</head>
<body>
	<div>
		<h1> 회원목록 </h1>
	</div>
	<div>
		<c:choose>
			<c:when test="${empty memberList}">
				<p> 데이터가 없습니다. </p>
			</c:when>
			<c:otherwise>
				<table border="1">
					<tr>
						<td>번호</td>
						<td>아이디</td>
						<td>이름</td>
					</tr>
					<c:forEach items="${memberList}" var="list">
						<tr>
							<td> <c:out value="${list.memSeq}" /> </td>
							<td> <c:out value="${list.memId}" /> </td>
							<td> <c:out value="${list.memName}" /> </td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<input type="button" value="회원가입" onclick="location.href='memberInsert.do'">
	</div>
</body>
</html>