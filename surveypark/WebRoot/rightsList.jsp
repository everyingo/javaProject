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
	<script type="text/javascript" src="scripts/jquery-1.8.0.js"></script>
    <script type="text/javascript">
       $(function(){
    	    $(':checkbox[name="all"]').change(function(){
    	    	$('.checkbox:checkbox').attr("checked",this.checked);
    	    });
    	    
            $(':checkbox[name="fan"]').change(function(){
            	var $ckeck=$('.checkbox:checkbox');
            	for(var i=0;i<$ckeck.length;i++){
            		$($ckeck.get(i)).attr("checked",!$ckeck.get(i).checked);
            	}
    	    });
       });
    </script>
  </head>
  
  <body>
        <jsp:include page="header.jsp"/>
        <s:debug></s:debug>
           <s:form action="RightAction-batchUpdateRight" namespace="/" method="post">
           <table>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <tr>
                 <td colspan="10" style="background:#BC79D8;text-align: right; padding-right: 20px;"><s:a action="RightAction-toAddRightPage">添加权限</s:a></td>
              </tr>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <tr>
                 <td colspan="10" style="background:#ccc;text-align: left; padding-left: 20px;">权限管理：</td>
              </tr>
              <tr>
                 <td colspan="10" style="height: 2px;"></td>
              </tr>
              <s:if test="rights.isEmpty()==true">
                                                           目前您没有任何权限
              </s:if>
              <s:else>
              <tr style="background: #ccc">
                 <td>ID</td>
                 <td>权限名称</td>
                 <td>公共资源|<s:checkbox name="all"/>全选|<s:checkbox name="fan"/>反选</td>
                 <td>权限URL</td>
                 <td>权限位</td>
                 <td>权限码</td>
                 <td>修改</td>
                 <td>删除</td>
              </tr>
              <s:iterator value="rights" var="r" status="sts">
              <tr>
                 <td><s:textfield name="rights[%{#sts.index}].id" readonly="true"/></td>
                 <td><s:textfield name="rights[%{#sts.index}].rightName"/></td>
                 <td><s:checkbox name="rights[%{#sts.index}].common" cssClass="checkbox"/></td>
                 <td><s:property value="#r.rightUrl"/></td>
                 <td><s:property value="#r.rightPos"/></td>
                 <td><s:property value="#r.rightCode"/></td>
                 <td><s:a action="RightAction-toEditRightPage?rid=%{#r.id}">修改</s:a></td>
                 <td><s:a action="RightAction-deleteRight?rid=%{#r.id}">删除</s:a></td>
              </tr>
              </s:iterator>
              <tr>
                 <td colspan="8">
                    <s:submit value="提交"/>
                 </td>
              </tr>
              </s:else>
           </table>
           </s:form>
  </body>
</html>
