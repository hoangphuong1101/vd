package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.NguoiDungBO;

/**
 * Servlet implementation class DangNhapServlet
 */
public class DangNhapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangNhapServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String tenDangNhap = request.getParameter("tenDangNhap");
		String matKhau = request.getParameter("matKhau");
		NguoiDungBO nguoiDungBO = new NguoiDungBO();
		if(nguoiDungBO.checkLogin(tenDangNhap, matKhau)){
			HttpSession session = request.getSession();
			session.setAttribute("tenDangNhap", tenDangNhap);
			response.sendRedirect("DanhSachSinhVienServlet");
		} else {
			request.setAttribute("thongBao", "Đăng nhập không thành công");
			RequestDispatcher rd = request.getRequestDispatcher("dangNhap.jsp");
			rd.forward(request, response);
		}
	}

}
