<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="administrator.request-auditor.list.label.firmm" path="firmm"/>
	<acme:list-column code="administrator.request-auditor.list.label.responsabilityStatt" path="responsabilityStatt"/>
</acme:list>
