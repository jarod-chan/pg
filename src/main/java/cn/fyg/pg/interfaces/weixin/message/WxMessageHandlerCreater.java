package cn.fyg.pg.interfaces.weixin.message;

import java.util.List;

import me.chanjar.weixin.cp.api.WxCpMessageHandler;
import me.chanjar.weixin.cp.api.WxCpMessageRouter;

public class WxMessageHandlerCreater {
	
	List<WxMessageHandler> wxMessageHandlers;
	
	WxCpMessageRouter wxCpMessageRouter;
	
	public WxMessageHandlerCreater(List<WxMessageHandler> wxMessageHandlers,WxCpMessageRouter wxCpMessageRouter){
		this.wxMessageHandlers=wxMessageHandlers;
		this.wxCpMessageRouter=wxCpMessageRouter;
	}
	
	public void initHandler(){
		for(WxMessageHandler wxMessageHandler:this.wxMessageHandlers){
			WxCpMessageHandler handler = wxMessageHandler.handler();
			wxMessageHandler.Router(wxCpMessageRouter, handler);
		}
	}

}
