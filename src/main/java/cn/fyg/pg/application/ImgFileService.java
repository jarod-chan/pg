package cn.fyg.pg.application;

import java.io.File;
import java.util.List;

import me.chanjar.weixin.common.exception.WxErrorException;

public interface ImgFileService {
	
	public void download(List<String> imgIds) throws WxErrorException ;

	public File getImgFile(String imgName);
}
