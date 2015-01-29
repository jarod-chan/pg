package cn.fyg.pg.application;

import java.util.List;

import cn.fyg.pg.domain.itemcmt.Itemcmt;

public interface ItemcmtService {
	
	List<Itemcmt> itemComment(String ques_key,String item_code);

}
