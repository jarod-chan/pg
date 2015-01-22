package cn.fyg.pg.application.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.pg.application.ItemService;
import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.infrastructure.persistent.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemMapper itemMapper;

	@Override
	public List<Item> SonOfCode(String ques_key, String code) {
		int level=StringUtils.split(code,'.').length+1;
		return this.itemMapper.findByQuesAndCodeAndLevel(ques_key, code, String.valueOf(level));
	}

	@Override
	public Item find(String ques_key, String code) {
		return this.itemMapper.find(ques_key,code);
	}

}
