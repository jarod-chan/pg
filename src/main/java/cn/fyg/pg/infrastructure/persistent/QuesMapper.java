package cn.fyg.pg.infrastructure.persistent;

import cn.fyg.pg.domain.ques.Ques;
import cn.fyg.pg.infrastructure.persistent.tag.Mapper;

@Mapper
public interface QuesMapper {
	
	public Ques currQues();

}
