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
    
    <title>注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   table{width: 400px;margin: 0 auto;}
	   table tr .create_new{color:#09a800;text-align: center;}
	   table tr .table_label{text-align: right; color: #09a800;}
	   input{ outline: none;}
	   li{list-style-type:none;color: red;}
	   .descriptiontext{height: 160px; width: 220px;}
	   .text{width: 200px;}
	</style>
  </head>
  
  <body>
         <jsp:include page="header.jsp"/>
         <div class="menu_sec">
         </div>
         <s:form action="QuestionAction-saveOrUpdateQuestion" namespace="/" method="post">
            <table cellspacing="1" border="1" bordercolor="#ccc">
                <s:hidden name="id"/>
                <s:hidden name="pid"/>
                <s:hidden name="sid"/>
                <s:hidden name="questionType"/>
                <tr>
                   <td class="table_label">问题标题：</td>
                   <td class="table_val">
                        <s:textfield name="title" cssClass="text"/>
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
