package web;

import Data.Term;
import Data.TermDaoImpl;

import javax.annotation.security.DeclareRoles;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@BasicAuthenticationMechanismDefinition(realmName="${'jdbc-realm'}")
@WebServlet(name="AdminServlet", urlPatterns={"/admin"})
@DeclareRoles({ "admin", "publicUser", "root", "instructor" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    private String termIDParm;
    private int termID;
    private Term term;
    private TermDaoImpl termDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("TermList.jsp").forward(req, resp);
        }
        else {
            TermDaoImpl termDaoImpl = new TermDaoImpl();
            switch (req.getParameter("action")){
                case "getAllTerms":

                    req.getSession().setAttribute(("termList"),termDao.updateTerm(term));
                    req.getRequestDispatcher("termList.jsp").forward(req, resp);

                    break;
                case "getTerm":
                    termIDParm = req.getParameter(("id"));
                    termID = Integer.parseInt(termIDParm);
                    req.getSession().setAttribute("id",term.getTermId());
                    req.getSession().setAttribute("termName",term.getTermName().toUpperCase());
                    req.getSession().setAttribute("termYear",term.getTermYear());
                    req.getRequestDispatcher("TermUpdate.jsp").forward(req, resp);
                    break;
                case "addTermGET":
                    termID=termDaoImpl.getNewTermID();
                    req.getSession().setAttribute("id",Integer.toString(termID));
                    req.getRequestDispatcher("TermAdd.jsp").forward(req, resp);
                    break;
            }

        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("Menu.jsp").forward(req, resp);
        }
        else{
            switch (req.getParameter("action")){
                case "addTermPOST":
                    termIDParm = req.getParameter("id");
                    termID = Integer.parseInt("actorIDParam");
                    int TermYear = Integer.parseInt(req.getParameter("termYear").toUpperCase());
                    int TermID = Integer.parseInt(req.getParameter("termID").toUpperCase());
                    Term term = new Term();
                    term.setTermId(TermID);
                    term.setTermYear(TermYear);

                    boolean termExist = termDao.termExists(term);
                    if(termExist){
                        req.getSession().setAttribute("add", true);
                        req.getRequestDispatcher("termFailurePage.jsp").forward(req, resp);
                    }
                    else{
                        if(!termDao.addTerm(term)){
                            req.getSession().setAttribute("add", true);
                            req.getRequestDispatcher("termFailurePage.jsp").forward(req, resp);
                        }
                        else{
                            req.getSession().setAttribute("id", term.getTermId());
                            req.getSession().setAttribute("Name", term.getTermName().toUpperCase());
                            req.getSession().setAttribute("Year", term.getTermYear());
                            req.getRequestDispatcher("term.jsp").forward(req, resp);
                        }
                    }
                    break;

            }
        }
    }
    }

