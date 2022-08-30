<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myCoupon_home.jsp</title>
</head>
<body>

<input>
<div>
<FORM METHOD="post" ACTION="coupontype.do" >
        <b>輸入會員編號 (如:11001):</b>
        <input type="text" name="memID">
        <input type="hidden" name="action" value="getMyCoup">
        <input type="submit" value="送出">
    </FORM>
</div>

</body>
</html>