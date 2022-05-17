package com.pro.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pro.service.CommentService;
import com.pro.vo.MemberVO;

public class CmListControl implements Control {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("movieId");

		CommentService service = new CommentService();
		List<MemberVO> list = service.listComment(id);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		JSONArray ary = new JSONArray();

		for (MemberVO element : list) {
			
			JSONObject jo = new JSONObject();
			jo.put("id", element.getId());
			jo.put("cmt", element.getCmCont());
			jo.put("stars", element.getCmStars());
			jo.put("date", element.getCmDate().substring(2, 10));
			jo.put("movie_id", element.getMovieId());
			jo.put("code", element.getCmCode());
			
			ary.put(jo);
		}
		
		System.out.println(ary);

		out.print(ary);
		out.close();

	}

}
