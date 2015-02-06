package cn.fyg.pg.domain.itemchk;

import java.util.List;

/**
 *专家答案
 */
public class Itemchk {
	
	private String ques_key;
	
	private String community_key;
	
	private String userid;
	
	private int id;
	
	private List<Itemval> val;

	public String getQues_key() {
		return ques_key;
	}

	public void setQues_key(String ques_key) {
		this.ques_key = ques_key;
	}

	public String getCommunity_key() {
		return community_key;
	}

	public void setCommunity_key(String community_key) {
		this.community_key = community_key;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Itemval> getVal() {
		return val;
	}

	public void setVal(List<Itemval> val) {
		this.val = val;
	}


	
	

}
