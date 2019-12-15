<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

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


	<acme:form-submit test="${command = 'create'}"
	code="create"
	action="/employer/job/create"/>

	<acme:form-return code="employer.job.form.button.return"/>	
</acme:form>
