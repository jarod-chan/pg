package cn.fyg.pg.interfaces.module.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.application.ItemchkService;
import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.domain.itemchk.Itemchk;
import cn.fyg.pg.interfaces.module.standard.dto.ItemChkCount;
import cn.fyg.pg.interfaces.module.standard.dto.ItemChkVal;

@Component
class StandardAssembler {
	
	@Autowired
	ItemService itemService;
	@Autowired
	ItemchkService itemchkService;
	
	public List<ItemChkCount> userItemChkCount(String userid,String community_key,String ques_key,String item_code){
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
		
		List<ItemChkCount> itemChkCountList = new ArrayList<ItemChkCount>();
		for (Item item : sonItemList) {
			ItemChkCount itemChkCount = new ItemChkCount();
			itemChkCount.setItem(item);
			itemChkCount.setCount(0);
			
			String thisItemCode=item.getCode();
			Integer count=level2count.get(thisItemCode);
			if(count!=null){
				itemChkCount.setCount(count);
			}
			itemChkCountList.add(itemChkCount);
		}
		return itemChkCountList;
	}
	
	public List<ItemChkVal> userItemChkVal(String userid,String community_key,String ques_key,String item_code){
		List<Item> sonItemList = itemService.SonOfCode(ques_key, item_code);
		String part_code=StringUtils.split(item_code,".")[0];
		
		Itemchk itemchk=itemchkService.userCheck(ques_key, userid, community_key, part_code);
		List<String> itemchkval=new ArrayList<String>();
		if(itemchk!=null&&itemchk.getVal()!=null){
			itemchkval=itemchk.getVal();
		}
		
		List<ItemChkVal> itemchkvalList = new ArrayList<ItemChkVal>();
		for(Item item:sonItemList){
			ItemChkVal itemChkVal = new ItemChkVal();
			itemChkVal.setItem(item);
			if(itemchkval.contains(item.getCode())){				
				itemChkVal.setIscheck(true);
			}else{
				itemChkVal.setIscheck(false);
			}
			itemchkvalList.add(itemChkVal);
		}
		return itemchkvalList;
	}

}
