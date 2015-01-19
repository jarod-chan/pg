package cn.fyg.pg.interfaces.module.standard.dto;

import cn.fyg.pg.domain.item.Item;

public class ItemChkCount {
	
	private Item item;

	private int count;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

}
