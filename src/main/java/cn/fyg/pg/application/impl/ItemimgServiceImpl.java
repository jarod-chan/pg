package cn.fyg.pg.application.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pg.application.ItemimgService;
import cn.fyg.pg.domain.itemchk.Itemchk;
import cn.fyg.pg.domain.itemimg.Itemimg;
import cn.fyg.pg.infrastructure.persistent.ItemchkMapper;
import cn.fyg.pg.infrastructure.persistent.ItemimgMapper;

@Service
public class ItemimgServiceImpl implements ItemimgService {

	@Autowired
	ItemchkMapper itemchkMapper;
	@Autowired
	ItemimgMapper itemimgMapper;
	
	@Override
	@Transactional
	public  List<Itemimg> saveImg(String ques_key, String userid,
			String community_key, String item_code, List<String> imgIds) {
		Itemchk itemchk = this.itemchkMapper.findByQuesAndUserAndCommunity(ques_key, userid, community_key);
		if(itemchk==null){
			itemchk=new Itemchk();
			itemchk.setQues_key(ques_key);
			itemchk.setUserid(userid);
			itemchk.setCommunity_key(community_key);
			this.itemchkMapper.save(itemchk);
		}
		int itemchk_id=itemchk.getId();
		ArrayList<Itemimg> itemimgList = new ArrayList<Itemimg>();
		for(String img_id:imgIds){
			Itemimg itemimg=new Itemimg();
			itemimg.setItemchk_id(itemchk_id);
			itemimg.setItem_code(item_code);
			itemimg.setImg_id(img_id);
			itemimg.setLocal(false);
			this.itemimgMapper.insert(itemimg);
			itemimgList.add(itemimg);
		}
		return itemimgList;
	}

	@Override
	public List<Itemimg> findImgs(String ques_key, String userid,
			String community_key, String item_code) {
		Itemchk itemchk = this.itemchkMapper.findByQuesAndUserAndCommunity(ques_key, userid, community_key);
		if(itemchk==null){
			return null;
		}
		int itemchk_id=itemchk.getId();
		return this.itemimgMapper.findImgs(itemchk_id,item_code);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.itemimgMapper.delete(id);
	}



}
