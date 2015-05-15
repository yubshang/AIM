<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:text name="label.addpeo"/></title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="css/application.css">
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/application.js" type="text/javascript"></script>
	<script type="text/javascript">
		var options;	
		var count = 1;
		$(function(){
			options = $("#select_target1").html();
			$(".delete_btn").click(function(){
				$(this).closest("div").remove();
			});
			hideValidateMessage($('#input_target1'));
			var max_target = null;
			$(".target").each(function(){
				if(max_target == null){
					max_target = $(this).text();
				}
				if($(this).text() > max_target){max_target=$(this).text();}
			});
			if(max_target != null){
				count = max_target.substring(6);
			};
		});
		
		function validateForm(){
			for(var i = 1; i <= count; i++){
				if($("#select_target"+i).val() != undefined&&$("#select_target"+i).val() != -1){
					if(isNaN($("#input_target"+i).val()) ||
						$("#input_target"+i).val() < 0 || $("#input_target"+i).val() > 100){
						$("#error_target"+i).text("Target must be a number between 0 and 100");
						return false;
					}
				}
			}
			return true;
		}
		
		function addRow(){
			count++;
			var html = "<div class=\"control-group\">" +
				"&nbsp;<select name=\"targets['target"+count+"'].semester\" id=\"select_target"+count+"\">" +
		    	options + "</select>" +
				"&nbsp;<input type=\"text\" name=\"targets['target"+count+"'].attainmentLevel\" value=\"0.0\" id=\"input_target"+count+"\"/>" +
				"&nbsp;<button class=\"btn delete_btn\">Delete</button>" +
				"&nbsp;<span id=\"error_target"+count+"\" class=\"error_abet\"></span></div>";
			$(".control-group").last().before(html);
			$(".delete_btn").click(function(){
				$(this).closest("div").remove();
			});
			$("#select_target"+count).val(-1);
			hideValidateMessage($("#input_target"+count));
			return false;
		}
	</script>
</head>
<body>
	<h3 class="title_abet"><s:text name="label.addpeo"/></h3>
	<s:form cssClass="form-horizontal" action="peo_addPeo" onsubmit="return validateForm()" method="post">
		<div class="control-group">
			<label class="control-label" for="inputId"><s:text name="label.identifier"/></label>
			<div class="controls">
      			<s:textfield id="inputId" name="identifier"/>
      			<span><s:text name="label.required"/></span>  
      			<span class="error_abet">
      				<s:if test="fieldErrors != null && fieldErrors.get('identifier') != null && fieldErrors.get('identifier').size() > 0">
     					<s:property value="fieldErrors.get('identifier').get(0)"/>
     				</s:if>
     			</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputSeq"><s:text name="label.sequence_num"/></label>
			<div class="controls">
      			<s:textfield id="inputSeq" name="sequenceNumber"/>
      			<span><s:text name="label.required"/></span>  
      			<span class="error_abet">
      				<s:if test="fieldErrors != null && fieldErrors.get('sequenceNumber') != null && fieldErrors.get('sequenceNumber').size() > 0">
     					<s:property value="fieldErrors.get('sequenceNumber').get(0)"/>
     				</s:if>
     			</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputName"><s:text name="label.short_name"/></label>
			<div class="controls">
      			<s:textfield id="inputName" name="shortName"/>
      			<span><s:text name="label.required"/></span>  
      			<span class="error_abet">
      				<s:if test="fieldErrors != null && fieldErrors.get('shortName') != null && fieldErrors.get('shortName').size() > 0">
     					<s:property value="fieldErrors.get('shortName').get(0)"/>
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
			<label class="control-label" for="input_target1"><s:text name="label.target"/></label>
			<div class="controls">
				<button class="btn" onclick="return addRow()">Add</button>
			</div>
		</div>
		<span class="error_abet">
			<s:if test="fieldErrors != null && fieldErrors.get('target') != null && fieldErrors.get('target').size() > 0">
     			<s:property value="fieldErrors.get('target').get(0)"/>
     		</s:if>
		</span>
		<s:if test="targets != null && targets.size() > 0">
			<s:iterator value="targets">
				<div class="control-group">
					<span class="target" style="display:none"><s:property value="key"/></span>
					<c:set var="t" scope="request"><s:property value="key"/></c:set>
					&nbsp;<s:select id="select_%{#request.t}" headerKey="-1" headerValue="Select Semester"
			 	name="targets['%{#request.t}'].semester" list="semesters" />
					<s:textfield id="input_%{#request.t}" name="targets['%{#request.t}'].attainmentLevel" />
					<button class="btn delete_btn">Delete</button>
					<span id="error_${t}" class="error_abet">
						<s:fielderror><s:param value="%{#request.t}" /> </s:fielderror>
					</span>
				</div>
			</s:iterator>
		</s:if>
		<s:else>
			<div class="control-group">
				&nbsp;<s:select id="select_target1" headerKey="-1" headerValue="Select Semester"
			 	name="targets['target1'].semester" list="semesters" />
				<s:textfield id="input_target1" name="targets['target1'].attainmentLevel" value="0.0"/>
				<button class="btn delete_btn">Delete</button>
				<span id="error_target1" class="error_abet"></span>
			</div>
		</s:else>
		<div class="control-group">
			<div class="controls">
				<s:submit key="dojo.label.submit" cssClass="btn btn-success" />
				<s:reset key="dojo.label.reset" cssClass="btn btn-warning" />
			</div>
		</div>
	</s:form>
</body>
</html>