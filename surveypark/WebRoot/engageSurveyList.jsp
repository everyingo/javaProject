<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'engageSurveyList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
      td{height: 25px;}
    </style>
  </head>
  
  <body>
           <jsp:include page="header.jsp"/>
           <s:debug></s:debug>
           <s:if test="surveys.size==0">暂无调查！</s:if>
           <s:else>
           <s:set var="cells" value="5"/>
           <table width="100%" cellspacing="1" border="1" bordercolor="#ccc">
                  <tr>
                      <td colspan='<s:property value="#cells"/>'></td>
                  </tr>
                  <tr>
                      <td colspan='<s:property value="#cells"/>'>参与调查：请选择要参与的调查</td>
                  </tr>
                  <tr>
                      <td colspan='<s:property value="#cells"/>'></td>
                  </tr>
                  <tr>
                      <td colspan='<s:property value="#cells"/>' style="text-align: center;background-color: gray;">请选择要参与的调查</td>
                  </tr>
                  <s:iterator var="i" begin="0" end="%{surveys.size-1}" step="#cells">
                  <s:set var="sId" value="id"/>
                  <tr>
                        <s:iterator var="j" begin="0" end="%{#cells-1}" step="1">
                            <s:set var="idx" value="#i+#j"/>
                            <td width='<s:property value="100/#cells"/>%'>
                                <s:if test="#idx<surveys.size">
                                    <s:a action="EngageSurveyAction-entry?sid=%{surveys[#idx].id}">
                                       <img src='<s:property value="getImageUrl(surveys[#idx].logoPhotoPath)"/>' width="80px" height="80px"/>
                                       <s:property value="#idx+1"/>.<s:property value="surveys[#idx].title"/>
                                    </s:a>
                                </s:if>
                            </td>
                        </s:iterator>
                  </tr>
                  </s:iterator>
           </table>
           </s:else>
           
  </body>
</html>
