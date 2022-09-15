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
	<h1>Hue Bridge: ${bridge.config.name}</h1>
 	<c:forEach var="light" items="${lights}" varStatus="loop">
		<p><a href="/toggle/${light.id}">[${light.id}] ${light.name} (On: ${light.state.on})</a></p>
	</c:forEach>
</body>
</html>