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

<acme:form>
	<acme:form-textbox code="employer.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<jstl:if test= "${command != 'show'}">
	<acme:form-select code="employer.job.form.label.publish" path="finalMode">
		<acme:form-option code="employer.job.form.label.publish-draft" value="false"/>
		<acme:form-option code="employer.job.form.label.publish-published" value="true"/>
	</acme:form-select>
	</jstl:if>
	<acme:form-textbox code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="employer.job.form.label.salary" path="salary"/>
	<acme:form-textbox code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="employer.job.form.label.description" path="descriptor.description"/>
	<jstl:if test= "${descriptor.id != null && !descriptor.getDuties().isEmpty()}">
		<b><acme:message code="employer.job.form.label.duty"/></b>
		<table>
			<tr>
				<th><acme:message code="employer.job.form.label.duty.title"/></th>
				<th><acme:message code="employer.job.form.label.duty.description"/></th>
				<th><acme:message code="employer.job.form.label.duty.percentage"/></th>
			</tr>
			<jstl:forEach var="duty" items="${descriptor.duties}">
			<tr>
				<td>${duty.title}</td>
				<td>${duty.description}</td>
				<td>${duty.percentage}</td>
				<td><input type="button" class="btn btn-default" onclick="location.href='/acme-jobs/employer/duty/show?jobId=${id}&dutyId=${duty.id}'" value="<acme:message code="employer.job.form.button.audit-record"/>"></td>
			</tr>
			</jstl:forEach>
		</table>
		<br>

	</jstl:if>

	<acme:form-submit test="${command == 'show'}" 
		code="administrator.announcement.form.button.update"
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.announcement.form.button.delete"
		action="/employer/job/delete"/>
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.announcement.form.button.create"
		action="/employer/job/create"/>
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.announcement.form.button.update"
		action="/employer/job/update"/>
	<acme:form-submit method="get" test="${command == 'show' && descriptor.id != null}" 
		code="Create duty"
		action="/employer/duty/create?jobId=${id}"/>
	<acme:form-submit test="${command == 'delete'}" 
		code="administrator.announcement.form.button.delete"
		action="/employer/job/delete"/>

	<input type="button" class="btn btn-default" onclick="location.href='/acme-jobs/employer/auditrecord/list-mine?jobId=${id}'" value="<acme:message code="employer.job.form.button.audit-record"/>">

	<acme:form-return code="employer.job.form.button.return"/>	
</acme:form>








	<%--
	<acme:form-submit method="get" code="employer.job.form.button.audit-record" action="auditrecord/list-mine?jobId=${id}"/>
	<acme:form-submit method="get" code="employer.job.form.button.audit-record" action="acme-jobs/employer/auditrecord/list-mine?jobId=${id}'"/>
	--%>