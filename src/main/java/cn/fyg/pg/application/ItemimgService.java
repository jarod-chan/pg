package cn.fyg.pg.application;

import java.util.List;

import cn.fyg.pg.domain.itemimg.Itemimg;

public interface ItemimgService {
	
	public  List<Itemimg> saveImg(String ques_key, String userid,
			String community_key, String item_code, List<String> imgIds);
	
	public List<Itemimg> findImgs(String ques_key,String userid,String community_key,String item_code);

	public void delete(int id);
	
	public Itemimg find(int id);


}
