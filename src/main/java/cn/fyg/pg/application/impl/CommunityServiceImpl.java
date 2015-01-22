package cn.fyg.pg.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.pg.application.CommunityService;
import cn.fyg.pg.domain.community.Community;
import cn.fyg.pg.infrastructure.persistent.CommunityMapper;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	CommunityMapper  communityMapper;

	@Override
	public List<Community> all() {
		return this.communityMapper.all();
	}

	@Override
	public Community find(String community_key) {
		return this.communityMapper.find(community_key);
	}

}
