package model.bo;

import java.util.ArrayList;

import model.bean.SinhVien;
import model.dao.SinhVienDAO;

/**
 * SinhVienBO.java
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

public class SinhVienBO {
	SinhVienDAO sinhVienDAO = new SinhVienDAO();
	
	public ArrayList<SinhVien> getListSinhVien() {
		return sinhVienDAO.getListSinhVien();
	}

	public ArrayList<SinhVien> getListSinhVien(String maKhoa) {
		return sinhVienDAO.getListSinhVien(maKhoa);
	}

	public ArrayList<SinhVien> getListSinhVienByKeyWord(String maKhoa, int page) {
		return sinhVienDAO.getListSinhVienByKeyWord(maKhoa, page);
	}
	
	public void themSinhVien(String msv, String hoTen, String gioiTinh, String maKhoa) {
		sinhVienDAO.themSinhVien(msv, hoTen, gioiTinh, maKhoa);
	}
	
	public SinhVien getThongTinSinhVien(String msv) {
		return sinhVienDAO.getThongTinSinhVien(msv);
	}
	
	public void suaSinhVien(String msv, String hoTen, String gioiTinh, String maKhoa) {
		sinhVienDAO.suaSinhVien(msv, hoTen, gioiTinh, maKhoa);
	}

	public void xoaSinhVien(String msv) {
		sinhVienDAO.xoaSinhVien(msv);
	}

}

