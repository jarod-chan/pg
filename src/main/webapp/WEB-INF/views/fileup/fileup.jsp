<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/setting.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/include.jsp"%>

<link href="${ctx}/resources/plug/upfile/upfile.css" rel="stylesheet" />
<script src="${ctx}/resources/plug/upfile/binaryajax.js"></script>
<script src="${ctx}/resources/plug/upfile/exif.js"></script>
<script src="${ctx}/resources/plug/upfile/megapix-image.js"></script>
<script src="${ctx}/resources/plug/upfile/jquery.make-thumb.js"></script>
<script type="text/javascript">var FYG_PATH='${ctx}/resources/'</script>
</head>

<body>

<div class="div_fileup">
	<c:forEach var="itemimg" items="${itemimgList}">
	<div class="img_ck" id="${itemimg.id}"><div class="close_span"  onclick="remove_del(${itemimg.id},event)"></div><img class="up_img" src="${itemimg.img}"/></div>	
	</c:forEach>
	<div class="add_img_div"><input class="fileinput" type="file" /></div> 
</div>

<div  class="preview none"></div>
	
		
</body>
<script type="text/javascript">
	function initFile(plug_fileup){
		var file = plug_fileup.find(".fileinput");
		var img_div=plug_fileup.find(".add_img_div");
		
		file.makeThumb({
			width: 400,
			height: 300,
			start:function(){
				img_div.addClass("add_img_load");
				file.hide();
			},
			success: function(dataURL, tSize, pfile, sSize, fEvt) {
				//console.log(dataURL);
				//return;
				$.post('${ctx}/fileup/${userid}/${community_key}/${ques_key}/${item_code}',{img:dataURL},function(imgid){	
					var id=imgid;
					var html =$('<div class="img_ck" id="'+id+'"><div class="close_span"  onclick="remove_del('+id+',event)"></div><img class="up_img" src="'+dataURL+'"/></div>');
					html.click(function(){
						var img=$(this).find(".up_img").attr("src");
						$(".preview").css("background-image","url('"+img+"')");
						$(".preview").show();
					});
					img_div.before(html);
					img_div.removeClass("add_img_load");
					file.show(); 
				})
			}
		});

	}

	function remove_del(id,event){
		event.stopPropagation();
		if(id){
			if(confirm('确定删除?')){
				var img_div=$("#"+id);
				img_div.find(".close_span").hide();
				$.post('${ctx}/fileup/delete/'+id,function(){	
					img_div.remove();
				})
			}
		}
	}

	$(function(){
		var div_fileup=$('.div_fileup');
		initFile(div_fileup);
		
		$(".img_ck").click(function(){
			var img=$(this).find(".up_img").attr("src");
			$(".preview").css("background-image","url('"+img+"')");
			$(".preview").show();
		})
		
		$(".preview").click(function(){
			$(".preview").hide();
		})
	});
</script>

</html>
