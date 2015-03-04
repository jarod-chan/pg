package cn.fyg.pg.interfaces.weixin.message;

import me.chanjar.weixin.cp.api.WxCpMessageHandler;
import me.chanjar.weixin.cp.api.WxCpMessageRouter;

public interface WxMessageHandler {
	
	public WxCpMessageHandler handler();
	
	public void Router(WxCpMessageRouter wxCpMessageRouter,WxCpMessageHandler handler);

}
