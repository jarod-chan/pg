package cn.fyg.pg.domain.itemimg;

/**
 *评分项目的附件
 */
public class Itemimg {
	
	private int id;//用来保持上传的顺序
	
	private int itemchk_id;
	
	private String item_code;
	
	private String img_id;//文件id
	
	private boolean local;//是否本地文件
	
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

	public String getImg_id() {
		return img_id;
	}

	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

		
}
