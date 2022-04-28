package com.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addPersonServlet
 */
@WebServlet("/addPersonServlet")
public class addPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public addPersonServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("uid");
		String name = request.getParameter("uname");
		String score = request.getParameter("uscore");
		String age = request.getParameter("uage");
		
		System.out.println("���̵�: " + id);
		System.out.println("�̸�: " + name);
		System.out.println("����: " + score);
		System.out.println("����: " + age);
		
		//db insert/update/delete/select
		
		PrintWriter out = response.getWriter(); // InputStream, OutputStream, ReadStream
		out.println("<h3>������</h3>");
		out.println("<h4>ID:" + id + "</h4>");
		out.println("<h4>Name:" + name + "</h4>");
		out.println("<h4>Score:" + score + "</h4>");
		out.println("<h4>age:" + age + "</h4>");
		
	}

}
