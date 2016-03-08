package com.leoman.common.exception;

import com.leoman.core.bean.Result;
import com.leoman.utils.WebUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2015/8/14.
 */
public class CustomSimpleMappingExceptionResolver  extends SimpleMappingExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        // Expose ModelAndView for chosen error view.
        String viewName = determineViewName(ex, request);

        System.out.println(request.getHeader("accept"));
        System.out.println(request.getHeader("X-Requested-With"));

        if (viewName != null) {// JSP格式返回
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                    .getHeader("X-Requested-With")!= null && request
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
                // 如果不是异步请求
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                return getModelAndView(viewName, ex, request);
            } else {// JSON格式返回
                WebUtil.print(response,new Result(false).msg(ex.getMessage()));
                return null;
            }
        } else {
            return null;
        }
    }


}
