<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 자바 스크립트로 사용자 객체 만들기 </title>
</head>
<body>
	<script>
	
	// new Object();로 빈 객체 생성하기  
	let obj = new Object();		// Object객체 생성자를 직접 부른다. 
	obj.name = "김갑수";
	obj.age = 21;
	obj.move = move;	// 뒤에 move는 함수(메소드) 이름을 말한다.
	
	function move() {
		// alert("열심히 달린다.");
		document.write("열심히 달린다");
		return;
	};

	document.write(obj.name + "<br>");
	document.write(obj.age + "<br>");
	obj.move();
	document.write("<br>");
	
	
	
	
	
	// 리터럴(값을 담아놓는 값 대입 방식) 객체 생성법 
	// 중괄호 영역을 만들고 그 안에 값을 넣는다.
	// 함수도 중괄호 안에 다 넣을 수 있음
	let obj2 = {
		
		name : "김한수",
		age : 21,
		move : function() {
			document.write("열심히 달린다~!");
		}
	};
	
	document.write(obj2.name+"<br>");
	document.write(obj2.age+"<br>");
	obj2.move();
	
	
	
	document.write("<br>");
	// 프로토타입(클래스) 함수 객체 생성하기 위한 선언
	function Person() {		// 프로토 타입 함수다 (생성자 함수)
		
		this.name ="김갑수";	// this는 자기자신 객체를 뜻한다.
		this.age = 21;
		this.move = function() {
			document.write("열심히 달린다.");
			return;
		}
	};
	
	
	let p = new Person();	//person 객체 생성
	document.write(p.name + "<br>");
	document.write(p.age + "<br>");
	p.move();

	</script>
</body>
</html>