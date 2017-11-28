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
	   tr.survey_title{background: #B0EAAC;}
	</style>
	<script type="text/javascript" src="scripts/jquery-1.8.0.js"></script>
	
  </head>
  
  <body>
         <jsp:include page="header.jsp"/>
         <table width="100%" cellspacing="1" border="1" bordercolor="#ccc">
             <tr>
                 <td colspan="3"></td>
             </tr>
             <tr>
                 <td colspan="3">移动/复制页：（同一调查内是移动，不同调查内是复制）</td>
             </tr>
             <tr>
                 <td colspan="3"></td>
             </tr>
             <!-- 遍历 mySurveys start-->
             <s:iterator var="s" value="mySurveys">
             <s:set var="sId" value="#s.id"/>
             <tr class="survey_title">
                      <td colspan="3">
                          <s:property value="title"/>
                      </td>
             </tr>
             <!-- 遍历pages start-->
                <s:iterator var="p" value="#s.pages">
                <s:set var="pId" value="#p.id"/>
                <!-- 当前的页面高亮 -->
                <s:if test="#pId==srcPid">
                    <s:set var="bgcolor" value="'#ccc'"/>
                </s:if>
                <s:else>
                    <s:set var="bgcolor" value="'#fff'"/>
                </s:else>
                <tr bgcolor='<s:property value="#bgcolor"/>'>
                   <td style="width: 130px;border-width: 0px;background: white;"></td>
                   <td><s:property value="#p.title"/></td>
                   <td>
                        <s:if test="srcPid!=#p.id">
                           <s:form action="MoveOrCopyPageAction-doMoveOrCopyPage" method="post">
                            <s:hidden name="srcPid"/>
                            <s:hidden name="targPid" value="%{#p.id}"/>
                            <!--移动/复制页 成功后 跳转至目标页 所在的survey   -->
                            <s:hidden name="sid" value="%{#sId}"/>
                            <s:radio list="#{0:'之前',1:'之后'}" name="pos"/>
                            <s:submit name="submit" class="btn" value="确定"/>
                           </s:form>
                        </s:if>
                   </td>
                </tr>
                </s:iterator>
             <!-- 遍历pages end-->
             </s:iterator>
             <!-- 遍历 mySurveys end-->
             
         </table>
  </body>
</html>
