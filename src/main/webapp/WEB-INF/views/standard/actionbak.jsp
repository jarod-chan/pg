<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>
<style type="text/css">
.content{
	padding:0px;
	padding-top:5px;
}

.li_cmt{
	width: 100%;
    background-color: #E0E0E0;
    margin-top: -5px;  
    height: 147px;
    border-bottom: 6px solid white;
}
.li_cmt div{
 	float:left;
	display: block;
}
.li_cmt .cmt_no{
	line-height:147px;
	height: 147px;
	width: 10%;
	background-color: gray;
	text-align: center;
	color:purple;
}
.li_cmt .cmt{
	padding-left:5px;
	width: 75%;
	overflow:hidden;
	height:147px;
}



.li_item{
	border-bottom: 1px solid rgba(0, 0, 0, 0.15);
    display:inline-block; 
    width: 100%;
    background-color: #E0E0E0;
    margin-top: -5px;  
}

.li_item:FIRST-CHILD{
	border-top: 1px solid rgba(0, 0, 0, 0.15);
}

.li_item_select{
	background-color:#390d31; 	
	color: #FFFFFF;
}

.li_item_select .div_no{
	color: black;
}

.li_item div{
 	float:left;
	display: block;
}
.div_no{
	line-height:50px;
	height: 50px;
	width: 10%;
	font-size: 30px;
	color:white;
	text-align: center;
	background-color: gray;
}
.div_content{
	padding-left:5px;
	width: 75%;
	overflow:hidden;
	height:50px;
	max-height: 50px;
}
.div_chk{
	width: 10%;
	color: red;
	font-size: 50px;
    font-family: SimHei;
    padding-top:10px;
    padding-left:5px;
    height: 40px;
}


.circle {
    height: 30px;
    width: 30px;
    border-radius: 15px;
    background: red;
}

.circle div {
    position: relative;
    height: 20px;
    width: 20px;
    background: #390d31;
    border-radius: 10px;
    left: 5px;
    top: 5px;
}


</style>
</head>
<body>
	<div class="container">
		<div class="content">
		<ul>
			<c:forEach var="uicv" items="${userItemChkValList}" varStatus="status"> 
				<c:choose>
					<c:when test="${status.count>=10}"><c:set var="no" value="A" /></c:when>
					<c:otherwise><c:set var="no" value="${status.count}" /></c:otherwise>
				</c:choose>
				<li class="li_item <c:if test="${uicv.ischeck}">li_item_select</c:if>"  data-code="${uicv.item.code}"  >
				<div class="div_no">${no}</div>
				<div class="div_content" data-cmt="${uicv.iscomment}">${uicv.item.content}</div>
				<div class="div_chk"><div class="div_circle <c:if test='${uicv.ischeck}'>circle</c:if>"><div></div></div></div>
				</li>
				<c:if test="${uicv.iscomment}">
				<li class="li_cmt">
					<div class="cmt_no">注</div>
					<div class="cmt">${uicv.comment}</div>
				</li>
				</c:if>
			</c:forEach>
		</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#head_title").html("${item.content}");
	$(".div_content").click(function(event){
		event.stopPropagation();
		var div=$(this);
		var iscomment=div.data("cmt"); 
		if(iscomment){
			var licmt=div.parent().next();
			licmt.toggle();
		}
	})
	
	$(".li_item").click(function(){
		var li=$(this);
		var chkdiv=li.find(".div_chk");
		if(chkdiv.hasClass("div_load")){
			return;
		}else{
			li.find(".div_circle").removeClass("circle");
			chkdiv.addClass("div_load");
		}
	  	
		var item_code=li.data("code");
	  	if(li.hasClass("li_item_select")){
	  	  $.post('${ctx}/standard/toggle/${userid}/${community_key}/${ques_key}/${item_code}',{val:item_code,action:'remove'},function(){	
	  		  li.removeClass("li_item_select"); 
	  		  chkdiv.removeClass("div_load");
	  	  });
	  	}else{
  		  $.post('${ctx}/standard/toggle/${userid}/${community_key}/${ques_key}/${item_code}',{val:item_code,action:'add'},function(){	
  			li.addClass("li_item_select"); 
  			li.find(".div_circle").addClass("circle");
  			chkdiv.removeClass("div_load");
  	      });
	  	}
	});
})
</script>
</html>
