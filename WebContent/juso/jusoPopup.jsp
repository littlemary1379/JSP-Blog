<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String inputYn = request.getParameter("inputYn"); 
	String roadFullAddr = request.getParameter("roadFullAddr"); 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Juso.kr 팝업</title>
</head>
<script>

function init(){
	var url = location.href;
	var confmKey = "devU01TX0FVVEgyMDIwMDYwMjA5NDkwMjEwOTgyMDE=";
	var resultType = "4";
	var inputYn= "<%=inputYn%>";
	if(inputYn != "Y"){
		document.form.confmKey.value = confmKey;
		document.form.returnUrl.value = url;
		document.form.resultType.value = resultType;
		document.form.action="http://www.juso.go.kr/addrlink/addrLinkUrl.do"; //해당 주소로 위의 세 값을 전송
		document.form.submit();
	}else{
		opener.jusoCallBack("<%=roadFullAddr%>");
		window.close();
	}
}
</script>
<body onload="init()"> <!-- body가 로드되면 바로 실행 -->
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
	</form>
</body>
</html>