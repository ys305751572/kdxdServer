package com.leoman.pay;

import com.leoman.pay.util.TenpayUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class PackageRequestHandler extends RequestHandler {

	public PackageRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * ��ȡ����������URL
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public String getRequestURL() throws UnsupportedEncodingException {
		
		this.createSign();
		
		StringBuffer sb = new StringBuffer();
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			
			sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
		}
		
		//ȥ�����һ��&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		// ����debug��Ϣ
		this.setDebugInfo("md5 sb:" + getDebugInfo() + "\r\npackage:" + reqPars);
		return reqPars;
		
	}

}
