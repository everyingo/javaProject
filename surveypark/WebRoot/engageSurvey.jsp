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
                  <s:form action="EngageSurveyAction-doEngageSurvey" method="post">
                  <s:hidden name="currPid" value="%{currPage.id}"/>
                       <table cellspacing="0" border="0" bordercolor="#ccc" width="100%">
                          <tr><td colspan="2" style="height: 2px;"></td></tr>
                          <tr><td colspan="2" style="text-align: left;background: #ccc;height: 25px; padding-left: 20px;"><s:property value="#session.session_survey.title"/></td></tr>
                          <tr><td colspan="2" style="height: 2px;"></td></tr>
                          <tr style="border: 0px; border-style: none;">
                              <td width="3%"></td>
                              <td width="97%">
                                   <!--Page 列表  2-->
                                   <table class="survey_page"  cellspacing="1" border="1" bordercolor="#ccc" width="100%">
                                   <!-- 迭代页面集合 start-->
                                   <tr class="page_header">
                                      <td class="survey_title" colspan="2">
                                       <!--#页标题  -->
                                       <s:property value="currPage.title"/>
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
                                                            <s:iterator var="q" value="currPage.questions">
                                                            <s:set var="qId" value="#p.id"/>
                                                            <tr style="background: #CCC">
                                                                <td width="20%" colspan="2">
                                                                   <!-- 问题的标题？ -->
                                                                   <s:property value="#q.title"/>
                                                                </td>
                                                        	</tr>
                                                     		<tr>
                                                    		   <td colspan="2">
                                                        		   <!-- 问题选项内容 start -->
                                                        		     
                                                        		     <!--定义变量 设置第一大类的题型  -->
                                                        		     <s:set var="qt" value="#q.questionType"/>
                                                        		     
                                                        		     <!-- 判断题型 是否属于第一大类{0,1,2,3} start -->
                                                        		     <s:if test="#qt lt 4">
                                                        		            <s:iterator value="#q.optionArr" status="st">
                                                        		                <input type='<s:property value="#qt<2?'radio':'checkbox' "/>' name='q<s:property value="#q.id"/>' value='<s:property value="#st.index"/>'  <s:property value='setTag("q"+#q.id,#st.index,"checked")'/> /><s:property/>
                                                        		                <s:if test="#qt==1 || #qt==3"><br></s:if>
                                                        		            </s:iterator>    
                                                        		                <!--  -->
                                                        		                <s:if test="#q.other">
                                                        		                   <input type='<s:property value="#qt<2?'radio':'checkbox' "/>' name="q<s:property value='#q.id'/>" value="other"  <s:property value='setTag("q"+#q.id,"other","checked")'/> />其他
                                                        		                   <!-- 文本 -->
                                                        		                   <s:if test="#q.otherStyle==1">
                                                        		                       <input type="text" name="q<s:property value='#q.id'/>other" <s:property value='setText("q"+#q.id+"other")'/> />
                                                        		                   </s:if>
                                                        		                   <!-- 下拉 -->
                                                        		                   <s:elseif test="#q.otherStyle==2">
                                                        		                       <select name="q<s:property value='#q.id'/>other">
                                                        		                           <s:iterator value="#q.otherSelectOptionArr" status="optst">
                                                        		                             <option value="<s:property value='#optst.index'/>" <s:property value='setTag("q"+#q.id+"other",#optst.index,"selected")'/>>
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
                                                        		          <select name="q<s:property value='#q.id'/>">
                                                        		                <s:iterator value="#q.optionArr" status="optst">
                                                        		                 <option value="<s:property value='#optst.index'/>" <s:property value='setTag("q"+#q.id,#optst.index,"selected")'/>>
                                                        		                       <s:property/>
                                                        		                 </option>
                                                        		                 </s:iterator>
                                                        		           </select>
                                                        		     </s:if>
                                                        		     <!-- 文本 -->
                                                        		     <s:if test="#qt==5">
                                                        		          <input type="text" name="q<s:property value='#q.id'/>" <s:property value='setText("q"+#q.id)'/> />
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
                                                        		              <s:iterator value="#q.matrixRowTitleArr" status="row">
                                                        		                  <tr>
                                                        		                      <td><s:property/></td>
                                                        		                      <s:iterator value="#q.matriColTitleArr" status="col">
                                                        		                      <td>
                                                        		                           <s:if test="#qt==6">
                                                        		                              <input type="radio" name="q<s:property value='#q.id'/>_<s:property value='#row.index'/>" value="<s:property value='#row.index'/>_<s:property value='#col.index'/>" <s:property value='setTag("q"+#q.id+"_"+#row.index,#row.index+"_"+#col.index,"checked")'/>/>
                                                        		                           </s:if>
                                                        		                           <s:if test="#qt==7">
                                                        		                              <input type="checkbox" name="q<s:property value='#q.id'/>" value="<s:property value='#row.index'/>_<s:property value='#col.index'/>" <s:property value='setTag("q"+#q.id,#row.index+"_"+#col.index,"checked")'/>/>
                                                        		                           </s:if>
                                                        		                           <s:if test="#qt==8">
                                                        		                                 <select name="q<s:property value='#q.id'/>">
                                                        		                                    <s:iterator value="#q.matriSelectOptionArr" status="optst">
                                                        		                                         <option value="<s:property value='#row.index'/>_<s:property value='#col.index'/>_<s:property value='#optst.index'/>" <s:property value='setTag("q"+#q.id,#row.index+"_"+#col.index+"_"+#optst.index,"selected")'/>>
                                                        		                                           <s:property/>
                                                        		                                         </option>
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
                                   <!-- 迭代页面集合 end-->
                                   </table>
                                   <!-- 2 -->
                              </td>
                          </tr>
                          <!-- 分页操作 -->
                          <tr>
                             <td colspan="2" style="text-align: center;background: #ccc; height: 25px;">
                                  <!-- 上一页 -->
                                  <s:if test="#session.session_survey.minOrderno!=currPage.orderno">
                                       <input type="submit" name="submit_pre" value="<s:property value='#session.session_survey.preText'/>"/>
                                  </s:if>
                                  <!-- 下一页 -->
                                  <s:if test="#session.session_survey.maxOrderno!=currPage.orderno">
                                      <input type="submit" name="submit_next" value="<s:property value='#session.session_survey.nextText'/>"/>
                                  </s:if>
                                  <!-- 提交 -->
                                  <s:if test="#session.session_survey.maxOrderno==currPage.orderno">
                                      <input type="submit" name="submit_done" value="<s:property value='#session.session_survey.doneText'/>"/>
                                  </s:if>
                                  <!-- 退出 -->
                                      <input type="submit" name="submit_exit" value="<s:property value='#session.session_survey.exitText'/>"/>
                             </td>
                          </tr>
                       </table>
                       </s:form>
                       <!-- 1 -->  
                  
  </body>
</html>
