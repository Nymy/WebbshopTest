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
       RequestDispatcher dis = null;

        if (username == null || username.equals("")){
            dis = req.getRequestDispatcher("/errorLogin.jsp");
            dis.forward(req,resp);
        }
        if (pwd == null || pwd.equals("")){
            dis = req.getRequestDispatcher("/errorLogin.jsp");;
            dis.forward(req,resp);
        }

        try {
            Collection<PersonInfo> person = PersonHandler.getPerson(username, pwd);
            Iterator<PersonInfo> p = person.iterator();
            if (!p.hasNext()){
                dis = req.getRequestDispatcher("/errorLogin.jsp");
                dis.forward(req,resp);
            }
            for (; p.hasNext(); ) {
                PersonInfo pe = p.next();
                if(username.equals(pe.getUsername()) && pwd.equals(pe.getPassword())){
                    req.setAttribute("username", username);
                    dis = req.getRequestDispatcher("/login.jsp");
                    dis.forward(req,resp);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void destroy() {
    }
}