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

<acme:form readonly="true">
	<acme:form-textbox  code="authenticated.message.form.label.moment" path="moment"/>
	<acme:form-textbox code="authenticated.message.form.label.title" path="title" placeholder="Title"/>
	<acme:form-textbox code="authenticated.message.form.label.user" path="user.userAccount.username"/>
	<acme:form-textbox code="authenticated.message.form.label.tags" path="tags" placeholder="Tags"/>
	<acme:form-textbox code="authenticated.message.form.label.body" path="body" placeholder= "Body Text"/>
	
	

	
	<acme:form-return code="authenticated.message.form.button.return"/>
</acme:form>
