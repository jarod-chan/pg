package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.H;

import cn.fyg.pg.domain.itemcmt.Itemcmt;
import cn.fyg.pg.infrastructure.persistent.ItemcmtMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ItemcmtTest {
	
	@Autowired
	ItemcmtMapper itemcmtMapper;
	
	@Test
	public void findByQuesAndCode(){
		List<Itemcmt> list = this.itemcmtMapper.findByQuesAndCode("v150116", "1.3");
		for (Itemcmt itemcmt : list) {
			H.p(itemcmt);
		}
	}

}
