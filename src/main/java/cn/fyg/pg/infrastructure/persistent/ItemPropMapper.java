package cn.fyg.pg.infrastructure.persistent;

import org.apache.ibatis.annotations.Param;

import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface ItemPropMapper {
	
	public String findPropCode(@Param("ques_key")String ques_key,@Param("prop_key")String prop_key);

}
