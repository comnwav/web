package co.dev.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	HashMap<String, Control> list = null;
	
	String charset = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		
		// HashMap<String keys, String values> or HashMap<String keys, integer values>
		// HashMap은 key/value 조합으로 key를 index로 이용한다.
		list = new HashMap<String, Control>();
		
		list.put("/memberInsert.do", new MemberInsertControl());
		list.put("/memberUpdate.do", new MemberUpdateControl());
		list.put("/memberList.do", new MemberListControl());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		String url = req.getRequestURI(); // /P20220503/memberInsert.do
		String context = req.getContextPath();
		String path = url.substring(context.length()); //

		Control exeCon = list.get(path);
		exeCon.execute(req, resp);
	}
}
