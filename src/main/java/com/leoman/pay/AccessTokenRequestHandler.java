package com.leoman.pay;

import com.leoman.pay.client.TenpayHttpClient;
import com.leoman.pay.util.ConstantUtil;
import com.leoman.pay.util.JsonUtil;
import com.leoman.pay.util.WXUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessTokenRequestHandler extends RequestHandler {

	public AccessTokenRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	private static String access_token = "";

	/**
	 * 锟斤拷取凭证access_token
	 * @return
	 */
	public static String getAccessToken() {
		if ("".equals(access_token)) {// 锟斤拷锟轿拷锟街憋拷踊锟饺�
			return getTokenReal();
		}

		if (tokenIsExpire(access_token)) {// 锟斤拷锟斤拷锟斤拷锟斤拷锟铰伙拷取
			return getTokenReal();
		}
		return access_token;
	}

	/**
	 * 实锟绞伙拷取access_token锟侥凤拷锟斤拷
	 * @return
	 */
	protected static String getTokenReal() {
		String requestUrl = ConstantUtil.TOKENURL + "?grant_type=" + ConstantUtil.GRANT_TYPE + "&appid="
				+ ConstantUtil.APP_ID + "&secret=" + ConstantUtil.APP_SECRET;
		String resContent = "";
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setMethod("GET");
		httpClient.setReqContent(requestUrl);
		if (httpClient.call()) {
			resContent = httpClient.getResContent();
			if (resContent.indexOf(ConstantUtil.ACCESS_TOKEN) > 0) {
				access_token = JsonUtil.getJsonValue(resContent, ConstantUtil.ACCESS_TOKEN);
			} else {
				System.out.println("锟斤拷取access_token值锟斤拷锟截达拷锟襟！ｏ拷锟斤拷");
			}
		} else {
			System.out.println("");
			System.out.println(httpClient.getResponseCode());
			System.out.println(httpClient.getErrInfo());
			// 锟叫匡拷锟斤拷锟斤拷为锟斤拷锟斤拷原锟斤拷锟斤拷锟斤拷锟窖撅拷锟斤拷锟�?锟斤拷未锟秸碉拷应锟斤拷
		}

		return access_token;
	}

	/**
	 * 锟叫断达拷锟捷癸拷锟斤拷锟侥诧拷锟斤拷access_token锟角凤拷锟斤拷锟�
	 * @param access_token
	 * @return
	 */
	private static boolean tokenIsExpire(String access_token) {
		boolean flag = false;
		PrepayIdRequestHandler wxReqHandler = new PrepayIdRequestHandler(null, null);
		wxReqHandler.setParameter("appid", ConstantUtil.APP_ID);
		wxReqHandler.setParameter("appkey",ConstantUtil.APP_KEY);
		wxReqHandler.setParameter("noncestr", WXUtil.getNonceStr());
		wxReqHandler.setParameter("package", ConstantUtil.packageValue);
		wxReqHandler.setParameter("timestamp", WXUtil.getTimeStamp());
		wxReqHandler.setParameter("traceid", ConstantUtil.traceid);

		// 锟斤拷锟街э拷锟角╋拷锟�
		String sign = wxReqHandler.createSHA1Sign();
		wxReqHandler.setParameter("app_signature", sign);
		wxReqHandler.setParameter("sign_method", ConstantUtil.SIGN_METHOD);
		String gateUrl = ConstantUtil.GATEURL + access_token;
		wxReqHandler.setGateUrl(gateUrl);

		// 锟斤拷锟斤拷锟斤拷锟斤拷
		String accesstoken = wxReqHandler.sendAccessToken();
		if (ConstantUtil.EXPIRE_ERRCODE.equals(accesstoken) || ConstantUtil.FAIL_ERRCODE.equals(accesstoken))
			flag = true;
		return flag;
	}

}
