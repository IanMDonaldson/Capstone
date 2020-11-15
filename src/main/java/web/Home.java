package web;

import Data.works_onDao;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("action") == null ) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action") == null ) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            if (request.getParameter("action").equals("registerPOST")) {
                String email = request.getParameter("email");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

}
