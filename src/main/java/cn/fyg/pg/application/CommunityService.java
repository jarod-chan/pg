package cn.fyg.pg.application;

import java.util.List;

import cn.fyg.pg.domain.community.Community;

public interface CommunityService {
	
	public List<Community> all();
	
	public Community find(String community_key);

}
