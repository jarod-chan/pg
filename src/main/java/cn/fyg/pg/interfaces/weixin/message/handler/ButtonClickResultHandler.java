package cn.fyg.pg.interfaces.weixin.message.handler;

import java.util.Map;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpMessageHandler;
import me.chanjar.weixin.cp.api.WxCpMessageRouter;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.pg.application.CommunityService;
import cn.fyg.pg.application.ExpertService;
import cn.fyg.pg.infrastructure.persistent.QuesMapper;
import cn.fyg.pg.interfaces.weixin.message.WxMessageHandler;
import cn.fyg.pg.interfaces.weixin.message.handler.common.OutMessage;

@Component(value="buttonClickResultHandler")
public class ButtonClickResultHandler implements WxMessageHandler {

	@Autowired
	ExpertService expertService;
	@Autowired
	CommunityService communityService;
	@Autowired
	QuesMapper quesMapper;
	@Autowired
	String serverNamePort; 
	
	@Override
	public WxCpMessageHandler handler() {
		return new WxCpMessageHandler(){

			@Override
			public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage,
					Map<String, Object> context, WxCpService wxCpService,
					WxSessionManager sessionManager) throws WxErrorException {
				OutMessage outMessage = new OutMessage(wxMessage);
				
				return outMessage.text("功能维护中！");
				
			}};
	}

	@Override
	public void Router(WxCpMessageRouter wxCpMessageRouter,
			WxCpMessageHandler handler) {
		wxCpMessageRouter
		.rule()
		.async(false)
		.msgType("event")
		.event("click")
		.eventKey("result")
		.handler(handler)
		.end();

	}

}
