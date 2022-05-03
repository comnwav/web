package co.dev.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.dev.DAO;
import co.dev.vo.MemberVO;

public class MemberDAO extends DAO {
	public void insertMember(MemberVO member) {
		conn = getConnect();
		String sql = "insert into member(id, name, pwd, email) values(?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getName());
			psmt.setString(3, member.getPwd());
			psmt.setString(4, member.getEmail());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력");	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public List<MemberVO> listMember() {
		conn = getConnect();
		String sql = "select * from member order by id";
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setEmail(rs.getString("email"));
				vo.setName(rs.getString("name"));
				vo.setPwd(rs.getString("pwd"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
}
