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
	<ul>
		<c:forEach var="uicv" items="${userItemChkValList}">
			<li class="context_li <c:if test="${uicv.ischeck}">sel_context</c:if>"  data-code="${uicv.item.code}" >${uicv.item.content}</li>
		</c:forEach>
	</ul>
</body>
<script type="text/javascript">
$(function(){
	$(".context_li").click(function(){
		var li=$(this);
	  	var item_code=li.data("code");
	  	if(li.hasClass("sel_context")){
	  	  $.post('${ctx}/standard/toggle/${userid}/${community_key}/${ques_key}/${item_code}',{val:item_code,action:'remove'},function(){	
	  		  li.removeClass("sel_context"); 
	  	  });
	  	}else{
  		  $.post('${ctx}/standard/toggle/${userid}/${community_key}/${ques_key}/${item_code}',{val:item_code,action:'add'},function(){	
  			li.addClass("sel_context"); 
  	      });
	  	}
	});
})
</script>
</html>
