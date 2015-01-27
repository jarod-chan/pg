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
	background-color: navy;
}

.li_sub{
	border: 1px solid rgba(0, 0, 0, 0.15);
    display:inline-block;
    word-break:break-all;
    width: 100%;
    margin-top: -5px;
    border-top:0px;
}

.li_sub:FIRST-CHILD{
	border-top: 1px solid rgba(0, 0, 0, 0.15);
}

.li_sub div{
 	float:left;
	display: block;
	height:50px;   
  	line-height:50px
}
.div_no{
	width: 10%;
}
.div_content{
	width: 80%;
}
.div_count{
	width: 10%;
}
</style>
</head>

<body>
	<div class="container">
		<div class="content">
			<h1 class="font-center font-white">${item.content}@${community.name}</h1>
			<div class="font-center" style="padding-top: 20px;"><span class="font-point">00</span></div>
		</div>
		<div class="footer">
			<ul>
				<c:forEach  var="uicc" varStatus="status" items="${userItemChkCountList}">
					<li class="li_sub" data-code="${uicc.item.code}"  >
					<div class="div_no">${status.count}</div>
					<div class="div_content">${uicc.item.content}</div>
					<div class="div_count"><span class="font-point-ss">${uicc.count}</span></div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>

<script type="text/javascript">
$(function(){ 
	$(".li_sub").click(function(){
		goto('${ctx}/standard/action/${userid}/${community_key}/${ques_key}/'+$(this).data("code"));
	});
})
</script>

</html>
