<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>

<style type="text/css">
/* .div_fileup{
	margin: 0;
	padding: 0;
}

.add_img_div{
 	background: url("${ctx}/resources/img/add.jpg") no-repeat center;
 	background-size:80px ;  
 	-moz-background-size:80px;   
    display: block;
    height: 75px;
    width: 75px;
	float:left;
	border:1px solid #E0E0E0;
	margin: 3px 0px 0px 3px;
} */

#box{
    width:120px;height:120px;
    display:table;
    text-align:center;
    border:1px solid #d3d3d3;background:red;
}
#box span{
    display:table-cell;
    vertical-align:middle;
}
#box img{
	max-width: 120px;
	max-height: 120px;
	width: auto;
	height: auto;
  
}

</style>

<script src='http://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>
<script type="text/javascript">
wx.config({
    debug: true, appId: '${wxCpConfigStorage.corpId}',timestamp:${wxCpJsapiSignature.timestamp} , 
    nonceStr: '${wxCpJsapiSignature.noncestr}', signature: '${wxCpJsapiSignature.signature}',
    jsApiList: ['chooseImage','previewImage','uploadImage','downloadImage'] 
})
</script>
</head>

<body>

	<div id="box">
	    <span><img src="${ctx}/resources/img/add.jpg"/></span>
	</div>
	
</body>
<script type="text/javascript">
	$(".add_img_div").click(function(){
		wx.chooseImage({
		    success: function (res) {
		        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		    }
		});
	});
</script>

</html>
