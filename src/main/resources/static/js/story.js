/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
 */

// (0) 현재 로긴한 사용자 아이디
let principalId = $("#principalId").val();

// (1) 스토리 로드하기
let page = 0;

function storyLoad() {
	$.ajax({
		url: `/api/image?page=${page}`,
		dataType: "json"
	}).done(res => {
		//console.log(res);
		res.data.content.forEach((image)=>{
			let storyItem = getStoryItem(image);
			$("#storyList").append(storyItem);
		});
	}).fail(error => {
		console.log("오류", error);
	});
}

storyLoad();

function getStoryItem(image) {
	
	let item = `<div class="story-list__item">
	<div class="sl__item__header">
		<div>
			<img class="profile-image" src="/upload/${image.user.profileImageUrl}"
				onerror="this.src='/images/person.jpeg'" />
		</div>
		<div>${image.user.username}</div>
	</div>

	<div class="sl__item__img">
		<img src="/upload/${image.postImageUrl}" />
	</div>

	<div class="sl__item__contents">
		
		<div class="sl__item__contents__icon">
		 <button>`;
		 
		 if(image.likeState){
			item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`; 
		 }else{
			 item += `<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
		 }
		 
		 item += `
		 </button>
		</div>
		
		<span class="like"><b id="storyLikeCount-${image.id}">${image.likeCount}</b>Likes</span>

		<div class="sl__item__contents__content">
			<p>${image.caption}</p>
		</div>

	</div>
</div>`;

	return item;
}


// 좋아요, 안좋아요
function toggleLike(imageId){
	let likeIcon = $(`#storyLikeIcon-${imageId}`);
	
	if(likeIcon.hasClass("far")){ // 좋아요 하겠다.
	alert("좋아요하겠")
		$.ajax({
			type:"post",
			url:`/api/image/${imageId}/likes`,
			dataType:"json",
			
		}).done(res =>{
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) + 1;
			console.log("좋아요 카운트 증가", likeCountStr)
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		}).fail(error =>{
			console.log("오류", error);
		});
	}else{ // 좋아요 취소하겠다.
		 $.ajax({
			 type:"delete",
			 url:`/api/image/${imageId}/likes`,
			 dateType: "json",
			
		 }).done(res =>{
			 let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) - 1;
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		 }).fail(error => {
			 console.log("오류", error);
		 })
	}
}




