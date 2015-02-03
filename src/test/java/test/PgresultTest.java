package test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.H;

import cn.fyg.pg.infrastructure.persistent.PgresultMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PgresultTest {
	
	@Autowired
	PgresultMapper pgresultMapper;
	
	@Test
	public void result(){
		List<Map<String,Object>> result = this.pgresultMapper.result();
		for (Map<String, Object> map : result) {
			H.ptl(map);
		}
	}

}
