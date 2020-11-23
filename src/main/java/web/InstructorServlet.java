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
import java.util.LinkedList;
import java.util.List;

@BasicAuthenticationMechanismDefinition(realmName="${'jdbc-realm'}")
@WebServlet(name="InstructorServlet", urlPatterns={"/instructor"})
@DeclareRoles({ "admin", "publicUser", "root", "instructor" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "instructor"))
public class InstructorServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    private String termIDParm;
    private String courseIDParm;
    private int termID;
    private int courseID;
    private Term term;
    private Student student;
    private TermDaoImpl termDao;
    private CourseDaoImpl courseDao;
    private StudentDaoImpl stundentDao;
    private StudentOutcomeDaoImpl soDao;
    private Course course;
    private Instructor instructor;
    private StudentOutcome SO;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doGet(req, resp);
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("TermList.jsp").forward(req, resp);
        }else {
            switch (req.getParameter("action")){
                case "assocStudentGET":
                    courseIDParm=req.getParameter("Cid");
                    courseID = Integer.parseInt((courseIDParm));
                    course = courseDao.getCourse(courseID);
                    req.getRequestDispatcher("assocStudent2Course.jsp").forward(req,resp);
                    break;
                case "listStudentOutcomeGET":



            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("TermList.jsp").forward(req, resp);
        }else{
            switch (req.getParameter("action")){
                case "assocStudentPOST":
                    List<Student> studentList = new LinkedList<>();
                    if(courseDao.associateStudents(studentList,courseID,termID))
                    {
                        req.getSession().setAttribute("studentList",courseDao.getAllCourses());
                        req.getSession().setAttribute("id",term.getTermId());
                        req.getSession().setAttribute("cid",course.getCourseId());
                        req.getRequestDispatcher("assocInstructor2term.jsp").forward(req,resp);
                    }else {req.getSession().setAttribute("update",true);
                        req.getRequestDispatcher("termFailurePage.jsp").forward(req, resp);}
                    break;
            }
        }

    }
}