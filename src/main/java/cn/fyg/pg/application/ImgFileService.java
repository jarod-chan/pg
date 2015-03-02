package cn.fyg.pg.application;

import java.io.File;
import java.util.List;

import cn.fyg.pg.domain.itemimg.Itemimg;

import me.chanjar.weixin.common.exception.WxErrorException;

public interface ImgFileService {
	
	public File getImgFile(String imgName);

	public void download(List<Itemimg> itemimgList)throws WxErrorException ;
}
