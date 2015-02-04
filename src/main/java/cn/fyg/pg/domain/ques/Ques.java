package cn.fyg.pg.domain.ques;

/**
 *问卷
 *key 关键字
 *state on/off  表示关闭或者开启
 */
public class Ques {
	
	private String key;
	
	private String state;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

}
