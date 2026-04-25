<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,company.User" %>

<!DOCTYPE html>
<html>
<head>
<title>User Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
    background: #eef2f7;
}

.main-card {
    background: #fff;
    border-radius: 16px;
    padding: 20px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.08);
}

.table th {
    background: #1f2937;
    color: #fff;
    font-weight: 500;
}

.table td {
    vertical-align: middle;
}

.badge {
    padding: 6px 10px;
    border-radius: 12px;
}

.btn-sm {
    border-radius: 18px;
    padding: 4px 10px;
}

.filter-box {
    background: #f8f9fc;
    padding: 15px;
    border-radius: 12px;
    margin-bottom: 15px;
}
</style>

</head>

<body>

<div class="container mt-4">

<!-- HEADER -->
<div class="d-flex justify-content-between align-items-center mb-3">
<h4 class="fw-bold">User Dashboard</h4>

<div>
<a href="home.jsp" class="btn btn-success btn-sm">➕ Add</a>
<a href="excelimg" class="btn btn-primary btn-sm">Excel</a>
<a href="pdfimg" class="btn btn-danger btn-sm">PDF</a>
<a href="login?action=logout" class="btn btn-dark btn-sm">Logout</a>
</div>
</div>

<!-- FILTER -->
<div class="filter-box">

<form action="view" method="get" class="row g-2">

<div class="col-md-4">
<input type="text" name="email" class="form-control"
placeholder="Search by Email"
value="<%= request.getParameter("email")!=null?request.getParameter("email"):"" %>">
</div>

<div class="col-md-3">
<select name="status" class="form-select">
<option value="">All Status</option>
<option value="Active"
<%= "Active".equals(request.getParameter("status"))?"selected":"" %>>
Active</option>

<option value="Inactive"
<%= "Inactive".equals(request.getParameter("status"))?"selected":"" %>>
Inactive</option>
</select>
</div>

<div class="col-md-2">
<button class="btn btn-dark w-100">Filter</button>
</div>

<div class="col-md-2">
<a href="view" class="btn btn-secondary w-100">Reset</a>
</div>

</form>

</div>

<!-- TABLE -->
<div class="main-card">

<table class="table table-hover text-center">

<thead>
<tr>
<th>UID</th>
<th>Username</th>
<th>Name</th>
<th>Email</th>
<th>Status</th>
<th>Actions</th>
</tr>
</thead>

<tbody>

<%
List<User> users = (List<User>) request.getAttribute("users");

if (users != null && !users.isEmpty()) {
for (User u : users) {
%>

<tr>

<td><%=u.getUid()%></td>
<td><%=u.getUsername()%></td>
<td><%=u.getName()%></td>
<td><%=u.getEmail()%></td>

<td>
<span class="badge bg-<%=u.getStatus().equals("Active")?"success":"secondary"%>">
<%=u.getStatus()%>
</span>
</td>

<td>

<a href="edit.jsp?uid=<%=u.getUid()%>" class="btn btn-warning btn-sm">✏️</a>

<a href="register?action=delete&uid=<%=u.getUid()%>"
onclick="return confirm('Delete?')"
class="btn btn-danger btn-sm">❌</a>

<a href="register?action=change&uid=<%=u.getUid()%>&status=<%=u.getStatus().equals("Active")?"Inactive":"Active"%>"
class="btn btn-info btn-sm">🔄</a>

</td>

</tr>

<%
}
} else {
%>

<tr>
<td colspan="6">No Data Found</td>
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
