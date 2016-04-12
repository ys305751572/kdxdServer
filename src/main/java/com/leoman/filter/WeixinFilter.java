package com.leoman.filter;

import com.leoman.common.logger.Logger;
import com.leoman.core.Configue;
import com.leoman.core.Constant;
import com.leoman.entity.KUser;
import com.leoman.entity.WxUser;
import com.leoman.utils.BeanUtil;
import com.leoman.utils.HttpUtil;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangbin on 2015/8/4.
 */
public class WeixinFilter implements Filter {

    @Autowired
    private WxMpService wxMpService;

    private static String[] SKIP_URLS = new String[]{};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("skipUrls");
        if (StringUtils.isNotBlank(urls)) {
            String temp[] = urls.split(",");
            List<String> list = new ArrayList<String>();

            for (String skipUrl : temp) {
                if (StringUtils.isNotBlank(skipUrl)) {
                    skipUrl = "^" + skipUrl.replaceAll("\\*", ".*") + "$";
                    list.add(skipUrl);
                }
            }
            SKIP_URLS = list.toArray(SKIP_URLS);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURI().toString();
        String contextPath = httpRequest.getContextPath();
        url = url.substring(contextPath.length());
        Logger.info(contextPath + ", " + url);
        Logger.info("url:" + httpRequest.getRequestURL());

        if (SKIP_URLS != null) {
            for (String skipUrl : SKIP_URLS) {
                Pattern pattern = Pattern.compile(skipUrl, Pattern.DOTALL);
                Matcher matcher = pattern.matcher(url);
                if (matcher.find()) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        KUser user = (KUser) httpRequest.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);

        if (user == null) {
            Logger.info("url:" + httpRequest.getContextPath());
            Map<String, String[]> paramsMap = httpRequest.getParameterMap();
            StringBuffer buffer = new StringBuffer("?");
            for (String s : paramsMap.keySet()) {
                String[] value = paramsMap.get(s);
                for (String v : value) {
                    buffer.append(s).append("=").append(v).append("&");
                }
            }
            String goUrl = httpRequest.getRequestURL() + buffer.toString().substring(0, buffer.length() - 1);
            System.out.println("goUrl:" + goUrl);
            ((HttpServletRequest) request).getSession().setAttribute(Constant.GO_URL, goUrl);
            final String toLoginUrl = httpRequest.getContextPath() + "/weixin/login/toLogin";
            httpResponse.sendRedirect(toLoginUrl);
        } else {
            String fullUrl = HttpUtil.getFullUrl(httpRequest, Configue.getBaseDomain());
            System.out.println("fullUrl:" + fullUrl);

            String OAUTH_URL = wxMpService.oauth2buildAuthorizationUrl(fullUrl, WxConsts.OAUTH2_SCOPE_BASE, Constant.WEIXIN_STATE);

            System.out.println("domain:" + httpRequest.getSession().getAttributeNames());
            Enumeration<String> enumeration = httpRequest.getSession().getAttributeNames();

            while (enumeration.hasMoreElements()) {
                System.out.println("-----");
                System.out.println(enumeration.nextElement());
                System.out.println("-----");
            }

            KUser loginUser = (KUser) httpRequest.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
            System.out.println("wxUser:" + loginUser);
            if (loginUser != null) {
                chain.doFilter(request, response);
                return;
            }
            if (Constant.WEIXIN_STATE.equals(request.getParameter("state"))) {
                chain.doFilter(request, response);
                return;
            }

            httpResponse.sendRedirect(OAUTH_URL);
            System.out.println("OAUTH_URL:" + OAUTH_URL);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
