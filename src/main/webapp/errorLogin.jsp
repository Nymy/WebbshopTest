<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login failed</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="wrapper">
            <h1><%= "Welcome to the webbshop!" %></h1>
            <img class="homepage" src="img/shop2.jpg">
            <form method="post" action="/WebbshopTest_war_exploded/hello-servlet">
                <font color="red">Wrong credentials</font> <br>
                Username: <input type="text" name="user" placeholder="enter username" required="required"><br>
                Password: <input type="password" name="pass" placeholder="**********" required="required"><br>
                <input type="hidden" name="task" value="login">
                <input type="submit" value="SUBMIT">
            </form>
        </div>
    </body>
</html>