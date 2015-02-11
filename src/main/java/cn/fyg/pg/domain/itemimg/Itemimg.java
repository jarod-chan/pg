package cn.fyg.pg.domain.itemimg;

/**
 *评分项目的附件，保存数据库中
 */
public class Itemimg {
	
	private int id;
	
	private int itemchk_id;
	
	private String item_code;
	
	private String img;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemchk_id() {
		return itemchk_id;
	}

	public void setItemchk_id(int itemchk_id) {
		this.itemchk_id = itemchk_id;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	

}
