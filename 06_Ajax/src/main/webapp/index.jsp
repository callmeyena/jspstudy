<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>
</head>
<body>

	<div class="wrap">
	
		<h1>회원 관리</h1>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id">
			</div>
				<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<input type="radio" id="male" name="gender" value="M">
				<label for="male">남자</label>
			</div>
			<div>
				<input type="radio" id="female" name="gender" value="F">
				<label for="female">여자</label>
			</div>
				<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>
			<div>
				<input type="hidden" id="memberNo" name="memberNo">
				<input type="button" value="초기화" onclick="fnInit()">
				<input type="button" value="신규등록" onclick="fnAddMember()">
				<input type="button" value="변경저장" onclick="fnModifyMember()">
				<input type="button" value="삭제" onclick="fnRemoveMember()">
			</div>
		</form>
		
		<hr>
		
		<table border="1">
			<caption>전체 회원 수: <span id="member_count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>ID</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
		</table>
		
	</div>



<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>

	// 함수 호출
	fnInit();
	fnGetAllMember();

	// 함수 정의
	function fnInit() {
		$('#id').val('').prop('disabled', false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#address').val('');
	}
	
	
	function fnGetAllMember() {
		$.ajax({
			// 요청
			type: 'get', 
			url: '${contextPath}/list.do',
			// 응답
			dataType: 'json',
			success: function(resData) {	// 응답 데이터는 resData로 전달된다.
				/*
					resData <--- obj.toString()
					resData = {
						"memberCount": 2,
						"memberList": [
							{	},
							{	}
						]
				
					}
				*/
				$('#member_count').text(resData.memberCount);
				 let memberList = $('#member_list');
				 memberList.empty();		// 기존의 회원 목록을 지운다.
				 
				 if(resData.memberCount === 0) {
					 memberList.append('<tr><td colspan="6">회원이 없습니다.</td></tr>');
				 } else {
					// $.each(배열, (인덱스, 요소)=> {})
					// $.each(배열, function(인덱스, 요소) {})
					$.each(resData.memberList, function(i, element) { // resData.memberList가 배열이다/ element는 하나의 회원 객체를 의미한다.
						let str = '<tr>';
						str += '<td>' + element.memberNo + '</td>';
						str += '<td>' + element.id + '</td>';
						str += '<td>' + element.name + '</td>';
						str += '<td>' + (element.gender === 'M' ? '남자' : '여자') + '</td>';
						str += '<td>' + element.address + '</td>';
						str += '<td><input type="button" value="조회" class="btn_detail" onclick="fnGetMember(' + element.memberNo + ')"></td>';
						memberList.append(str);

					})	
					
				 }
				 
			}
		})
	}
	
	function fnAddMember() {
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/add.do',
			data: $('#frm_member').serialize(),		// 폼의 모든 입력 요소를 파라미터로 전송한다. (입력 요소에는 name 속성이 필요하다.) 		
			// 응답
			dataType: 'json',
			success: function(resData) {			// try문의 응답이 resData에 저장된다. resData = {"insertResult": 1}
				if(resData.insertResult == 1) { 
					alert('신규 회원 등록 되었습니다.');
					fnInit(); 						// 입력란 초기화
					fnGetAllMember();				// 새로운 회원 목록으로 갱신하기
				} else {
					alert('신규 회원 등록이 실패 하였습니다.');
				}		
			},
			error: function(jqXHR) {		// jqXHR 객체에는 예외코드(응답코드: 404, 500 등)와 예외 메세지 등(오류 관련된 것들..?)이 저장된다.
										//  catch문의 응답 코드는 jqXHR 객체의 status 속성에 저장된다.
										// catch문의 응답은 jqXHR 객체의 responseText 속성에 저장된다.
					alert(jqXHR.responseText + '(' + jqXHR.status + ')'); 		// msg = jqXHR.responseText
			}
		})
	}
	
	// onclick="fnGetMember(element.memberNo)"
	// fnGetMember() 함수를 호출할 때 회원번호(element.memberNo)를 인수로 전달하면 매개변수 memberNo가 받는다.
	function fnGetMember(memberNo) {
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/detail.do',
			data: 'memberNo=' + memberNo,
			// 응답
			dataType: 'json',
			success: function(resData) {	// resData = {"member": {"memberNo": 회원번호, ...}}
				alert('회원 정보가 조회되었습니다.');
				$('#id').val(resData.member.id).prop('disabled', true);
				$('#name').val(resData.member.name);
				$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true);		// 두개의 라디오를 의미 그래서 그 중 값이 일치하는 라디오를 DB에서 찾아야함
				$('#address').val(resData.member.address);
				$('#memberNo').val(resData.member.memberNo);		// <input type="hidden">에 저장하는 값
				
				
			}
		})
	}
	
	function fnModifyMember() {
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/modify.do',
			data: $('#frm_member').serialize(),
			// 응답
			dataType: 'json',
			success: function(resData) {	// resData = {"updateResult": 1}
				if(resData.updateResult === 1) {
					alert('회원 정보가 수정 되었습니다.');
					fnGetAllMember();		// 새로운 회원 목록으로 갱신
				} else {
					alert('회원 정보 수정이 실패하였습니다.')
				}
			}
		})
	}
	
	function fnRemoveMember() {
		
	}
</script>

</body>
</html>