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
     * Check which task it is to be done and call the right function
     * @param req
     * @param resp
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

    /**
     * Check if user exists in database, redirects to homePage if exists otherwise errorLogin
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        String username = req.getParameter("user");
        String pwd = req.getParameter("pass");
        RequestDispatcher dis;

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

    /**
     * Logout user by removing session attribute and redirect to index
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        RequestDispatcher dis;
        session.removeAttribute("username");
        dis = req.getRequestDispatcher("/index.jsp");
        dis.forward(req, resp);
    }

    /**
     * get item list from itemhandler and set HttpServletRequest attribute
     * redirect to homePage
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * Add items to cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            CartHandler.addToCart(req.getParameter("user"), Integer.valueOf(req.getParameter("itemId")));
            req.setAttribute("loop", "getAllItems");
            doPost(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove item from cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void removeItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            CartHandler.removeFromCart( Integer.valueOf(req.getParameter("itemId")), Integer.valueOf(req.getParameter("orderID")));
            req.setAttribute("loop", "showCart");
            doPost(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get cart from database and redirect to cart.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
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

}