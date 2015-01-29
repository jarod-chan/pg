package cn.fyg.pg.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.pg.application.ItemcmtService;
import cn.fyg.pg.domain.itemcmt.Itemcmt;
import cn.fyg.pg.infrastructure.persistent.ItemcmtMapper;

@Service
public class ItemcmtServiceImpl implements ItemcmtService {
	
	@Autowired
	ItemcmtMapper itemcmtMapper;

	@Override
	public List<Itemcmt> itemComment(String ques_key, String item_code) {
		return this.itemcmtMapper.findByQuesAndCode(ques_key, item_code);
	}

}
