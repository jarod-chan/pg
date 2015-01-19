package cn.fyg.pg.interfaces.module.standard.dto;

import cn.fyg.pg.domain.item.Item;

public class ItemChkVal {
	
	private Item item;

	private boolean ischeck;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isIscheck() {
		return ischeck;
	}

	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}
}
