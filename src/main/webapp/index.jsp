<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Webbshop</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="wrapper">
    <h1><%= "Welcome to the webbshop!" %>
    </h1>
    <img class="homepage" src="img/shop2.jpg">
    <input type="hidden" id="status" value="<%request.getAttribute("status");%>">
    <form method="post" action="/WebbshopTest_war_exploded/hello-servlet">
    Username: <input type="text" name="user" placeholder="enter username" required = "required"><br>
    Password: <input type="password" name="pass" placeholder="**********" required ="required"><br>
        <input type="submit" value="SUBMIT">
    </form>
</div>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script type="text/javascript">
    var status = document.getElementbyId("status").value;
    if (status == "invalid username"){
        swal("Wrong username");
    }
    if (status == "invalid password"){
        swal("Sorry", "Wrong password")
    }
</script>
</body>
</html>