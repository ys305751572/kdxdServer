/**
 * 
 */
package com.leoman.pay.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author ChengYu
 * 
 */
public class CommonUtils {

	private static Properties properties = null;

	static {
		if (properties == null) {
			properties = new Properties();
		}
	}

	/**
	 * 将对象转换成POJO类
	 * 
	 * @param type
	 * @param map
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	public static Object convertMapToPOJO(Class<?> type, Map<String, Object> map)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return
	 */
	public static Date getCurrentDate(String fomater) {
		return new Date();
	}

	/**
	 * 手机号中间4位隐藏
	 * 
	 * @author gaolei
	 */
	public static String getBeHidedMobile(String mobile) {
		if (mobile.length() < 11) {
			return mobile;
		}
		String a = mobile.substring(0, 3);
		String b = mobile.substring(7, 11);
		return a + "****" + b;
	}

	// 根据url获取商品id
	// String t = "http://www.uhoem.com/product/detail/130.jhtml";
	public static String getProductid(String t) {
		if (StringUtils.isEmpty(t)) {
			return "";
		}
		t = t.substring(t.lastIndexOf("/") + 1);
		t = t.substring(0, t.lastIndexOf("."));
		return t;
	}

	public static String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	public static String mapToXml(Map<String, Object> map) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");

		for (Map.Entry<String, Object> entity : map.entrySet()) {
			String key = entity.getKey();
			Object obj = entity.getValue();

			buffer.append("<").append(key).append(">").append(obj.toString()).append("</").append(key).append(">");
		}
		buffer.append("</xml>");
		return buffer.toString();
	}

	public static String getRealAddress(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}

	/**
	 * 生成验证码
	 * @param length
	 * @return
	 */
	public static String getCode(int length) {
	    Random random = new Random();
	    StringBuffer result = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	    	result.append(random.nextInt(10));
	    }
	    return result.toString();
	}
	
	public static void main(String[] args) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("user", "yesong");
//		System.out.println(mapToXml(map));
		
		String reg = "^[0-9]+(,[0-9]+)*$";
		
	}

	public static String getSessionId() {
		return null;
	}
}