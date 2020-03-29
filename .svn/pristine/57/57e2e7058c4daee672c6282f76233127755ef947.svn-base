package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Person;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstServlet() {
		super();

	}

	@Override
	public void init() throws ServletException {
		System.out.println("servlet初始化……");
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<Html>");
		out.println("<head><title>第一个servlet</title></head>");
		out.println("<body>");
		out.println("实例xxx <br>");
		out.println(getClass());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		System.out.println("now xxx:" + now);
		out.println("当前时间(now):" + now);
		Person person =new Person();
		String personInfo = person.getInfo();
		out.println("<br>personInfo:" + personInfo);
		out.println("</body>");
		out.println("</Html>");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("servlet销毁！");
		super.destroy();
	}
}
