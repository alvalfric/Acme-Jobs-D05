<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.user-account.form.label.username" path="username" readonly="true"/>
	<acme:form-textarea code="administrator.user-account.form.label.name" path="firm" readonly="true"/>
	<acme:form-textarea code="administrator.user-account.form.label.surname" path="responsabilityStat" readonly="true"/>
	
	<acme:form-submit test="${command == 'show'}" 
		code="Aceptar"
		action="/administrator/request-auditor/create"/>
	<acme:form-submit test="${command == 'create'}" 
		code="Aceptar"
		action="/administrator/request-auditor/create"/>
	
  	<acme:form-return code="administrator.user-account.form.button.return"/>
</acme:form>
