package cn.fyg.pg.interfaces.module.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pg.application.CommunityService;
import cn.fyg.pg.application.ExpertService;
import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.application.ItemchkService;
import cn.fyg.pg.domain.community.Community;
import cn.fyg.pg.domain.expert.Expert;
import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.interfaces.module.standard.dto.ItemChkVal;


@Controller
@RequestMapping("standard")
public class StandardCtl {
	
	private static final String PATH = "standard/";
	private interface Page {
		String  LIST = PATH + "list";
		String ACTION = PATH+"action";
		String MODULE = PATH+"module";
	}
	

	@Autowired
	ItemchkService itemchkService;
	@Autowired
	CommunityService communityService;
	@Autowired
	ItemService itemService;
	@Autowired
	StandardAssembler standardAssembler;
	@Autowired
	ExpertService expertService;
	
	@RequestMapping(value="module/{userid}/{community_key}/{ques_key}",method=RequestMethod.GET)
	public String toModule(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,Map<String,Object> map){
		List<Item> parts = this.itemService.parts(ques_key);
		Expert expert = this.expertService.find(userid);
		
		List<Map<String,Object>> partList=new ArrayList<Map<String,Object>>();
		for(Item part:parts){
			Map<String, Object> partMap = new HashMap<String,Object>();
			partMap.put("item", part);
			boolean hasPartcode=expert.hasPartcode(part.getCode());
			partMap.put("hasPartcode", hasPartcode);
			partList.add(partMap);
		}
		map.put("partList", partList);
		
		Community community = this.communityService.find(community_key);
		map.put("community", community);
		
		map.put("userid", userid);
		map.put("community_key", community_key);
		map.put("ques_key", ques_key);
		return Page.MODULE;
	}
	
	@RequestMapping(value="list/{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.GET)
	public String toList(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,@PathVariable("item_code")String item_code,Map<String,Object> map){

		List<Item> itemList =this.itemService.SonOfCode(ques_key, item_code);
		map.put("itemList", itemList);
		
		Community community = this.communityService.find(community_key);
		map.put("community", community);
		
		Item item = this.itemService.find(ques_key, item_code);
		map.put("item", item);
		
		map.put("userid", userid);
		map.put("community_key", community_key);
		map.put("ques_key", ques_key);
		return Page.LIST;
	}
	
	@RequestMapping(value="action/{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.GET)
	public String toAction(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,@PathVariable("item_code")String item_code,Map<String,Object> map){	
		Item item = this.itemService.find(ques_key, item_code);
		map.put("item", item);
		List<ItemChkVal> userItemChkValList = standardAssembler.userItemChkVal(userid, community_key, ques_key, item_code);
		map.put("userItemChkValList", userItemChkValList);
		
		map.put("userid", userid);
		map.put("community_key", community_key);
		map.put("ques_key", ques_key);
		map.put("item_code", item_code);
		return Page.ACTION;
	}

}
