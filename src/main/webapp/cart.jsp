<%@ page import="ui.CartInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="ui.ItemInfo" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Nonno
  Date: 2022-10-06
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h1>Your cart</h1>
<div class="homeButtons" >
    <form method="post" action="/WebbshopTest_war_exploded/hello-servlet">
        <input type="hidden" name="task" value="getAllItems">
        <input type="submit" value="Show Items">
    </form>
    <form method="post" action="/WebbshopTest_war_exploded/hello-servlet">
        <input type="hidden" name="task" value="showCart">
        <input type="hidden" name="user" value=<%= session.getAttribute("username")%>>
        <input type="submit" value="Show Cart">
    </form>
</div>
<% if (request.getAttribute("showCart") != null) { %>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Amount</th>
        <th> </th>
    </tr>
    </thead>
    <tbody>
    <%
        int total = 0;
        ArrayList<ItemInfo> item = (ArrayList<ItemInfo>) request.getAttribute("showCart");
        for (int i = 0; i < item.size(); i++) {
            total = total + item.get(i).getPrice() * item.get(i).getQuantity();
    %>
    <tr>
        <td><%= item.get(i).getiName()%></td>
        <td><%= item.get(i).getPrice()%></td>
        <td><%= item.get(i).getQuantity()%></td>
        <td>
            <form method="post" action="/WebbshopTest_war_exploded/hello-servlet">
                <input type="hidden" name="itemId" value=<%= item.get(i).getItemID()%>>
                <input type="hidden" name="task" value="removeItem">
                <input type="hidden" name="user" value=<%= session.getAttribute("username")%>>
                <input type="hidden" name="orderID" value=<%= request.getAttribute("orderID") %>>
                <input type="submit" value="Remove item from cart" >
            </form>
        </td>
    </tr>

    <%
            }
    %>

    </tbody>
</table>
<p>your total price is: <%=total%> dollar</p>
<%

    }
%>
<% if (request.getAttribute("showCart") == null) { %>
<h2>Cart is empty..</h2>
<%}%>
</body>
</html>
