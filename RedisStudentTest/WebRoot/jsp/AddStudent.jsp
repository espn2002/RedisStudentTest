<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AddStudent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <form action="addStudent" method="get">
       <table align="center">
           <tr>
              <td>请输入学生学号:</td>
              <td><input type="text" name="id"></td>
           </tr>
           <tr>
              <td>请输入学生姓名:</td>
              <td><input type="text" name="name"></td>
           </tr>
           <tr>
              <td>请输入学生出生日期:</td>
              <td><input type="text" name="birthday"></td>
           </tr>
           <tr>
              <td>请输入备注信息：</td>
              <td><textarea rows="5" cols="10" name="description"></textarea></td>
           </tr>
           <tr>
              <td>请输入成绩：</td>
              <td><input type="text" name="avgscore"></td>
           </tr>
           <tr>
              <td><input type="submit" value="提交"></td>
           </tr>
       </table>
    </form>
  </body>
</html>
