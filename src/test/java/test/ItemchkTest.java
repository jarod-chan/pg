package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.H;

import cn.fyg.pg.domain.itemchk.Itemchk;
import cn.fyg.pg.domain.itemchk.Itemval;
import cn.fyg.pg.infrastructure.persistent.ItemchkMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ItemchkTest {
	
	@Autowired
	ItemchkMapper itemchkMapper;
	
	@Test
	public void findItemchk(){
		Itemchk itemchk = itemchkMapper.findByQuesAndUserAndCommunity("v150116", "13857659857", "jyhy");
		H.p(itemchk);
		for (Itemval val : itemchk.getVal()) {
			H.p(val);
		}
	}
	
	@Test
	public void save(){
		Itemchk itemchk = new Itemchk();
		itemchk.setQues_key("v150116");
		itemchk.setCommunity_key("jyhy");
		itemchk.setUserid("13857659857");
		int ret = this.itemchkMapper.save(itemchk);
		System.out.println(ret);
		H.p(itemchk);
	}
	
	@Test
	public void exits(){
		boolean exits = this.itemchkMapper.exits(22, "1.1.11");
		H.p(exits);
	}

	@Test
	public void findpartscore(){
		int count = this.itemchkMapper.countByQuesAndUserAndCommunityAndItem("v150116", "13857659857", "jyhy", "1");
		H.p(count);
	}
}
