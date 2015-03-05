<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Degree</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="css/application.css">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
$(function(){
	hideValidateMessage($('#inputId'));
	hideValidateMessage($('#inputDep'));
});
function hideValidateMessage(input){
	input.keyup(function(event){
		$(this).siblings('.error').text("");
	});
}
</script>
</head>
<body>
<h3 class="title"><s:text name="label.adddegree"/></h3>
<s:form cssClass="form-horizontal" action="degree_addDegree" method="post">		
	<div class="control-group">
		<label class="control-label" for="inputId"><s:text name="label.identifier"/></label>
		<div class="controls">
      		<s:textfield id="inputId" name="identifier"/>
      		<span>*</span>  
      		<span class="error">
      			<s:if test="fieldErrors != null && fieldErrors.get('identifier') != null && fieldErrors.get('identifier').size() > 0">
     				<s:property value="fieldErrors.get('identifier').get(0)"/>
     			</s:if>
     		</span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputDep"><s:text name="label.department"/></label>
		<div class="controls">
      		<s:textfield id="inputDep" name="department"/>
      		<span>*</span> 
      		<span class="error">
      			<s:if test="fieldErrors != null && fieldErrors.get('department') != null && fieldErrors.get('department').size() > 0">
     				<s:property value="fieldErrors.get('department').get(0)"/>
     			</s:if>
     		</span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputDes"><s:text name="label.description"/></label>
		<div class="controls">
      		<s:textarea id="inputDes" name="description" cols="30" rows="8"/>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<s:submit key="dojo.label.submit" cssClass="btn" />
			<s:reset key="dojo.label.reset" cssClass="btn" />
		</div>
	</div>
</s:form>
</body>
</html>