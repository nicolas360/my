<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iframe长连接</title>

<script type="text/javascript">
	function msg(m) {
// 		alert("msg: "+m);
		alert(m.name)
	}
</script>
</head>
<body>

	<form action="/my/servlet/longConnectServlet" target="my">
		<input type="submit" />
	</form>

	<iframe style="display: none;" name="my" id="my"></iframe>

</body>
</html>