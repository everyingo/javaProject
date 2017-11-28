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
    
    <title>My JSP 'surveyDesign.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	   td{height: 25px;}
	   li{list-style-type:none;color: #000;float:right; margin:0 60px 0 0;padding:0px}
	   table tr td.design{ height:30px;background: #7373D0; padding-left: 20px;}
	   table tr td.nullspan{ height:5px;}
	   
	   table tr#survey{height: 30px;background: #A775E8;}
	   table tr#survey td{border-color: #A775E8;}
	   table tr#survey .survey_title{padding-left: 20px;}
	   
	   .survey_page .page_header{height: 30px;background: #6FB592;}
	</style>

  </head>
  
  <body>
                       <jsp:include page="header.jsp"/>
                       <table cellspacing="0" border="0" bordercolor="#ccc" width="100%">
                          <tr><td colspan="2" style="height: 2px;"></td></tr>
                          <tr><td colspan="2" style="text-align: left;background: #ccc;height: 25px; padding-left: 20px;"><s:property value="title"/></td></tr>
                          <tr><td colspan="2" style="height: 2px;"></td></tr>
                          <s:iterator var="p" value="pages">
                          <tr><td colspan="2" style="text-align: left;background: #ccc;height: 25px; padding-left: 20px;"><s:property value="#p.title"/></td></tr>
                          <tr style="border: 0px; border-style: none;">
                              <td width="3%"></td>
                              <td width="97%">
                                   <!--2-->
                                   <table class="survey_page"  cellspacing="1" border="1" bordercolor="#ccc" width="100%">
                                   <s:iterator var="q" value="#p.questions" status="sts">
                                   <s:set var="qt" value="#q.questionType"/>
                                   <tr class="page_header">
                                      <td class="survey_title" width="70%">
                                        <s:property value="(#sts.index+1)+'.'+#q.title"/>
                                      </td>
                                      <td class="survey_title" width="30%" style="text-align: right; padding-right: 30px;">
                                                 <s:if test="#qt>5">
                                                         <s:form action="MaxtriStatisticAction" method="post" namespace="/" target="_blank">
                                                         <s:hidden name="qid" value="%{#q.id}"/>
                                                         <s:submit value="查看矩阵式问题统计结果"/>
                                                         </s:form>
                                                 </s:if><s:elseif test="#qt lt 5">
                                                         <s:form action="ChartStatictisAction" method="post" namespace="/" target="_blank">
                                                         <s:hidden name="qid" value="%{#q.id}"/>
                                                         <s:set var="charList" value="#{0:'平面饼图',1:'立体饼图',2:'横向平面柱状图',3:'纵向平面柱状图',4:'横向立体柱状图',5:'纵向立体柱状图',6:'平面折线图',7:'立体折线图' }"/>
                                                         <s:select name="charType" list="#charList"/>
                                                         <s:submit value="查看"/>
                                                         </s:form>
                                                 </s:elseif>
                                      </td>
                                   </tr>
                                   </s:iterator>
                                   </table>
                                   <!-- 2 -->
                              </td>
                          </tr>
                          </s:iterator>
                       </table>
                       <!-- 1 -->  
                  
  </body>
</html>
