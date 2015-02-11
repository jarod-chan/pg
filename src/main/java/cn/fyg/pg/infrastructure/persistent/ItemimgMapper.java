package cn.fyg.pg.infrastructure.persistent;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fyg.pg.domain.itemimg.Itemimg;
import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface ItemimgMapper {
	
	public int insert(Itemimg itemimg);
	
	public List<Itemimg> findImgs(@Param("itemchk_id")int itemchk_id,@Param("item_code")String item_code);

	public void delete(@Param("id")int id);

}
