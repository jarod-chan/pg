package cn.fyg.pg.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.pg.application.ItemPropService;
import cn.fyg.pg.infrastructure.persistent.ItemPropMapper;

@Service
public class ItemPropServiceImpl implements ItemPropService {

	@Autowired
	ItemPropMapper itemPropMapper;
	
	@Override
	public String propItemcode(String ques_key, String prop_key) {	
		return this.itemPropMapper.findPropCode(ques_key, prop_key);
	}

}
