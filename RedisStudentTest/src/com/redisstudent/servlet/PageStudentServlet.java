package com.redisstudent.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redisstudent.bean.GetPage;
import com.redisstudent.bean.Student;

import redis.clients.jedis.Jedis;
@WebServlet("/pageStudentServlet")
public class PageStudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
		 * Constructor of the object.
		 */
	public PageStudentServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		// 判断是不是第一页
		String s = request.getParameter("currentPage");
		int currentPage;
		if (s != null && !s.equals("")) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		System.out.println(currentPage);
		//按平均分降序排列
		Set<String> studentIds = jedis.zrevrange("avgscore", (currentPage - 1) * 10, currentPage * 10);
		System.out.println("studentid:"+studentIds.size());
		List<Student> students = new ArrayList<Student>();
		for (String id : studentIds) {
			Student student = new Student();
			try {
				student.fromMap(jedis.hgetAll("student:" + id));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			students.add(student);
		}
		//页对象
		GetPage page = new GetPage();
		//总条数
		int total = studentIds.size();
		System.out.println("total:"+total);
		//计算总页数
		if (total % 10 != 0) {
			total = total / 10 + 1;
			page.setTotal(total);
		} else {
			total = total / 10;
			page.setTotal(total);
		}
		//设置当前页
		page.setCurrentPage(currentPage);
		//计算是否还有下一页
		if (currentPage < total) {
			page.setHasNextPage(true);
			page.setNextPage(currentPage + 1);
		} else {
			page.setHasNextPage(false);
		}
		//计算是否还有上一页
		if (currentPage > 1) {
			page.setHasPrePage(true);
			page.setPrePage(currentPage - 1);
		} else {
			page.setHasPrePage(false);
		}
		request.setAttribute("students", students);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.getRequestDispatcher("jsp/ShowStudent.jsp").forward(request, response);
		jedis.close();
		out.flush();
		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doGet(request, response);
		out.flush();
		out.close();
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
