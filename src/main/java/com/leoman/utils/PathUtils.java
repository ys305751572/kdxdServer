package com.leoman.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 上传文件到服务器及删除文件工具类
 * 
 * @依赖：spring-security-core-3.1.3.RELEASE.jar,spring-core-3.1.2.RELEASE.jar,spring-web-3.1.2.RELEASE.jar
 * @author 龙哲
 * @date 2014-10-14 16:07:05
 */
public class PathUtils {
	/**
     * @Description 获得项目访问地址
     *            
     * @return String 项目访问地址
     * @date 2014-10-14 16:12:04
     * @author 龙哲
     */
    public static String getRemotePath() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        String absPath = scheme + "://" + host + (port != 80 ? ":" + port : "");
        return absPath;
    }
    
    /**
     * @Description 获得项目访问地址
     *            
     * @return String 项目访问地址
     * @date 2014-10-14 16:12:04
     * @author 龙哲
     */
    public static String getRemoteProJectPath() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        String absPath = scheme + "://" + host + (port != 80 ? ":" + port : "")+request.getContextPath()+"/";
        return absPath;
    }
    
    /**
     * @Description 获得项目名称
     *            
     * @return String 项目名称
     * @date 2014-10-14 16:12:04
     * @author 龙哲
     */
    public static String getProjectName() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        String context = request.getContextPath().replace("/", "")+"_";
        return context;
    }

    /**
     * @Description 返回用户的IP地址
     *           HttpServletRequest request request对象;
     * @return String ip地址
     * @date 2014-10-14 16:12:04
     * @author 龙哲
     */
    public static String toIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
