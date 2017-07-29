<%@page import="model.bean.Khoa"%>
<%@page import="common.StringProcess"%>
<%@page import="model.bean.SinhVien"%>
<%@page import="model.bean.Pagination"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Tìm kiếm</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <form action="TimKiemSinhVienServlet" method="get" id="tim-Kiem-Sinh-Vien">
            <div class="col-lg-9">
		        <div class="row form-group">
		            <label class="col-lg-3">Tên SV</label>
		            <div class="col-lg-9">
		                <input type="text" class=" form-control" name="tuKhoa"/>
		            </div>
		        </div>
            </div> 
			<input type="hidden" name="page">
            <script type="text/javascript">   
	            $(document).ready(function() { 
	            	$("[name='page']").val(1); 
	            });
        		$("[name='tuKhoa']").val("<%=StringProcess.getVaildString(request.getParameter("tuKhoa"))%>");
	            function handleClickAction(page) {  
	            	$("[name='page']").val(page);   
	                document.getElementById('tim-Kiem-Sinh-Vien').submit();
	            }
            </script>
            <button type="submit" class="btn btn-primary">Tìm kiếm</button> 
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
        
        <%
        
    	for(int i = 0; i < Pagination.totalPage; i++){
    		out.println("<a href='#' onclick='handleClickAction("+ (i + 1) +");'>"+(i + 1)+"</a>");
    	}
        
        %>
    </div>
</div>
</body>
</html>