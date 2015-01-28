package cn.fyg.pg.interfaces.module.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.pg.application.ExpertService;
import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.application.ItemchkService;
import cn.fyg.pg.domain.expert.Expert;
import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.domain.itemchk.Itemchk;

@Controller
@RequestMapping("standard/service")
public class StandardServiceCtl {
	
	@Autowired
	ItemService itemService;
	@Autowired
	ExpertService expertService;
	@Autowired
	ItemchkService itemchkService;
	
	@RequestMapping(value="modulescore/{userid}/{community_key}/{ques_key}",method=RequestMethod.GET)
	@ResponseBody 
	public List<Map<String, Object>> moduleScore(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key){
		List<Item> parts = this.itemService.parts(ques_key);
		Expert expert = this.expertService.find(userid);
		
		List<Map<String,Object>> partList=new ArrayList<Map<String,Object>>();
		for(Item part:parts){
			boolean hasPartcode=expert.hasPartcode(part.getCode());
			if(hasPartcode){
				Map<String, Object> partMap = new HashMap<String,Object>();
				partMap.put("code", part.getCode());
				int score=this.itemchkService.partScore(ques_key, userid, community_key, part.getCode());
				partMap.put("score", score);
				partList.add(partMap);
			}
		}
		return partList;
	}
	
	@RequestMapping(value="itemscore/{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> toList(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,@PathVariable("item_code")String item_code){
		String part_code=StringUtils.split(item_code,".")[0];
		List<Item> sonItemList = itemService.SonOfCode(ques_key, part_code);
		
		Itemchk itemchk=itemchkService.userCheck(ques_key, userid, community_key, part_code);
		List<String> itemchkval=new ArrayList<String>();
		if(itemchk!=null&&itemchk.getVal()!=null){
			itemchkval=itemchk.getVal();
		}
		
		Map<String,Integer> level2count=new HashMap<String,Integer>();
		for (String chk_code : itemchkval) {
			String level2code=StringUtils.substringBeforeLast(chk_code, ".");
			Integer count=level2count.get(level2code);
			if(count!=null){
				level2count.put(level2code, count+1);
			}else{
				level2count.put(level2code, 1);
			}
		}

		List<Map<String,Object>> partList=new ArrayList<Map<String,Object>>();
		for (Item item : sonItemList) {
			HashMap<String,Object> itemMap = new HashMap<String,Object>();
			String thisItemCode=item.getCode();
			Integer count=0;
			if(level2count.containsKey(thisItemCode)){
				count=level2count.get(thisItemCode);
			}
			itemMap.put("code", thisItemCode);
			itemMap.put("score", count);
			partList.add(itemMap);
		}
		return partList;
	}

}
