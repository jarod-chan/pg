<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
</head>

<body>
	<form action="${ctx}/standard/save/${userid}/${community_key}/${ques_key}/${item_code}" method="post">
	<ul>
		<c:forEach var="uicv" items="${userItemChkValList}">
			<li>${uicv.item.content}<input type="checkbox" name="val" value="${uicv.item.code}" <c:if test="${uicv.ischeck}">checked="checked"</c:if> ></li>
		</c:forEach>
	</ul>
	<input type="submit" value="保存">
	</form>

</body>
</html>
