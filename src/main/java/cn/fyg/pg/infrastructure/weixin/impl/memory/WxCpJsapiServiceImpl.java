package cn.fyg.pg.infrastructure.weixin.impl.memory;

import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.cp.api.WxCpService;
import cn.fyg.pg.infrastructure.weixin.api.WxCpJsapiService;
import cn.fyg.pg.infrastructure.weixin.api.WxCpJsapiSignature;
import cn.fyg.pg.infrastructure.weixin.api.WxCpJsapiStorage;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;

public class WxCpJsapiServiceImpl implements WxCpJsapiService {

	protected final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	protected final Random RANDOM = new Random();

	protected final Object globalJsapiTicketRefreshLock = new Object();

	public String getJsapiTicket() throws WxErrorException {
		return getJsapiTicket(false);
	}
	
	private WxCpJsapiStorage wxCpJsapiStorage;
	
	private WxCpService wxCpService;
	
	public void setWxCpJsapiStorage(WxCpJsapiStorage wxCpJsapiStorage) {
		this.wxCpJsapiStorage = wxCpJsapiStorage;
	}

	public void setWxCpService(WxCpService wxCpService) {
		this.wxCpService = wxCpService;
	}
	

	public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
		if (forceRefresh) {
			wxCpJsapiStorage.expireJsapiTicket();
		}
		if (wxCpJsapiStorage.isJsapiTicketExpired()) {
			synchronized (globalJsapiTicketRefreshLock) {
				if (wxCpJsapiStorage.isJsapiTicketExpired()) {
					String url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket";
					String responseContent = wxCpService.get(url, null);
					JsonElement tmpJsonElement = Streams.parse(new JsonReader(
							new StringReader(responseContent)));
					JsonObject tmpJsonObject = tmpJsonElement.getAsJsonObject();
					String jsapiTicket = tmpJsonObject.get("ticket")
							.getAsString();
					int expiresInSeconds = tmpJsonObject.get("expires_in")
							.getAsInt();
					wxCpJsapiStorage.updateJsapiTicket(jsapiTicket,
							expiresInSeconds);
				}
			}
		}
		return wxCpJsapiStorage.getJsapiTicket();
	}

	public WxCpJsapiSignature createJsapiSignature(String url)
			throws WxErrorException {
		long timestamp = System.currentTimeMillis() / 1000;
		String noncestr = getRandomStr();
		String jsapiTicket = getJsapiTicket(false);
		try {
			String signature = SHA1.genWithAmple("jsapi_ticket=" + jsapiTicket,
					"noncestr=" + noncestr, "timestamp=" + timestamp, "url="
							+ url);
			WxCpJsapiSignature jsapiSignature = new WxCpJsapiSignature();
			jsapiSignature.setJsapiTicket(jsapiTicket);
			jsapiSignature.setTimestamp(timestamp);
			jsapiSignature.setNoncestr(noncestr);
			jsapiSignature.setUrl(url);
			jsapiSignature.setSignature(signature);
			return jsapiSignature;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	protected String getRandomStr() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
		}
		return sb.toString();
	}

}
