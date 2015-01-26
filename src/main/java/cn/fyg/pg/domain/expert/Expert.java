package cn.fyg.pg.domain.expert;

import java.util.List;

/**
 *专家
 */
public class Expert {
	
	private String userid;//专家的手机
	
	private String name; //name 专家的姓名
	
	private List<String> partcodes;//专家拥有的模块代码

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPartcodes() {
		return partcodes;
	}

	public void setPartcodes(List<String> partcodes) {
		this.partcodes = partcodes;
	}

	public boolean hasPartcode(String partcode){
		if(this.partcodes==null||this.partcodes.isEmpty()){
			return false;
		}
		return this.partcodes.contains(partcode);
	}

}
