package cn.fyg.pg.interfaces.module.standard.dto;

import cn.fyg.pg.domain.item.Item;

public class ItemChkVal {
	
	private Item item;
	
	private boolean iscomment;
	
	private String comment;

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

	public boolean isIscomment() {
		return iscomment;
	}

	public void setIscomment(boolean iscomment) {
		this.iscomment = iscomment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
