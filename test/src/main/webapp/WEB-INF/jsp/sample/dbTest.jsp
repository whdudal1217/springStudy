<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> dbTest </h1>
<p> 아 배고파 </p>
<%-- testNo : ${testNo}
testName : ${testName}
 --%>
<c:forEach var="result" items="${TestList}">
testNo : <c:out value="${result.testno}"/>
testName : <c:out value="${result.testname}"/>
</c:forEach>

그냥 테스트 중입니다
</body>
</html>