package com.pro.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pro.DAO;
import com.pro.vo.MemberVO;

public class CommentDAO extends DAO {
	
	public void addComment (MemberVO vo) {
		conn = getConnect();
		String sql = "INSERT INTO info_cmt (\n"+
				"    usr_id,\n"+
				"    cm_code,\n"+
				"    cm_cont,\n"+
				"    cm_date,\n"+
				"    cm_stars,\n"+
				"    movie_id\n"+
				") VALUES (\n"+
				"    ?,\n"+
				"    'cm'||cm_code_seq.nextval,\n"+
				"    ?,\n"+
				"    sysdate,\n"+
				"    ?,\n"+
				"    ?\n"+
				")";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getCmCont());
			psmt.setInt(3, vo.getCmStars());
			psmt.setString(4, vo.getMovieId());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 수정");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
//	public void delComment(MemberVO vo) {
//		conn = getConnect();
//		
//		try {
//			psmt = conn.prepareStatement(sql);
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disConnect();
//		}
//	}
	
	public List<MemberVO> listComment(String id) {
		conn = getConnect();
		String sql = "select * from info_cmt where movie_id = ? order by cm_date desc";
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		
		try {			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("usr_id"));
				vo.setCmCont(rs.getString("cm_cont"));
				vo.setCmDate(rs.getString("cm_date"));
				vo.setCmStars(rs.getInt("cm_stars"));
				vo.setCmCode(rs.getString("cm_code"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return list;
	}

}
