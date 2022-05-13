package com.pro.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pro.DAO;
import com.pro.vo.MemberVO;

public class MemberDAO extends DAO {
	
	public void addComment (MemberVO vo) {
		conn = getConnect();
		String sql = "INSERT INTO info_cmt (\n"+
				"    usr_id,\n"+
				"    cm_code,\n"+
				"    cm_cont,\n"+
				"    cm_date,\n"+
				"    cm_stars\n"+
				") VALUES (\n"+
				"    ?,\n"+
				"    'cm'||cm_code_seq.nextval,\n"+
				"    ?,\n"+
				"    sysdate + 3,\n"+
				"    ?\n"+
				")";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getCmCode());
			psmt.setString(3, vo.getCmCont());
			psmt.setInt(4, vo.getCmStars());
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
	
	public List<MemberVO> listComment() {
		conn = getConnect();
		String sql = "select * from info_cmt order by sysdate";
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("usr_id"));
				vo.setCmCont(rs.getString("cm_cont"));
				vo.setCmStars(rs.getInt("cm_stars"));
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
