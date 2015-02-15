package cn.fyg.pg.infrastructure.weixin.impl.memory;

import cn.fyg.pg.infrastructure.weixin.api.WxCpJsapiStorage;

public class WxCpJsapiStorageImpl implements WxCpJsapiStorage {

	protected volatile String jsapiTicket;
	protected volatile long jsapiTicketExpiresTime;

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public boolean isJsapiTicketExpired() {
		return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
	}

	public synchronized void updateJsapiTicket(String jsapiTicket,
			int expiresInSeconds) {
		this.jsapiTicket = jsapiTicket;
		// 预留200秒的时间
		this.jsapiTicketExpiresTime = System.currentTimeMillis()
				+ (expiresInSeconds - 200) * 1000l;
	}

	public void expireJsapiTicket() {
		this.jsapiTicketExpiresTime = 0;
	}

}
