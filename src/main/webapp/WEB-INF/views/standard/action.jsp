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
.li_item{
	border: 1px solid rgba(0, 0, 0, 0.15);
	border-top:0px;
    display:inline-block; 
    width: 100%;
    margin-top: -5px;  
}

.li_item:FIRST-CHILD{
	border-top: 1px solid rgba(0, 0, 0, 0.15);
}

.li_item_select{
	background-color:#FECF78; 	
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
.div_load{
	 background: url("${ctx}/resources/img/ld.gif") center no-repeat;
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
    background: #FECF78;
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
				<li class="li_item <c:if test="${uicv.ischeck}">li_item_select</c:if>"  data-code="${uicv.item.code}" >
				<div class="div_no">${status.index}</div>
				<div class="div_content">${uicv.item.content}</div>
				<div class="div_chk"><div class="div_circle <c:if test='${uicv.ischeck}'>circle</c:if>"><div></div></div></div>
				</li>
			</c:forEach>
		</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
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
