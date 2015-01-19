package cn.fyg.pg.interfaces.module.standard;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pg.application.ItemchkService;
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
	StandardAssembler standardAssembler;
	
	@RequestMapping(value="list/{userid}/{community_key}/{ques_key}/{item_code:.+}",method=RequestMethod.GET)
	public String toList(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,@PathVariable("item_code")String item_code,Map<String,Object> map){
		List<ItemChkCount> userItemChkCountList = standardAssembler.userItemChkCount(userid, community_key, ques_key, item_code);
		map.put("userItemChkCountList", userItemChkCountList);
		
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

}
