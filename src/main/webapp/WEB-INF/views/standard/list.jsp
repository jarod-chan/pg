<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>
<style type="text/css">

.li_sub{
	border: 1px solid rgba(0, 0, 0, 0.15);
    display:inline-block;
    word-break:break-all;
    width: 100%;
    margin-bottom: -5px;
    border-top:0px;
    background-color: #E0E0E0;
}

.li_sub:FIRST-CHILD{
	border-top:1px solid rgba(0, 0, 0, 0.15);
}

.li_sub div{
 	float:left;
	display: block;
	height:50px;   
  	line-height:50px
}
.div_no{
	width: 10%;
	font-size: 30px;
	text-align: center;
	background-color: gray;
	color: white;
}
.div_content{
	padding-left:5px;
	width: 75%;
}
.div_count{
	width: 10%;
	min-width: 40px;
}

.blink{
	background-color: #390d31;
	color:white;
	
}
</style>
</head>

<body>
	<c:set var="maincode" value="${item.code}"></c:set>
	<div class="container md_${maincode}">
		<div class="content">
			<h1 class="font-center font-white">${item.content}</h1>
			<h1 class="font-center font-white">@${community.name}</h1>
			<div class="font-center magtop20"><span class="font-point md_s_total"></span></div>
		</div>
		<div class="footer">
			<ul>
				<c:forEach  var="item" varStatus="status" items="${itemList}">
					<li class="li_sub" data-code="${item.code}"  >
					<div class="div_no">${status.count}</div>
					<div class="div_content">${item.content}</div>
					<div class="div_count font-center"><span class="font-point-ss"></span></div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>

<script type="text/javascript">
$(function(){ 
	 var timestamp=new Date().getTime();
 	 var scoreSpan=$(".font-point-ss");
	 $.get('${ctx}/standard/service/itemscore/${userid}/${community_key}/${ques_key}/${item.code}?timestamp='+timestamp,function(data){
		 var total=0;
		 for(var i=0;i<data.length;i++){
			 item=data[i];
			 total=total+item.score;
			 scoreSpan.eq(i).html(item.score);
		 }
		 $(".md_s_total").html(total);
	 }); 
	$(".li_sub").click(function(){
		$(this).addClass('blink');
		$(this).find('.font-point-ss').html("");
		$(this).find(".div_count").addClass("div_load");
		goto('${ctx}/standard/action/${userid}/${community_key}/${ques_key}/'+$(this).data("code"));
	});
})
</script>

</html>
