<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오지도(내위치)</title>
</head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa72da3161e727e3195719bf2a1123e5"></script>
<script>
	// 위도와 경도 구하기
	navigator.geolocation.getCurrentPosition(function(position) {
		const lat = position.coords.latitude;
		const lng = position.coords.longitude;
		geo_map(lat, lng);
	});	
</script>
<body>
	<%-- 
		카카오 디벨로퍼 로그인 후 내 애플리케이션에서 애플리케이션 선택 한 후 javascript 키 복사 : fa72da3161e727e3195719bf2a1123e5
	 --%>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width: 100%; height: 350px;"></div>
	<script>
	function geo_map(lat, lng) {
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표(위도, 경도)
			level : 3 // 지도의 확대 레벨
		};
	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);
	}
	</script>
</body>
</html>