<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
    <title>Hue</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Hue Light: ${light.name}</h1>
<form action="/edit" method="POST">
    <input type="hidden" id="id" name="id" value="${id}">
    Name: <input name="name" value="${light.name}"/>
    <br />
    <input type="submit" />
</form>
</body>
</html>