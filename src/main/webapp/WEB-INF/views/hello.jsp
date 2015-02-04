<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<script src="${ctx}/resources/zepto.min.js"></script>
<style type="text/css">


body,html{  
  margin:0;  
  padding:0;  
  font:12px/1.5arial;  
  height:100%;  
 }  
 #container{  
  min-height:100%;  
  position:relative;  
 }  
 #content{  
  padding:10px;  
  padding-bottom:60px;    
  /*20px(font-size)
x2(line-height)+10px(padding)x2=60px*/  
 }  
 #footer{  
  position:absolute;  
  bottom:0;  
  padding:10px0;  
  background-color:#AAA;  
  width:100%;  
    min-height:50%;  
 }  
 #footerh1{  
  font:20px/2Arial;  
  margin:0;  
  padding:010px;  
 }  


</style>
<script type="text/javascript">
	$(function(){
		$("#x").click(function(){
			//$("#footer").append($('<h1>ssss</h1>'));
			//$("#footer h1").css("height",'200px');
		})
	})
</script>
</head>
<body>
	<div id="container">
		<div id="content">
			<button id="x"> dddd</button>
			<h1>Content</h1>
			<p>请改变浏览器窗口的高度，以观察footer效果。</p>
			<p>这里是示例文字，DIV固定………，这里是示例文字。</p>
		</div>
		<div id="footer">
			<h1>Footer</h1>
		</div>
	</div>
</body>
</html>
