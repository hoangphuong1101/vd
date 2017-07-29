package model.bo;

import model.dao.NguoiDungDAO;

/**
 * NguoiDungBO.java
 *
 * Version 1.0
 *
 * Date: Jan 21, 2015
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jan 21, 2015        	DaiLV2          Create
 */

public class NguoiDungBO {
	NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();

	public boolean checkLogin(String tenDangNhap, String matKhau) {
		return nguoiDungDAO.checkLogin(tenDangNhap, matKhau);
	}
	
}

