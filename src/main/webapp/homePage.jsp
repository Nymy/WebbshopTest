<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="ui.ItemInfo" %><%--
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
        <div class="homeButtons" >
            <form method="post" action="/WebbshopTest_war_exploded/hello-servlet">
                <input type="hidden" name="task" value="logout">
                <input type="submit" value="Logout">
            </form>
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
        <% if (request.getAttribute("showItems") != null) { %>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                <%
                    Collection<ItemInfo> items = (Collection) request.getAttribute("showItems");
                    Iterator<ItemInfo> it = items.iterator();
                %>
                <%
                    for (; it.hasNext(); ) {
                        ItemInfo item = it.next();
                %>
                <tr>
                    <td><%= item.getiName()%></td>

                    <td><%= item.getPrice()%></td>
                    <td>
                        <form method="post" action="/WebbshopTest_war_exploded/hello-servlet">
                            <input type="hidden" name="itemId" value=<%= item.getItemID()%>>
                            <input type="hidden" name="task" value="addItem">
                            <input type="hidden" name="user" value=<%= session.getAttribute("username")%>>
                            <input type="submit" value="Add item to cart" >
                        </form>
                    </td>
                </tr>
                <%  }   %>
                </tbody>
            </table>
    <%  } %>
    </body>
</html>
