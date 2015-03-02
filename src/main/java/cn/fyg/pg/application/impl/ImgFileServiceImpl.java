package cn.fyg.pg.application.impl;

import java.io.File;
import java.util.List;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.fyg.pg.application.ImgFileService;
import cn.fyg.pg.domain.itemimg.Itemimg;
import cn.fyg.pg.infrastructure.persistent.ItemimgMapper;

@Service
public class ImgFileServiceImpl implements ImgFileService {
	
	private static Logger logger=LoggerFactory.getLogger(ImgFileServiceImpl.class);
	
	@Autowired
	String fileDirectory;
	
	@Autowired
	ItemimgMapper itemimgMapper;
	
	@Autowired
	WxCpService wxCpService;
	  
	@Async
	@Override
	/*后台服务器线程异步执行文件下载*/
	public void download(List<Itemimg> itemimgList) throws WxErrorException {
		logger.info("async download file from weixin server");
		for(Itemimg itemimg:itemimgList){			
			File file = wxCpService.mediaDownload(itemimg.getImg_id());
			String filename=file.getName();
			String suffix=StringUtils.substringAfterLast(filename, ".");
			File newFile = new File(fileDirectory +itemimg.getImg_id()+"."+suffix);
			file.renameTo(newFile); 
			this.itemimgMapper.setItemimgLocal(itemimg.getId());
		}
	}
	

	@Override
	public File getImgFile(String imgName) {
		return	new File(fileDirectory +imgName);
	}
	
	@Async
	@Override
	public void delete(String imgid){
		//微信上传图片的文件格式暂时都作为jpg处理，可能有问题
		File file = new File(fileDirectory +imgid+".jpg");
		if(file.exists()&& !file.isDirectory()){
			file.delete();
		}
	}
	
}
