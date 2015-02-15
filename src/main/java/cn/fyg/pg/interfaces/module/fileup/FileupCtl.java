package cn.fyg.pg.interfaces.module.fileup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpConfigStorage;
import me.chanjar.weixin.cp.api.WxCpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.application.ItemimgService;
import cn.fyg.pg.domain.itemimg.Itemimg;
import cn.fyg.pg.infrastructure.weixin.api.WxCpJsapiService;
import cn.fyg.pg.infrastructure.weixin.api.WxCpJsapiSignature;

@Controller
@RequestMapping("fileup")
public class FileupCtl {

	private static final Logger logger=LoggerFactory.getLogger(FileupCtl.class);
	
	private static final String PATH = "fileup/";

	private interface Page {
		String FILEUP = PATH + "md";
	}

	@Autowired
	ItemService itemService;
	@Autowired
	WxCpConfigStorage wxCpConfigStorage;
	@Autowired
	WxCpJsapiService wxCpJsapiService;
	@Autowired
	WxCpService wxCpService;


	@RequestMapping(value = "{userid}/{community_key}/{ques_key}/{item_code:.+}", method = RequestMethod.GET)
	public String toFileup(@PathVariable("userid") String userid,
			@PathVariable("community_key") String community_key,
			@PathVariable("ques_key") String ques_key,
			@PathVariable("item_code") String item_code, Map<String, Object> map,HttpServletRequest request) {
		
		String url=request.getRequestURL().toString();
		try {
			WxCpJsapiSignature wxCpJsapiSignature = this.wxCpJsapiService.createJsapiSignature(url);
			map.put("wxCpJsapiSignature", wxCpJsapiSignature);
			map.put("wxCpConfigStorage", wxCpConfigStorage);
			map.put("ACCESS_TOKEN", wxCpService.getAccessToken());
		} catch (WxErrorException e) {
			logger.error("can't get jsapi Signature", e);
		}
		
		List<Itemimg> itemimgList = this.itemimgService.findImgs(ques_key, userid, community_key, item_code);
		map.put("itemimgList", itemimgList);
		
		map.put("userid",userid);
		map.put("community_key",community_key);
		map.put("ques_key",ques_key);
		map.put("item_code",item_code);
		return Page.FILEUP;
	}
	
	@Autowired
	ItemimgService itemimgService;
	
	@RequestMapping(value="{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.POST)
	@ResponseBody
	public List<Integer> upload(@PathVariable("userid") String userid,
			@PathVariable("community_key") String community_key,
			@PathVariable("ques_key") String ques_key,
			@PathVariable("item_code") String item_code,@RequestParam(value = "imgIds[]")List<String> imgIds){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try{
			List<Itemimg> itemimgList = this.itemimgService.saveImg(ques_key, userid, community_key, item_code, imgIds);
			for(Itemimg itemimg:itemimgList){
				ids.add(itemimg.getId());
			}
		}catch(Exception e){
			logger.error("save imgs error", e);	
		}
		return ids;
	}

	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	@ResponseBody
	public boolean delete(@PathVariable("id") int id){
		this.itemimgService.delete(id);
		return true;
	}
	
}
