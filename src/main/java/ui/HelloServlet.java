package ui;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import bo.CartHandler;
import bo.ItemHandler;
import bo.PersonHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }


    /**
     * Check with the database if the username and password exists.
     *
     * @param req  HTTP servlet request
     * @param resp HTTP servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String task = req.getParameter("task");
        if(req.getAttribute("loop") != null)
            task = req.getAttribute("loop").toString();

        switch (task){
            case "login":
                login(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            case "getAllItems":
                getAllItems(req, resp);
                break;
            case "addItem":
                addItem(req, resp);
                break;
            case "removeItem":
                removeItem(req, resp);
                break;
            case "showCart":
                showCart(req, resp);
                break;
            default:
                System.out.println("servlet switch case default");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        String username = req.getParameter("user");
        String pwd = req.getParameter("pass");
        RequestDispatcher dis;

        if (username == null || username.equals("")) {
            dis = req.getRequestDispatcher("/errorLogin.jsp");
            dis.forward(req, resp);
        }
        if (pwd == null || pwd.equals("")) {
            dis = req.getRequestDispatcher("/errorLogin.jsp");
            dis.forward(req, resp);
        }
        try {
            Collection<PersonInfo> person = PersonHandler.getPerson(username, pwd);
            Iterator<PersonInfo> p = person.iterator();
            if (!p.hasNext()) {
                dis = req.getRequestDispatcher("/errorLogin.jsp");
                dis.forward(req, resp);
            }
            for (; p.hasNext(); ) {
                PersonInfo pe = p.next();
                if (username.equals(pe.getUsername()) && pwd.equals(pe.getPassword())) {
                    session.setAttribute("username", username);
                    dis = req.getRequestDispatcher("/homePage.jsp");
                    dis.forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        RequestDispatcher dis;
        session.removeAttribute("username");
        dis = req.getRequestDispatcher("/index.jsp");
        dis.forward(req, resp);
    }

    private void getAllItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dis;
        try {
            Collection<ItemInfo> items = ItemHandler.getItems();
            req.setAttribute("showItems", items);
            dis = req.getRequestDispatcher("/homePage.jsp");
            dis.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            CartHandler.addToCart(req.getParameter("user"), Integer.valueOf(req.getParameter("itemId")));
            req.setAttribute("loop", "getAllItems");
            doPost(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            CartHandler.removeFromCart( Integer.valueOf(req.getParameter("itemId")), Integer.valueOf(req.getParameter("orderID")));
            req.setAttribute("loop", "showCart");
            doPost(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dis;
        try {
            Collection<CartInfo> cart = CartHandler.showCart(req.getParameter("user"));
            Iterator<CartInfo> it = cart.iterator();
            for (; it.hasNext();){
                it.next();
                req.setAttribute("showCart", cart.iterator().next().getItems());
                req.setAttribute("orderID", cart.iterator().next().getOrderID());
            }
            dis = req.getRequestDispatcher("/cart.jsp");
            dis.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}