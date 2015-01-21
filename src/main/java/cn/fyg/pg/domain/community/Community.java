package cn.fyg.pg.domain.community;

/**
 *社区
 */
public class Community {
	
	private String key; //*key 主键
	 
	private String name;//*name 名称
	
	private int no;//*no 序号

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
