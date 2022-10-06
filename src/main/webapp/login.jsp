<%@ page import="java.util.Collection" %>
<%@ page import="ui.PersonInfo" %>
<%@ page import="bo.PersonHandler" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="bo.Person" %><%--
  Created by IntelliJ IDEA.
  User: Nonno
  Date: 2022-10-04
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success Login</title>

</head>
<body>
<h1>Hello <%= request.getAttribute("username")%></h1>
</body>
</html>
