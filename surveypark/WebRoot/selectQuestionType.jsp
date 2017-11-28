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
	   .descriptiontext{height: 120px;}
	</style>
  </head>
  
  <body>
         <jsp:include page="header.jsp"/>
         <div class="menu_sec">
         </div>
         <s:form action="QuestionAction-toDesignQuestionPage" namespace="/" method="post">
            <table cellspacing="1" border="1" bordercolor="#ccc">
                <s:hidden name="pid"/>
                <s:hidden name="sid"/>
                <tr>
                   <td class="table_val" width="100%">
                        <select name="questionType" onchange="this.form.submit()" style="width:100%;">
                           <option selected="selected">--选择问题类型--</option>
                           <option value="0">非矩阵式横向单选按钮</option>
                           <option value="1">非矩阵式纵向单选按钮</option>
                           <option value="2">非矩阵式横向复选按钮</option>
                           <option value="3">非矩阵式纵向复选按钮</option>
                           <option value="4">非矩阵式下拉列表</option>
                           <option value="5">非矩阵式文本框</option>
                           <option value="6">矩阵式单选按钮</option>
                           <option value="7">矩阵式复选按钮</option>
                           <option value="8">矩阵式下拉列表</option>
                        </select>
                   </td>
                </tr>
            </table>
         </s:form>
  </body>
</html>
