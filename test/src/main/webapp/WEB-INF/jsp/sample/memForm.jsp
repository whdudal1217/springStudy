<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function goPopup(){
			var pop = window.open("jusoPopup/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
		}
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd,
			rnMgtSn, bdMgtSn , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm,
			buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
			 // 2017년 2월 제공항목이 추가되었습니다. 원하시는 항목을 추가하여 사용하시면 됩니다.
			 document.form.roadFullAddr.value = roadFullAddr;
			 document.form.roadAddrPart1.value = roadAddrPart1;
			 document.form.roadAddrPart2.value = roadAddrPart2;
			 documentform.addrDetail.value = addrDetail;
			 document.form.zipNo.value = zipNo;
	}
	function doSubmit() {
		document.memberInsertForm.action = "<c:url value='/memberInsert.do'/>";
		document.memberInsertForm.submit();
	}


</script>
</head>
<body>
<div>
	<h1> 회원가입 </h1>
</div>
<div>
	<table border="1">
		<form:form commandName="memberVO"  id="memberInsertForm" name="memberInsertForm" method="post">
		<tr>
			<td>아이디</td>
			<td><form:input path="memId" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><form:password path="memPwd" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><form:input path="memName" /></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><form:input path="memTel" /></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><form:input path="memEmail" /></td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
				<p>도로명주소 전체(포맷) : <form:input type="text" id="roadFullAddr" name="roadFullAddr" path="roadFullAddr" /></p>
				<p>도로명주소 : <form:input type="text" id="roadAddrPart1" name="roadAddrPart1" path="roadAddrPart1"/></p>
				<p>참고주소 : <form:input type="text" id="roadAddrPart2" name="roadAddrPart2" path="roadAddrPart2" /></p>
				<p>우편번호 : <form:input type="text" id="zipNo" name="zipNo" path="zipNo" /></p>
				<p>고객입력 상세주소 : <form:input type="text" id="addrDetail" name="addrDetail" path="addrDetail" /></p>
			</td>
		</tr>
		</form:form>
	</table>
	<br />
	<input type="submit" value="가입하기" onclick="doSubmit()">
</div>
</body>
</html>