<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${ctx}/resources/app.css" rel="stylesheet" />
<script src="http://cdnjs.gtimg.com/cdnjs/libs/jquery/2.1.1/jquery.min.js"></script>
<script src='${ctx}/resources/fastclick.min.js'></script>
<script type="text/javascript">
	$(function() {FastClick.attach(document.body);}); function goto(url){window.open(url,"_self");}//window.location.replace(url);
	$.ajaxSetup({timeout:8000,error:function(jqXHR,textStatus,errorThrown){if(textStatus=='timeout'){alert("请求超时，请检查网络连接");}else{alert("操作失败，请刷新页面重试。");}  }});
</script> 
