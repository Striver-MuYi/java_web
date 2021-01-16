package com.mk;
import java.sql.*;
public class JDBCUtil {
	public static Connection conn;
	public static PreparedStatement pr;
	public static ResultSet rs;

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/goods_manager?useUnicode=true&characterEncoding=utf8&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	
	public static void closeAll(ResultSet rs,PreparedStatement pr,Connection conn){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(pr!=null){
			try{
				pr.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet executeQuery(String sql,Object...obj){
		try {
			conn=this.getConnection();
			pr=conn.prepareStatement(sql);	
			for(int i=0;i<obj.length;i++){
				pr.setObject(i+1,obj[i]);
			}
			rs=pr.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}
	
	
}
