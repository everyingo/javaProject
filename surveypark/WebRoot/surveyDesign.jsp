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
         <s:set var="sId" value="id"/>
         <table width="100%" cellspacing="1" border="1" bordercolor="#ccc">
               <tr>
                   <td colspan="2" class="nullspan"></td>
               </tr>
               <tr>
                   <td colspan="2" class="design">设计调查</td>
               </tr>
               <tr>
                   <td colspan="2" class="nullspan"></td>
               </tr>
               <tr id="survey" style="border: 0px; border-style: none;">
                   <td class="survey_title">
                       <s:if test="isLogoPhotoExists()">
                           <img width="60px" height="35" alt="" src='<s:property value="logoPhotoPath"/>'>
                       </s:if>
                       <!-- 调查标题 -->
                       <s:property value="title"/>   
                   </td>
                   <td class="survey_controller"> 
                      <li>
                        <s:a action="SurveyAction-toAddLogoPage?sid=%{sId}" namespace="/">增加Logo</s:a>
                      </li>
                      <li>
                        <s:a action="SurveyAction-editSurvey?sid=%{sId}" namespace="/">编辑调查</s:a>
                      </li>
                      <li>
                        <s:a action="PageAction-toAddPage?sid=%{sId}" namespace="/">增加页</s:a>
                      </li>
                   </td>
               </tr>
               <tr>
                   <td colspan="2">
                       <!-- 1 -->
                       <table cellspacing="0" border="0" bordercolor="#ccc" width="100%">
                          <tr style="border: 0px; border-style: none;">
                              <td width="3%"></td>
                              <td width="97%">
                                   <!--Page 列表  2-->
                                   <table class="survey_page"  cellspacing="1" border="1" bordercolor="#ccc" width="100%">
                                   <!-- 迭代页面集合 start-->
                                   <s:iterator value="pages" var="p">
                                   <s:set var="pId" value="#p.id"/>
                                   <tr class="page_header">
                                      <td class="survey_title" width="20%">
                                       <!--#页标题  -->
                                       <s:property value="#p.title"/>
                                      </td>
                                      <td width="80%">
                                        <li>
                        					<s:a action="PageAction-toEditPage?sid=%{sId}&pid=%{#p.id}" namespace="/">编辑页标题</s:a>
                      					</li>
                      					<li>
                        					<s:a action="MoveOrCopyPageAction-toMoveOrCopyPage?srcPid=%{#p.id}" namespace="/">移动复制页</s:a>
                      					</li>
                      					<li>
                        					<s:a action="QuestionAction-toSelectQuestionType?sid=%{sId}&pid=%{#p.id}" namespace="/">增加问题</s:a>
                      					</li>
                      					<li>
                        					<s:a action="PageAction-deletePage?pid=%{#p.id}&sid=%{sId}" namespace="/">删除页</s:a>
                      					</li>
                                      </td>
                                   </tr>
                                   <tr>
                                      <td colspan="2">
                                           <!-- 3 -->
                                           <table width="100%" cellspacing="1" border="1" bordercolor="#ccc" width="100%">
                                                <tr>
                                                       <!-- xx -->
                                                       <td width="3%"></td>
                                                       <td width="97%">
                                                            <!-- 4 问题-->
                                                            <table width="100%" cellspacing="1" border="1" bordercolor="#ccc" width="100%">
                                                            <!-- 迭代问题集合 start -->
                                                            <s:iterator var="q" value="#p.questions">
                                                            <s:set var="qId" value="#p.id"/>
                                                            <tr style="background: #CCC">
                                                                <td width="20%">
                                                                   <!-- 问题的标题？ -->
                                                                   <s:property value="#q.title"/>
                                                                </td>
                                                         		<td width="80%">
                                                           			<li>
                        												<s:a action="QuestionAction-toEditQuestionPage?qid=%{#q.id}&pid=%{pId}&sid=%{sId}" namespace="/">编辑问题</s:a>
                      												</li>
                      												<li>
                        												<s:a action="QuestionAction-deleteQuestion?qid=%{#q.id}&sid=%{sId}" namespace="/">删除问题</s:a>
                      												</li>
                                                         		</td>
                                                        	</tr>
                                                     		<tr>
                                                    		   <td colspan="2">
                                                        		   <!-- 问题选项内容 start -->
                                                        		     
                                                        		     <!--定义变量 设置第一大类的题型  -->
                                                        		     <s:set var="qt" value="#q.questionType"/>
                                                        		     
                                                        		     <!-- 判断题型 是否属于第一大类{0,1,2,3} start -->
                                                        		     <s:if test="#qt lt 4">
                                                        		            <s:iterator value="#q.optionArr">
                                                        		                <input type='<s:property value="#qt<2?'radio':'checkbox' "/>'/><s:property/>
                                                        		                <s:if test="#qt==1 || #qt==3"><br></s:if>
                                                        		            </s:iterator>    
                                                        		                <!--  -->
                                                        		                <s:if test="#q.other">
                                                        		                   <input type='<s:property value="#qt<2?'radio':'checkbox' "/>'/>其他
                                                        		                   <!-- 文本 -->
                                                        		                   <s:if test="#q.otherStyle==1">
                                                        		                       <input type="text"/>
                                                        		                   </s:if>
                                                        		                   <!-- 下拉 -->
                                                        		                   <s:elseif test="#q.otherStyle==2">
                                                        		                       <select>
                                                        		                           <s:iterator value="#q.otherSelectOptionArr">
                                                        		                             <option>
                                                        		                                <s:property/>
                                                        		                             </option>
                                                        		                           </s:iterator>
                                                        		                       </select>
                                                        		                   </s:elseif>
                                                        		                </s:if>
                                                        		            
                                                        		     </s:if>
                                                        		     <!-- 判断题型 是否属于第一大类{0,1,2,3} end -->
                                                        		     <!-- 判断题型 是否属于第二大类{4,5}  start-->
                                                        		     <!-- 下拉列表 -->
                                                        		     <s:if test="#qt==4">
                                                        		          <select>
                                                        		                <s:iterator value="#q.optionArr">
                                                        		                 <option>
                                                        		                       <s:property/>
                                                        		                 </option>
                                                        		                 </s:iterator>
                                                        		           </select>
                                                        		     </s:if>
                                                        		     <!-- 文本 -->
                                                        		     <s:if test="#qt==5">
                                                        		          <input type="text"/>
                                                        		     </s:if>
                                                        		     <!-- 判断题型 是否属于第二大类{4,5}  start-->
                                                        		     <!-- 判断题型(矩阵) 是否属于第二大类{6,7,8}  start-->
                                                        		     <s:if test="#qt>5">
                                                        		         <!-- 矩阵表格 -->
                                                        		         <table width="100%" cellspacing="1" border="1" bordercolor="#ccc" width="100%">
                                                        		              <tr>
                                                        		                   <td></td>
                                                        		                   <s:iterator value="#q.matriColTitleArr">
                                                        		                   <td><s:property/></td>
                                                        		                   </s:iterator>
                                                        		              </tr>
                                                        		              <s:iterator value="#q.matrixRowTitleArr">
                                                        		                  <tr>
                                                        		                      <td><s:property/></td>
                                                        		                      <s:iterator value="#q.matriColTitleArr">
                                                        		                      <td>
                                                        		                           <s:if test="#qt==6">
                                                        		                              <input type="radio"/>
                                                        		                           </s:if>
                                                        		                           <s:if test="#qt==7">
                                                        		                              <input type="checkbox"/>
                                                        		                           </s:if>
                                                        		                           <s:if test="#qt==8">
                                                        		                                 <select>
                                                        		                                    <s:iterator value="#q.matriSelectOptionArr">
                                                        		                                         <option><s:property/></option>
                                                        		                                    </s:iterator>
                                                        		                                 </select>
                                                        		                           </s:if>
                                                        		                      </td>
                                                        		                      </s:iterator>
                                                        		                  </tr>
                                                        		              </s:iterator>
                                                        		         </table>
                                                        		     </s:if>
                                                        		     <!-- 判断题型 (矩阵)是否属于第二大类{6,7,8}  end-->
                                                         		   <!-- 问题选项内容 end   -->  
                                                       		   </td>
                                              				</tr>
                                              				</s:iterator>
                                              				<!-- 迭代问题集合 end -->
                                           					</table>
                                           					<!-- 4 -->
                                      					</td>
                                                       <!-- xx -->
                                                </tr>
                                           </table>
                                           <!-- 3 -->
                                      </td>
                                   </tr>
                                   </s:iterator>
                                   <!-- 迭代页面集合 end-->
                                   </table>
                                   <!-- 2 -->
                              </td>
                          </tr>
                       </table>
                       <!-- 1 -->  
                   </td>
               </tr>
         </table>
  </body>
</html>
