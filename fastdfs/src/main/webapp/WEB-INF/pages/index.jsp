<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>新巴巴运动网-后台首页</title>
<script src="/js/jquery.js" type="text/javascript"></script>
<script src="/js/jquery.ext.js" type="text/javascript"></script>
<script src="/js/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">
   function uploadPic(){
	   var options={
			  url:"/upload.do",
			  dataType:"json",
			  type:"post",
			  success:function(data){
				  var imgUrl=data.imgUrl;
				  //$("#allUrl").attr("src",imgUrl);
				  //$("#imgUrl").val(imgUrl);
			  }
	   };
	   //alert("aa");
       $("#jvForm").ajaxSubmit(options);
   }
</script>
</head>
新巴巴运动网-后台首页
<form id="jvForm" action="/upload.do" method="post"  enctype="multipart/form-data" >
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
						<input type="file" name="pic"/>
					</td>
				</tr>
				
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</html>
