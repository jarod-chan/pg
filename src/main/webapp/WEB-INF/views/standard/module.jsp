<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>
<style type="text/css">
	.li_layout{
	    word-break:break-all;
	    width: 50%;
	    margin-top: 5px;
	    float: left;
	}
	.div_md{
		border: 1px solid rgba(0, 0, 0, 0.15);
		border-radius: 0.3125em;
		height: 100%;
		width: 100%;
		min-height: 60px;
		display:inline-block;
		padding-left:10px;
	}
	
</style>
</head>


<body>
	<h3>${community.name}</h3>
	<ul>
		<c:forEach var="partMap" items="${partList}">
			<li class="li_layout" data-code="${partMap['item'].code}"  ><div class="div_md">${partMap['item'].content}</div></li>
		</c:forEach>
	</ul>
</body>

<script type="text/javascript">
$(function(){
	$(".li_layout").click(function(){
		goto('${ctx}/standard/list/${userid}/${community_key}/${ques_key}/'+$(this).data("code"));
	});
})
</script>

</html>
