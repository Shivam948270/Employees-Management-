<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>
<head>
    <title>Register User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg p-4">
        <h3 class="text-center mb-4">Register User</h3>

```
    <form action="register" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="add"/>

        <div class="row">
            <div class="col-md-6 mb-3">
                <input type="text" name="username" class="form-control" placeholder="Username" required>
            </div>
            <div class="col-md-6 mb-3">
                <input type="text" name="name" class="form-control" placeholder="Full Name" required>
            </div>

            <div class="col-md-6 mb-3">
                <input type="email" name="email" class="form-control" placeholder="Email" required>
            </div>

            <div class="col-md-6 mb-3">
                <input type="password" name="password" class="form-control" placeholder="Password" required>
            </div>

            <div class="col-md-6 mb-3">
                <select name="gender" class="form-control">
                    <option>Male</option>
                    <option>Female</option>
                </select>
            </div>

            <div class="col-md-6 mb-3">
                <input type="date" name="dob" class="form-control">
            </div>

            <div class="col-md-6 mb-3">
                <select name="status" class="form-control">
                    <option>Active</option>
                    <option>Inactive</option>
                </select>
            </div>

            <div class="col-md-6 mb-3">
                <select name="role" class="form-control">
                    <option>User</option>
                    <option>Admin</option>
                </select>
            </div>

            <h5>Bank Details</h5>
            <div class="col-md-4 mb-3">
                <input type="text" name="accountNo" class="form-control" placeholder="Account No">
            </div>
            <div class="col-md-4 mb-3">
                <input type="text" name="bankName" class="form-control" placeholder="Bank Name">
            </div>
            <div class="col-md-4 mb-3">
                <input type="text" name="ifscCode" class="form-control" placeholder="IFSC">
            </div>

            <div class="col-md-6 mb-3">
                <label>Upload Images</label>
                <input type="file" name="image" multiple class="form-control">
            </div>

            <div class="col-md-6 mb-3">
                <label>Upload Table Image</label>
                <input type="file" name="tableimage" class="form-control">
            </div>
        </div>

        <button class="btn btn-primary w-100">Register</button>
    </form>
</div>
```

</div>

</body>
</html>
