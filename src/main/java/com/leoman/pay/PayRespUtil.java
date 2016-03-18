package com.leoman.pay;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class PayRespUtil {

	public static String toStringFromReq(HttpServletRequest request) {
		
		String msg = "";
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader reader = request.getReader();
			while((msg = reader.readLine()) != null) {
				buffer.append(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
