package com.leoman.pay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.json.JSONException;

import com.bluemobi.pay.client.TenpayHttpClient;
import com.bluemobi.pay.util.ConstantUtil;
import com.bluemobi.pay.util.JsonUtil;
import com.bluemobi.pay.util.MD5Util;
import com.bluemobi.pay.util.Sha1Util;
import com.bluemobi.pay.util.XMLUtil;
import com.bluemobi.utils.CommonUtils;

public class PrepayIdRequestHandler extends RequestHandler {

	public PrepayIdRequestHandler(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public Map<String,Object> getMap() {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			resultMap.put(k, v);
		}
		return resultMap;
	}
	
	/**
	 * ����ǩ��SHA1
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createSHA1Sign() {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String params = sb.substring(0, sb.lastIndexOf("&"));
		String appsign = Sha1Util.getSha1(params);
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "sha1 sb:" + params);
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "app sign:" + appsign);
		return appsign;
	}
	/**
	 * ����ǩ��SHA1
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createMD5Sign() {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		sb.append("key=").append(ConstantUtil.API_KEY);
		
		String appsign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		System.out.println("appsign:" + appsign);
		return appsign;
	}

	// �ύԤ֧��
	public String sendPrepay() throws JSONException {
		String prepayid = "";
//		StringBuffer sb = new StringBuffer("{");
//		Set es = super.getAllParameters().entrySet();
//		Iterator it = es.iterator();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String k = (String) entry.getKey();
//			String v = (String) entry.getValue();
//			if (null != v && !"".equals(v) && !"appkey".equals(k)) {
//				sb.append("\"" + k + "\":\"" + v + "\",");
//			}
//		}
//		String params = sb.substring(0, sb.lastIndexOf(","));
//		params += "}";

		String params = CommonUtils.mapToXml(super.getAllParameters());
		String requestUrl = ConstantUtil.GATEURL;
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:" + requestUrl);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			try {
				Map<String,Object> resultMap = XMLUtil.doXMLParse(resContent);
				
				for (Map.Entry<String, Object> map: resultMap.entrySet()) {
					System.out.println(map.getKey() + ":" + map.getValue());
				}
				prepayid = resultMap.get("prepay_id").toString();
				System.out.println("prepayid================:" + prepayid);
			} catch (JDOMException | IOException e) {
				e.printStackTrace();
			}
		}
		return prepayid;
	}

	// �ж�access_token�Ƿ�ʧЧ
	public String sendAccessToken() {
		String accesstoken = "";
		StringBuffer sb = new StringBuffer("{");
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"appkey".equals(k)) {
				sb.append("\"" + k + "\":\"" + v + "\",");
			}
		}
		String params = sb.substring(0, sb.lastIndexOf(","));
		params += "}";

		String requestUrl = super.getGateUrl();
		// this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"
		// + requestUrl);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		// this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" +
		// params);
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (2 == resContent.indexOf(ConstantUtil.ERRORCODE)) {
				accesstoken = resContent.substring(11, 16);// ��ȡ��Ӧ��errcode��ֵ
			}
		}
		return accesstoken;
	}
}
