package web;
import Data.*;
import Data.Login;

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

/*@BasicAuthenticationMechanismDefinition(realmName="${'jdbc/dcia'}")
@DeclareRoles({ "admin", "publicUser", "root", "instructor" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "instructor"))*/
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
    private int studentID = -1;
    private int swpID = -1;
    private int soID = -1;
    private Term term = new Term();
    private Student student = new Student();
    private StudentWorkProduct SWP = new StudentWorkProduct();
    private StudentOutcome SO = new StudentOutcome();
    private Course course = new Course();
    private Instructor instructor = new Instructor();
    private InstructorDaoImpl instructorDao = new InstructorDaoImpl();
    private TermDaoImpl termDao = new TermDaoImpl();
    private CourseDaoImpl courseDao = new CourseDaoImpl();
    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private SwpDaoImpl swpDao = new SwpDaoImpl();
    private StudentOutcomeDaoImpl soDao = new StudentOutcomeDaoImpl();
    private List<StudentOutcome> soList = new LinkedList<>();




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("TermList.jsp").forward(req, resp);
        }else {
            switch (req.getParameter("action")){
                case "addStudentsGET":

                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
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
                    termID = Integer.parseInt(req.getParameter("termID"));
                    uname = req.getParameter("uname");

                    req.getSession().setAttribute("term", termDao.getLastTerm());
                    req.getSession().setAttribute("courses",
                            instructorDao.getCoursesTaught(uname, termDao.getLastTerm().getTermId()));
                    req.getRequestDispatcher("Instructor/changeCourse.jsp").forward(req, resp);
                    break;
                case "createSwpGET":
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID", courseID);
                    req.getSession().setAttribute("uname", uname);
                    req.getSession().setAttribute("studentOutcomes", soDao.getAllSO());
                    req.getRequestDispatcher("Instructor/createSwp.jsp").forward(req,resp);
                    break;
                case "editStudentGET":
                    studentID = Integer.parseInt(req.getParameter("studentID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    termID = Integer.parseInt(req.getParameter("termID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
                    req.getSession().setAttribute("student", studentDao.getStudent(studentID));
                    req.getRequestDispatcher("Instructor/editStudent.jsp").forward(req,resp);
                    break;
                case "deleteStudentGET":
                    studentID = Integer.parseInt(req.getParameter("studentID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    termID = Integer.parseInt(req.getParameter("termID"));
                    uname = req.getParameter("uname");
                    if (studentDao.deleteStudent(studentID)) {
                        req.getSession().setAttribute("message", "Student Deletion Successful");
                        req.getSession().setAttribute("term", termDao.getTerm(Integer.parseInt(req.getParameter("termID"))));
                        req.getSession().setAttribute("course", courseDao.getCourse(Integer.parseInt(req.getParameter("courseID"))));
                        req.getSession().setAttribute("uname", uname);
                        req.getSession().setAttribute("students", courseDao.getStudents4Course(courseID, termID));
                        req.getRequestDispatcher("Instructor/studentList.jsp").forward(req,resp);
                    } else {
                        req.getSession().setAttribute("message", "Student Deletion Failed!");
                        req.getRequestDispatcher("failure_page.jsp").forward(req,resp);
                    }
                    break;
                case "gradebookGET":
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
                    req.getSession().setAttribute("students", studentDao.sortStudents(studentDao.getStudentsEnrolled2Course(courseID, termID)));
                    req.getSession().setAttribute("swpNames", courseDao.getSwpNames(courseID, termID));
                    req.getRequestDispatcher("Instructor/gradebook.jsp").forward(req,resp);
                    break;
                case "studentListGET":
                    //pass entire course and Term for descriptive output on page
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("term", termDao.getTerm(Integer.parseInt(req.getParameter("termID"))));
                    req.getSession().setAttribute("course", courseDao.getCourse(Integer.parseInt(req.getParameter("courseID"))));
                    req.getSession().setAttribute("uname", uname);
                    req.getSession().setAttribute("students", courseDao.getStudents4Course(courseID, termID));
                    req.getRequestDispatcher("Instructor/studentList.jsp").forward(req,resp);
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
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    List<Student> students = new LinkedList<>();
                    int x = 0;
                    while (req.getParameter("fname_labels[new"+x+"][fname]") != null){
                        Student student = new Student();
                        student.setStudentFname(req.getParameter("fname_labels[new"+x+"][fname]"));
                        student.setStudentLname(req.getParameter("lname_labels[new"+x+"][lname]"));
                        students.add(student);
                        x++;
                    }
                    if (studentDao.addStudents(students,courseID,termID)) {

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

                    Object o = req.getSession().getAttribute("termID");
                    termID = Integer.parseInt(o.toString());
                    o = req.getSession().getAttribute("courseID");
                    courseID = Integer.parseInt(o.toString());
                    o = req.getSession().getAttribute("uname");
                    uname = o.toString();
                    String[] studentIDs = req.getParameterValues("student");
                    for (int i = 0; i< studentIDs.length;i++) {
                        studentList.add(studentDao.getStudent(Integer.parseInt(studentIDs[i])));
                    }
                    if(courseDao.associateStudents(studentList,courseID,termID)) {
                        req.getSession().setAttribute("termID", termID);
                        req.getSession().setAttribute("courseID",courseID);
                        req.getSession().setAttribute("uname", uname);
                        req.getSession().setAttribute("students", courseDao.getStudents4Course(courseID,termID));
                        req.getRequestDispatcher("Instructor/studentList.jsp").forward(req,resp);
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
                    req.getSession().setAttribute("course", courseDao.getCourse(courseID));
                    req.getSession().setAttribute("uname", uname);
                    req.getSession().setAttribute("students", courseDao.getStudents4Course(courseID,termID));
                    req.getRequestDispatcher("Instructor/studentList.jsp").forward(req,resp);
                    break;
                case "createSwpPOST":
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    String swpTitle = req.getParameter("swpTitle");
                    String[] soIDs = req.getParameterValues("soID");
                    //creates blank SWPS for each student
                    if (swpDao.createSwp(swpTitle,courseID,termID,uname)) {

                        for (int i = 0; i < soIDs.length;i++) {
                            soList.add(soDao.getSO(Integer.parseInt(soIDs[i])));
                        }
                        if (swpDao.assocStudentOutcomes(soList,swpDao.getSwpsUnassoc2SO(courseID,termID))) {
                            req.getSession().setAttribute("termID", termID);
                            req.getSession().setAttribute("courseID",courseID);
                            req.getSession().setAttribute("uname", uname);
                            req.getSession().setAttribute("students", studentDao.getStudentsEnrolled2Course(courseID, termID));
                            req.getSession().setAttribute("swpNames", courseDao.getSwpNames(courseID, termID));
                            req.getSession().setAttribute("swpList", courseDao.getSWPs4Course(courseID,termID));
                            req.getRequestDispatcher("Instructor/gradebook.jsp").forward(req,resp);
                        } else {
                            req.getSession().setAttribute("message", "assoc SOs to SWPs failed!");
                            req.getRequestDispatcher("failure_page.jsp").forward(req,resp);
                        }
                    } else {
                        req.getSession().setAttribute("message", "create SWP failed!");
                        req.getRequestDispatcher("failure_page.jsp").forward(req,resp);
                    }
                    break;
                case "editStudentPOST":
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    studentID = Integer.parseInt(req.getParameter("studentID"));
                    student.setStudentId(studentID);
                    student.setStudentFname(req.getParameter("studentFname"));
                    student.setStudentLname(req.getParameter("studentLname"));
                    if (studentDao.updateStudent(student)) {
                        req.getSession().setAttribute("termID", termID);
                        req.getSession().setAttribute("courseID",courseID);
                        req.getSession().setAttribute("uname", uname);
                        req.getSession().setAttribute("students", courseDao.getStudents4Course(courseID,termID));
                        req.getSession().setAttribute("message", "Student Edit Successful!");
                        req.getRequestDispatcher("Instructor/studentList.jsp").forward(req,resp);
                    } else {
                        req.getSession().setAttribute("termID", termID);
                        req.getSession().setAttribute("courseID",courseID);
                        req.getSession().setAttribute("uname", uname);
                        req.getSession().setAttribute("students", courseDao.getStudents4Course(courseID,termID));
                        req.getSession().setAttribute("message", "Student Edit Failed!");
                        req.getRequestDispatcher("Instructor/studentList.jsp").forward(req,resp);

                    }
                    break;
                case "gradebookPOST":
                    List<StudentWorkProduct> swpList = courseDao.getSWPs4Course(courseID,termID);
                    float grade = 0;
                    for (StudentWorkProduct swp : swpList ) {
                        grade = Float.parseFloat(req.getParameter("swp"+swp.getSwpID()));
                        swp.setGrade(grade);
                    }
                    if (swpDao.updateSwpGrades(swpList)) {
                        termID = Integer.parseInt(req.getParameter("termID"));
                        courseID = Integer.parseInt(req.getParameter("courseID"));
                        uname = req.getParameter("uname");
                        req.getSession().setAttribute("termID", termID);
                        req.getSession().setAttribute("courseID",courseID);
                        req.getSession().setAttribute("uname", uname);
                        req.getSession().setAttribute("students", studentDao.sortStudents(studentDao.getStudentsEnrolled2Course(courseID, termID)));
                        req.getSession().setAttribute("swpNames", courseDao.getSwpNames(courseID, termID));
                        req.getRequestDispatcher("Instructor/gradebook.jsp").forward(req,resp);
                    } else {
                        req.getSession().setAttribute("message", "not all SWP grades were updated. Update Swp Grades failed!");
                        req.getRequestDispatcher("failure_page.jsp").forward(req, resp);
                    }
                    break;
                case "studentListPOST":
                    termID = Integer.parseInt(req.getParameter("termID"));
                    courseID = Integer.parseInt(req.getParameter("courseID"));
                    uname = req.getParameter("uname");
                    req.getSession().setAttribute("termID", termID);
                    req.getSession().setAttribute("courseID",courseID);
                    req.getSession().setAttribute("uname", uname);
                    req.getSession().setAttribute("students", courseDao.getStudents4Course(courseID, termID));
                    req.getRequestDispatcher("Instructor/studentList.jsp").forward(req,resp);
                    break;
            }
        }
    }
}