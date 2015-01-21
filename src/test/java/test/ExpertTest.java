package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.H;

import cn.fyg.pg.domain.expert.Expert;
import cn.fyg.pg.infrastructure.persistent.ExpertMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ExpertTest {
	
	@Autowired
	ExpertMapper expertMapper; 
	
	@Test
	public void exist(){
		int ret=this.expertMapper.exist("13857659857");
		System.out.println(ret);
		ret=this.expertMapper.exist("123");
		System.out.println(ret);
	}
	
	@Test
	public void find(){
		Expert expert = this.expertMapper.find("13857659857");
		H.p(expert);
	}

}
