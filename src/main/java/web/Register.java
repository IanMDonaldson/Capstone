package web;

import Data.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private PublicUserDaoImpl publicUserDao = new PublicUserDaoImpl();
    private RootUserDaoImpl rootUserDao = new RootUserDaoImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action")== null) {
            req.getRequestDispatcher("User/login.jsp").forward(req,resp);
        } else {
            switch (req.getParameter("action")) {
                case "registerGET":
                    req.getRequestDispatcher("User/register.jsp").forward(req,resp);
                    break;
                default:
                    req.getSession().setAttribute("message", "Not a valid URL");
                    req.getRequestDispatcher("failure_page.jsp").forward(req,resp);
                    break;
            }
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action")== null) {
            req.getRequestDispatcher("User/login.jsp").forward(req,resp);
        } else {
            switch (req.getParameter("action")) {
                case "registerPOST":
                    String fname =req.getParameter("first_name");
                    String lname = req.getParameter("last_name");
                    String email = req.getParameter("email");
                    String accessLevel = req.getParameter("access_level");
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    PublicUser pub = new PublicUser(username,password,fname,lname,email,accessLevel);
                    if (publicUserDao.addPublicUser(pub)) {
                        req.getSession().setAttribute("pubUser",pub);
                        RootUser root = rootUserDao.getRootUser("root1");
                        JavaMail jmail = new JavaMail();
                        String message= "A new user registration request having username = "+pub.getUsername() +
                                "\n First Name = "+fname +
                                "\n Last Name = "+lname +
                                "\n Email = "+email +
                                "\n Requested Access Level = "+accessLevel+
                                "\n\n\n Please go to dcia.dragomundo.com/capstone and log in to accept or reject their registration request.";
                        try {
                            jmail.sendEmail(root.getEmail(),message,"New User Registration Request");
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                        req.getRequestDispatcher("User/registerSuccess.jsp").forward(req,resp);
                    } else {
                        req.getSession().setAttribute("message","Registration Failed! Username already Exists!");
                        req.getRequestDispatcher("failure_page.jsp").forward(req,resp);
                    }
                    break;
                default:
                    req.getSession().setAttribute("message", "Not a valid URL");
                    req.getRequestDispatcher("failure_page.jsp").forward(req,resp);
                    break;
            }
        }
    }

}
