<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShowStudent.jsp' starting page</title>
    
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
    <p align="center"><a href="jsp/AddStudent.jsp">添加学生信息</a></p>
    <hr width="80%">
    <table border="1" align="center">
          <tr align="center">
               <td>学号</td>
               <td>姓名</td>
               <td>出生日期</td>
               <td>备注</td>
               <td>成绩</td>
               <td>修改数据</td>
               <td>删除数据</td>
           </tr>
          <c:forEach var="stu" items="${students}"  >
              <tr align="center">
                  <td>${stu.id}</td>
                  <td>${stu.name}</td>
                  <td>${stu.birthday}</td>
                  <td>${stu.description}</td>
                  <td>${stu.avgscore}</td>
                  <td><a href="updateStudent?id=${stu.id}" >修改</a></td>
                  <td><a href="deleteStudent?id=${stu.id}" >删除</a></td>
                  <td></td>
               </tr> 
           </c:forEach>
          </table>
         <p align="center">
			<c:if test="${page.hasPrePage}">
				<a href="pageStudentServlet?currentPage=1">首页</a>
				<a href="pageStudentServlet?currentPage=${page.prePage}">上一页</a>
			</c:if>
			<c:forEach begin="1" end="${page.total}" var="each">
				<c:choose>
					<c:when test="${each == page.currentPage}">
						<a style="color: black;">${each}</a>
					</c:when>
					<c:when
						test="${each >= (page.currentPage- 2) && each <= (page.currentPage + 2)}">
						  <a href="pageStudentServlet?currentPage=${each}">${each}</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${page.hasNextPage}">
				<a href="pageStudentServlet?currentPage=${page.nextPage}">下一页</a>
				<a href="pageStudentServlet?currentPage=${page.total}">尾页</a>
			</c:if>
	</p>
    
  </body>
</html>
