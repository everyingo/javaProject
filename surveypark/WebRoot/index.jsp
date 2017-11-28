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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   table{width: 400px;margin: 0 auto;border: 1px solid #666;}
	   table tr td{border: 1px solid #666;}
	   table tr .create_new{color:#09a800;text-align: center;}
	   table tr .table_label{text-align: right; color: #09a800;}
	   input{ outline: none;}
	   li{list-style-type:none;color: red;}
	   .menu_sec{width:100%;height: 30px;border: 1px solid green;}
	   .menu_sec .text1{text-align: center;margin-left: 0px;}
	   .menu_sec .text2{text-align: center;margin-right: 20px;float: right;}
	</style>
  </head>
  
  <body>
         <jsp:include page="header.jsp"/>
         <div class="menu_sec">
            <span class="text1">
                             用户登录
            </span>
            <span class="text2">
               <s:if test="#session.user!=null">
                                     欢迎您：<s:property value="#session.user.nickName"/>
               </s:if>
               
            </span>
         </div>
   
         <s:form action="LoginAction-doLogin" namespace="/" method="post">
            <table>
                <tr>
                   <td class="table_label">用户名：</td>
                   <td class="table_val">
                        <s:textfield name="username" value="admin" cssClass="text"/>
                   </td>
                </tr>
                <tr>
                   <td class="table_label">密码：</td>
                   <td class="table_val">
                        <s:password name="password" value="123456"/>
                   </td>
                </tr>
                <tr>
                   <td colspan="2" class="create_new">
                   <s:actionerror/>
                   </td>
                </tr>
                <tr>
                   <td class="table_label">
                        <s:reset value="重置"/>
                   </td>
                   <td class="table_val">
                        <s:submit value="提交" />
                   </td>
                </tr>
                
            </table>
         </s:form>
  </body>
</html>
