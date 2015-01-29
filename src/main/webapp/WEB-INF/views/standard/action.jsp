<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>
<style type="text/css">
	.row{
		width:100%;
		height: 50px;
		background-color:#9e9480;
		border-top:1px solid gray;
	}
	
	.row:FIRST-CHILD{
		border: 0px;
	}
	
	
	
	.row .div_no,.row .div_chk,.row .div_content{
		display:block;
		height: 50px;
	}
	
	.row .div_no{
		line-height:50px;
		float: left;
		width: 50px;
		font-size: 30px;
		color:white;
		text-align: center;
		background-color: #38444e;
	}
	
	.row .div_chk{
		float: right;
		width: 50px;
	}
	
	.row .div_content{
		padding-left: 5px;
		overflow:hidden;
	}
	
	.row_select {
		background-color:#38444e; 	
		color: #FFFFFF;
	}
	.row_select .div_no{
		color: #ea4424;
	}
	.row_select  .circle div {
		background-color:#38444e;
	}
	/*------------------------------*/
	.circle {
		margin:0 auto;
		margin-top:10px;
	    height: 30px;
	    width: 30px;
	    border-radius: 15px;
	    background: #ea4424;
	}
	
	.circle div {
	    position: relative;
	    height: 20px;
	    width: 20px;
	    background: #9e9480;
	    border-radius: 10px;
	    left: 5px;
	    top: 5px;
	}
	

	.row .div_chk .times{
		height:50px;
		line-height:50px;
		font-size: 50px;
		color:white;
		text-align: center;
		margin-top: -5px;
		font-weight: 900;
	}
	
	/*-----------------------------------*/
	.cmt{
		background-color:#38444e;
		width:100%;
	}	
	.cmt .cmt_content{
		border-top:1px dashed gray;
		margin-left:50px;
		padding-left:5px;
		padding-right:50px;
		min-height: 50px;
		background-color:#9e9480;
	}
	/*----*/
	.none{
		display: none;
	}
	
</style>
</head>
<body>
<div class="ct">
<c:forEach var="uicv" items="${userItemChkValList}" varStatus="status"> 
	<c:choose>
		<c:when test="${status.count>=10}"><c:set var="no" value="A" /></c:when>
		<c:otherwise><c:set var="no" value="${status.count}" /></c:otherwise>
	</c:choose>
	<div class="row <c:if test="${uicv.ischeck}">row_select</c:if>"  data-code="${uicv.item.code}"  >
		<div class="div_no">${no}</div>
		<div class="div_chk"><div class="circle <c:if test='${!uicv.ischeck}'>none</c:if>"><div></div></div> <div class="times <c:if test='${uicv.ischeck}'>none</c:if>">&times;</div> </div>
		<div class="div_content" data-cmt="${uicv.iscomment}">${uicv.item.content}</div>
	</div>
	<c:if test="${uicv.iscomment}">
	<div class="cmt none">
		<div class="cmt_content">${uicv.comment}</div>
	</div>
	</c:if>
</c:forEach>	
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
			var lidiv=div.parent().next();
			lidiv.toggle();
		}
	})
	
	$(".div_chk").click(function(){
		var chkdiv=$(this);
		if(chkdiv.hasClass("div_load")){
			return;
		}else{
			chkdiv.find(".circle,.times").hide();
			chkdiv.addClass("div_load");
		}
	  	
		var row=chkdiv.parent();
		var item_code=row.data("code");
	  	if(row.hasClass("row_select")){
	  	  $.post('${ctx}/standard/toggle/${userid}/${community_key}/${ques_key}/${item_code}',{val:item_code,action:'remove'},function(){	
	  		  row.removeClass("row_select"); 
	  		  chkdiv.removeClass("div_load");
	  		  chkdiv.find(".times").show();
	  	  });
	  	}else{
  		  $.post('${ctx}/standard/toggle/${userid}/${community_key}/${ques_key}/${item_code}',{val:item_code,action:'add'},function(){	
  			row.addClass("row_select"); 
  			chkdiv.removeClass("div_load");
  			chkdiv.find(".circle").show();
  	      });
	  	}
	});
})
</script>
</html>
