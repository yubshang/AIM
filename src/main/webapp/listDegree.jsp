<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="label.listdegree"/></title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.6.0/bootstrap-table.min.css">
<link rel="stylesheet" type="text/css" href="css/application.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.6.0/bootstrap-table.min.js"></script>
</head>
<body class="center_abet">
	<h3 class="title_abet"><s:text name="label.listdegree"/></h3>
	<div class="table_holder_abet">
		<table data-toggle="table" data-sort-order="desc" data-url="json_degree_listDegreeJSON.action">
    		<thead>
        		<tr>
            		<th data-field="identifier" class="col-sm-2" data-sortable="true"><s:text name="label.identifier"/></th>
            		<th data-field="department" class="col-sm-3" data-sortable="true"><s:text name="label.department"/></th>
            		<th data-field="description" class="col-sm-7" data-sortable="true"><s:text name="label.description"/></th>
        		</tr>
    		</thead>
		</table>
	</div>
</body>
</html>