package weixin;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpConfigStorage;
import me.chanjar.weixin.cp.api.WxCpService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.pg.infrastructure.weixin.api.WxCpJsapiService;

import test.common.H;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class WeixinTest {
	
	@Autowired
	WxCpConfigStorage wxCpConfigStorage;
	
	@Autowired
	WxCpService wxCpService;
	
	@Autowired
	WxCpJsapiService wxCpJsapiService;

	@Test
	public void notnull()  {
		H.p(this.wxCpJsapiService);
		
	}
	
	@Test
	public void getJsapiTicket() throws WxErrorException{
		String ret = wxCpService.get("https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket", null);
		System.out.println(ret);
	}
}
