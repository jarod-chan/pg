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
	public Itemchk userCheck(String ques_key, String userid,
			String community_key, String part_code) {
		return this.itemchkMapper.findByQuesAndUserAndCommunityAndItem(ques_key, userid, community_key, part_code);
	}

	@Override
	@Transactional
	public void saveUserCheck(String ques_key, String userid,
			String community_key, String part_code, String[] val) {
		Itemchk itemchk = this.itemchkMapper.findByQuesAndUserAndCommunityAndItem(ques_key, userid, community_key, part_code);
		if(itemchk==null){
			itemchk=new Itemchk();
			itemchk.setQues_key(ques_key);
			itemchk.setUserid(userid);
			itemchk.setCommunity_key(community_key);
			itemchk.setItem_code(part_code);
			this.itemchkMapper.save(itemchk);
		}
		
		int itemchk_id=itemchk.getId();
		this.itemchkMapper.deleteVal(itemchk_id);
		if(val!=null&&val.length>0){
			for (String item_code : val) {
				this.itemchkMapper.saveVal(itemchk_id, item_code);
			}
		}
	}

	@Override
	@Transactional
	public void addUserCheck(String ques_key, String userid,
			String community_key, String part_code, String val) {
		Itemchk itemchk = this.itemchkMapper.findByQuesAndUserAndCommunityAndItem(ques_key, userid, community_key, part_code);
		if(itemchk==null){
			itemchk=new Itemchk();
			itemchk.setQues_key(ques_key);
			itemchk.setUserid(userid);
			itemchk.setCommunity_key(community_key);
			itemchk.setItem_code(part_code);
			this.itemchkMapper.save(itemchk);
		}
		int itemchk_id=itemchk.getId();
		this.itemchkMapper.saveVal(itemchk_id, val);
	}

	@Override
	@Transactional
	public void removeUserCheck(String ques_key, String userid,
			String community_key, String part_code, String val) {
		Itemchk itemchk = this.itemchkMapper.findByQuesAndUserAndCommunityAndItem(ques_key, userid, community_key, part_code);
		if(itemchk!=null){
			int itemchk_id=itemchk.getId();
			this.itemchkMapper.deleteOneVal(itemchk_id, val);
		}
	}

}
