package model.bo;

import java.util.ArrayList;

import model.bean.Khoa;
import model.dao.KhoaDAO;

/**
 * KhoaBO.java
 *
 * Version 1.0
 *
 * Date: Jan 20, 2015
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jan 20, 2015        	DaiLV2          Create
 */

public class KhoaBO {
	KhoaDAO khoaDAO = new KhoaDAO();

	public ArrayList<Khoa> getListKhoa() {
		return khoaDAO.getListKhoa();
	}
}

