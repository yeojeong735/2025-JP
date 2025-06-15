package inclass2.inclass2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;



public class StudentManager extends JFrame{
	Scanner sc = new Scanner(System.in);
	Connection conn;
	JButton btnAdd, btnSearch, btnExellence, btnMajor,btnUpdate;
	JTextField tfName, tfID, tfMajor, tfScore;
	JTextArea ta;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public StudentManager() {
		this.setTitle("Student Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CreateGUI();
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dbAdd();
				
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dbSearch();
				
			}
		});
		btnExellence.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dbExellence();
			}
		});
		btnMajor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dbmajor();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dbUpdate();
			}
		});
		this.setSize(300,600);
		this.setVisible(true);
		
	}
	private void CreateGUI() {
		// TODO Auto-generated method stub
		this.setLayout(new FlowLayout());
		this.add(new JLabel("이름"));
		tfName = new JTextField(7);
		this.add(tfName);
		
		this.add(new JLabel("학번"));
		tfID = new JTextField(10);
		this.add(tfID);
		
		this.add(new JLabel("전공"));
		tfMajor = new JTextField(10);
		this.add(tfMajor);
		
		this.add(new JLabel("점수"));
		tfScore = new JTextField(10);
		this.add(tfScore);
		
		ta = new JTextArea(15,20);
		this.add(ta);
		
		btnAdd = new JButton("학생 추가");
		this.add(btnAdd);
		btnSearch = new JButton("전체 조회");
		this.add(btnSearch);
		btnExellence = new JButton("우수 학생 조회");
		this.add(btnExellence);
		btnMajor = new JButton("전공별 학생 조회");
		this.add(btnMajor);	
		btnUpdate = new JButton("수정");
		this.add(btnUpdate);
	}
	public void dbmajor() {
		try {
			conn = DBConn.dbConnection();
			String sql = "select * from student where major = ?;";
			String in_major = tfMajor.getText().toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, in_major);
			
			rs = pstmt.executeQuery();
			
			String line = "";
			ta.setText("     name            id             major          score\n");
			ta.append("------------------------------------------------------------\n");
			while(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String major = rs.getString("major");
				String score = rs.getString("score");
				line = "|"+name+"|"+id+"|"+major+"|"+score+"\n";
				System.out.println("rs => " + line);
				ta.append(line);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void dbExellence() {
		
		try {
			conn = DBConn.dbConnection();
			String sql = "select * from student where score >85";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String line = "";
			ta.setText("     name            id             major          score\n");
			ta.append("------------------------------------------------------------\n");
			while(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String major = rs.getString("major");
				String score = rs.getString("score");
				line = "|"+name+"|"+id+"|"+major+"|"+score+"\n";
				System.out.println("rs => " + line);
				ta.append(line);
			}
			pstmt.close();
			conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	}
	public void dbUpdate() {
		try {
			conn = DBConn.dbConnection();
			String sql = "update student set name = ?, major = ?, score = ? where id = ?;";
			String in_name = tfName.getText().toString();
			String in_id = tfID.getText().toString();
			String in_maj = tfMajor.getText().toString();
			String in_sco = tfScore.getText().toString();
			pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, in_name);
			pstmt.setString(2, in_maj);
			pstmt.setString(3, in_sco);
			pstmt.setString(4, in_id);
			
			pstmt.executeUpdate();
			System.out.println(in_name + " 수정 완료");
			
			tfID.setText("");
			tfName.setText("");
			tfMajor.setText("");
			tfScore.setText("");
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("수정 에러");
		}
	}
	
	public void dbAdd() {
		
		try {
			conn = DBConn.dbConnection();
			String in_name = tfName.getText().toString();
			String in_id = tfID.getText().toString();
			String in_maj = tfMajor.getText().toString();
			String in_sco = tfScore.getText().toString();
			String sql = "insert into student(name, id, major, score) values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, in_name);
			pstmt.setString(2, in_id);
			pstmt.setString(3, in_maj);
			pstmt.setString(4, in_sco);
			
			pstmt.executeUpdate();
			System.out.println(in_name + "입력 완료");
			
			tfID.setText("");
			tfName.setText("");
			tfMajor.setText("");
			tfScore.setText("");
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("입력 에러");
		}
		
	}
	
	public void dbSearch() {
		
		try {
			conn = DBConn.dbConnection();
			String sql = "select * from student";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			String line = "";
			ta.setText("     name            id             major          score\n");
			ta.append("------------------------------------------------------------\n");
			while(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String major = rs.getString("major");
				String score = rs.getString("score");
				line = "|"+name+"|"+id+"|"+major+"|"+score+"\n";
				System.out.println("rs => " + line);
				ta.append(line);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentManager();
	}

}
