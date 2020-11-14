package web;

import Data.Term;
import Data.TermDaoImpl;

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
    private Term term;

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
            TermDaoImpl termDaoImpl = new TermDaoImpl();
            switch (request.getParameter("action")){
                case "getAllTerms":
                    request.getSession().setAttribute(("termList",TermDaoImpl.updateTerm);
                    request.getRequestDispatcher("termList.jsp").forward(request, response);
                    break;
                case "getTerm":
                    termIDParm = request.getParameter(("id"));
                    termID = Integer.parseInt(termIDParm);
                    term= termDaoImpl.getTerm(termID);
                    request.getSession().setAttribute("id",term.getTermId());
                    request.getSession().setAttribute("termName",term.getTermName().toUpperCase());
                    request.getSession().setAttribute("termYear",term.getTermYear());
                    request.getRequestDispatcher("TermUpdate.jsp").forward(request, response);
                    break;
                case "addTermGET":
                    termID=termDaoImpl.getNewTermID();
                    request.getSession().setAttribute("id",Integer.toString(termID));
                    request.getRequestDispatcher("TermAdd.jsp").forward(request, response);
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
                Term term = new Term();
                term.setTermId(TermID);
                term.setTermYear(TermYear);

                boolean termExist = TermDaoImpl.termExists();
                if(termExist){
                    request.getSession().setAttribute("add", true);
                    request.getRequestDispatcher("termFailurePage.jsp").forward(request, response);
                }
                else{
                    if(!TermDaoImpl.addTerm(Term)){
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
