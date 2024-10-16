<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제이쿼리 연습하기</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<script>
$(document).ready(function(){
//	alert("hello world!");
	$("button").click(function(){
		$("p").hide();   // 숨긴다. 
	});
	
	// 태그를 부를때는 태그 셀렉터 호출
	$("button").dblclick(function(){  // 더블클릭 했을 때 
		$("p").show();   //숨겨진 <p> 요소를 다시 표시한다.
	});
	
	
	// 아이디를 부를때는 #표시를 한다.
	$("#btn").click(function() {
		$("#divid").html("");
	});
	
	
	/* $("셀렉터").메소드(function() {
		alert("ffffff");
	});
	 */
	
	$("#btn3").click(function(){
		
		$("#div1").fadeOut();
		$("#div2").fadeOut("slow");
		$("#div3").fadeOut(3000);
	});
	 
	 
	$("#filep").click(function(){
		$("#panel").slideDown("slow");
	});
	
});
</script>

<p>저는 홍길동입니다.</p>
<button id ="btn2">클릭하면 글이 사라집니다.</button>
<div id = "divid">
안녕하세요
</div>
<button id = "btn">클릭해보세요</button>
<br>
<br>
<button id = "btn3">서서히 나타나다</button>

<div id="div1" style="width:80px; height:80px; background-color:red;"></div>
<br>
<div id="div2" style="width:80px; height:80px; background-color:green;"></div>
<br>
<div id="div3" style="width:80px; height:80px; background-color:blue;"></div>
<br>

<div id ="filep" style="padding:5px; text-align:center;
 background-color:#e5eecc; 
 border: 1px solid #c3c3c3;">
 클릭하면 아래로 펼쳐져요</div>
<div id ="panel" style="padding:50px; background-color:#e5eecc; display:none;">안녕 반가워요</div>
</body>
</html>