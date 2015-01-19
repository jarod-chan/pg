package cn.fyg.pg.application;

import java.util.List;

import cn.fyg.pg.domain.item.Item;

public interface ItemService {

	List<Item> SonOfCode(String ques_key,String code);
	
}
