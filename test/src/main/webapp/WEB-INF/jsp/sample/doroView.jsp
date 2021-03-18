<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 도로 상세보기 페이지 </title>
</head>
<script type="text/javascript">
	function deleteClick(rnCd, emdNo) {
		var res = confirm("정말 삭제하시겠습니까?");
		if(res){
			location.href="doroDelete.do?rnCd="+rnCd+"&emdNo="+emdNo;
		}else{
			location.href="doro.do";
		}
	}

	function updateClick(rnCd, emdNo) {
		location.href="doroInsert.do?rnCd="+rnCd+"&emdNo="+emdNo;
	}
</script>
<body>
<!--
,RN_CD       --1
,RN          --2
,ENG_RN      --3
,EMD_NO      --4
,CN          --5
,ENG_CN      --6
,SIGN        --7
,ENG_SIGN    --8
,EMDN        --9
,ENG_EMD     --10
,EMD_SE      --11
,EMD_CD      --12
,USE_YN      --13
,CHGHY       --14
,AFTCH_INFO  --15
,NTFC_DE     --16
,ERSR_DE     --17
 -->
<div>
	<h1> 도로명 자세히 보기 </h1>
	<div>
		<input type="button" value="목록으로" onclick="window.history.back();">
	</div>
	<table border="1">
		<tr>
			<th>도로명 코드</th>
			<td>${selectDoro.rnCd}</td>
		</tr>
		<tr>
			<th>도로명</th>
			<td>${selectDoro.rn}</td>
		</tr>
		<tr>
			<th>영문 도로명</th>
			<td>${selectDoro.engRn}</td>
		</tr>
		<tr>
			<th>읍면동 일련번호</th>
			<td>${selectDoro.emdNo}</td>
		</tr>
		<tr>
			<th>시도명</th>
			<td>${selectDoro.cn}</td>
		</tr>
		<tr>
			<th>영문 시도명</th>
			<td>${selectDoro.engCn}</td>
		</tr>
		<tr>
			<th>시군구명</th>
			<td>${selectDoro.sign}</td>
		</tr>
		<tr>
			<th>영문 시군구명</th>
			<td>${selectDoro.engSign}</td>
		</tr>
		<tr>
			<th>읍면동명</th>
			<td>${selectDoro.emdn}</td>
		</tr>
		<tr>
			<th>영문 읍면동명</th>
			<td>${selectDoro.engEmd}</td>
		</tr>
		<tr>
			<th>읍면동 구분</th>
			<td>${selectDoro.emdSe}</td>
		</tr>
		<tr>
			<th>읍면동 코드</th>
			<td>${selectDoro.emdCd}</td>
		</tr>
		<tr>
			<th>사용여부</th>
			<td>${selectDoro.useYn}</td>
		</tr>
		<tr>
			<th>변경사유</th>
			<c:choose>
				<c:when test="${empty selectDoro.chghy}">
					<td> 변경내역이 없습니다 </td>
				</c:when>
				<c:otherwise>
					<td> ${selectDoro.chghy} </td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>변경이력정보</th>
			<c:choose>
				<c:when test="${empty selectDoro.aftchInfo}">
					<td>  도로명코드(12)+읍면동일련번호(2) 신규 정보일 경우 “신규”로 표시 잠시만요 곧 해보겠습니다. 잠시만요 ㅠ-ㅠ </td>
				</c:when>
				<c:otherwise>
					<td> ${selectDoro.aftchInfo} </td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>고시일자</th>
			<c:choose>
				<c:when test="${empty selectDoro.ntfcDe}">
					<td> 고시일자가 없습니다 </td>
				</c:when>
				<c:otherwise>
					<td> ${selectDoro.ntfcDe} </td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>말소일자</th>
			<c:choose>
				<c:when test="${empty selectDoro.ersrDe}">
					<td> 말소일자가 없습니다 </td>
				</c:when>
				<c:otherwise>
					<td> ${selectDoro.ersrDe} </td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
</div>
<div>
	<input type="button" value="삭제하기" onclick="deleteClick(${selectDoro.rnCd},${selectDoro.emdNo})">
	<input type="button" value="수정하기" onclick="location.href='doroInsert.do?rnCd=${selectDoro.rnCd}&emdNo=${selectDoro.emdNo}'">
</div>
</body>
</html>