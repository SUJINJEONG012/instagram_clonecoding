<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="popular">
 <div class="exploreContainer">
 	<!-- 인기게시글 갤러리 -->
 	 <div class="popular-gallery">
 	 	<c:forEach var="image" items="${images}">
 	 	<div cvlass="p-img-box">
 	 	 <a href="/user/${image.user.id}"><img src="/upload/${image.postImageUrl}" alt=""></a>
 	 	</div>
 	 	</c:forEach>
 	 </div> 
 	 
 </div>
</main>

<%@ include file="../layout/footer.jsp"%>
</body>
</html>