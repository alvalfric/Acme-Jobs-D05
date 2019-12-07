<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="employer.application.list.label.reference" path="reference" width="40%"/>
	<acme:list-column code="employer.application.list.label.moment" path="moment" width="30%"/>
	<acme:list-column code="employer.application.list.label.status" path="status" width="30%"/>
</acme:list>


