<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<!-- 사진 업로드 페이지 중앙배치 -->
<main clas="uploadContainer">
	<section class="upload">
		
		<div class="upload-top ">
			<a href="home.html" class="">
				<img src="/images/logo.jpg" alt="">
			</a>
			<p>사진 업로드</p>
		</div>
		
		<form action="/image" class="upload-form" method="post" entype="multipart/form-data">
			<input type="file" name="file" onchange="imageChoose(this)" />
			<div class="upload-img">
				<img src="/images/person.jpeg" alt="" id="imageUploadPreview" />
			</div>		
			
			<div class="upload-form-detail">
				<input type="text" placeholder="사진설명" name="caption">
				<button class="cta btn">업로드</button>	
			</div>
		
		</form>
		
	</section>
</main>

<script src="/js/upload.js"></script>
 <%@ include file="../layout/footer.jsp" %>
</body>
</html>