

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBMgr {
	DBCon conn;
	public DBMgr() {
		conn = new DBCon();
	}
	
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static String sql = "";
	
	public ArrayList<Bean>update() {
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public ArrayList<Bean> jo3() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Bean> jo5() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Bean> jo6() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public ArrayList<Bean> jo7() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public ArrayList<Bean> jo4() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Bean> jo1() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public ArrayList<Bean> jo2() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public ArrayList<Bean> jo10() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				bean.setH(rs.getString(8));
				bean.setI(rs.getString(9));
				bean.setJ(rs.getString(10));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public ArrayList<Bean> jo9() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				bean.setH(rs.getString(8));
				bean.setI(rs.getString(9));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public ArrayList<Bean> jo8() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				bean.setH(rs.getString(8));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public ArrayList<Bean> jo11() {
		// TODO Auto-generated method stub
		ArrayList<Bean>list = new ArrayList<Bean>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				bean.setH(rs.getString(8));
				bean.setI(rs.getString(9));
				bean.setJ(rs.getString(10));
				bean.setK(rs.getString(11));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
}
