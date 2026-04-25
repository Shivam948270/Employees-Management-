<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>
<head>
<title>Register User</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body { background:#eef2f7; }

.main-card {
    background:#fff;
    border-radius:16px;
    padding:30px;
    box-shadow:0 8px 25px rgba(0,0,0,0.08);
}

.section-title {
    font-weight:600;
    margin-top:20px;
}

/* PREVIEW */
.preview, #profilePreview, #multiPreview {
    display:flex;
    flex-wrap:wrap;
    margin-top:10px;
}

.preview-box {
    position:relative;
    margin:6px;
}

.preview-box img {
    width:110px;
    height:110px;
    object-fit:cover;
    border-radius:10px;
    border:1px solid #ccc;
}

.remove-btn {
    position:absolute;
    top:-7px;
    right:-7px;
    background:red;
    color:#fff;
    border:none;
    border-radius:50%;
    width:22px;
    height:22px;
    cursor:pointer;
}

.bankRow {
    background:#f8f9fc;
    padding:12px;
    border-radius:10px;
    margin-bottom:10px;
}
</style>

</head>

<body>

<div class="container mt-5">
<div class="main-card">

<h3 class="text-center mb-4">User Registration</h3>

<form action="register" method="post" enctype="multipart/form-data">

<!-- BASIC DETAILS -->

<div class="row">

<div class="col-md-6 mb-3">
<label>Username</label>
<input type="text" name="username" class="form-control" required>
</div>

<div class="col-md-6 mb-3">
<label>Full Name</label>
<input type="text" name="name" class="form-control" required>
</div>

<div class="col-md-6 mb-3">
<label>Email</label>
<input type="email" name="email" class="form-control" required>
</div>

<div class="col-md-6 mb-3">
<label>Password</label>
<input type="password" name="password" class="form-control" required>
</div>

<div class="col-md-4 mb-3">
<label>Date of Birth</label>
<input type="date" name="dob" class="form-control">
</div>

<div class="col-md-4 mb-3">
<label>Role</label>
<select name="role" class="form-select">
<option value="User">User</option>
<option value="Admin">Admin</option>
</select>
</div>

<div class="col-md-4 mb-3">
<label>Status</label><br>
<div class="form-check form-switch">
<input class="form-check-input" type="checkbox" name="status" value="Active" checked>
<label class="form-check-label">Active</label>
</div>
</div>

</div>

<!-- GENDER -->

<div class="mb-3">
<label>Gender</label><br>

<div class="form-check form-check-inline">
<input class="form-check-input" type="radio" name="gender" value="Male" checked>
<label class="form-check-label">Male</label>
</div>

<div class="form-check form-check-inline">
<input class="form-check-input" type="radio" name="gender" value="Female">
<label class="form-check-label">Female</label>
</div>

</div>

<hr>

<!-- PROFILE IMAGE -->

<h5 class="section-title">Profile Image</h5>
<input type="file" name="profileImage" class="form-control" onchange="previewSingle(event,'profilePreview')">
<div id="profilePreview"></div>

<hr>

<!-- BANK DETAILS -->

<h5 class="section-title">Bank Details</h5>

<div id="bankContainer">

<div class="row bankRow align-items-center">

<div class="col-md-3">
<input type="text" name="accountNo[]" class="form-control" placeholder="Account No">
</div>

<div class="col-md-3">
<input type="text" name="bankName[]" class="form-control" placeholder="Bank Name">
</div>

<div class="col-md-2">
<input type="text" name="ifscCode[]" class="form-control" placeholder="IFSC">
</div>

<div class="col-md-3">
<input type="file" name="tableimage[]" class="form-control bankImage">
<div class="preview"></div>
</div>

<div class="col-md-1 text-center">
<button type="button" class="btn btn-danger removeRow">✖</button>
</div>

</div>

</div>

<button type="button" class="btn btn-primary mb-3" onclick="addRow()">+ Add Bank Row</button>

<hr>

<!-- MULTIPLE IMAGES -->

<h5 class="section-title">Other Images</h5>
<input type="file" name="image[]" multiple class="form-control" onchange="previewMulti(event,'multiPreview')">
<div id="multiPreview"></div>

<br>

<button class="btn btn-dark w-100">Register User</button>

</form>

</div>
</div>

<script>

// PROFILE IMAGE
function previewSingle(e,id){
    let container = document.getElementById(id);
    container.innerHTML="";

    let file = e.target.files[0];
    if(file){
        let reader = new FileReader();
        reader.onload = function(ev){
            container.innerHTML=`
                <div class="preview-box">
                    <img src="${ev.target.result}">
                    <button type="button" class="remove-btn" onclick="removeImage(this)">×</button>
                </div>`;
        };
        reader.readAsDataURL(file);
    }
}

// MULTIPLE IMAGES
function previewMulti(e,id){
    let container = document.getElementById(id);
    container.innerHTML="";

    Array.from(e.target.files).forEach(file=>{
        let reader = new FileReader();
        reader.onload=function(ev){
            let div=document.createElement("div");
            div.className="preview-box";

            div.innerHTML=`
                <img src="${ev.target.result}">
                <button type="button" class="remove-btn" onclick="removeImage(this)">×</button>
            `;

            container.appendChild(div);
        };
        reader.readAsDataURL(file);
    });
}

// REMOVE IMAGE
function removeImage(btn){
    let box = btn.parentElement;
    let container = box.parentElement;

    let input = container.previousElementSibling;
    if(input && input.type==="file"){
        input.value="";
    }

    box.remove();
}

// ADD ROW
function addRow(){
    let row = document.querySelector(".bankRow").cloneNode(true);

    row.querySelectorAll("input").forEach(i=>i.value="");
    row.querySelector(".preview").innerHTML="";

    document.getElementById("bankContainer").appendChild(row);
}

// REMOVE ROW
document.addEventListener("click",function(e){
    if(e.target.classList.contains("removeRow")){
        let rows = document.querySelectorAll(".bankRow");
        if(rows.length>1){
            e.target.closest(".bankRow").remove();
        }
    }
});

// BANK IMAGE PREVIEW
document.addEventListener("change",function(e){
    if(e.target.classList.contains("bankImage")){
        let preview = e.target.parentElement.querySelector(".preview");
        preview.innerHTML="";

        let file = e.target.files[0];
        if(file){
            let reader = new FileReader();
            reader.onload=function(ev){
                preview.innerHTML=`
                    <div class="preview-box">
                        <img src="${ev.target.result}">
                        <button type="button" class="remove-btn" onclick="removeImage(this)">×</button>
                    </div>`;
            };
            reader.readAsDataURL(file);
        }
    }
});

</script>

</body>
</html>
