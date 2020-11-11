package web;

import Data.Admin;
import Data.AdminDaoImpl;
import Data.Instructor;
import Data.InstructorDaoImpl;


import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("action") == null ) {
            request.getRequestDispatcher("home_page.jsp").forward(request, response);
        } else {
            switch (request.getParameter("action")) {
                case "loginPage":

                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    break;
                case "registerPage":
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    break;
                case "login":
                    break;
                default:

                    request.getRequestDispatcher("home_page.jsp").forward(request, response);
                    break;
            }
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action") == null ) {
            request.getRequestDispatcher("home_page.jsp").forward(request, response);
        } else {
            switch (request.getParameter("action")) {
                case "loginPOST":
                    if (request.getParameter("access_Level").equals("instructor")) {
                        InstructorDaoImpl instImpl = new InstructorDaoImpl();
                        Instructor instructor = new Instructor();
                        instructor.setUsername(request.getParameter("username"));
                        instructor.setPassword(request.getParameter("password"));
                        if (instImpl.instructorExists(instructor)) {
                            request.getRequestDispatcher("home_page.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("failure_page.jsp").forward(request, response);
                        }
                    } else {
                        AdminDaoImpl adminImpl = new AdminDaoImpl();
                        Admin admin = new Admin();
                        admin.setUsername(request.getParameter("username"));
                        admin.setPassword(request.getParameter("password"));
                        if (adminImpl.adminExists(admin)) {
                            request.getRequestDispatcher("home_page.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("failure_page.jsp").forward(request, response);
                        }
                    }
                    break;
                case "registerPOST":

                    break;
                default:
                    request.getRequestDispatcher("failure_page.jsp").forward(request, response);

            }
        }
    }
}
