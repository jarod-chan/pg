package cn.fyg.pg.infrastructure.persistent;

import org.apache.ibatis.annotations.Param;

import cn.fyg.pg.domain.expert.Expert;
import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface ExpertMapper {
	
	public int exist(@Param("userid")String userid);
	
	public Expert find(@Param("userid")String userid);

}
