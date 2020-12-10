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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//@BasicAuthenticationMechanismDefinition(realmName="${'jdbc-realm'}")
//@DeclareRoles({ "admin", "publicUser", "root", "instructor" })
//@ServletSecurity(@HttpConstraint(rolesAllowed = "instructor"))
@WebServlet("/InstructorServlet")
public class InstructorServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    private String termIDParm;
    private String courseIDParm;
    private String SWPidParm;
    private String SOidParm;
    private String uname = null;
    private int termID = -1;
    private int courseID = -1;
    private int swpID = -1;
    private int soID = -1;
    private Term term;
    private Student student;
    private StudentWorkProduct SWP;
    private StudentOutcome SO;
    private Course course;
    private Instructor instructor;
    private InstructorDaoImpl instructorDao = new InstructorDaoImpl();
    private TermDaoImpl termDao = new TermDaoImpl();
    private CourseDaoImpl courseDao = new CourseDaoImpl();
    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private StudentOutcomeDaoImpl soDao = new StudentOutcomeDaoImpl();




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("TermList.jsp").forward(req, resp);
        }else {
            switch (req.getParameter("action")){
                case "addStudentsGET":
                    termID = 1;
                    courseID=1;
                    uname = "instructor1";
//                    termID = Integer.parseInt(req.getParameter("termID"));
//                    courseID = Integer.parseInt(req.getParameter("courseID"));
//                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
                    //todo: addstudentsGET, set attributes for page so that the if they go to another page, the terms
                    // and course id will be there
                    req.getRequestDispatcher("Instructor/addStudents.jsp").forward(req, resp);
                    break;
                case "assocStudentsGET":
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
                    req.getSession().setAttribute("students", studentDao.sortStudents(studentDao.getAllStudents()));
                    req.getSession().setAttribute("courses", instructorDao.getCoursesTaught(uname, termID));
                    req.getRequestDispatcher("Instructor/assocStudents.jsp").forward(req, resp);
                    break;
                case "changeCourseGET":
                    /*DOES NOT NEED COURSE ID PASSED*/
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("term", termDao.getLastTerm());
                    req.getSession().setAttribute("courses",
                            instructorDao.getCoursesTaught(uname, termDao.getLastTerm().getTermId()));
                    req.getRequestDispatcher("Instructor/changeCourse.jsp").forward(req, resp);
                    break;
                case "editStudentsGET":
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
                    req.getSession().setAttribute("students", courseDao.getStudents4Course(courseID, termID));
                    req.getRequestDispatcher("Instructor/editStudents.jsp").forward(req,resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("TermList.jsp").forward(req, resp);
        }else{
            switch (req.getParameter("action")){
                case "addStudentsPOST":
                    List<Student> students = new LinkedList<>();
                    int x = 0;
                    while (req.getParameter("fname_labels[new"+x+"][fname]") != null){
                        Student student = new Student();
                        student.setStudentFname(req.getParameter("fname_labels[new"+x+"][fname]"));
                        student.setStudentLname(req.getParameter("lname_labels[new"+x+"][lname]"));
                        students.add(student);
                        x++;
                    }
                    if (studentDao.addStudents(students)) {
                        termID = Integer.parseInt(req.getParameter("termID"));
                        courseID = Integer.parseInt(req.getParameter("courseID"));
                        uname = req.getParameter("uname");
                        req.getSession().setAttribute("termID", termID);
                        req.getSession().setAttribute("courseID",courseID);
                        req.getSession().setAttribute("uname", uname);
                        req.getSession().setAttribute("students", studentDao.getAllStudents());
                        req.getSession().setAttribute("courses", instructorDao.getCoursesTaught(uname, termID));
                        req.getRequestDispatcher("Instructor/assocStudents.jsp").forward(req,resp);
                    }
                    break;
                case "assocStudentsPOST":
                    List<Student> studentList = new LinkedList<>();
                    if(courseDao.associateStudents(studentList,courseID,termID)) {
                        termID = Integer.parseInt(req.getParameter("termID"));
                        courseID = Integer.parseInt(req.getParameter("courseID"));
                        uname = req.getParameter("uname");
                        req.getSession().setAttribute("termID", termID);
                        req.getSession().setAttribute("courseID",courseID);
                        req.getSession().setAttribute("uname", uname);

                        req.getRequestDispatcher("assocInstructor2term.jsp").forward(req,resp);
                    } else {
                        req.getSession().setAttribute("message","student Association failed, courseDao.associateStudents returned false");
                        req.getRequestDispatcher("failure_page.jsp").forward(req, resp);}
                    break;

                case "changeCoursePOST":
                    /*START PASSING TERM,UNAME, AND COURSEID HERE*/
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
                    //todo finish what page to redirect to next?
                    break;
                case "editStudentsPOST":
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
                    //todo finish -- go back to students list?
                    break;
            }
        }
    }
}