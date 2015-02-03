package cn.fyg.pg.interfaces.module.rpt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.infrastructure.persistent.PgresultMapper;

@Controller
@RequestMapping("rpt")
public class RptCtl {
	
	private static final String PATH = "rpt/";
	private interface Page {
		String  RPT = PATH + "rpt";
	}
	
	@Autowired
	ItemService itemService;
	@Autowired
	PgresultMapper pgresultMapper;
	
	
	@RequestMapping(value="{ques_key}",method=RequestMethod.GET)
	public String toRpt(@PathVariable("ques_key")String ques_key,Map<String,Object> map){
		List<Item> parts = this.itemService.parts(ques_key);
		map.put("parts", parts);
		
		List<Map<String, Object>> resultList = this.pgresultMapper.result();
		map.put("resultList", resultList);
		return Page.RPT;
	}

}
