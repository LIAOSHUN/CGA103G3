<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>login_success.jsp</title>
<meta http-equiv="refresh" content="5;url=<%=request.getContextPath()%>/backend/index.jsp"> 

</head>
<body>

	<br>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='orange' align='center' valign='middle' height='20'>
			 <td>   
				     <h3> 歡迎:<font color=red> ${account} </font>你不是小組長喔</h3>
			 </td>
		</tr>
	</table>
	<b> <br>
	<br>                以下留空....
	</b>
	
</body>
</html>
