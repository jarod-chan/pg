package cn.fyg.pg.domain.expert;

import java.util.List;

/**
 *专家
 */
public class Expert {
	
	private String userid;//专家的手机
	
	private String name; //name 专家的姓名
	
	private List<String> prop;//专家的专业属性

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

	public List<String> getProp() {
		return prop;
	}

	public void setProp(List<String> prop) {
		this.prop = prop;
	}

	
	public boolean hasProp(String prop_key){
		if(this.prop==null||this.prop.isEmpty()){
			return false;
		}
		return this.prop.contains(prop_key);
	}

}
