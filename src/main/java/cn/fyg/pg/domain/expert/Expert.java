package cn.fyg.pg.domain.expert;

/**
 *专家
 *userid 专家的手机
 *name 专家的姓名
 */
public class Expert {
	
	private String userId;
	
	private String name;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
