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
        <s:debug></s:debug>
        <s:if test="surveys.isEmpty()==true">
                        目前您没有任何调查项
        </s:if>
        <s:else>
           <table>
              <tr>
                 <td colspan="10"></td>
              </tr>
              <tr>
                 <td colspan="10">我的调查：</td>
              </tr>
              <tr>
                 <td colspan="10"></td>
              </tr>
              <tr>
                 <td>ID</td>
                 <td>调查标题</td>
                 <td>创建时间</td>
                 <td>状态</td>
                 <td>设计</td>
                 <td>收集信息</td>
                 <td>分析</td>
                 <td>打开/关闭</td>
                 <td>清除调查</td>
                 <td>删除</td>
              </tr>
              <s:iterator value="surveys" var="s">
              <tr>
                 <td><s:property value="#s.id"/></td>
                 <td><s:property value="#s.title"/></td>
                 <td><s:date name="#s.createTime" format="yyyy-MM-dd hh:mm"/></td>
                 <td>
                     <s:if test="#s.closed==true">关闭</s:if>
                     <s:else>开放</s:else>
                 </td>
                 <td><s:a action="SurveyAction-designSurvey?sid=%{#s.id}">设计</s:a></td>
                 <td><s:a action="CollectionSurveyAction?sid=%{#s.id}">收集信息</s:a></td>
                 <td><s:a action="SurveyAction-analyzeSurvey?sid=%{#s.id}">分析</s:a></td>
                 <td><s:a action="SurveyAction-toggleStatus?sid=%{#s.id}">打开/关闭</s:a></td>
                 <td><s:a action="SurveyAction-clearSurvey?sid=%{#s.id}">清除调查</s:a></td>
                 <td><s:a action="SurveyAction-deleteSurvey?sid=%{#s.id}">删除</s:a></td>
              </tr>
              </s:iterator>
           </table>
        </s:else>
  </body>
</html>
