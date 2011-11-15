<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page session="false" %>

 
<tags:template>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / <a href="./project/">Projects</a> /</jsp:attribute>
	<jsp:body>

		<c:forEach items="${projects}" var="p">
				${ p.name }
				${ p.id }
	
		</c:forEach>
	</jsp:body>
</tags:template>
