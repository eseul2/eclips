<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 수학 객체 Math </title>
</head>
<body>
	<script>
	// 0에서 99까지의 랜덤 숫자를 출력하세요 
	
	let m;
	let n;
	
	for(let i=0; i<10; i++) {
		m = Math.random()*100; 	// 0~99.999999 사이의 값이 나온다
		n = Math.floor(m);			// 소수점을 제거한 정수
	}
	document.write("랜덤값은? " + n);
	
	
	// 자바스크립트 기능중에 setTimeout 메소드가 있어요 
	setTimeout('location.reload()',3000);  //location 객체를 3초마다 reload 시키겠다. 
	



	</script>

</body>
</html>