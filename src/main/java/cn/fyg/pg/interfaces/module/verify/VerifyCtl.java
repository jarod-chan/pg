package cn.fyg.pg.interfaces.module.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.pg.application.CommunityService;
import cn.fyg.pg.application.ExpertService;
import cn.fyg.pg.application.ItemPropService;
import cn.fyg.pg.domain.community.Community;
import cn.fyg.pg.domain.expert.Expert;
import cn.fyg.pg.domain.ques.Ques;
import cn.fyg.pg.infrastructure.persistent.QuesMapper;

@Controller
@RequestMapping("verify")
public class VerifyCtl {

	@Autowired
	ExpertService expertService;
	@Autowired
	QuesMapper quesMapper;
	@Autowired
	ItemPropService itemPropService;
	@Autowired
	CommunityService communityService;
	
	@RequestMapping(value="/{userid}/{prop_key}",method=RequestMethod.GET)
	@ResponseBody 
	public Map<String, Object> verify(@PathVariable("userid")String userid,@PathVariable("prop_key")String prop_key){
		Map<String,Object> map=new HashMap<String,Object>();
		if(!this.expertService.exist(userid)){
			map.put("result", false);
			map.put("message", "你不是合法的系统用户。");
			return map;
		}
		
		Expert expert = this.expertService.find(userid);
		if(!expert.hasProp(prop_key)){
			map.put("result", false);
			map.put("message", "你没有该模块的操作权限。");
			return map;
		}
		
		Ques ques = this.quesMapper.currQues();
		if(ques==null){
			map.put("result", false);
			map.put("message", "评分功能处于关闭状态。");
			return map;
		}
		String ques_key=ques.getKey();
		
		String item_code=this.itemPropService.propItemcode(ques_key, prop_key);
		if(item_code==null){
			map.put("result", false);
			map.put("message", "系统没有对应模块。");
			return map;
		}
		
		List<Community> communityList = this.communityService.all();
		if(communityList==null||communityList.isEmpty()){
			map.put("result", false);
			map.put("message", "系统小区没有初始化。");
			return map;
		}
		
		List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
		for (Community community : communityList) {
			HashMap<String, String> dataMap = new HashMap<String,String>();
			dataMap.put("name", community.getName());
			dataMap.put("url", String.format("standard/list/%s/%s/%s/%s",userid,community.getKey(),ques_key,item_code));
			dataList.add(dataMap);
		}
		map.put("result", true);
		map.put("data", dataList);
		
		return map;
	}
	
}
