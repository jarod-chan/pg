<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
</head>

<body>

	<ul>
		<c:forEach var="uicc" items="${userItemChkCountList}">
			<li>${uicc.item.content}${uicc.count}分&nbsp;&nbsp;<a href="${ctx}/standard/action/${userid}/${community_key}/${ques_key}/${uicc.item.code}">详细</a></li>
		</c:forEach>
	</ul>

</body>
</html>
