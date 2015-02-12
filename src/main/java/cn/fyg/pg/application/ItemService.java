package cn.fyg.pg.application;

import java.util.List;

import cn.fyg.pg.domain.item.Item;

public interface ItemService {

	List<Item> SonOfCode(String ques_key,String code);
	
	List<Item> levelItem(String ques_key,String code,int level);
	
	Item find(String ques_key,String code);
	
	List<Item> parts(String ques_key);
}
