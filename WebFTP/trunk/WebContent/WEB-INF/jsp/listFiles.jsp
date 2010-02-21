<%@ page contentType="text/html"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Filelist</title>
<style>
</style>
</head>
<body>
<h1>Filelist</h1>
<table>
	<tr>
		<th>Filename</th><th>Size</th>
	</tr>
	<c:forEach items="${files}" var="file">
	<tr>
		<td>
			<img src="static/mimeTyp?mimeType="<c:out value="${file.mimeType}" /> width="32" height="32"/>
			<c:out value="${file.fileName}" />
		</td>
		<td>
			<c:out value="${file.size}" />
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>