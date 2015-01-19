package cn.fyg.pg.infrastructure.persistent;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface ItemMapper {
	
	public List<Item> getAll();
	
	public List<Item> findByQuesAndCodeAndLevel(@Param("ques_key")String ques_key,@Param("code")String code,@Param("level")String level);
	
}
