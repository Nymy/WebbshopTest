package ui;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String username = req.getParameter("user");
       String pwd = req.getParameter("pass");
       HttpSession session = req.getSession();
       RequestDispatcher dis = null;

        if (username == null || username.equals("")){
            session.setAttribute("status", "invalid username");
            dis = req.getRequestDispatcher("/WebbshopTest_war_exploded/login.jsp");
            dis.forward(req,resp);
        }
        if (pwd == null || pwd.equals("")){
            session.setAttribute("status", "invalid password");
            dis = req.getRequestDispatcher("/WebbshopTest_war_exploded/login.jsp");
            dis.forward(req,resp);
        }

        try {
            Collection<PersonInfo> person = PersonHandler.getPerson(username, pwd);
            Iterator<PersonInfo> p = person.iterator();
            for (; p.hasNext(); ) {
                PersonInfo pe = p.next();
                System.out.println(pe.getUsername());
                System.out.println("hej kompis");
                System.out.println(pe.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void destroy() {
    }
}