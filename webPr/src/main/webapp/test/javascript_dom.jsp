<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바스크립트로 DOM객체 제어하기</title>
</head>
<body>
															    <!--문단을 클릭하면 빨간색으로 변한다 -->
	<p id="firstP" style="color : yellow; background:green;" onclick="this.style.color='red'">
	<span id="mySpan" style="color:blue;">안녕</span>하세요 
	</p>
	
	<input type="button" value="스타일바꾸기" onclick="change();">  <!--체인지를 클릭하게 되면 스타일이 바뀌는거-->
	<input type="button" value="글자내용바꾸기" onclick="textChange();">
<script>
let p = document.getElementById("firstP");   // 속성값 id로 객체를 찾는 메소드. 
document.write("객체의 id는? " + p.id + "<br>"); // firstP
document.write("객체의 태그는? " + p.tagName +"<br>"); // p
document.write("객체가 담고 있는 내용은" + p.innerHTML+"<br>");
document.write("객체의 스타일? " + p.style.color+"<br>");  // yello 
document.write("객체의 이벤트 리스너(감지기)는? " + p.onclick +"<br>"); //function onclick(event) { this.style.color='red' }
document.write("객체의 너비는? " + p.offsetWidth +"<br>"); //1233
document.write("객체의 높이는? " + p.offsetHeight +"<br>"); // 21

// 스타일바꾸기 함수
function change() {
	let span = document.getElementById("mySpan");
	span.style.color ="#FF6600";		// 이렇게 자바스크립트로 제어를 할 수 있다.
	span.style.fontSize = "30px";
	span.style.border = "3px dotted skyblue"; 
	
	return;
}

// 글자내용바꾸기 함수 
 function textChange() {
	let myP = document.getElementById("firstP");
	myP.innerHTML = "무궁화 꽃이 <span style='color:red;'>피었습니다.</span>";
	return;
} 


// 태그 이름으로 DOM 객체 찾기
function fruit() {			// 여러개기 때문에 Element에 s를 붙여줘야 한다.
	// let sp = document.getElementsByTagName("span"); 	// 여러개의 span태그 컬렉션 집합 객체변수 
	let cls = document.getElementsByClassName("a");		// 클래스속성(프로퍼티)으로 컬렉션 객체를 찾는다.								
	
	for(let i=0; i<cls.length; i++) {
		let entityCls = cls[i];				//하나씩 꺼낸걸 다시 entity로 넣는다.
		
		entityCls.style.color="red";
	}	
	return;
}



// 새창 열기 함수 
function winOpen() {
												// 많이 쓰는 함수
	window.open("<%=request.getContextPath()%>/member/memberJoin.jsp","winName","width=800,height=600;");  
	
	return;
}


/* document.open();
document.write("<html><title>새화면</title><body>새로 열립니다</body></html>");
document.close(); */

</script>



저는 빨간 <span class="a">사과</span>를 좋아해서
 아침마다 한 개씩 먹고 있어요. 운동할 때는 중간 중간에
 <span class="a">바나나</span>를 먹지요. 탄수화물 섭취가 빨라
 힘이 납니다. 또한 달콤한 향기를 품은 <span class="a">체리</span>와
 여름 냄새 물씬 나는 <span class="a">자두</span>를 좋아합니다.
 
 <br>
 아침에는 <span class="b">우유</span>도 같이 먹고 있어요 
 
 <br>
 위 문장에서 과일이름은 빨간색상으로 변경하세요 
 <button type="button" onclick="fruit();">과일이름바꾸기</button>




새 창 열기 연습
 <button type="button" onclick="winOpen();">새창열기</button>



</body>
</html>