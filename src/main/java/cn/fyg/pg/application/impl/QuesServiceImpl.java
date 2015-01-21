package cn.fyg.pg.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.fyg.pg.application.QuesService;
import cn.fyg.pg.domain.ques.Ques;
import cn.fyg.pg.infrastructure.persistent.QuesMapper;

public class QuesServiceImpl implements QuesService {
	
	@Autowired
	QuesMapper quesMapper;

	@Override
	public Ques currQues() {
		return this.quesMapper.currQues();
	}

}
