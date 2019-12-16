<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="administrator.auditor.list.label.firm" path="firm" width="10%"/>
	<acme:list-column code="administrator.auditor.list.label.responsabilityStat" path="responsabilityStat" width="10%"/>
</acme:list>
