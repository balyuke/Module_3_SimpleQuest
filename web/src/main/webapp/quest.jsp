<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Simple Quest</title>
</head>
<body>
<section>
    <a href="index.jsp">Начать заново</a>
    <h3>${question.content}</h3>

    <form action="${question.type == "LOST" || question.type == "WON" ? 'auth' : 'quest'}" method="get">
        <c:forEach items="${answers}" var="answer">
            <jsp:useBean id="answer" type="com.javarush.balyuke.simplequest.entities.Answer"/>
            <input type="radio" name="answerid" value="${answer.id}">${answer.content}<br>
        </c:forEach>
        <button type="submit">${question.type == "LOST" || question.type == "WON" ? 'Сыграть еще' : 'Ответить'}</button>
    </form>
</section>
<br>
<br>
<br>
<br>
<br>
Статистика:<br>
IP address: <i>${ip}</i><br>
Имя в игре: <i>${userName}</i><br>
Количество игр: <i>${attempt}</i><br>
</body>
</html>