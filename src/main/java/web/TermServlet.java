package web;

import Data.term;
import Data.termDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/WebActor")
public class TermServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    private String termIDParm;
    private int termID;
    private term Term;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebTerm() {
        super();
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("action") == null) {
            request.getRequestDispatcher("TermList.jsp").forward(request, response);
        }
        else {
            termDaoImpl TermDaoImpl = new termDaoImpl();
            switch (request.getParameter("action")){
                case "getAllTerms":
                    request.getSession().setAttribute(("termList",termDaoImpl.updateTerm);
                    request.getRequestDispatcher("termList.jsp").forward(request, response);
                    break;
            }
        }
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (request.getParameter("action") == null) {
            request.getRequestDispatcher("Menu.jsp").forward(request, response);
        }
        else{
            switch (request.getParameter("action")){
                case "addTermPOST":
                termIDParm = request.getParameter("id");
                termID = Integer.parseInt("actorIDParam");
                int TermYear = Integer.parseInt(request.getParameter("termYear").toUpperCase());
                int TermID = Integer.parseInt(request.getParameter("termID").toUpperCase());
                term Term = new term();
                term.setTermId(TermID);
                term.setTermYear(TermYear);

                boolean termExist = termDaoImpl.termExists(Term);
                if(termExist){
                    request.getSession().setAttribute("add", true);
                    request.getRequestDispatcher("termFailurePage.jsp").forward(request, response);
                }
                else{
                    if(!termDaoImpl.addTerm(Term)){
                        request.getSession().setAttribute("add", true);
                        request.getRequestDispatcher("termFailurePage.jsp").forward(request, response);
                    }
                    else{
                        request.getSession().setAttribute("id", term.getTermId());
                        request.getSession().setAttribute("Name", term.getTermName().toUpperCase());
                        request.getSession().setAttribute("Year", term.getTermYear());
                        request.getRequestDispatcher("term.jsp").forward(request, response);
                    }
                }
                break;

            }
        }
    }
}
