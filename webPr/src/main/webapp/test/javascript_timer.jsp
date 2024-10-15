<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOM객체로 타이머 사용하기</title>
</head>
<body>

<img id = "img" src="../images/strawberry.png" 
onmouseover="startTimer(5000);"
onmouseout = "cancleTimer();">


<button onclick="window.open('./javascript_size.jsp','test','width=300,height=300')">
버튼 크기 조절 눌려보려구요
</button>

<script>
let timerID = null;

function startTimer(time){
	timerID = setTimeout("load('http://naver.com')",time);
}

function load(url) {
	window.document.location.href = url;	// 이동한다. 
}

function cancleTimer() {
	clearTimeout(timerID);		// 타이머를 정리한다. 
}


</script>

</body>
</html>