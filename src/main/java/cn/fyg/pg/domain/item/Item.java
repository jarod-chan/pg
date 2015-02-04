package cn.fyg.pg.domain.item;

/**
 *明细
 *ques_key 问卷版本
 *content 内容
 *level 等级
 *code 编码
 */
public class Item {
	
	private String ques_key;
	
	private String content;
	
	private String  level;
	
	private String code;

	public String getQues_key() {
		return ques_key;
	}

	public void setQues_key(String ques_key) {
		this.ques_key = ques_key;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
