package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.H;
import cn.fyg.pg.domain.item.Item;
import cn.fyg.pg.infrastructure.persistent.ItemMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ItemTest {
	@Autowired
	ItemMapper itemMapper;
	
	
	@Test
	public void find1N(){
		List<Item> all = itemMapper.getAll();
		for (Item item : all) {
			H.p(item);
		}
	}
	
	@Test
	public void findByCodeAndLevel(){
		List<Item> all = itemMapper.findByQuesAndCodeAndLevel("v150116","1.1", "3");
		for (Item item : all) {
			H.p(item);
		}
	}
}
