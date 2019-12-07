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

<style>
table {
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #e9ecef;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #e9ecef;
}
</style>

<acme:form readonly="true">
	<acme:form-textbox  code="authenticated.thread.form.label.moment" path="moment"/>
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title"/>
	<b><acme:message code="authenticated.thread.form.label.messages"/></b>
	<table>
		<tr>
			<th><acme:message code="authenticated.thread.form.label.message.title"/></th>
			<th><acme:message code="authenticated.thread.form.label.message.moment"/></th>
			<th><acme:message code="authenticated.thread.form.label.message.user"/></th>
		</tr>
		<jstl:forEach var="message" items="${messages}">
		<tr>
			<td>${message.title}</td>
			<td>${message.moment}</td>
			<td>${message.user.userAccount.username}</td>
			<td><a href = "authenticated/message/show?id=${message.id}"><acme:message code="authenticated.thread.form.label.message.show"/></a></td>
		</tr>
		</jstl:forEach>
	</table>
	<br>

	
	<acme:form-return code="authenticated.thread.form.button.return"/>
</acme:form>
