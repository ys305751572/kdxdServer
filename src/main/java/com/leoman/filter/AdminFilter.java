package com.leoman.filter;

import com.leoman.common.logger.Logger;
import com.leoman.core.Constant;
import com.leoman.entity.Admin;
import com.leoman.entity.Member;
import com.leoman.utils.WebUtil;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangbin on 2015/8/10.
 */
public class AdminFilter implements Filter {

    private static String[] SKIP_URLS = new String[]{};

    public AdminFilter() {
    }

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
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String url = httpRequest.getRequestURI().toString();
        String contextPath = httpRequest.getContextPath();
        url = url.substring(contextPath.length());
        Logger.info(contextPath + ", " + url);

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

        Admin admin = (Admin)httpRequest.getSession().getAttribute(Constant.SESSION_MEMBER_GLOBLE);
        if(admin!=null){
            chain.doFilter(request, response);
            return;
        }

        String xRequested = httpRequest.getHeader("X-Requested-With");
        if (xRequested != null && xRequested.indexOf("XMLHttpRequest") != -1) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            WebUtil.print(httpResponse, "重新登录！");
        } else {
            httpResponse.sendRedirect(contextPath+"/admin/login");
        }
    }

    @Override
    public void destroy() {
    }

}
