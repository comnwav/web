package com.pro.service;

import java.util.List;

import com.pro.dao.CommentDAO;
import com.pro.vo.MemberVO;

public class CommentService {
	CommentDAO dao = new CommentDAO(); // db CRUD
	
	public void addComment(MemberVO member) {
		dao.addComment(member);
	}
	
	public List<MemberVO> listComment(String id) {
		return dao.listComment(id);
	}
}
