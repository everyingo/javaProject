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
    
    <title>My JSP 'mySurveys.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   table{width: 100%;margin: 0 auto;border: 1px solid #666;}
	   table tr td{height:20px;border: 1px solid #666;text-align: center;line-height: }
	   
	</style>

  </head>
  
  <body>
          <jsp:include page="header.jsp"/>
           <table>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <tr>
                 <td colspan="10" style="background:#ccc;text-align: left; padding-left: 20px;">授权管理：</td>
              </tr>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <s:if test="users.isEmpty()==true">
                                                           目前没有任何用户
              </s:if>
              <s:else>
              <tr style="background: #ccc">
                 <td>序号</td>
                 <td>ID</td>
                 <td>Email</td>
                 <td>昵称</td>
                 <td>修改授权</td>
                 <td>清除授权</td>
              </tr>
              <s:iterator value="users" var="u" status="sts">
              <tr>
                 <td><s:property value="#sts.index+1"/></td>
                 <td><s:property value="#u.id"/></td>
                 <td><s:property value="#u.email"/></td>
                 <td><s:property value="#u.nickName"/></td>
                 <td><s:a action="UserAuthorizeAction-userAuthorize?uid=%{#u.id}">修改</s:a></td>
                 <td><s:a action="UserAuthorizeAction-clearUserAuthorize?uid=%{#u.id}">删除</s:a></td>
              </tr>
              </s:iterator>
              </s:else>
           </table>
           
  </body>
</html>