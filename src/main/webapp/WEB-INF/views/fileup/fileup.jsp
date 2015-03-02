<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>

<style type="text/css">
.ul_fileup {
	margin: 0;
	padding: 0;
}

.ul_fileup li {
	list-style: none;
	display:block;
	margin-left: 3px;
	margin-top: 3px;
	float: left;
}

.ul_fileup li div {
	display: table-cell;
	width: 75px;
	height: 75px;
	border: 1px solid #E0E0E0;
	text-align: center;
	vertical-align: middle;
}

.ul_fileup li div img {
	vertical-align: middle;
	max-height: 75px;
	max-width: 75px;
}

.ul_fileup li .close_span{
	background: url("${ctx}/resources/img/close.png") no-repeat center;
 	background-size:25px ;  
 	-moz-background-size:25px; 
 	width:25px;
 	height:25px; 
	position: absolute;
	display: block;
	margin-left: 50px;
	
}

.ul_fileup li .none{
	display: none;
}

.add_img_div{
 	background: url("${ctx}/resources/img/add.jpg") no-repeat center;
 	background-size:80px ;  
 	-moz-background-size:80px;   
}


.load_img_div{
	background: url("${ctx}/resources/img/ld.gif") no-repeat center;
	background-size:40px ;  
	font-size: 30px;
}



</style>
<script src='http://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>
<script type="text/javascript">
wx.config({
    debug: false, appId: '${wxCpConfigStorage.corpId}',timestamp:${wxCpJsapiSignature.timestamp} , 
    nonceStr: '${wxCpJsapiSignature.noncestr}', signature: '${wxCpJsapiSignature.signature}',
    jsApiList: ['chooseImage','previewImage','uploadImage','downloadImage'] 
})
</script>

</head>

<body>
<ul class="ul_fileup">
	 <c:forEach var="itemimg" items="${itemimgList}" varStatus="status">
	 <c:choose>
	 <c:when test="${itemimg.local}"><c:set var="dataUrl" value="${serverNameWithPort}${ctx}/fileimg/${itemimg.img_id}.jpg" /></c:when>
	 <c:otherwise><c:set var="dataUrl" value="https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=${ACCESS_TOKEN}&media_id=${itemimg.img_id}" /></c:otherwise>
	 </c:choose>
	 <li id='${itemimg.id}' data-url='${dataUrl}' ><div class='close_span'></div><div><img src='${dataUrl}' /></div></li> 
	</c:forEach>
    <li id="last_li">
        <div class="add_img_div"></div>
    </li>
</ul>
</body>
<script type="text/javascript">
	$(function(){	
		var last_li=$("#last_li");
		var add_img_div=last_li.find(".add_img_div");
		
		add_img_div.click(function(){
			if(add_img_div.hasClass("load_img_div")) return;
			wx.chooseImage({
			    success: function (res) {
			    	add_img_div.addClass("load_img_div");
			        upload(res.localIds);
			    }
			});
		});
		
		
		function upload(localIds){
			var len=localIds.length;
			var serverIds=[];
			function uploadOne(thisidx,localId){
				if(thisidx==len){
					saveImgs(serverIds);
					return;
				}
				add_img_div.html((thisidx+1)+"/"+len);//显示上传序号
				wx.uploadImage({
				    localId: localId, 
				    isShowProgressTips: 0,
				    success: function (res) { 
				        var serverId = res.serverId; 
				        serverIds.push(serverId);
				        addImgLi(localId,serverId);
				        
				        thisidx=thisidx+1;
				        uploadOne(thisidx,localIds[thisidx]);
				    }
				});
			}
			uploadOne(0,localIds[0]);
		}
		
		function addImgLi(localId,serverId){
			var html=$("<li class='temp' data-url='https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=${ACCESS_TOKEN}&media_id="+serverId+"'><div class='close_span none'></div><div><img src='"+localId+"' /></div></li>");
			html.find(".close_span").click(removeImgLi);
			html.click(previewImage);
			last_li.before(html); 
		}
		
		//删除图片
		function removeImgLi(event){
			event.stopPropagation();
			if(confirm('确定删除?')){
				$(this).hide();
				var li=$(this).parent();
				var id=li.attr("id");		
				$.post('${ctx}/fileup/delete/'+id,function(){	
					li.remove();
				})
			}
		}
		$(".close_span").click(removeImgLi);
		
		//预览图片
		function previewImage(){
			var urls=[];
			$('li').not('#last_li').each(function(){
				urls.push($(this).data('url'));
			});
			var src=$(this).data("url");
			wx.previewImage({
			    current: src, // 当前显示的图片链接
			    urls: urls // 需要预览的图片链接列表
			});
		}
		$('li').not('#last_li').click(previewImage);
		
				
		function saveImgs(serverIds){
			add_img_div.html("");
		 	$.post('${ctx}/fileup/${userid}/${community_key}/${ques_key}/${item_code}',{imgIds:serverIds},function(ret){	
		 		if(ret){
		 			var temp=$(".temp");
		 			var len=ret.length;
		 			for(var i=0;i<len;i++){
		 				temp.eq(i).removeClass('temp').attr("id",ret[i]).find(".close_span").show();
		 			}
		 			add_img_div.removeClass("load_img_div");	 
		 		}
			});
		};
		
	})
</script>
</html>