<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page session="true" %>
<%
    // Use the implicit session object provided by JSP
    if(session == null || session.getAttribute("username") == null){
        response.sendRedirect("loginpage.html"); // redirect if not logged in
        return;
    }

    // Retrieve user info from session
    String fullname = (String) session.getAttribute("fullname");
    String username = (String) session.getAttribute("username");
    String email = (String) session.getAttribute("email");
    String phone = (String) session.getAttribute("phone");
    String dob = (String) session.getAttribute("dob");
    String gender = (String) session.getAttribute("gender");
    String address = (String) session.getAttribute("address");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Profile</title>
<style>
body { font-family: Poppins, Arial; background:#eef2ff; padding:20px; }
.profile-card { background:white; max-width:600px; margin:auto; padding:30px; border-radius:12px; box-shadow:0 8px 20px rgba(0,0,0,.1); }
h2 { color:#0b3d91; text-align:center; }
p { font-size:16px; margin:10px 0; }
button { padding:10px 20px; background:#0b3d91; color:white; border:none; border-radius:8px; cursor:pointer; }
button:hover{background:#08306e;}
</style>
</head>
<body>

<div class="profile-card">
<h2>Welcome, <%= fullname %>!</h2>
<p><strong>Username:</strong> <%= username %></p>
<p><strong>Email:</strong> <%= email %></p>
<p><strong>Phone:</strong> <%= phone %></p>
<p><strong>Date of Birth:</strong> <%= dob %></p>
<p><strong>Gender:</strong> <%= gender %></p>
<p><strong>Address:</strong> <%= address %></p>


</div>

</body>
</html>
