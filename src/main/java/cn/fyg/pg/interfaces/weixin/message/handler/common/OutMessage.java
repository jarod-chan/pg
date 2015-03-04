package cn.fyg.pg.interfaces.weixin.message.handler.common;

import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutTextMessage;

public class OutMessage {
	
	private WxCpXmlMessage wxCpXmlMessage;
	
	public OutMessage(WxCpXmlMessage wxCpXmlMessage){
		this.wxCpXmlMessage=wxCpXmlMessage;
	}

	public WxCpXmlOutTextMessage text(String content){
		 return WxCpXmlOutMessage
			.TEXT()
			.content(content)
			.fromUser(wxCpXmlMessage.getToUserName())
			.toUser(wxCpXmlMessage.getFromUserName())
			.build();
	}
}
