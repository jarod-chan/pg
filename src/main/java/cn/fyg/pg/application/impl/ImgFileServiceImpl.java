package cn.fyg.pg.application.impl;

import java.io.File;
import java.util.List;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.pg.application.ImgFileService;

@Service
public class ImgFileServiceImpl implements ImgFileService {

	@Autowired
	String fileDirectory;
	
	@Autowired
	WxCpService wxCpService;
	
	@Override
	public void download(List<String> imgIds) throws WxErrorException {
		for(String imgid:imgIds){			
			File file = wxCpService.mediaDownload(imgid);
			File newFile = new File(fileDirectory +file.getName());
			file.renameTo(newFile); 
		}
	}

	@Override
	public File getImgFile(String imgName) {
		return	new File(fileDirectory +imgName);
	}
	
	
}
