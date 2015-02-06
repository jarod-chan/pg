<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>
<style type="text/css">

.li_md{
	display:block;
	float: left;
    width: 50%;
}
.div_md{
	padding: 5px;
	min-height: 100px;
	font-size: 20px;
	padding-top: 20px;
}
.footer{
	margin-left: 0px;
	margin-bottom: -1px;
}



.content{
	margin:0 auto;
	width: 300px;
}

.content .backimg{
	height:252px;
	background: url("${ctx}/resources/img/2circle.png") no-repeat -20px -20px;
 	-moz-background-size:340px;  
    background-size:340px ;    
}

.divscore .score_a{
	margin-top:-220px;
	float:left;
	width: 50%;
}

.divscore .score_b{
	margin-top:-140px;
	float:right;
	width: 50%;
}
.content .divcommunity{
	width: 140px;
	float: right;
	margin-top: 20px;
	font-size: 25px;
}

</style>
</head>


<body>
	<div class="container md_total">
		<div class="content">
			<div class="divcommunity font-center">${community.name}</div>
			<div class="backimg"></div>
			<div class="divscore">
				<div class="score_a">
					<div class="font-center font-typea">加权分值</div>
					<div class="font-center"><span class="font-point-small">00.00</span></div>
				</div>
				<div class="score_b">
					<div  class="font-center font-typeb">累积分值</div>
					<div class="font-center"><span class="font-point-small md_s_total"></span></div>
				</div>
			</div>
			
		</div>
		
		
		
		<div class="footer">
			<ul>
				<c:forEach var="partMap" items="${partList}">
					<li class="li_md">
						<div class="div_md font-center font-white md_${partMap['item'].code} <c:if test="${partMap['hasPartcode']}">div_md_sel</c:if>" data-code="${partMap['item'].code}">
						<div>${partMap['item'].content}</div>
						<div class="div_count font-center" style="margin-top: 10px;"><span class="font-point-small md_s_${partMap['item'].code}"></span></div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>

<script type="text/javascript">
$(function(){
	 var timestamp=new Date().getTime();
	 $.get('${ctx}/standard/service/modulescore/${userid}/${community_key}/${ques_key}?timestamp='+timestamp,function(data){
		 var total=0;
		 for(var i=0;i<data.length;i++){
			 item=data[i];
			 total=total+item.score;
			 $(".md_s_"+item.code).html(item.score);
		 }
		 $(".md_s_total").html(total);
	 });
	$(".div_md_sel").click(function(){
		$(this).find('.font-point-small').html("&nbsp;");
		$(this).find(".div_count").addClass("div_load");
		goto('${ctx}/standard/list/${userid}/${community_key}/${ques_key}/'+$(this).data("code"));
	});
})
</script>

</html>
