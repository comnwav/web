package co.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edu/info.do")
public class Info extends HttpServlet {
	
	// Info ������ ��û -> Init() -> Service() -> Destroy()
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		out.print("<h3>���� ������</h3>");
		out.print("<h3>�ȳ��ϼ���</h3>");
		out.print("<a href=\"../index.html\">index</a>");
		
		out.print("<table border=1>\r\n"
				+ "    <thead>\r\n"
				+ "        <tr>\r\n"
				+ "            <th>�̸�</th>\r\n"
				+ "            <th>��å</th>\r\n"
				+ "        </tr>\r\n"
				+ "    </thead>\r\n"
				+ "    <tbody>\r\n"
				+ "        <tr>\r\n"
				+ "            <td>�ֱԿ�</td>\r\n"
				+ "            <td>ū ������</td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "            <td>������</td>\r\n"
				+ "            <td>���� ������</td>\r\n"
				+ "        </tr>\r\n"
				+ "    </tbody>\r\n"
				+ "</table>");
	}
	
}
