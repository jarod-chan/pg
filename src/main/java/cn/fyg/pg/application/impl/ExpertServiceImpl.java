package cn.fyg.pg.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.pg.application.ExpertService;
import cn.fyg.pg.domain.expert.Expert;
import cn.fyg.pg.infrastructure.persistent.ExpertMapper;

@Service
public class ExpertServiceImpl implements ExpertService {
	
	@Autowired
	ExpertMapper expertMapper;
	
	@Override
	public boolean exist(String userid) {
		return this.expertMapper.exist(userid)>0;
	}

	@Override
	public Expert find(String userid) {
		return this.expertMapper.find(userid);
	}

}
