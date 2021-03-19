
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도로명주소</title>
<!-- <script src="https://code.jquery.com/jquery-3.2.1.min.js" > </script> -->
<!-- <script src="https://code.jquery.com/jquery-3.2.1.js"></script> -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>


<script type="text/javascript" defer="defer">


function viewPage(rnCd,emdNo,emdSe,currentPageNo){
	location.href ="doroView.do?rnCd="+rnCd+"&emdNo=0"+emdNo;
}

var selected = [];
selected[3] = "엥";
var notChSign;
var notChEmdn;
$(document).ready(function() {
	//공통 SELECT
	notChSign = $('#signSearch').val();
	notChEmdn = $('#emdnSearch').val();
	console.log("notChSign >> " + notChSign);
	console.log("notChEmdn >> " + notChEmdn);
	$('select').on('change', function(){
		var selectId = $(this).attr('id');
		var cn;
		var sign;
		var emdn;
		if(selectId == 'cnSearch'){
			$('#emdnSearch').children('option:not(:first)').remove();
			cn = $('select option:selected').val(); //value값
			sign = $("#signSearch").val(); //none
			emdn = $("#emdnSearch").val(); //none
			rn = $("rnInput").val();
			ajaxPassTest(cn,sign,emdn,selectId);
		}else if(selectId == 'signSearch'){
			cn = $("#cnSearch").val(); //value 값
			sign = $("#signSearch").val(); //value 값
			emdn = $("#emdnSearch").val(); //none
			ajaxPassTest(cn,sign,emdn,selectId);
		}
	});

	if("${doroVO.emdn}" =="" && "${doroVO.sign}" == ""){
		ajaxPassTest("${doroVO.cn}","${doroVO.sign}","${doroVO.emdn}","cnSearch");
	}else{
		selected[0] = "${selectedSign}";
		selected[1] = "${selectedEmdn}";
		ajaxPassTest("${doroVO.cn}","${doroVO.sign}","${doroVO.emdn}","cnSearch");
		ajaxPassTest("${doroVO.cn}","${doroVO.sign}","${doroVO.emdn}","signSearch");

	}

});

function ajaxPassTest(cn,sign,emdn,selectId) {
	var dataJson = {
		"selectId" : selectId,
		"cn" : cn,
		"sign" : sign,
		"emdn" : emdn,
	}

	$.ajax({
		type : 'POST',
		url : 'selectSearch.do',
		data : dataJson,
		success : function(data) {
			var options;
			var tbOptions;
			if(selected != ''){
				if (selectId == 'cnSearch')
				{
					options +="<option value='all'> 전체선택 </option>";
					for(var a=0 ; a < data.dataList.length; a++){
						if(data.dataList[a].SIGN != selected[0] ){
							options += "<option value="+data.dataList[a].SIGN+">" + data.dataList[a].SIGN + "</option>";
						}else{
							options += "<option value="+data.dataList[a].SIGN+" selected='selected'>" + data.dataList[a].SIGN + "</option>";
						}
					}
					$("#signSearch").html(options);
				}
				else if (selectId == 'signSearch')
				{
					options +="<option value='all'> 전체선택 </option>";
					for(var a=0 ; a < data.dataList.length; a++){
						if(data.dataList[a].EMDN != selected[1] ){
							options += "<option value="+data.dataList[a].EMDN+">" + data.dataList[a].EMDN + "</option>";
						}else{
							options += "<option value="+data.dataList[a].EMDN+" selected='selected'>" + data.dataList[a].EMDN + "</option>";
						}
					}
					/* for(var i = 0; i<data.dataList.length; i++){
						options += "<option value=" + data.dataList[i].EMDN +">" + data.dataList[i].EMDN + "</option>";
					} */
					$("#emdnSearch").html(options);
				}
			}else{
				if (selectId == 'cnSearch')
				{
					options +="<option value='all'> 전체선택 </option>";
					for(var a=0 ; a < data.dataList.length; a++){
						options += "<option value="+data.dataList[a].SIGN+">" + data.dataList[a].SIGN + "</option>";
					}
					$("#signSearch").html(options);
				}
				else if (selectId == 'signSearch')
				{
					options +="<option value='all'> 전체선택 </option>";
					for(var i = 0; i<data.dataList.length; i++){
						options += "<option value=" + data.dataList[i].EMDN +">" + data.dataList[i].EMDN + "</option>";
					}
					$("#emdnSearch").html(options);
				}
			}

		},
		error : function(xhr, status,error){
			console.log("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+error);
			alert(xhr.status);
		}
	});

}

<!--
	 function fn_egov_link_page(pageNo) {
		if($('#cnSearch').val() != null) {
			document.listForm.selectId.value = 'cnSearch';
		}else if($('#signSearch').val() != null){
			document.listForm.selectId.value = 'signSearch';
		}else if($('#emdnSearch').val() != null){
			document.listForm.selectId.value = 'emdnSearch';
		}

		var form = document.listForm;

		var hiddenSignField = document.createElement("input");
		hiddenSignField.setAttribute("type", "hidden");
		hiddenSignField.setAttribute("name", "notChSign");
		hiddenSignField.setAttribute("value", notChSign);
		form.appendChild(hiddenSignField);

		var hiddenEmdnField = document.createElement("input");
		hiddenEmdnField.setAttribute("type", "hidden");
		hiddenEmdnField.setAttribute("name", "notChEmdn");
		hiddenEmdnField.setAttribute("value", notChEmdn);
		form.appendChild(hiddenEmdnField);

		document.listForm.pageIndex.value = pageNo;
		document.listForm.action = "<c:url value='/doro.do?searchYn=false'/>";
		document.listForm.submit();


	}

	function fn_egov_selectList() {
		if($('#cnSearch').val() != null) {
			document.listForm.selectId.value = 'cnSearch';
		}else if($('#signSearch').val() != null){
			document.listForm.selectId.value = 'signSearch';
		}else if($('#emdnSearch').val() != null){
			document.listForm.selectId.value = 'emdnSearch';
		}
		document.listForm.pageIndex.value = 1;
		document.listForm.action = "<c:url value='/doro.do?searchYn=true'/>";
		document.listForm.submit();
	}

	//-->

</script>
</head>
<body>
<div style="">
	<h1 onclick="location.href ='doro.do?searchYn=false'" style="cursor:pointer; width:200px;">도로명주소</h1>
	<input type="button" id="insert" onclick="location.href='doroInsert.do'" value="도로명 추가">
	<input type="button" id="memInsert" onclick="location.href='memberSelect.do'" value="회원목록">
	<c:if test="${sessionScope.LOGIN_USER != null}">
		<input type="button" id="logout" onclick="location.href= 'logout.do'" value="로그아웃">
		${LOGIN_USER.memId} 님 환영합니다.
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER == null}">
		<input type="button" id="memInsert" onclick="location.href='memberInsert.do'" value="회원가입">
	</c:if>
	<p/>
