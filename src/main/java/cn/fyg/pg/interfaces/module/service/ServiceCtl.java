package cn.fyg.pg.interfaces.module.service;

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
import cn.fyg.pg.domain.community.Community;
import cn.fyg.pg.domain.ques.Ques;
import cn.fyg.pg.infrastructure.persistent.QuesMapper;

@Controller
@RequestMapping("service")
public class ServiceCtl {
	
	@Autowired
	ExpertService expertService;
	@Autowired
	CommunityService communityService;
	@Autowired
	QuesMapper quesMapper;
	
	@RequestMapping(value="community/{userid}",method=RequestMethod.GET)
	@ResponseBody 
	public Map<String, Object> community(@PathVariable("userid")String userid){
		Map<String,Object> map=new HashMap<String,Object>();
		if(!this.expertService.exist(userid)){
			map.put("result", false);
			map.put("message", "你不是合法的系统用户。");
			return map;
		}
		
		Ques ques = this.quesMapper.currQues();
		if(ques==null){
			map.put("result", false);
			map.put("message", "评分功能处于关闭状态。");
			return map;
		}
		String ques_key=ques.getKey();
		
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
			dataMap.put("url", String.format("standard/module/%s/%s/%s",userid,community.getKey(),ques_key));
			dataList.add(dataMap);
		}
		map.put("result", true);
		map.put("data", dataList);
		
		return map;
	}
	
}
