package cn.fyg.pg.interfaces.module.standard;

import java.math.BigDecimal;
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
import cn.fyg.pg.domain.itemchk.Itemval;

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
	public List<Map<String, Object>> itemscore(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,@PathVariable("item_code")String item_code){
		String part_code=StringUtils.split(item_code,".")[0];
		List<Item> level2Items = itemService.SonOfCode(ques_key, part_code);
		List<Item> level3Items = itemService.levelItem(ques_key, part_code, 3);
		
		Itemchk itemchk=itemchkService.userCheck(ques_key, userid, community_key);
		List<Itemval> itemvalList=new ArrayList<Itemval>();
		if(itemchk!=null&&itemchk.getVal()!=null){
			//过滤掉其它模块的值
			for(Itemval itemval:itemchk.getVal()){
				String item_part_code=StringUtils.split(itemval.getItem_code(),".")[0];
				if(part_code.equals(item_part_code)){
					itemvalList.add(itemval);
				}
			}
		}
		
		Map<String, Integer> level2count = countCheckVal(itemvalList);
		
		List<Map<String,Object>> partList=new ArrayList<Map<String,Object>>();
		for (Item item : level2Items) {
			Map<String,Object> itemMap = new HashMap<String,Object>();
			String thisItemCode=item.getCode();
			Integer count=0;
			if(level2count.containsKey(thisItemCode)){
				count=level2count.get(thisItemCode);
			}
			itemMap.put("code", thisItemCode);
			itemMap.put("score", count);
			partList.add(itemMap);
		}
		
		BigDecimal totalCheckVal=sumCheckVal(level2Items,level3Items,itemvalList);
		Map<String,Object> itemMap = new HashMap<String,Object>();
		itemMap.put("score", totalCheckVal.toString());
		partList.add(itemMap);
		
		return partList;
	}
	
	private BigDecimal sumCheckVal(List<Item> level2Items,
			List<Item> level3Items, List<Itemval> itemvalList) {
		
		Map<String,Item> itemMap = new HashMap<String,Item>();
		for (Item item : level2Items) {
			itemMap.put(item.getCode(), item);
		}
		for (Item item : level3Items) {
			itemMap.put(item.getCode(), item);
		}
		
		BigDecimal sum = BigDecimal.valueOf(0);
		for (Itemval itemval : itemvalList) {
			String item_code = itemval.getItem_code();
			String level2code=StringUtils.substringBeforeLast(item_code, ".");
			
			Item item=itemMap.get(item_code);
			BigDecimal thisCheckVal=BigDecimal.valueOf(itemval.getVal()).divide(BigDecimal.valueOf(4),6,BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(item.getFson()))
					.divide(BigDecimal.valueOf(item.getFmonther()),6,BigDecimal.ROUND_HALF_UP);
			
			item=itemMap.get(level2code);
			thisCheckVal=thisCheckVal.multiply(BigDecimal.valueOf(item.getFson()))
					.divide(BigDecimal.valueOf(item.getFmonther()),6,BigDecimal.ROUND_HALF_UP);
			
			thisCheckVal=thisCheckVal.multiply(BigDecimal.valueOf(100));
			
			sum=sum.add(thisCheckVal);
		}
		
		return sum.setScale(2, BigDecimal.ROUND_UP);
	}

	private Map<String, Integer> countCheckVal(List<Itemval> itemvalList) {
		
		Map<String,Integer> level2count=new HashMap<String,Integer>();
		for (Itemval itemval : itemvalList) {
			String level2code=StringUtils.substringBeforeLast(itemval.getItem_code(), ".");
			Integer sum=level2count.get(level2code);
			if(sum!=null){
				level2count.put(level2code, sum+itemval.getVal());
			}else{
				level2count.put(level2code, itemval.getVal());
			}
		}
		return level2count;
	}
	
	
	
	
	@RequestMapping(value="save/{userid}/{community_key}/{ques_key}",method=RequestMethod.POST)
	@ResponseBody
	public boolean save(@PathVariable("userid")String userid,@PathVariable("community_key")String community_key,@PathVariable("ques_key")String ques_key,String item_code,int val){
		this.itemchkService.saveUserCheck(ques_key, userid, community_key, item_code, val);
		return true;
	}

}
