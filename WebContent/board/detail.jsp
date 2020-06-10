<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp"%>

<div class="container">
<!-- <button class="btn btn-secondary" href="javascript:history.back();">뒤로가기</button>  -->
	<button class="btn btn-primary" onclick="history.back()">뒤로가기</button>
	<c:if test="${sessionScope.principal.id==dto.board.userId }">
		<a href="/blog/board?cmd=update&id=${dto.board.id }" class="btn btn-primary">수정</a>
		<button class="btn btn-danger" onclick="deleteById(${dto.board.id})">삭제</button>
	</c:if>
	<br/><br/>	
	<h6>작성자 : <i>${dto.username },</i> 조회수 :${dto.board.readCount }</h6>
	<br/>	
	<h3><b>${dto.board.title }</b></h3>
	
	<div class="form-group">
		<div class="container p-3 my-3 border">${dto.board.content }</div>
	</div>
	

</div>
<%@ include file="../include/footer.jsp"%>

<script>
	function deleteById(boardId){
		$.ajax({
				type:"post",
				url:"/blog/board?cmd=delete&id="+boardId,
				dataType:"text"
		}).done(function(result){
				console.log(result);
				if(result==1){
					alert("삭제 성공");
					location.href="/blog/index.jsp";
				}else{
					alert("삭제 실패");
				}
		}).fail(function(error){
				console.log(error);
				console.log(error.responseText);
				console.log(error.status);
				alert("서버 오류");
		});
		}
</script>