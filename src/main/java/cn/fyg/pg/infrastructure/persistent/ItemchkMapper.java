package cn.fyg.pg.infrastructure.persistent;

import org.apache.ibatis.annotations.Param;

import cn.fyg.pg.domain.itemchk.Itemchk;
import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface ItemchkMapper {

	public Itemchk findByQuesAndUserAndCommunityAndItem(
			@Param("ques_key") String ques_key, 
			@Param("userid") String userid,
			@Param("community_key") String community_key,
			@Param("item_code") String item_code);

	public int save(Itemchk itemchk);
	
	public void deleteVal(@Param("itemchk_id") int itemchk_id);
	
	public int saveVal(@Param("itemchk_id") int itemchk_id,@Param("item_code")String item_code);

}
