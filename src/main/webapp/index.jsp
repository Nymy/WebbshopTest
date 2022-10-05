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
    <form method="post" action="/login.jsp">
        <div class="card-details">
            <div class="card-box">
                <span class="details">Username</span>
                <input type="text" placeholder="Enter e-mail :)">
            </div>
            <div class="card-box">
                <span class="details">Password</span>
                <input type="text" placeholder="**********">
            </div>
            <div class="button">
                <input type="submit" value="confirm">
            </div>
        </div>
    </form>
</div>
</body>


</html>