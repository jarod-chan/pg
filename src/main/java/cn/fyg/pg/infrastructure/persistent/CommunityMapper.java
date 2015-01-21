package cn.fyg.pg.infrastructure.persistent;

import java.util.List;

import cn.fyg.pg.domain.community.Community;
import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface CommunityMapper {
	
	public List<Community> all();

}
