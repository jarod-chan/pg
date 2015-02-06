package cn.fyg.pg.interfaces.module.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.application.ItemchkService;
import cn.fyg.pg.application.ItemcmtService;
import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.domain.itemchk.Itemchk;
import cn.fyg.pg.domain.itemchk.Itemval;
import cn.fyg.pg.domain.itemcmt.Itemcmt;
import cn.fyg.pg.interfaces.module.standard.dto.ItemChkVal;

@Component
class StandardAssembler {
	
	@Autowired
	ItemService itemService;
	@Autowired
	ItemchkService itemchkService;
	@Autowired
	ItemcmtService itemcmtService;
	
	
	public List<ItemChkVal> userItemChkVal(String userid,String community_key,String ques_key,String item_code){
		//问题内容
		List<Item> sonItemList = this.itemService.SonOfCode(ques_key, item_code);
		
		//问题答案
		Itemchk itemchk=this.itemchkService.userCheck(ques_key, userid, community_key);
		Map<String,Integer> itemvalMap=new HashMap<String,Integer>();
		if(itemchk!=null&&itemchk.getVal()!=null){
			for (Itemval itemval : itemchk.getVal()) {
				itemvalMap.put(itemval.getItem_code(),itemval.getVal());
			}
		}
		
		//问题注释
		List<Itemcmt> itemComment = this.itemcmtService.itemComment(ques_key, item_code);
		Map<String,String> codeCommentMap = new HashMap<String,String>();
		if(itemComment!=null&&!itemComment.isEmpty()){			
			for (Itemcmt itemcmt:itemComment) {
				codeCommentMap.put(itemcmt.getItem_code(), itemcmt.getItemcmt());
			}
		}
		
		List<ItemChkVal> itemchkvalList = new ArrayList<ItemChkVal>();
		for(Item item:sonItemList){
			ItemChkVal itemChkVal = new ItemChkVal();
			itemChkVal.setItem(item);
			String thisItemCode = item.getCode();
			Integer val = itemvalMap.get(thisItemCode);
			if(val!=null){				
				itemChkVal.setVal(val);
			}else{
				itemChkVal.setVal(Integer.valueOf(0));
			}
			String comment=codeCommentMap.get(thisItemCode);
			if(comment!=null){
				itemChkVal.setIscomment(true);
				itemChkVal.setComment(comment);
			}else{
				itemChkVal.setIscomment(false);
			}
			
			itemchkvalList.add(itemChkVal);
		}
		return itemchkvalList;
	}

}
