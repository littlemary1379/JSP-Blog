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
			dataType: "json"
		}).done(function(result){
			//정상응답
			if(result==-1 || result==0){
				alert("덧글 등록 실패");
			}else{
			//1.reply__list를 찾아 비우기
			alert("덧글 등록 성공");
			$("#reply__list").empty();
			console.log(result);
			//2.ajax 재호출 findAll()
			renderReplyList(result);
			//3.reply__list를 찾아 내부에 채워주기
			$("#reply__write__form").val("");
			}
		}).fail(function(error){
			alert("덧글 등록 실패");
		});
	}

function renderReplyList(replyDtos){
	for (var replyDto of replyDtos) {
		$("#reply__list").append(makeReplyItem(replyDto));
		//reply-id 추가 시작
		var replyItem = `<li id="reply-${replyDto.reply.id }" class="media">`;
		//reply-id 추가 끝
	}
}

function makeReplyItem(replyDto) {
	var replyItem=`<li class = "media">`;
	
	if(replyDto.userProfile ==null){
		replyItem+=`<img src="/blog/image/userProfile.png" class="img-circle">`;
		
	}else{
		replyItem+=`<img src="${replyDto.userProfile}" class="img-circle">`;
		
	}
	replyItem += `<div class ="media-body">`;
	replyItem += `<strong class="text-primary">${replyDto.username}</strong>`;
	replyItem += `<p>${replyDto.reply.content}</p>`;
	replyItem += `</div>`;

	//휴지통 추가 시작
	replyItem += `<div class="m-3">`;
	replyItem += `<i onclick="replydelete(${replyDto.reply.id})" style="cursor : pointer;" class="Tiny material-icons">delete</i>`;
	replyItem += `</div>`;
	
	replyItem += `</li>`;
	
	return replyItem;
}