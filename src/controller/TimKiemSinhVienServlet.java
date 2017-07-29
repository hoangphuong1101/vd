package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Pagination;
import model.bean.SinhVien;
import model.bo.SinhVienBO;

/**
 * Servlet implementation class TimKiemSinhVienServlet
 */
public class TimKiemSinhVienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimKiemSinhVienServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//kiem tra da dang nhap chua
		HttpSession session = request.getSession();
		if(session.getAttribute("tenDangNhap")==null){
			response.sendRedirect("DangNhapServlet");
			return;
		}
		
		//lay danh sach sinh vien
		ArrayList<SinhVien> listSinhVien;
		SinhVienBO sinhVienBO = new SinhVienBO();
		String maKhoa=request.getParameter("tuKhoa");
		int page = 0;
		try {
			page = Integer.parseInt(request.getParameter("page")) - 1; 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(maKhoa==null || maKhoa.length()==0){
			listSinhVien = sinhVienBO.getListSinhVien();
		} else {
			listSinhVien = sinhVienBO.getListSinhVienByKeyWord(maKhoa, page);
		}
		System.out.println("Page current: " + Pagination.page + ", Total page: "+ Pagination.totalPage);
		request.setAttribute("listSinhVien", listSinhVien);
		request.setAttribute("tuKhoa", maKhoa);
		System.out.println(listSinhVien.toString());
		RequestDispatcher rd = request.getRequestDispatcher("timKiem.jsp");
		rd.forward(request, response);
	}

}
