package ui;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
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
        switch (req.getParameter("task")){
            case "login":
                System.out.println("login");
                login(req, resp);
                break;
            case "logout":
                System.out.println("logout");
                break;
            case "getAllItems":
                System.out.println("getAllItems");
                getAllItems(req, resp);
                break;
            case "addItem":
                addItem(req, resp);
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
        RequestDispatcher dis = null;

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

    private void getAllItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dis = null;
        try {
            Collection<ItemInfo> items = ItemHandler.getItems();
            Iterator<ItemInfo> it = items.iterator();
            for (; it.hasNext();){
                ItemInfo item = it.next();
                req.setAttribute("showItems", items);
            }
            dis = req.getRequestDispatcher("/homePage.jsp");
            dis.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("addItem" + " " + req.getParameter("itemId") + " " + req.getParameter("user"));
        //CartHandler.addItemToCart
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CartHandler.showCart(req.getParameter("user"));
    }

    public void destroy() {
    }
}