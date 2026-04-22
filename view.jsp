<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,company.User" %>

<!DOCTYPE html>

<html>
<head>
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">

```
<div class="d-flex justify-content-between mb-3">
    <h3>User Management</h3>

    <div>
        <a href="home.jsp" class="btn btn-success">+ Add User</a>
        <a href="excelimg" class="btn btn-primary">Download Excel</a>
        <a href="pdfimg" class="btn btn-danger">Download PDF</a>
        <a href="login?action=logout" class="btn btn-dark">Logout</a>
    </div>
</div>

<!-- Filters -->
<form action="view" method="get" class="row mb-3">
    <div class="col-md-3">
        <input type="text" name="email" class="form-control" placeholder="Filter by Email">
    </div>
    <div class="col-md-3">
        <input type="text" name="uid" class="form-control" placeholder="Filter by UID">
    </div>
    <div class="col-md-3">
        <select name="status" class="form-control">
            <option value="">All Status</option>
            <option>Active</option>
            <option>Inactive</option>
        </select>
    </div>
    <div class="col-md-3">
        <button class="btn btn-info w-100">Filter</button>
    </div>
</form>

<!-- Table -->
<div class="card shadow">
    <div class="card-body">

        <table class="table table-bordered table-hover text-center">
            <thead class="table-dark">
                <tr>
                    <th>UID</th>
                    <th>Username</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Status</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                if (users != null) {
                    for (User u : users) {
            %>
                <tr>
                    <td><%=u.getUid()%></td>
                    <td><%=u.getUsername()%></td>
                    <td><%=u.getName()%></td>
                    <td><%=u.getEmail()%></td>
                    <td><%=u.getGender()%></td>
                    <td><%=u.getDob()%></td>
                    <td><%=u.getStatus()%></td>
                    <td><%=u.getRole()%></td>

                    <td>
                        <a href="edit.jsp?uid=<%=u.getUid()%>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="register?uid=<%=u.getUid()%>" 
                           onclick="return confirm('Delete?')" 
                           class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>

    </div>
</div>
```

</div>

</body>
</html>

