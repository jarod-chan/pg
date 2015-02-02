package cn.fyg.pg.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pg.application.ItemchkService;
import cn.fyg.pg.domain.itemchk.Itemchk;
import cn.fyg.pg.infrastructure.persistent.ItemchkMapper;

@Service
public class ItemchkServiceImpl implements ItemchkService {
	
	@Autowired
	ItemchkMapper itemchkMapper;
	
	@Override
	public Itemchk userCheck(String ques_key, String userid,String community_key) {
		return this.itemchkMapper.findByQuesAndUserAndCommunity(ques_key, userid, community_key);
	}


	@Override
	public int partScore(String ques_key, String userid, String community_key,
			String part_code) {
		return this.itemchkMapper.countByQuesAndUserAndCommunityAndItem(ques_key,userid,community_key,part_code);
	}


	@Override
	@Transactional
	public void addUserCheck(String ques_key, String userid,
			String community_key, String part_code, String val) {
		Itemchk itemchk = this.itemchkMapper.findByQuesAndUserAndCommunity(ques_key, userid, community_key);
		if(itemchk==null){
			itemchk=new Itemchk();
			itemchk.setQues_key(ques_key);
			itemchk.setUserid(userid);
			itemchk.setCommunity_key(community_key);
			this.itemchkMapper.save(itemchk);
		}
		int itemchk_id=itemchk.getId();
		this.itemchkMapper.insertVal(itemchk_id, val);
	}

	@Override
	@Transactional
	public void removeUserCheck(String ques_key, String userid,
			String community_key, String part_code, String val) {
		Itemchk itemchk = this.itemchkMapper.findByQuesAndUserAndCommunity(ques_key, userid, community_key);
		if(itemchk!=null){
			int itemchk_id=itemchk.getId();
			this.itemchkMapper.deleteVal(itemchk_id, val);
		}
	}

}
