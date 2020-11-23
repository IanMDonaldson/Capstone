package web;

import Data.*;

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
    private String courseIDParm;
    private int termID;
    private int courseID;
    private Term term;
    private TermDaoImpl termDao;
    private CourseDaoImpl courseDao;
    private Course course;
    private Instructor instructor;
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
                    req.getSession().setAttribute("id",Integer.toString(termID));
                    req.getRequestDispatcher("TermAdd.jsp").forward(req, resp);
                    break;
                case "analyzeRawGET":
                    req.getSession().setAttribute("rawList", courseDao.getCourseSORaw(1,1));
                    req.getSession().setAttribute("meanList", courseDao.getCourseSOMean(1,1));
                    req.getSession().setAttribute("medianList", courseDao.getCourseSOMedian(1,1));
                    req.getRequestDispatcher("analysis_swp.jsp").forward(req, resp);
                    break;
                case "assocCourseTermGET":
                    termIDParm = req.getParameter(("id"));
                    termID = Integer.parseInt(termIDParm);
                    term = termDao.getTerm(termID);
                    req.getSession().setAttribute("courseList", courseDao.getAllCourses());
                    req.getRequestDispatcher("assocCourse2term.jsp").forward(req,resp);
                    break;
                case "assocInstructorGET":
                    termIDParm = req.getParameter(("id"));
                    termID = Integer.parseInt(termIDParm);
                    term = termDao.getTerm(termID);
                    courseIDParm = req.getParameter(("cid"));
                    courseID = Integer.parseInt(courseIDParm);
                    course = courseDao.getCourse(courseID);
                    req.getRequestDispatcher("assocCourse2term.jsp").forward(req,resp);
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
                    int TermYear = Integer.parseInt(req.getParameter("termYear"));
                    int TermID = Integer.parseInt(req.getParameter("termID"));
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
                            req.getSession().setAttribute("Name", term.getTermName());
                            req.getSession().setAttribute("Year", term.getTermYear());
                            req.getRequestDispatcher("term.jsp").forward(req, resp);
                        }
                    }
                    break;
                case "assocCourseTermPOST":
                    termIDParm = req.getParameter(("id"));
                    termID = Integer.parseInt(termIDParm);
                    term = termDao.getTerm(termID);
                    courseIDParm = req.getParameter(("cid"));
                    courseID = Integer.parseInt(courseIDParm);
                    if (termDao.assocCourse(termID, courseID))
                    {
                        req.getSession().setAttribute("termList",termDao.getAllTerms());
                        req.getSession().setAttribute("id",term.getTermId());
                        req.getSession().setAttribute("cid", course.getCourseId());
                        req.getRequestDispatcher("assocCourse2term.jsp").forward(req,resp);
                    }else{
                        req.getSession().setAttribute("update",true);
                        req.getRequestDispatcher("termFailurePage.jsp").forward(req, resp);
                    }
                    break;
                case "assocInstructorPOST ":
                    termIDParm = req.getParameter(("id"));
                    termID = Integer.parseInt(termIDParm);
                    term = termDao.getTerm(termID);
                    courseIDParm = req.getParameter(("cid"));
                    courseID = Integer.parseInt(courseIDParm);
                    course = courseDao.getCourse(courseID);
                    if (courseDao.associateInstructor(instructor,termID,courseID))
                    {
                        req.getSession().setAttribute("termList",termDao.getAllTerms());
                        req.getSession().setAttribute("id",term.getTermId());
                        req.getSession().setAttribute("cid",course.getCourseId());
                        req.getRequestDispatcher("assocInstructor2term.jsp").forward(req,resp);

                    }else{
                        req.getSession().setAttribute("update",true);
                        req.getRequestDispatcher("termFailurePage.jsp").forward(req, resp);
                    }
                    break;
                    }






            }
        }
    }


