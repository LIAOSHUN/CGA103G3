<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@include file="/frontend/frontendhead.jsp"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��ƭק� - update_member_input.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>���u��ƭק� - update_member_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~���C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>�|���s��:<font color=red><b>*</b></font></td>
				<td><%=memberVO.getMemID()%></td>
			</tr>
			<jsp:useBean id="membergradeSvc" scope="page"
				class="com.membergrade.model.MemberGradeService" />
			<tr>
				<td>�|������:<font color=red><b>*</b></font></td>
				<td><select size="1" name="gradeID">
						<c:forEach var="membergradeVO" items="${membergradeSvc.all}">
							<option value="${membergradeVO.gradeID}"
								${(membergradeVO.gradeID==membergradeVO.gradeID)?'selected':'' }>${membergradeVO.gradeName}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>�|���m�W:</td>
				<td><input type="TEXT" name="memName" size="45"
					value="<%=memberVO.getMemName()%>" /></td>
			</tr>
			<tr>
				<td>�ϥΪ̦W��:</td>
				<td><input type="TEXT" name="memAccount" size="45"
					value="<%=memberVO.getMemAccount()%>" /></td>
			</tr>
			<tr>
				<td>�K�X:</td>
				<td><input type="TEXT" name="memPassWord" size="45"
					value="<%=memberVO.getMemPassWord()%>" /></td>
			</tr>

			<tr>
				<td>�T�{�K�X:</td>
				<td><input type="password" name="PWD2" size="45" /></td>
			</tr>
			<tr>
				<td>�ʧO:</td>
				<td><input type="TEXT" name="memGender" size="45"
					value="<%=memberVO.getMemGender()%>" /></td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="memPh" size="45"
					value="<%=memberVO.getMemPh()%>" /></td>
			</tr>
			<tr>
				<td>�H�c:</td>
				<td><input type="TEXT" name="memEmail" size="45"
					value="<%=memberVO.getMemEmail()%>" /></td>
			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input type="TEXT" name="memAddress" size="45"
					value="<%=memberVO.getMemAddress()%>" /></td>
			</tr>
			<tr>
				<td>�ͤ�:</td>
				<td><input name="memBirthday" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>�Ш|�~���ҩ�:</td>
				<td><input type="file" name="memCard" size="45"
					value="<%=memberVO.getMemCard()%>" /></td>
			</tr>
			<tr>
				<td>�H�W�O�I:</td>
				<td><input type="TEXT" name="memVio" size="45"
					value="<%=memberVO.getMemVio()%>" /></td>
			</tr>
			<tr>
				<td>�|�����A:</td>
				<td><input type="TEXT" name="memStatus" size="45"
					value="<%=memberVO.getMemStatus()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="memID" value="<%=memberVO.getMemID()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=memberVO.getMemBirthday()%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>
<%@include file="/frontend/frontendfoot.jsp"%>