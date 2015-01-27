<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>
<style type="text/css">
.container{
	background-color: gray;
}

.li_md{
	display:block;
	float: left;
    width: 50%;
}
.div_md{
	border: 1px solid rgba(0, 0, 0, 0.15);
	padding: 5px;
	min-height: 120px;
}
.div_md_sel{
	background-color:#FF8080;
	background-color: navy;
}

</style>
</head>


<body>
	<div class="container">
		<div class="content">
			<h1 class="font-center font-white">${community.name}</h1>
			<div class="font-center"><span class="font-point">00</span></div>
		</div>
		<div class="footer">
			<ul>
				<c:forEach var="partMap" items="${partList}">
					<li class="li_md">
						<div class="div_md font-white <c:if test="${partMap['hasPartcode']}">div_md_sel</c:if>" data-code="${partMap['item'].code}">
						<div>${partMap['item'].content}</div>
						<div class="font-center"><span class="font-point-small">00</span></div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>

<script type="text/javascript">
$(function(){
	$(".div_md_sel").click(function(){
		goto('${ctx}/standard/list/${userid}/${community_key}/${ques_key}/'+$(this).data("code"));
	});
})
</script>

</html>
