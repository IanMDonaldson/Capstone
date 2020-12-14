package web;

import Data.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@BasicAuthenticationMechanismDefinition(realmName="${'jdbc-realm'}")
//@WebServlet(name="AdminServlet", urlPatterns={"/admin"})
//@DeclareRoles({ "admin", "publicUser", "root", "instructor" })
//@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    private String termIDParm;
    private String courseIDParm;
    private String instructorIDParm;
    private int termID;
    private int courseID;
    private Term term;
    private TermDaoImpl termDao = new TermDaoImpl();
    private CourseDaoImpl courseDao = new CourseDaoImpl();
    private InstructorDaoImpl instructorDao = new InstructorDaoImpl();
    private Course course;
    private Instructor instructor;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("Admin/Term.jsp").forward(req, resp);
        }
        else {
            TermDaoImpl termDaoImpl = new TermDaoImpl();
            switch (req.getParameter("action")){
                case "getAllTerms":

                    req.getSession().setAttribute("termList",termDao.getAllTerms());
                    req.getRequestDispatcher("Admin/TermList.jsp").forward(req, resp);

                    break;
                case "getTerm":
                    termIDParm = req.getParameter(("id"));
                    termID = Integer.parseInt(termIDParm);
                    req.getSession().setAttribute("id",term.getTermId());
                    req.getSession().setAttribute("termName",term.getTermName());
                    req.getSession().setAttribute("termYear",term.getTermYear());
                    req.getRequestDispatcher("Admin/TermUpdate.jsp").forward(req, resp);
                    break;
                case "addTermGET":
                    req.getSession().setAttribute("id",Integer.toString(termID));
                    req.getRequestDispatcher("Admin/TermAdd.jsp").forward(req, resp);
                    break;
                case"addCourseGet":
                    req.getSession().setAttribute("id",Integer.toString(courseID));
                    req.getRequestDispatcher("Admin/CourseAdd.jsp").forward(req, resp);
                    break;
                case "analyzeRawGET":

                    String[] soNames = courseDao.SOnames(1,1);
                    req.getSession().setAttribute("soNames",soNames);
                    req.getSession().setAttribute("rawList", courseDao.so2Array(courseDao.getCourseSORaw(1,1),soNames));
                    req.getSession().setAttribute("meanList", courseDao.so2Array(courseDao.getCourseSOMean(1,1),soNames));
                    req.getSession().setAttribute("medianList", courseDao.so2Array(courseDao.getCourseSOMedian(1,1),soNames));
                    req.getSession().setAttribute("courseList", courseDao.getAllCourses());
                    req.getSession().setAttribute("termList", termDao.getAllTerms());
                    req.getRequestDispatcher("Admin/analysis_swp.jsp").forward(req, resp);
                    break;
                case "assocCourseTermGET":
                    req.getSession().setAttribute("term", termDao.getLastTerm());
                    req.getSession().setAttribute("courseList", courseDao.getAllCourses());
                    req.getRequestDispatcher("Admin/assocCourse2term.jsp").forward(req,resp);
                    break;
                case "assocInstructorGET":
                    req.getSession().setAttribute("term", termDao.getLastTerm());
                    req.getSession().setAttribute("courseList", courseDao.getCoursesNotAssocWInstructor());
                    req.getSession().setAttribute("instructorList", instructorDao.getAllInstructor());
                    req.getRequestDispatcher("Admin/assocCourse2Instructor.jsp").forward(req,resp);
                    break;
                case "getAllCourse":
                    req.getSession().setAttribute("courseList",courseDao.getAllCourses());
                    req.getRequestDispatcher("Admin/CourseList.jsp").forward(req, resp);
                    break;

            }

        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("Menu.jsp").forward(req, resp);
        }
        else{
            switch (req.getParameter("action")){
                case "addTermPOST":
                    String termName = req.getParameter("termName");
                    int termYear = Integer.parseInt(req.getParameter("termYear"));
                    Term term = new Term();
                    term.setTermName(termName);
                    term.setTermYear(termYear);
                    boolean termExist = termDao.termExists(term);
                    if(termExist){
                        req.getRequestDispatcher("failure_page.jsp").forward(req, resp);
                    }
                    else{
                        if(!termDao.addTerm(term)){
                            req.getSession().setAttribute("add", true);
                            req.getRequestDispatcher("failure_page.jsp").forward(req, resp);
                        }
                        else{
                            req.getSession().setAttribute("Name", term.getTermName());
                            req.getSession().setAttribute("Year", term.getTermYear());
                            req.getSession().setAttribute("termList",termDao.getAllTerms());
                            req.getRequestDispatcher("Admin/TermList.jsp").forward(req, resp);
                        }
                    }
                    break;
                case "addCoursePOST":
                String courseName = req.getParameter("courseName");
                int courseNumber = Integer.parseInt(req.getParameter("courseNumber"));
                String departmetID = req.getParameter("departmentID");
                Course course = new Course();
                course.setCourseTitle(courseName);
                course.setCourseNumber(courseNumber);
                course.setDepartment(departmetID);
                boolean courseExist = courseDao.courseExist(course);
                if(courseExist){
                    req.getRequestDispatcher("failure_page.jsp").forward(req, resp);
                }
                else{
                    if(!courseDao.insertCourse(course)){
                        req.getSession().setAttribute("add", true);
                        req.getRequestDispatcher("failure_page.jsp").forward(req, resp);
                    }
                    else{
                        req.getSession().setAttribute("courseNumber", course.getCourseNumber());
                        req.getSession().setAttribute("Department", course.getDepartment());
                        req.getSession().setAttribute("courseTitle", course.getCourseTitle());
                        req.getSession().setAttribute("courseList",courseDao.getAllCourses());
                        req.getRequestDispatcher("Admin/CourseList.jsp").forward(req, resp);
                    }
                }
                break;


                case "assocCourseTermPOST":
                    termIDParm = req.getParameter(("Term"));
                    termID = Integer.parseInt(termIDParm);
                    term = termDao.getTerm(termID);

                    courseIDParm = req.getParameter(("Cid"));
                    courseID = Integer.parseInt(courseIDParm);
                    if (termDao.assocCourse(termID, courseID))
                    {
                        req.getRequestDispatcher("Admin/TermList.jsp").forward(req,resp);
                    }else{
                        req.getSession().setAttribute("update",true);
                        req.getRequestDispatcher("failure_page.jsp").forward(req, resp);
                    }
                    break;
                case "assocInstructorPOST":

                    termIDParm = req.getParameter(("Term"));
                    termID = Integer.parseInt(termIDParm);
                    courseIDParm = req.getParameter(("Cid"));
                    courseID = Integer.parseInt(courseIDParm);
                    instructorIDParm=req.getParameter("InsID");
                    instructor = new Instructor();
                    instructor.setUsername(instructorIDParm);


                    if (courseDao.associateInstructor(instructor,termID,courseID))
                    {
                        req.getRequestDispatcher("Admin/TermList.jsp").forward(req,resp);

                    }else{
                        req.getSession().setAttribute("update",true);
                        req.getRequestDispatcher("failure_page.jsp").forward(req, resp);
                    }
                    break;
                case "analyzeRawPOST":
                    //change the changeME to the id or name? cant remember which, of the option value on the dropdowns
                    //DROPDOWN DATA - will be given as ARRAY of objects, which i think js can parse
                    // form method=post  action=../AdminServlet?action=analyzeRawPOST
                    String termIDparm = req.getParameter("termID");
                    int termID = Integer.parseInt(termIDparm);
                    String courseIDparm = req.getParameter("courseID");
                    int courseID = Integer.parseInt(courseIDparm);
                    String[] soNames = courseDao.SOnames(courseID, termID);

                    req.getSession().setAttribute("soNames",soNames);
                    req.getSession().setAttribute("rawList", courseDao.so2Array(courseDao.getCourseSORaw(courseID, termID),soNames));
                    req.getSession().setAttribute("meanList", courseDao.so2Array(courseDao.getCourseSOMean(courseID, termID),soNames));
                    req.getSession().setAttribute("medianList", courseDao.so2Array(courseDao.getCourseSOMedian(courseID, termID),soNames));
                    req.getSession().setAttribute("courseList", courseDao.getAllCourses());
                    req.getSession().setAttribute("termList", termDao.getAllTerms());
                    req.getRequestDispatcher("Admin/analysis_swp.jsp").forward(req, resp);
                    break;


                    }






            }
        }
    }


