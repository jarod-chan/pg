package cn.fyg.pg.application;

import cn.fyg.pg.domain.itemchk.Itemchk;

public interface ItemchkService {
	
	Itemchk userCheck(String ques_key,String userid,String community_key);
	
	int partScore(String ques_key,String userid,String community_key,String part_code);
	
	void saveUserCheck(String ques_key,String userid,String community_key,String item_code,int val);

}
