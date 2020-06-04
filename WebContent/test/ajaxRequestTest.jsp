<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>
<style>
	div{
		border:1px solid black;
		margin: 5px;
		padding: 5px;
	}
</style>
</head>
<body>

<div id="reply-box">
<div id="reply-1">
첫번째 덧글입니다.
</div>
</div>
<input type="text" id="tf-reply"/><br/>

<button onclick="start()">덧글쓰기</button>

<script>
var num=1;
function start(){

	num++;
	var a= $('#tf-reply').val();

	var data={
		username:"ssar",
		content:a
	};
	

	// 통신이 성공하면 아래 로직 실행
	$.ajax({
		type : 'POST',
		url : 'ajaxResponseTest.jsp', // 요건 필수값임..!
		data : JSON.stringify(data), // 보내는 데이터
		contentType : 'application/json; charset=utf-8', // 보내는 데이터 
		dataType: 'json' //응답받을 데이터로 자동 파싱(text or json)
		
	}).done(function(result){
		console.log(result);
		$('#reply-box').prepend("<div id='reply-"+num+"'>"+a+"</div>");
	}).fail(function(error){
		console.log('으악에러');
		console.log(error);
	});
	
	
}
</script>
</body>
</html>