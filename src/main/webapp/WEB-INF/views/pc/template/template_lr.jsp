<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/pc/pages/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>

<tiles:insertAttribute name="pri_link"></tiles:insertAttribute>
</head>
<body>
	<!-- head -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	</div>
	<!-- body -->
	<div class="container-fluid">
		<div class="row">
			<!-- left -->
			<div class="col-sm-3 col-md-2 sidebar">
				<input type="hidden" id="active_bar_tag" value="<tiles:insertAttribute name="active_bar" />">
				<ul class="nav nav-sidebar">
					<li class="active bar_borrow"><a href="#">借阅</a></li>
					<li class="bar_manage"><a href="#">管理</a></li>
				</ul>
			</div>
			<!-- right -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>


</body>
</html>