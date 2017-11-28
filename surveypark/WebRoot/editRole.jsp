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
	   select{height: 260px; width: 120px;}
	</style>
	<script type="text/javascript" src="scripts/jquery-1.8.0.js"></script>
	<script type="text/javascript">
	    $(function(){
	    	  var $left=$('#left');
	    	  var $right=$('#right');
	    	  $('#to_left').click(function(){
	    		  var options=$right.val();
	    		  for(var i=0;i<options.length;i++){
	    			  var $option=$('#right option[value='+options[i]+']');
	    			  $option.appendTo($left);
	    		  }
	    		  
	    	  });
	    	  $('#to_right').click(function(){
	    		  var options=$left.val();
	    		  for(var i=0;i<options.length;i++){
	    			  var $option=$('#left option[value='+options[i]+']');
	    			  $option.appendTo($right);
	    		  }
	    		  
	    	  });
	    	  $('#to_left_all').click(function(){
	    		    var $options=$('#right option');
	    			$options.appendTo($left);
	    	  });
	    	  $('#to_right_all').click(function(){
	    		    var $options=$('#left option');
	    			$options.appendTo($right);
	    	  });
	    });
	    function selectAll(){
	    	$('#left option').attr("selected",true);
	    	return true;
	    }
	</script>
  </head>
  
  <body>
         <jsp:include page="header.jsp"/>
         <s:form action="RoleAction-saveOrUpdateRole" namespace="/" method="post" onsubmit="selectAll()">
            <table cellspacing="1" border="1" bordercolor="#ccc" width="900px;">
                <s:hidden name="id"/>
                <tr>
                   <td class="table_label">角色名称：</td>
                   <td class="table_val">
                        <s:textfield name="roleName" cssClass="text"/>
                   </td>
                </tr>
                <tr>
                   <td class="table_label">角色值：</td>
                   <td class="table_val">
                        <s:textfield name="roleValue" cssClass="text"/>
                   </td>
                </tr>
                <tr height="120px;">
                   <td class="table_label">角色描述：</td>
                   <td class="table_val">
                         <s:textarea name="roleDesc" cssClass="descriptiontext"/>
                   </td>
                </tr>
                <tr>
                   <td colspan="2">
                       
                       <table cellspacing="1" border="1" bordercolor="#ccc" width="100%">
                           <tr height="300px;">
                               <td width="35%">
                                    <select id="left" name="wonRightIds" multiple="multiple">
                                         <s:iterator var="r" value="rights">
                                             <option value='<s:property value="#r.id"/>'>
                                                 <s:property value="#r.rightName"/>
                                             </option>
                                         </s:iterator>
                                     </select>
                               </td>
                               <td width="30%" style="text-align: center;">
                                    <input type="button" id="to_left"  value="<" style="width: 50px;"/><br/><br/>
                                    <input type="button" id="to_right" value=">" style="width: 50px;"/><br/><br/>
                                    <input type="button" id="to_left_all"  value="<<" style="width: 50px;"/><br/><br/>
                                    <input type="button" id="to_right_all"  value=">>" style="width: 50px;"/><br/><br/>
                               </td>
                               <td width="35%">
                                     <select id="right" multiple="multiple">
                                         <s:iterator var="no" value="noWonRights">
                                             <option value='<s:property value="#no.id"/>'>
                                                 <s:property value="#no.rightName"/>
                                             </option>
                                         </s:iterator>
                                     </select>
                               </td>
                           </tr>
                       </table>
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
