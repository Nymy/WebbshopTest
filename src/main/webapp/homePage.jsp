<%@ page import="java.util.Collection" %>
<%@ page import="ui.PersonInfo" %>
<%@ page import="bo.PersonHandler" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="bo.Person" %>
<%@ page import="ui.ItemInfo" %>
<%@ page import="java.util.List" %><%--
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
<h1>Hello <%= session.getAttribute("username")%></h1>
<form method="post" action="/WebbshopTest_war_exploded/hello-servlet">
    <input type="hidden" name="task" value="getAllItems">
    <input type="submit" value="Show Items" >
</form>
<table>
    <thead>
    <tr>
        <th>name</th>
        <th>price</th>
        <th>amount</th>
        <th>id</th>
    </tr>
    </thead>
    <tbody>
    <%
        if(request.getAttribute("showItems") != null){
            int i = 1;
            Collection<ItemInfo> items = (Collection) request.getAttribute("showItems");
            Iterator<ItemInfo> it = items.iterator();
    %>
    <%
        for(; it.hasNext();){
            ItemInfo item = it.next();
    %>

    <tr>
        <td><%= item.getiName()%></td>
        <td><%= item.getPrice()%></td>
        <td><%= item.getQuantity()%></td>
        <td><%= item.getItemID()%></td>
    </tr>

    <%
            }}
    %>
    </tbody>
</table>
</body>
</html>
