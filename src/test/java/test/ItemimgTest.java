package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.H;

import cn.fyg.pg.domain.itemimg.Itemimg;
import cn.fyg.pg.infrastructure.persistent.ItemimgMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ItemimgTest {
	
	@Autowired
	ItemimgMapper itemimgMapper;
	
	@Test
	public void insert(){
		Itemimg itemimg=new Itemimg();
		itemimg.setItemchk_id(22);
		itemimg.setItem_code("1.1.2");
		itemimg.setImg_id("ddd");
		this.itemimgMapper.insert(itemimg);
		H.p(itemimg);
	}

}
