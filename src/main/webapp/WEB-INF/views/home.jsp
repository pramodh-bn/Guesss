<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/cover.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">
</head>
<body>
	<div class="container-fluid">
		<div class="inner cover">
			<div class="row-fluid">
			<h1 class="cover-heading">Guess Game</h1>
			<p class="lead">
				I am thinking of a number from 1 to 10. <br> You must guess
				what it is in three tries.
			</p>
			<form:form action="" method="post" class="form-inline" role="form">
				<c:choose>
					<c:when test="${gameObj.gameStatus == 'start' || gameObj.gameStatus == 'playing'}">
						<div class="form-group">
							<input class="form-control" placeholder="guess" id="guess" autofocus="autofocus" type="number" min="1" max="10" name="guess" />
						</div>
						<div class="form-group">
							<button class="btn btn-primary" type="submit">Guess It</button>
						</div>
					</c:when>
				</c:choose>
				<input type="hidden" name="attempt" value='${gameObj.attempt}' />
			</form:form>
			<c:choose>
				<c:when test="${gameObj.gameStatus == 'playing'}">
					<p class="lead">Your ${gameObj.attemptString} guess is: ${gameObj.guess}(${gameObj.type})</p> 
				</c:when>
				<c:when test="${gameObj.gameStatus == 'done'}">
					<c:choose>
						<c:when test="${gameObj.result == 'won'}">
							<p class="lead guess_correct">
							   Right!<br>
							   Your ${gameObj.attemptString} guess ${gameObj.guess} is correct.<br>
							   You won.<br>
							   The Fibonacci number of ${gameObj.guess} is ${gameObj.fibNumber}<br>
							   ${gameObj.isItPrimeMessage}
							</p>
						</c:when>
						<c:otherwise>
							<p class="lead guess_lost">
							Sorry!<br>
							Game Over<br>
							Your ${gameObj.attemptString} guess is: ${gameObj.guess}<br>
							The actual number is: ${gameObj.actual}<br>
							You lost.
							</p>
						</c:otherwise>
					</c:choose>
					<br>
					<a class="btn btn-lg btn-primary" href="">Wanna play again?</a>
				</c:when>
			</c:choose>
			</div>
		</div>
	</div>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>
