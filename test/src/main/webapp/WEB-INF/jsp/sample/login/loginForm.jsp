<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$("#memPwd").keydown(function(key) { //키를 눌렀을때 keydown
	if(key.keyCode == 13){ //엔터키를 눌렀을때
		login();
	}
});
function login() {
	document.memberExist.action = "<c:url value='/login/loginForm.do'/>";
	document.memberExist.submit();
}
function memInsert() {
	alert("?");
	location.href ="../memberInsert.do";
}
</script>
</head>
<body>
<div>
	<h1>로그인창</h1>
</div>
<div>
	<form:form commandName="memberVO" id="memberExist" name="memberExist" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<form:input id="memId" name="memId" path="memId" />
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<form:password id="memPwd" name="memPwd" path="memPwd" />
				</td>
			</tr>
			<tr>
				<td> <input type="button" value="로그인" onclick="login()"> </td>
				<td>
					<input type="button" value="회원가입" onclick="memInsert()">
				</td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>