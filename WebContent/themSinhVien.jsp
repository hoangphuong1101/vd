<%@ page import="model.bean.Khoa"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Thêm sinh viên</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3>Thêm mới sinh viên</h3>
    <br>
    <form action="ThemSinhVienServlet" method="post">
        <div class="row form-group">
            <label class="col-lg-2">Mã SV</label>
            <div class="col-lg-3">
                <input type="text" class=" form-control" name="msv"/>
            </div>
        </div>
        <div class="row form-group">
            <label class="col-lg-2">Họ tên</label>
            <div class="col-lg-3">
                <input type="text" class="form-control" name="hoten"/>
            </div>
        </div>
        <div class="row form-group">
            <label class="col-lg-2">Giới tính</label>
            <div class="col-lg-3">
                <input type="radio" name="gioitinh" value="1" style="margin-right: 10px;">Nam
                <input type="radio" style="margin: 0px 10px 0px 30px;" name="gioitinh" value="0">Nữ
            </div>
        </div>
        <div class="row form-group">
            <label class="col-lg-2">Khoa</label>
            <div class="col-lg-3">
                <select class="form-control" name="khoa">
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
        </div>
        <div class="row form-group">
            <div class="col-lg-3 col-lg-offset-2">
                <button class="btn btn-primary" type="submit" value="submit" name="submit">Thêm mới</button>
                <input class="btn btn-primary" type="button" value="Quay lại" onclick="history.go(-1);" />
            </div>
        </div>
    </form>
    <br>
</div>
</body>
</html>