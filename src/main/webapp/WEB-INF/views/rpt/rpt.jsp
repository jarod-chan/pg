<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>
<style type="text/css">
	table th,table td{
		width: 100px; 
		text-align: right;
	}
</style>
</head>


<body>
<table border="1">
<thead>
<tr>
	<th>小区</th>
	<c:forEach var="part" items="${parts}">
	<th colspan="2">${part.content}</th>
	</c:forEach>
	<th colspan="2">合计</th>
</tr>
</thead>
<tbody>
	<c:forEach var="map" items="${resultList}">
	<tr>
		<td>${map["community_name"]}</td>
		<td><fmt:formatNumber value="${map['w1']}" pattern="#0.00"/>%</td>
		<td>${map['s1']}</td>
		<td><fmt:formatNumber value="${map['w2']}" pattern="#0.00"/>%</td>
		<td>${map["s2"]}</td>
		<td><fmt:formatNumber value="${map['w3']}" pattern="#0.00"/>%</td>
		<td>${map["s3"]}</td>
		<td><fmt:formatNumber value="${map['w4']}" pattern="#0.00"/>%</td>
		<td>${map["s4"]}</td>
		<td><fmt:formatNumber value="${map['wt']}" pattern="#0.00"/>%</td>
		<td>${map["st"]}</td>		
	</tr>
	</c:forEach>
</tbody>
</table>

</body>


</html>
