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
	   #aa{float: right;margin-right: 10px;}
	</style>

  </head>
  
  <body>
          <jsp:include page="header.jsp"/>
           <table>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <tr>
                 <td colspan="10" style="background:#ccc;text-align: left; padding-left: 20px;">日志列表：
                   <span id="aa">
                        <s:form action="LogAction-findNearLogList">
                                                                              前几个月的
                          <input name="n" type="text" size="14px;"/>
                          <input type="submit" value="确认"/>
                        </s:form>
                   </span>
                 </td>
              </tr>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <s:if test="users.isEmpty()==true">
                                                           目前没有任何日志
              </s:if>
              <s:else>
              <tr style="background: #ccc">
                 <td>操作人</td>
                 <td>操作名称</td>
                 <td>参数</td>
                 <td>操作结果</td>
                 <td>消息</td>
                 <td>时间</td>
              </tr>
              <s:iterator value="logs" var="l" status="sts">
              <tr>
                 <td><s:property value="#l.operator"/></td>
                 <td><s:property value="#l.operName"/></td>
                 <td title="<s:property value='#l.operParams'/>"><s:property value="@com.mine.util.StringUtil@subString(#l.operParams)"/></td>
                 <td><s:property value="#l.operResult"/></td>
                 <td title="<s:property value='#l.resultMsg'/>"><s:property value="@com.mine.util.StringUtil@subString(#l.resultMsg)"/></td>
                 <td><s:date name="#l.operTime" format="yyyy-MM-dd HH:mm"/></td>
              </tr>
              </s:iterator>
              </s:else>
           </table>
           
  </body>
</html>