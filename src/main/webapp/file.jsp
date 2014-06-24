<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript">
	function su(){
		var file = $('#file').val();
		if(!file){
			alert('文件为空！');
			return;
		}
		
		var option = {
			beforeSubmit : function(formData,jqForm,options){
				alert(JSON.stringify(formData));
			},
			success : function(data, status, xhr){
				alert(data);
// 				alert(status);
// 				alert(xhr);
			}
		};
		$('#form').ajaxSubmit(option);
		
// 		$('#form').submit();
	}
</script>
</head>
<body>
	<form id="form" method="post" action="servlet/MultipartServlet" enctype="multipart/form-data">
		
		<input type="text" name="name" value="123" id="name" />
		<input type="file" name="file" id="file" />
		<input type="button" value="提交" onclick="su();" />
	</form>
	
<!-- 	<form id="form" method="post" action="servlet/MultipartServlet" target="uploadframe"  -->
<!-- 		enctype="multipart/form-data"> -->
		
<!-- 		<iframe style="display: none" name="uploadframe"></iframe> -->
<!-- 		<input type="file" name="file" id="file" /> -->
<!-- 		<input type="button" value="提交" onclick="su();" /> -->
<!-- 	</form> -->

</body>
</html>