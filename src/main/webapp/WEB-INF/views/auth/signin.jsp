<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>instagram</title>
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
</head>
<body>

	<div class="container">

		<main class="loginMain">
			<section class="login">
				<article class="login__form__container">
					<div class="login__form">
						<h1>
							<img src="/images/logo.jpg" alt="로고">
						</h1>

						<form class="login__input" action="/auth/signin" method="post">
							<input type="text" name="username" placeholder="유저네임"
								required="required"> <input type="password"
								name="password" placeholder="비밀번호" required="required">
							<button>로그인</button>
						</form>

						<div class="login__facebook">
							<button onclick="javascript:location.href=''">
								<i class="fab fa-facebook-square"></i> <span>Facebook으로
									로그인</span>
							</button>
						</div>

					</div>
					
					<div class="login__register">
					 <span>계정이 없으신가요?</span>
					 <a href="/auth/signup">가입하기</a>
					</div>


				</article>

			</section>
		</main>
	</div>


</body>
</html>