package web;

import Data.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootServlet extends HttpServlet {
    private PublicUserDaoImpl publicUserDao = new PublicUserDaoImpl();
    private RootUserDaoImpl rootUserDao = new RootUserDaoImpl();
    private String pubUsername = null;
    private PublicUser publicUser = null;
    private JavaMail jmail = new JavaMail();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action")== null) {
            req.getRequestDispatcher("User/login.jsp").forward(req,resp);
        } else {
            switch (req.getParameter("action")) {
                case "acceptUserGET":
                    pubUsername = req.getParameter("username");
                    publicUser = publicUserDao.getPublicUser(pubUsername);
                    if (publicUserDao.swapToUserTable(publicUser) && publicUserDao.deletePublicUser(pubUsername)) {
                        req.getSession().setAttribute("message","User has been successfully registered");

                        try {
                            jmail.sendEmail(publicUser.getEmail(),"Your account has been accepted with your requested " +
                                    "access level! Please go to dcia.dragomundo.com/capstone to login with your new credentials.",
                                    "Registration request Accepted!");
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                        req.getRequestDispatcher("Root/successPage.jsp").forward(req,resp);
                    } else {
                        req.getSession().setAttribute("message","swap to user table failed!");
                        req.getRequestDispatcher("failure_page.jsp").forward(req,resp);
                    }
                    break;
                case "denyUserGET":
                    pubUsername = req.getParameter("username");
                    publicUser = publicUserDao.getPublicUser(pubUsername);
                    if (publicUserDao.deletePublicUser(pubUsername)) {
                        req.getSession().setAttribute("message","User has been removed from your list of public users!" +
                                "\n An email has been sent notifying them of your decision.");
                        try {
                            jmail.sendEmail(publicUser.getEmail(),"Your account has been rejected with your requested " +
                                            "access level! If you would like to register again for a different access level, please" +
                                            "go to dcia.dragomundo.com/capstone and follow the link to register once again.",
                                    "Registration request Rejected!");
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                        req.getRequestDispatcher("Root/successPage.jsp").forward(req,resp);
                    } else {
                        req.getSession().setAttribute("message","delete public user failed!");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action")== null) {
            req.getRequestDispatcher("User/login.jsp").forward(req,resp);
        } else {
            switch (req.getParameter("action")) {
                case "registerPOST":

                    break;
                default:
                    req.getSession().setAttribute("message", "Not a valid URL");
                    req.getRequestDispatcher("failure_page.jsp").forward(req,resp);
                    break;
            }
        }
    }
}
