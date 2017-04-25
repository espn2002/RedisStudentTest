package com.redisstudent.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redisstudent.bean.Student;

import redis.clients.jedis.Jedis;
@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
		 * Constructor of the object.
		 */
	public AddStudent() {
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Jedis jedis=new Jedis("127.0.0.1",6379);
		Student stu=new Student();
		long id;
		stu.setName(request.getParameter("name"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stu.setBirthday(sdf.parse(request.getParameter("birthday")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stu.setDescription(request.getParameter("description"));
		Integer avgscore=Integer.parseInt(request.getParameter("avgscore"));
		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			id = System.currentTimeMillis();			
			jedis.rpush("studentid", id+"");// 这条数据存储所有的id
		} else {
			id = Long.parseLong(request.getParameter("id"));
		}
		stu.setId(id);
		stu.setAvgscore(avgscore);
		System.out.println(stu);
		//使用zadd方法，按平均分降序排序
		jedis.zadd("avgscore", avgscore, id+"");
		Map<String, String> map = stu.toMap();
		System.out.println(map);
		jedis.hmset("student:" + id+"", map);
		response.sendRedirect("pageStudentServlet");
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
