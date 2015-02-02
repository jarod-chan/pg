package cn.fyg.pg.application;

import cn.fyg.pg.domain.itemchk.Itemchk;

public interface ItemchkService {
	
	Itemchk userCheck(String ques_key,String userid,String community_key);
	
	void addUserCheck(String ques_key,String userid,String community_key,String part_code,String val);
	
	void removeUserCheck(String ques_key,String userid,String community_key,String part_code,String val);
	
	int partScore(String ques_key,String userid,String community_key,String part_code);
}
