<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
<script>

	//오브젝트의 window.car 내부 function this
	//매서드 내부의 this가 가리키는 것은 그 매서드를 감싸는 오브젝트.

	var car={
		name:'소나타';
		move:function(){
			console.log('car object function');
			console.log(this.name);
			}
		};
	car.move();

 
	$('#btn').on('click', function(){
		var username='ssar';
		console.log('onclick의 내부');
		console.log(this);
		function inner(){
			console.log("inner 함수");
			console.log(this);
		}
		
		inner();
	});
	
</script>