<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="date" uri="/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    String _path = request.getContextPath();
    String _basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + _path + "/";
%>
<base href="<%=_basePath%>">