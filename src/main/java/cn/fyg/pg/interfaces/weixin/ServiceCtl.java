package cn.fyg.pg.interfaces.weixin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.cp.api.WxCpConfigStorage;
import me.chanjar.weixin.cp.api.WxCpMessageRouter;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("service")
public class ServiceCtl {
	
	@Autowired
	WxCpConfigStorage wxCpConfigStorage;
	@Autowired
	WxCpService wxCpService;
	@Autowired
	WxCpMessageRouter wxCpMessageRouter;
	
	@RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
	public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 	response.setContentType("text/html;charset=utf-8");
		    response.setStatus(HttpServletResponse.SC_OK);

		    String msgSignature = request.getParameter("msg_signature");
		    String nonce = request.getParameter("nonce");
		    String timestamp = request.getParameter("timestamp");
		    String echostr = request.getParameter("echostr");

		    if (StringUtils.isNotBlank(echostr)) {
		      if (!wxCpService.checkSignature(msgSignature, timestamp, nonce, echostr)) {
		        // 消息签名不正确，说明不是公众平台发过来的消息
		        response.getWriter().println("非法请求");
		        return;
		      }
		      WxCpCryptUtil cryptUtil = new WxCpCryptUtil(wxCpConfigStorage);
		      String plainText = cryptUtil.decrypt(echostr);
		      // 说明是一个仅仅用来验证的请求，回显echostr
		      response.getWriter().println(plainText);
		      return;
		    }
		  
		 
		    WxCpXmlMessage inMessage = WxCpXmlMessage.fromEncryptedXml(request.getInputStream(), wxCpConfigStorage, timestamp, nonce, msgSignature);
		    
		    WxCpXmlOutMessage outMessage = wxCpMessageRouter.route(inMessage);
		    if (outMessage != null) {
		      response.getWriter().write(outMessage.toEncryptedXml(wxCpConfigStorage));
		    }

	}

}