</div>

<!--
form:form commandName, modelAttribute : req로부터 객체를 찾을 때 사용할 이름!! (commandName이 에러가 나면 modelAttribute를 사용하면 됨)
그러면 객체는 어떻게 지정하느냐? -> 컨트롤러에서 model.addAttribute("VO", VO); -> 이렇게 하면 객체를 지정
@ModelAttribute VO VO -> 어노테이션으로도 가능
form:*** path="" v	->> Path to property for data binding -> path 안에 적으면 데이터 바인딩을 컨트롤러에서 파람맵이나 vo 타입으로 되나봄 ㅇㅁㅇ
form:form commandName 아래의 form태그에선 vo안의 변수들을 자유롭게 사용 가능!
 -->
<input type="hidden" name="selectedId" />
		<!-- 목록 -->
<form:form commandName="doroVO"  id="listForm" name="listForm" method="post">
<form:hidden path="selectId"/>
<c:forEach items="${doroList}" var="doroList">
	<input type="hidden" id="rnCd" name="rnCd" value="${doroList.rnCd}"/>
</c:forEach>
		<!-- 검색창 -->

			<label> 시도 선택 : </label>
			<form:select path="cn" id="cnSearch" name="cnSearch">
				<form:option value="all">-- 전체선택 --</form:option>
				<c:forEach items="${cnList}" var="cnList">
					<c:choose>
						<c:when test="${cnList.cn eq selectedCn}">
							<form:option value="${cnList.cn}" selected='selected'></form:option>
						</c:when>
						<c:otherwise>
							<form:option value="${cnList.cn}"></form:option>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</form:select>
			<label> 시군구 선택 </label>
			<form:select path="sign" id="signSearch" name="signSearch">
				<form:option value="all" selected="selected">-- 전체선택 --</form:option>
			</form:select>
			<label> 읍면동 선택 </label>
			<form:select path="emdn" id="emdnSearch" name="emdnSearch">
				<form:option value="all" selected="selected">-- 전체선택 --</form:option>
			</form:select>
			<label> 도로명 </label>
			<form:input path="rn" id="rn"/>
			<input type="button" value="검색" onclick="fn_egov_selectList();">

			<p/>
		<div id="doroList">
			<table border="1">
				<c:choose>
					<c:when test="${empty doroList}">
							<p> 데이터가 없습니다 </p>
					</c:when>
					<c:otherwise>
						<tr id="table-head">
							<th>순번</th>
							<!-- 1 -->
							<th>도로명코드</th>
							<!-- 2 -->
							<th>도로명</th>
							<!-- 3 -->
							<th>도로명_영문자</th>
							<!-- 4 -->
							<th>읍면동_일련번호</th>
							<!-- 5 -->
							<th>시도명</th>
							<!-- 6 -->
							<th>시도명_영문자</th>
							<!-- 7 -->
							<th>시군구명</th>
							<!-- 8 -->
							<th>시군구_영문자</th>
							<!-- 9 -->
							<th>읍면동명</th>
							<!-- 10 -->
							<th>읍면동_영문자</th>
							<!-- 11 -->
						</tr>
						<c:forEach items="${doroList}" var="list">
							<tr id="tr_data" onclick="viewPage(${list.rnCd},${list.emdNo},${list.emdSe})" style="cursor:pointer;">
								<td align="center" class="listtd"><c:out value="${list.rnum}" />
								</td>
								<td id="rnCd"><c:out value="${list.rnCd}"></c:out></td>
								<td id="rn"><c:out value="${list.rn}"></c:out></td>
								<td id="engRn"><c:out value="${list.engRn}"></c:out></td>
								<td id="emdNo"><c:out value="${list.emdNo}"></c:out></td>
								<td id="cn"><c:out value="${list.cn}"></c:out></td>
								<td id="engCn"><c:out value="${list.engCn}"></c:out></td>
								<td id="sign"><c:out value="${list.sign}"></c:out></td>
								<td id="engSign"><c:out value="${list.engSign}"></c:out></td>
								<td id="emdn"><c:out value="${list.emdn}"></c:out></td>
								<td id="engEmd"><c:out value="${list.engEmd}"></c:out></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<p />
		<!-- 페이징처리 -->
		<div id="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
			<form:hidden path="pageIndex" />
		</div>
	</form:form>
	<%-- <div id="test">
		<p> selectedCn : ${selectedCn}</p>
		<p> selectedSign : ${selectedSign}</p>
		<p> selectedEmdn : ${selectedEmdn}</p>
	</div> --%>
</body>
</html>