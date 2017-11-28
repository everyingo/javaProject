<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page pb15">
	<span class="r inb_a page_b">
        <c:if test="${!page.isFirstPage}"><a href="${pageUrl}&pageNo=${page.firstPage}"><font size="2">首页</font></a></c:if>	
	    <c:if test="${page.hasPreviousPage }"><a href="${pageUrl}&pageNo=${page.prePage}"><font size="2">上一页</font></a></c:if>
	    <c:forEach var="num" items="${page.navigatepageNums}">
	       <c:if test="${num==page.pageNum }">
	         <strong>${num}</strong>
	       </c:if>
	       <c:if test="${num!=page.pageNum }">
	          <a href="${pageUrl}&pageNo=${num}">${num}</a>
	       </c:if>
		</c:forEach>
		<c:if test="${page.hasNextPage }"><a href="${pageUrl}&pageNo=${page.nextPage}"><font size="2">下一页</font></a></c:if>
		<c:if test="${!page.isLastPage }"><a href="${pageUrl}&pageNo=${page.lastPage}"><font size="2">末页</font></a></c:if>
		&nbsp;&nbsp;&nbsp;共<var>${page.pages}</var>页 到第<input type="text" size="3" id="PAGENO"/>页 <input type="button" onclick="javascript:window.location.href = '${pageUrl}&pageNo=' + $('#PAGENO').val() " value="确定" class="hand btn60x20" id="skip"/>
	</span>
</div>