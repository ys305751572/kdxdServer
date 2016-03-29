<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/dd.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <script type="text/javascript"></script>
    <title>收货地址编辑</title>
</head>
<body>
<header class="header">
    <div class="all">
        <div class="note"></div>
        <div class="header_box" id="header_box">
            <h3>
                福利大派送，水果大抢购。<br>
                只要分享100%中奖率，就是这么任性
            </h3>
        </div>
    </div>
</header>
<section>
    <div class="section">
        <img src="${contextPath}/static/weixin/images/Bitmap.png">
        <p>
            福利大派送，水果大抢购.只要分享100%中奖率，就是这么任性!
            福利大派送，水果大抢购.只要分享100%中奖率，就是这么任性!
            福利大派送，水果大抢购.只要分享100%中奖率，就是这么任性!
            福利大派送，水果大抢购.只要分享100%中奖率，就是这么任性!
            福利大派送，水果大抢购.只要分享100%中奖率，就是这么任性!
        </p>
    </div>
    <div style="position: absolute;bottom: 30px;left: 60px;font-size: 30px;color: rgba(0,0,0,0.5);">
        <script language="JavaScript">
            <!--
            var caution = false
            function setCookie(name, value, expires, path, domain, secure) {
                var curCookie = name + "=" + escape(value) +
                        ((expires) ? "; expires=" + expires.toGMTString() : "") +
                        ((path) ? "; path=" + path : "") +
                        ((domain) ? "; domain=" + domain : "") +
                        ((secure) ? "; secure" : "")
                if (!caution || (name + "=" + escape(value)).length <= 4000)
                    document.cookie = curCookie
                else if (confirm("Cookie exceeds 4KB and will be cut!"))
                    document.cookie = curCookie
            }
            function getCookie(name) {
                var prefix = name + "="
                var cookieStartIndex = document.cookie.indexOf(prefix)
                if (cookieStartIndex == -1)
                    return null
                var cookieEndIndex = document.cookie.indexOf(";", cookieStartIndex + prefix.length)
                if (cookieEndIndex == -1)
                    cookieEndIndex = document.cookie.length
                return unescape(document.cookie.substring(cookieStartIndex + prefix.length, cookieEndIndex))
            }
            function deleteCookie(name, path, domain) {
                if (getCookie(name)) {
                    document.cookie = name + "=" +
                            ((path) ? "; path=" + path : "") +
                            ((domain) ? "; domain=" + domain : "") +
                            "; expires=Thu, 01-Jan-70 00:00:01 GMT"
                }
            }
            function fixDate(date) {
                var base = new Date(0)
                var skew = base.getTime()
                if (skew > 0)
                    date.setTime(date.getTime() - skew)
            }
            var now = new Date()
            fixDate(now)
            now.setTime(now.getTime() + 365 * 24 * 60 * 60 * 1000)
            var visits = getCookie("counter")
            if (!visits)
                visits = 1
            else
                visits = parseInt(visits) + 1
            setCookie("counter", visits, now)
            document.write("阅读次数:" + visits)
            // -->
        </script>
    </div>


</section>
</body>
</html>