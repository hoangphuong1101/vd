<%@page import="model.bean.Khoa"%>
<%@page import="common.StringProcess"%>
<%@page import="model.bean.SinhVien"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Danh sách sinh viên</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <form action="DanhSachSinhVienServlet" method="get">
            <div class="col-lg-4">
                <select class="form-control" name="maKhoa">
                    <option value="">-- Chọn khoa --</option>
                    <%
                    	ArrayList<Khoa> listKhoa = (ArrayList<Khoa>)request.getAttribute("listKhoa");
                    	for(Khoa khoa:listKhoa){
                    %>
                    <option value="<%=khoa.getMaKhoa()%>"><%=khoa.getTenKhoa() %></option>
                    <%
                    	}
                    %>
                </select>
            </div>
            <script type="text/javascript">
            	$("[name='maKhoa']").val("<%=StringProcess.getVaildString(request.getParameter("khoa"))%>");
            </script>
            <button type="submit" class="btn btn-primary">Xem</button>
            <div class="col-lg-2 pull-right">
                <a class="btn btn-primary" href="ThemSinhVienServlet" role="button">Thêm mới</a>
            </div>
        </form>
    </div>
    <br>
    <div class="bs-example">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>MSV</th>
                <th>Họ và tên</th>
                <th>Giới tính</th>
                <th>Khoa</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
            	ArrayList<SinhVien> listSinhVien = (ArrayList<SinhVien>)request.getAttribute("listSinhVien");
            	for(SinhVien sv:listSinhVien){
            %>
            <tr>
                <th scope="row"><%=sv.getMsv() %></th>
                <td><%=sv.getHoTen() %></td>
                <td><%=StringProcess.gioiTinh(sv.getGioiTinh()) %></td>
                <td><%=sv.getTenKhoa() %></td>
                <td>
                    <a href="SuaSinhVienServlet?msv=<%=sv.getMsv()%>"><span class="glyphicon glyphicon-edit""></span></a>
                    <a href="XoaSinhVienServlet?msv=<%=sv.getMsv()%>" style="margin-left: 30px;"><span class="glyphicon glyphicon-trash"></span></a>
                </td>
            </tr>
            <%
            	}
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>