package cn.fyg.pg.interfaces.module.standard;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.pg.application.CommunityService;
import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.application.ItemchkService;
import cn.fyg.pg.domain.community.Community;
import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.interfaces.module.standard.dto.ItemChkCount;
import cn.fyg.pg.interfaces.module.standard.dto.ItemChkVal;


@Controller
@RequestMapping("standard")
public class StandardCtl {
	
	private static final String PATH = "standard/";
	private interface Page {
		String  LIST = PATH + "list";
		String ACTION = PATH+"action";
	}
	

	@Autowired
	ItemchkService itemchkService;
	@Autowired
	CommunityService communityService;
	@Autowired
	ItemService itemService;
	@Autowired
	StandardAssembler standardAssembler;
	
	@RequestMapping(value="list/{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.GET)
	public String toList(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,@PathVariable("item_code")String item_code,Map<String,Object> map){
		List<ItemChkCount> userItemChkCountList = standardAssembler.userItemChkCount(userid, community_key, ques_key, item_code);
		map.put("userItemChkCountList", userItemChkCountList);
		
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
		List<ItemChkVal> userItemChkValList = standardAssembler.userItemChkVal(userid, community_key, ques_key, item_code);
		map.put("userItemChkValList", userItemChkValList);
		
		map.put("userid", userid);
		map.put("community_key", community_key);
		map.put("ques_key", ques_key);
		map.put("item_code", item_code);
		return Page.ACTION;
	}
	
	@RequestMapping(value="save/{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.POST)
	public String save(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,@PathVariable("item_code")String item_code,String[] val){
		String part_code=StringUtils.split(item_code,".")[0];
		itemchkService.saveUserCheck(ques_key, userid, community_key, part_code, val);
		return String.format("redirect:/standard/list/%s/%s/%s/%s", userid,community_key,ques_key,part_code);
	}
	
	@RequestMapping(value="toggle/{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.POST)
	@ResponseBody
	public boolean toggle(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,@PathVariable("item_code")String item_code,String val,String action){
		String part_code=StringUtils.split(item_code,".")[0];
		if(action.equals("add")){
			this.itemchkService.addUserCheck(ques_key, userid, community_key, part_code, val);
		}
		if(action.equals("remove")){
			this.itemchkService.removeUserCheck(ques_key, userid, community_key, part_code, val);
		}
		return true;
	}

}
