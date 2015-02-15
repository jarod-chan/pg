package cn.fyg.pg.interfaces.module.fileup;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.application.ItemimgService;
import cn.fyg.pg.domain.itemimg.Itemimg;

@Controller
@RequestMapping("fileup1")
public class FileupBakCtl {

	private static final String PATH = "fileup/";

	private interface Page {
		String FILEUP = PATH + "fileup";
	}

	@Autowired
	ItemService itemService;


	@RequestMapping(value = "{userid}/{community_key}/{ques_key}/{item_code:.+}", method = RequestMethod.GET)
	public String toFileup(@PathVariable("userid") String userid,
			@PathVariable("community_key") String community_key,
			@PathVariable("ques_key") String ques_key,
			@PathVariable("item_code") String item_code, Map<String, Object> map) {
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
	
//	@RequestMapping(value="{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.POST)
//	@ResponseBody
//	public int upload(@PathVariable("userid") String userid,
//			@PathVariable("community_key") String community_key,
//			@PathVariable("ques_key") String ques_key,
//			@PathVariable("item_code") String item_code, String img){
//		Itemimg saveImg = this.itemimgService.saveImg(ques_key, userid, community_key, item_code, img);
//		return 0;
//	}

	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	@ResponseBody
	public boolean upload(@PathVariable("id") int id){
		this.itemimgService.delete(id);
		return true;
	}

}
