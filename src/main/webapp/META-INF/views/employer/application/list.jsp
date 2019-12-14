<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="employer.application.list.label.reference" path="reference" width="40%"/>
	<acme:list-column code="employer.application.list.label.moment" path="moment" width="30%"/>
	<acme:list-column code="employer.application.list.label.status" path="status" width="30%"/>
</acme:list>

<acme:form>
	<acme:form-submit code="employer.application.form.button.accepted" 
	action="/employer/application/list-status?appStatus=ACCEPTED"/>
	<acme:form-submit code="employer.application.form.button.pending" 
	action="/employer/application/list-status?appStatus=PENDING"/>
	<acme:form-submit code="employer.application.form.button.rejected" 
	action="/employer/application/list-status"/>
</acme:form>

<input type="button" class="btn btn-default" onclick="location.href='/acme-jobs/employer/auditrecord/list-mine?jobId=${id}'" value="<acme:message code="employer.job.form.button.audit-record"/>" >



