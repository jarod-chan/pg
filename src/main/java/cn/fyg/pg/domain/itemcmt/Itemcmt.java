package cn.fyg.pg.domain.itemcmt;

/**
 *问题的注释
 */
public class Itemcmt {
	
	private String ques_key;//版本
	
	private String item_code;//代码
	
	private String itemcmt;//注释内容

	public String getQues_key() {
		return ques_key;
	}

	public void setQues_key(String ques_key) {
		this.ques_key = ques_key;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItemcmt() {
		return itemcmt;
	}

	public void setItemcmt(String itemcmt) {
		this.itemcmt = itemcmt;
	}
	
	

}
