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
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	 *{margin: 0px;padding: 0px;}
	 #top{width:100%;height:100px;border: 1px solid red;}
	 #menu{width:100%;height: 50px;border: 1px solid red;line-height: 50px;}
	 #menu li{float:left; margin:0 0 0 60px;padding:0px}
	</style>

  </head>
  
  <body>
         <div id="top">
             
         </div>
         <div id="menu">
             <li><a href="LoginAction-toLoginPage">首页</a></li>
             <li><a href="SurveyAction-newSurvey">新建调查</a></li>
             <li><a href="SurveyAction-mySurveys">我的调查</a></li>
             <li><a href="EngageSurveyAction-toEngageSurveyList">参与调查</a></li>
             <li><a href="RegAction-toReg">注册用户</a></li>
             <li><a href="UserAuthorizeAction-userListPage">用户授权管理</a></li>
             <li><a href="RoleAction-toRoleListPage">角色管理</a></li>
             <li><s:a action="RightAction-toRightsListPage">权限管理</s:a></li>
             <li><s:a action="LogAction-findNearLogList">日志管理</s:a></li>
         </div>
  </body>
</html>
