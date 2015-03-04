package cn.fyg.pg.interfaces.weixin.message.handler;

import java.util.List;
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
import cn.fyg.pg.domain.community.Community;
import cn.fyg.pg.domain.ques.Ques;
import cn.fyg.pg.infrastructure.persistent.QuesMapper;
import cn.fyg.pg.interfaces.weixin.message.WxMessageHandler;
import cn.fyg.pg.interfaces.weixin.message.handler.common.OutMessage;

@Component(value="buttonClickCommunityHandler")
public class ButtonClickCommunityHandler implements WxMessageHandler {

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
				
				String userid=wxMessage.getFromUserName();
				if(!expertService.exist(userid)){
					return outMessage.text("你不是合法的系统用户!");
				}
				
				Ques ques = quesMapper.currQues();
				if(ques==null){
					return outMessage.text("评分功能处于关闭状态!");
				}
				String ques_key=ques.getKey();
				
				List<Community> communityList = communityService.all();
				if(communityList==null||communityList.isEmpty()){
					return outMessage.text("系统小区没有初始化!");
				}
				
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("你参与的评测小区：\n\n");
				for (int i=0;i<communityList.size();i++) {
					Community community = communityList.get(i);
					String url=serverNamePort+String.format("standard/module/%s/%s/%s",userid,community.getKey(),ques_key);
					stringBuffer.append(String.format("%s.<a href='%s'>%s</a>\n\n",i+1,url,community.getName()));
				}
				stringBuffer.append("点击小区名称，进入评分页面。");
				return outMessage.text(stringBuffer.toString());
			
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
		.eventKey("community")
		.handler(handler)
		.end();

	}

}
