package web;


import Data.Term;
import Data.TermDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@BasicAuthenticationMechanismDefinition(realmName="${'jdbc-realm'}")
@WebServlet(name="AdminServlet", urlPatterns={"/AdminServlet"})
//@DeclareRoles({ "admin", "publicUser", "root", "instructor" })
//@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    private String termIDParam;
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
                    termIDParam = req.getParameter(("id"));
                    termID = Integer.parseInt(termIDParam);
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
                    termIDParam = req.getParameter("id");
                    termID = Integer.parseInt(req.getParameter(termIDParam));
                    int TermYear = Integer.parseInt(req.getParameter("termYear"));
                    String TermName = req.getParameter("termID");
                    Term term = new Term();
                    term.setTermName(TermName);
                    term.setTermYear(TermYear);

                    boolean termExist = termDao.termExists(term);
                    if(termExist){
                        req.getSession().setAttribute("add", true);
                        req.getRequestDispatcher("Admin/termFailurePage.jsp").forward(req, resp);
                    }
                    else{
                        if(!termDao.addTerm(term)){
                            req.getSession().setAttribute("add", true);
                            req.getRequestDispatcher("Admin/termFailurePage.jsp").forward(req, resp);
                        }
                        else{
                            req.getSession().setAttribute("id", term.getTermId());
                            req.getSession().setAttribute("Name", term.getTermName().toUpperCase());
                            req.getSession().setAttribute("Year", term.getTermYear());
                            req.getRequestDispatcher("Admin/Term.jsp").forward(req, resp);
                        }
                    }
                    break;

            }
        }
    }
    }

