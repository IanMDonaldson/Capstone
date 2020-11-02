package web;

import Data.Instructor;
import Data.InstructorDaoImpl;
import Data.works_onDao;

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
                case "registerPage":
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                case "login":

                default:
                    request.getRequestDispatcher("home_page.jsp").forward(request, response);
            }
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action") == null ) {
            request.getRequestDispatcher("home_page.jsp").forward(request, response);
        } else {
            if (request.getParameter("action").equals("loginPOST")) {
                    if (request.getParameter("admin") == null) {
                        InstructorDaoImpl instImpl = new InstructorDaoImpl();
                        Instructor instructor = new Instructor();
                        instructor.setUsername(request.getParameter("usernameinstructor"));
                        instructor.setPassword(request.getParameter("passwordinstructor"));
                        if (instImpl.instructorExists(instructor)) {
                            request.getRequestDispatcher("home_page.jsp").forward(request, response);
                        } else {
                            PrintWriter writer = response.getWriter();
                            writer.println("login");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }

            }
            else {
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }
}
