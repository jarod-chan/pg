package cn.fyg.pg.infrastructure.weixin.api;

public interface WxCpJsapiStorage {
	
	  public String getJsapiTicket();

	  public boolean isJsapiTicketExpired();

	  /**
	   * 强制将jsapi ticket过期掉
	   */
	  public void expireJsapiTicket();

	  /**
	   * 应该是线程安全的
	   * @param jsapiTicket
	   */
	  public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

}
