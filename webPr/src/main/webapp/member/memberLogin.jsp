@@ -0,0 +1,117 @@
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 연습하기</title>
</head>
<body> <!-- 얘도 이벤트임 -->

<script>

// 이벤트 onload 함수를 이용해서 창이 뜨게 한다.
//window.onload=function() {
	
//	alert('모든 객체가 생성된 후에 로딩됩니다2')
//}


function calculate() {
	let exp = document.getElementById("exp"); // exp input 객체찾는다.
	let result = document.getElementById("result"); // result input 객체를 찾는다.
	
	result.value = eval(exp.value);  // 문자열을 숫자로 변환하는 함수? 계산해서 담기 오작동이 많아서 안쓰는게 좋다. 
	
	
	return;
}


function aaa() {
	alert("오른쪽 버튼은 사용금지입니다.");
	return false;
}

// document.oncontextmenu = aaa;


function changeImage() {
	alert("함수에 들어왔나요?");
	let sel = document.getElementById("sel"); // select 객체 찾기
	alert("객체가 생성되었나요?" + sel);
	let img = document.getElementById("myImg"); // 이미지 객체 찾기
	alert("객체가 생성되었나요?" + img);
	
//	img.onload = function() {  //이미지가 로딩이 되면 익명함수 동작 
		alert("이미지가 로딩되었나요?");
		let mySpan = document.getElementById("mySpan");
		alert("객체가 생성되었나요?" + mySpan);
		mySpan.innerHTML = img.width + "x" + img.height;
//	}
	
	let index = sel.selectedIndex;
	img.src = sel.options[index].value;
}


function chk() {
	let nm = document.getElementById("nm");  // nm객체를 찾는다. 

	if(nm.value=="") {    //아무것도 입력을 안했을 때 포커스 
		nm.focus();      
		return;
	}
	return;
}


</script>
<form>
<input type = "text" id= "exp" value="">
<br>
<input type = "text" id="result">
<br>
<input type= "button" value="계산하기" onclick="calculate();"> <!--onclick 이벤트리스너 걸기 -->

<hr>


<p>
마우스 오른쪽 버튼 클릭하기
</p>

<hr>

<form>
<select id="sel" onchange="changeImage();"> <!-- onchange에 함수를 건다. -->
<option value="../images/apple.png">사과</option>
<option value="../images/banana.png">바나나</option>
<option value="../images/strawberry.png">딸기</option>
</select>
<span id="mySpan">이미지 크기</span>
</form>



<p>
<img id= "myImg" src="images/apple.png" title="사과">
</p>


<input type = "text" name="nm" id="nm"> <!-- 그 위치를 떠날때 감지하는 이벤트 -->  

<script>
let nm = document.getElementById("nm");
nm.onblur = function() {
	if(nm.value=="") {
	nm.focus();
	}
};

</script>
	
</form>

</body>
</html>