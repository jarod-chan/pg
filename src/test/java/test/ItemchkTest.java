package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.H;

import cn.fyg.pg.domain.itemchk.Itemchk;
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
		for (String val : itemchk.getVal()) {
			System.out.println(val);
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
	public void saveVal(){
		this.itemchkMapper.insertVal(1,"1.1.1");
	}
	
	@Test
	public void findpartscore(){
		int count = this.itemchkMapper.countByQuesAndUserAndCommunityAndItem("v150116", "13857659857", "jyhy", "1");
		H.p(count);
	}
}
