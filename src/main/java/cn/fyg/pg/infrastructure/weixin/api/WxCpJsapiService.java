package cn.fyg.pg.infrastructure.weixin.api;

import me.chanjar.weixin.common.exception.WxErrorException;

public interface WxCpJsapiService {
	
	 /**
	   * 获得jsapi_ticket,不强制刷新jsapi_ticket
	   * @see #getJsapiTicket(boolean)
	   * @return
	   * @throws WxErrorException
	   */
	  public String getJsapiTicket() throws WxErrorException;

	  /**
	   * 获得jsapi_ticket
	   * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
	   * @param forceRefresh 强制刷新
	   * @return
	   * @throws WxErrorException
	   */
	  public String getJsapiTicket(boolean forceRefresh) throws WxErrorException;

	  /**
	   * 创建调用jsapi时所需要的签名
	   * @param url       url
	   * @return
	   */
	  public WxCpJsapiSignature createJsapiSignature(String url) throws WxErrorException;

}
