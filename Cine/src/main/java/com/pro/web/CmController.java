package com.pro.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	HashMap<String, Control> list;

	String charset;

	public CmController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		charset = config.getInitParameter("charset");

		list = new HashMap<String, Control>();

		list.put("/commentAdd.do", new CmAddControl());
		list.put("/commnetList.do", new CmListControl());
		list.put("/commentDel.do", new CmDelControl());
		list.put("/commnetPage.do", new CmPageControl());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding(charset);

		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String path = uri.substring(context.length());

		Control exeCon = list.get(path);
		exeCon.execute(req, resp);

	}

}
