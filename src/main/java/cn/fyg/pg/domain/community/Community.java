package cn.fyg.pg.domain.community;

/**
 *社区
 *key 主键
 *name 名称
 *no 序号
 */
public class Community {
	
	private String key;
	
	private String name;
	
	private int no;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
