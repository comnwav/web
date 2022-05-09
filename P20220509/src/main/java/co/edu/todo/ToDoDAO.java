package co.edu.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// 리스트.
	public List<ToDo> getList() {
		String sql = "select * from todo_list";
		
		getConnect();
		
		List<ToDo> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ToDo td = new ToDo(rs.getString("cont"), rs.getString("checked"));
				list.add(td);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return list;
	}

	public void getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void disConnect() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addEvent(ToDo td) {
		
	}

	public void delTodo(String val) {
		
	}

	public void checkTodo(String val) {
//		if (val.equals("c")) {
//			String sql = "update todo_list set checked = 'n' where = "; 
//		}
	}
	
	
}
