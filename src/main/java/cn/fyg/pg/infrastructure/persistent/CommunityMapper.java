package cn.fyg.pg.infrastructure.persistent;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fyg.pg.domain.community.Community;
import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface CommunityMapper {
	
	public List<Community> all();

	public Community find(@Param("key")String community_key);

}
