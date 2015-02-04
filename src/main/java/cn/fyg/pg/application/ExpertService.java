package cn.fyg.pg.application;

import cn.fyg.pg.domain.expert.Expert;

public interface ExpertService {
	
	public boolean exist(String userid);
	
	public Expert find(String userid);

}
