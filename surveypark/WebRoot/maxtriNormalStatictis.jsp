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
    
    <title>My JSP '/maxtriNormalStatictis.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
        table,p{margin: 0 auto;}
        p{text-align: center;}
    </style>
  </head>
  
  <body>
        <s:debug></s:debug>
        <table width="800px" cellspacing="1" border="1" bordercolor="#ccc">
                <tr>
                     <td></td>
                     <s:iterator var="col" value="qsm.question.matriColTitleArr">
                     <td>
                        <s:property/>
                     </td>
                     </s:iterator>
                </tr>
                <s:iterator var="row" value="qsm.question.matrixRowTitleArr" status="rowIndex">
                <tr>
                     <td>
                        <s:property/>
                     </td>
                     <s:iterator var="col" value="qsm.question.matriColTitleArr" status="colIndex">
                     <td>
                          <s:property value="getValue(#rowIndex.index,#colIndex.index)"  />
                     </td>
                     </s:iterator>
                </tr>      
                </s:iterator>
        </table>
        <p> 共 <s:property value="qsm.count"/> 人回答 该问题！</p>
  </body>
</html>
