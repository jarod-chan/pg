package cn.fyg.pg.application.impl;

import java.io.File;
import java.util.List;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.fyg.pg.application.ImgFileService;

@Service
public class ImgFileServiceImpl implements ImgFileService {
	
	private static Logger logger=LoggerFactory.getLogger(ImgFileServiceImpl.class);
	
	@Autowired
	String fileDirectory;
	
	@Autowired
	WxCpService wxCpService;
	  
	@Async
	@Override
	public void download(List<String> imgIds) throws WxErrorException {
		for(String imgid:imgIds){			
			File file = wxCpService.mediaDownload(imgid);
			File newFile = new File(fileDirectory +file.getName());
			file.renameTo(newFile); 
			logger.info(file.getName());
		}
	}
	
	private static int count=0;

	@Override
	public File getImgFile(String imgName) {
		System.out.println(++count);
		return	new File(fileDirectory +imgName);
	}
	
	
}
