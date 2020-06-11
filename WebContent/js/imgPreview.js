
	$("#img__preview").on("change", function(e){
		var f=e.target.files[0];

		if(!f.type.match("image*")){
			alert("이미지만 첨부할 수 있습니다..");
			$("#img__preview").val('');
			return;
		}
	
		// f.size = 1024*1024*2
		
		if(f.size>1024*1024*2){
			alert("2mb까지의 사진만 업데이트 할 수 있습니다.");
			$("#img__preview").val('');
			return;
		}
		
		var reader=new FileReader();
		

		
		reader.onload=function(e){
			$("#img__wrap").attr("src",e.target.result);
		}
		reader.readAsDataURL(f); //비동기적 진행(파일 읽기)


		
	});
	