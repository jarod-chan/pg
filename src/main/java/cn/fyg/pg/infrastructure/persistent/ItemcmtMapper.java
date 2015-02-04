package cn.fyg.pg.infrastructure.persistent;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fyg.pg.domain.itemcmt.Itemcmt;
import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface ItemcmtMapper {
	
	public List<Itemcmt> findByQuesAndCode(@Param("ques_key")String ques_key,@Param("item_code")String item_code);

}
