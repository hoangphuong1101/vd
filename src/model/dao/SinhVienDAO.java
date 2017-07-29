package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Pagination;
import model.bean.SinhVien;


/**
 * SinhVienDAO.java
 *
 * Version 1.0
 *
 * Date: Jan 19, 2015
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jan 19, 2015        	DaiLV2          Create
 */

public class SinhVienDAO {
	String url = "jdbc:sqlserver://localhost:1433;databaseName=JavaEE_Example";
	String userName = "sa";
	String password = "123";
	Connection connection;
	
	void connect(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SinhVien> getListSinhVien() {
		connect();
		String sql=	"SELECT sv.msv, sv.HoTen, sv.GioiTinh, k.TenKhoa"+
					" FROM   SinhVien sv INNER JOIN Khoa AS k ON k.MaKhoa = sv.MaKhoa";
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		SinhVien sinhVien;
		try {
			while(rs.next()){
				sinhVien = new SinhVien();
				sinhVien.setMsv(rs.getString("msv"));
				sinhVien.setHoTen(rs.getString("HoTen"));
				sinhVien.setGioiTinh(rs.getString("GioiTinh"));
				sinhVien.setTenKhoa(rs.getString("TenKhoa"));
				list.add(sinhVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SinhVien> getListSinhVien(String maKhoa) {
		connect();
		String sql=	String.format("SELECT sv.msv, sv.HoTen, sv.GioiTinh, k.TenKhoa"+
					" FROM   SinhVien sv INNER JOIN Khoa AS k ON k.MaKhoa = sv.MaKhoa"+
					" WHERE sv.MaKhoa = '%s'", maKhoa);
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		SinhVien sinhVien;
		try {
			while(rs.next()){
				sinhVien = new SinhVien();
				sinhVien.setMsv(rs.getString("msv"));
				sinhVien.setHoTen(rs.getString("HoTen"));
				sinhVien.setGioiTinh(rs.getString("GioiTinh"));
				sinhVien.setTenKhoa(rs.getString("TenKhoa"));
				list.add(sinhVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<SinhVien> getListSinhVienByKeyWord(String tuKhoa, int page) {
		connect();

		int totalItem = 0;
		int itemPerPage = 2;
		int totalPage = 0;
		int offset = page * itemPerPage;
		int feetchnext = page * itemPerPage + itemPerPage;


		String sql0=	"SELECT count(sv.msv) as TotalItem"+
					" FROM   SinhVien sv INNER JOIN Khoa AS k ON k.MaKhoa = sv.MaKhoa"+
					" WHERE sv.HoTen LIKE '%" + tuKhoa + "%'";
		ResultSet rs0 = null;
		try {
			Statement stmt0 = connection.createStatement();
			rs0 = stmt0.executeQuery(sql0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(rs0.next()){
				totalItem = rs0.getInt("TotalItem");
				totalPage = (int) Math.ceil(totalItem * 1.0 / itemPerPage);
				Pagination.page = page;
				Pagination.totalPage = totalPage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		System.out.println("Page: " + page + ", Total page: " + totalPage + ", Total item: " + totalItem
				+ ", Offset: " + offset + ", Feetch next: " + feetchnext);
		
		
		String sql=	""
				+ "SELECT * FROM"
				+ " ("
				+ " SELECT ROW_NUMBER() OVER ( ORDER BY msv )  AS RowNum, sv.msv, sv.HoTen, sv.GioiTinh, k.TenKhoa"
				+ " FROM SinhVien sv INNER JOIN Khoa"
				+ " AS k"
				+ " ON k.MaKhoa = sv.MaKhoa"
				+ " WHERE sv.HoTen LIKE '%" + tuKhoa + "%'"
				+ " ) AS RowConstrainedResult"
				+ " WHERE   RowNum > " + offset
				+ " AND RowNum <= " + feetchnext
				+ " ORDER BY RowNum";
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		SinhVien sinhVien;
		try {
			while(rs.next()){
				sinhVien = new SinhVien();
				sinhVien.setMsv(rs.getString("msv"));
				sinhVien.setHoTen(rs.getString("HoTen"));
				sinhVien.setGioiTinh(rs.getString("GioiTinh"));
				sinhVien.setTenKhoa(rs.getString("TenKhoa"));
				list.add(sinhVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page = 10;
		return list;
	}

	public void themSinhVien(String msv, String hoTen, String gioiTinh, String maKhoa) {
		connect();
		String sql=	String.format("INSERT INTO SinhVien(msv,HoTen,GioiTinh,MaKhoa) "+
					" VALUES ( '%s',N'%s','%s','%s' )", msv, hoTen, gioiTinh, maKhoa);
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SinhVien getThongTinSinhVien(String msv) {
		connect();
		String sql=	String.format("SELECT HoTen, GioiTinh, MaKhoa, msv "+
					" FROM SinhVien WHERE msv = '%s'", msv);
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		SinhVien sinhVien = new SinhVien();
		try {
			while(rs.next()){
				sinhVien.setMsv(msv);
				sinhVien.setHoTen(rs.getString("HoTen"));
				sinhVien.setGioiTinh(rs.getString("GioiTinh"));
				sinhVien.setMaKhoa(rs.getString("MaKhoa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sinhVien;
	}

	public void suaSinhVien(String msv, String hoTen, String gioiTinh, String maKhoa) {
		connect();
		String sql=	String.format("UPDATE SinhVien "+
					" SET HoTen = N'%s', GioiTinh = %s, MaKhoa = '%s' " +
					" WHERE msv = '%s'", hoTen, gioiTinh, maKhoa, msv);
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void xoaSinhVien(String msv) {
		connect();
		String sql=	String.format("DELETE FROM SinhVien WHERE msv = '%s'", msv);
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

