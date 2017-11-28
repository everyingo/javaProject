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
                 <td colspan="10" style="background:#BC79D8;text-align: right; padding-right: 20px;"><s:a action="RoleAction-toAddRolePage">添加角色</s:a></td>
              </tr>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <tr>
                 <td colspan="10" style="background:#ccc;text-align: left; padding-left: 20px;">角色管理：</td>
              </tr>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <s:if test="roles.isEmpty()==true">
                                                           目前没有任何角色
              </s:if>
              <s:else>
              <tr style="background: #ccc">
                 <td>序号</td>
                 <td>ID</td>
                 <td>角色名称</td>
                 <td>修改</td>
                 <td>删除</td>
              </tr>
              <s:iterator value="roles" var="r" status="sts">
              <tr>
                 <td><s:property value="#sts.index+1"/></td>
                 <td><s:property value="#r.id"/></td>
                 <td><s:property value="#r.roleName"/></td>
                 <td><s:a action="RoleAction-toEditRolePage?roleId=%{#r.id}">修改</s:a></td>
                 <td><s:a action="RoleAction-deleteRole?roleId=%{#r.id}">删除</s:a></td>
              </tr>
              </s:iterator>
              </s:else>
           </table>
           
  </body>
</html>
