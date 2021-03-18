<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 도로 입력 페이지 </title>
<script type="text/javascript">
	function doSubmit(num){
		if(num == 1){
			document.insertForm.action = "<c:url value='/doroInsert.do'/>";
			document.insertForm.submit();
		}else{
			document.insertForm.action = "<c:url value='/doroUpdate.do'/>";
			document.insertForm.submit();
		}
	}
</script>
</head>
<body>
	<div>
		<c:choose>
			<c:when test="${empty selectDoro}">
				<h1> 도로명 주소 입력 </h1>
			</c:when>
			<c:otherwise>
				<h1> 도로명 주소 수정 </h1>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<input type="button" value="돌아가기" onclick="window.history.back();">
	</div>
	<div>
		<form:form commandName="doroVO"  id="insertForm" name="insertForm" method="post">
			<c:choose>
				<c:when test="${selectDoro.rnCd != null}">
					도로명 코드 : <form:input path="rnCd" readonly="true"/> <br />
				</c:when>
				<c:otherwise>
					도로명 코드 : <form:input path="rnCd" /> <br />
				</c:otherwise>
			</c:choose>
			<br />
			읍명동 번호 : <form:input path="emdNo"/> <br />
			<br />
			시도명 : <form:input path="cn" value="${selectDoro.cn}"/><br />
			<br />
			도로명 : <form:input path="rn" value="${selectDoro.rn}"/> <br />
			<br />
			시군구명 : <form:input path="sign" value="${selectDoro.sign}"/> <br />
			<br />
			읍면동명 : <form:input path="emdn" value="${selectDoro.emdn}"/> <br />
			<br />
		</form:form>
		<c:choose>
			<c:when test="${empty selectDoro.rnCd}">
				<h1> <input type="submit" value="입력" onclick="doSubmit(1)"> </h1>
			</c:when>
			<c:otherwise>
				<h1> <input type="submit" value="수정" onclick="doSubmit(2)"> </h1>
			</c:otherwise>
		</c:choose>

	</div>
</body>
</html>