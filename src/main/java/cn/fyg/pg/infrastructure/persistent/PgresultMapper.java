package cn.fyg.pg.infrastructure.persistent;

import java.util.List;
import java.util.Map;

import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface PgresultMapper {
	
	List<Map<String,Object>> result();

}
