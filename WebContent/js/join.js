var isCheckedUsername = false;

// juso. go.kr 라이브러리 함수(시작)
function goPopup() {
	window.open("/blog/juso/jusoPopup.jsp", "pop",
			"width=570,height=420, scrollbars=yes, resizable=yes");
}

function jusoCallBack(roadFullAddr) {
	var tfAddress = document.querySelector("#address");
	tfAddress.value = roadFullAddr;
}

//juso. go.kr 라이브러리 함수(끝)


// 아이디 중복 체크 함수

function validate() {
	if (!isCheckedUsername) {
		alert("username 중복체크를 해주세요.");
	}
	return isCheckedUsername;
}

//데이터베이스에 ajax 요청해서 중복 유저네임 있는지 확인 -> 
//있으면 1, 없으면 0, 서버오류 -1

function usernamecheck() {
	// 성공(ajax)
	var tfUsername = $('#username').val();
	console.log(tfUsername);
	$.ajax({
		type : 'get',
		url : `/blog/user?cmd=usernameCheck&username=${tfUsername}`
	}).done(function(result) {
		console.log(result);
		if (result == 1) {
			alert("아이디가 중복되었습니다.");
		} else if (result == 0) {
			alert("사용하실 수 있는 아이디입니다.");
			isCheckedUsername = true;
		} else {
			console.log('develop : 서버 오류');
		}
	}).fail(function(error) {
		console.log(error);

	});

}