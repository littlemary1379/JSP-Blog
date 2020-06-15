	function replydelete(replyId){

		$.ajax({
			type: "post",
			url : "/blog/reply?cmd=deleteProc",
			data : "replyId="+replyId,
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "text"
		}).done(function(result){
			if(result=="1"){
			alert("덧글 삭제 성공");
			var replyItem=$("#reply-"+replyId);
			replyItem.remove();
			}else{
				alert("덧글 삭제 실패");
			}
			

			
		}).fail(function(error){
			alert("덧글 삭제 실패");
			alert("replyId="+replyId);
			
		});
		
	}

function replyWrite(boardId, userId){
		console.log(userId);
		if(userId===undefined){
			alert("로그인이 필요합니다.");
			return;
		}
		var data= {
			boardId : boardId,
			userId: userId,
			content : $("#reply__write__form").val()
		};

		$.ajax({
			type: "post",
			url : "/blog/reply?cmd=writeProc",
			data : JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "text"
		}).done(function(result){
			//정상응답
			//1.reply__list를 찾아 비우기
			alert("덧글 등록 성공");
			$("#reply__list").empty();
			//2.ajax 재호출 findAll()
			//3.reply__list를 찾아 내부에 채워주기
			
		}).fail(function(error){

		});
		
	}