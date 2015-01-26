<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>
</head>

<body>
	<h3>${community.name}${item.content}</h3>
	<ul>
		<c:forEach var="uicc" items="${userItemChkCountList}">
			<li class="context_li" data-code="${uicc.item.code}"  >${uicc.item.content}${uicc.count}分</li>
		</c:forEach>
	</ul>
</body>

<script type="text/javascript">
$(function(){ 
	$(".context_li").click(function(){
		goto('${ctx}/standard/action/${userid}/${community_key}/${ques_key}/'+$(this).data("code"));
	});
})
</script>

</html>
