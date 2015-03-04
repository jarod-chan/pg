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
		line-height:50px;
		float: right;
		color: #ffffff;
	    font-size: 30px;
	    font-family: SimHei;
		width: 50px;
		text-align: center;
	}
	
	.row_select .div_chk{
		color: #ea4424;
	}
	
	.row .div_content{
		padding-left:5px;
		overflow:hidden;
	}
	

 	.row .div_sel{
		position:absolute; 
		margin-top:-50px;
		right:0px;
		color: #FFFFFF;
	    font-size: 30px;
	    text-align: center;
	    font-family: SimHei;
	    background-color:#9e9480;
	    width: 100%;
	} 
	
	.row .row_select{
		background-color:  #38444e;
	}
	
	
	.row .div_sel .selop{
		float:right;
		display:block;
		width:50px;
		height: 50px;
		line-height: 50px;
		border-left: 1px solid gray;
	}
	
	.row .div_sel .selchk{
		color: #ea4424;
	}
	
	.row .div_sel .selfile{
		float:left;
		display:block;
		width:50px;
		height: 50px;
		line-height: 50px;
		border-right: 1px solid gray;
	}
	
	.row_select {
		background-color:#38444e; 	
		color: #FFFFFF;
	}
	.row_select .div_no{
		color: #ea4424;
	}

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
<div>
<c:forEach var="uicv" items="${userItemChkValList}" varStatus="status"> 
	<c:choose>
		<c:when test="${status.count>=10}"><c:set var="no" value="A" /></c:when>
		<c:otherwise><c:set var="no" value="${status.count}" /></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${uicv.val!=null}"><c:set var="val" value="${uicv.val}" /></c:when>
		<c:otherwise><c:set var="val" value="&times;" /></c:otherwise>
	</c:choose>
	<div class="row  <c:if test='${uicv.val!=null}'>row_select</c:if>"  data-code="${uicv.item.code}"  >
		<div class="div_no">${no}</div>
		<div class="div_chk">${val}</div>
		<div class="div_content" data-cmt="${uicv.iscomment}">${uicv.item.content}</div>
		<div class="div_sel <c:if test='${uicv.val!=null}'>row_select</c:if> none">
			<c:forEach var="s"  begin="0" end="4" >
				<div class="selop  <c:if test="${uicv.val==4-s}">selchk</c:if>">${4-s}</div>
			</c:forEach>
				<div class="selfile">附</div>
		</div> 
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
		$(".div_sel").hide();
		var div=$(this);
		var iscomment=div.data("cmt"); 
		if(iscomment){
			var lidiv=div.parent().next();
			lidiv.toggle();
		}
	})
	
	$(".selop").click(function(){
		var selop=$(this);
		if(selop.hasClass("selchk")){
			selop.parent().hide();
			return;//得分未改变
		}
		
		var row=selop.parent().parent();
		var div_chk=row.find(".div_chk");
		var div_sel=row.find(".div_sel");
		var oldval=div_chk.html();
		var selopval=selop.html();	
		var item_code=row.data("code");	
		div_chk.html('').addClass("div_load");
		div_sel.hide();
		$.post('${ctx}/standard/service/save/${userid}/${community_key}/${ques_key}',{item_code:item_code,val:selopval},function(){	
	  		row.find(".selchk").removeClass('selchk');
	  		div_chk.html(selopval);
	  		row.addClass("row_select");
	  		div_sel.addClass("row_select");//选中修改背景色
	  		selop.addClass("selchk");
		})
		.complete(function(){
			div_chk.removeClass("div_load");
		})
		.error(function(){
			div_chk.html(oldval);//恢复原值
		})
	
	});
	
	$(".div_chk").click(function(event){
		event.stopPropagation();
		$(".div_sel").hide();
		$(this).parent().find(".div_sel").show();
	});
	
	$(".selfile").click(function(){
		var row=$(this).parent().parent();
		var item_code=row.data("code");	
		goto('${ctx}/fileup/${userid}/${community_key}/${ques_key}/'+item_code);
	});
})
</script>
</html>
