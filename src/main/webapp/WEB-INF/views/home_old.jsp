<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Guess Game!  
</h1>

<P>I am thinking a number from 1 to 10. <br>
You must guess what it is in three tries.
</P>
<form:form action="" method="post">
	<c:choose>
		<c:when test="${gameObj.gameStatus == 'start' || gameObj.gameStatus == 'playing'}">
			Enter a guess: 
			<input type="text" name="guess" />
			<input type="submit" value="Guess It"/>
		</c:when>
		<c:when test="${gameObj.gameStatus == 'done'}">
			Game Over!!!<br>
			<!-- Enter a guess:
			<input type="text" name="guess" value="Game Over" disabled="disabled" />
			<input type="submit" value="Guess It" disabled="disabled"/>  -->
		</c:when>
	</c:choose>
	<input type="hidden" name="attempt" value='${gameObj.attempt}' />
</form:form>
<c:choose>
<c:when test="${gameObj.gameStatus == 'playing'}">
	Your ${gameObj.attemptString} guess is: ${gameObj.guess}(${gameObj.type}) 
</c:when>	
<c:when test="${gameObj.gameStatus == 'done'}">
	<c:choose>
		<c:when test="${gameObj.result == 'won'}">
		Your ${gameObj.attemptString} guess is: ${gameObj.guess}<br>
		Right! You have won the game. <br>
		The Fibonacci number of ${gameObj.guess} is ${gameObj.fibNumber}<br>
		${gameObj.isItPrimeMessage}
		</c:when>
		<c:otherwise>
		Your ${gameObj.attemptString} guess is: ${gameObj.guess}<br>
		the actual number is: ${gameObj.actual}<br>
		Sorry! You have lost the game.
		</c:otherwise>
	</c:choose>
	<br><a href="">play again?</a>
</c:when>	
</c:choose>
</body>
</html>
