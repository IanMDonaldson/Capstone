package web;

//import Data.Admin;
//import Data.AdminDaoImpl;
//import Data.Instructor;
//import Data.InstructorDaoImpl;


//import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
/////////A REALM is a database of users and groups///////////////////
@BasicAuthenticationMechanismDefinition(realmName="${'user‑realm'}")
@WebServlet("/user")
@DeclareRoles({ "admin", "user", "demo" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "user"))
=============================================================
////////////    @LoginToContinue - configures the app's login and error page

@FormAuthenticationMechanismDefinition(
   loginToContinue = @LoginToContinue(
       loginPage="/login‑servlet",
       errorPage="/error",
       useForwardToLoginExpression="${appConfig.forward}"
   )
)
@ApplicationScoped
=======================================================
/////////////  Intended to better align with JavaServer Pages (JSF)

@CustomFormAuthenticationMechanismDefinition(
   loginToContinue = @LoginToContinue(
       loginPage="/login.do"
   )
)
@WebServlet("/admin")
@DeclareRoles({ "admin", "user", "demo" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
*/
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("action") == null ) {
            request.getRequestDispatcher("home_page.jsp").forward(request, response);
        } else {
            switch (request.getParameter("action")) {
                case "loginPage":

                    request.getRequestDispatcher("User/login.jsp").forward(request, response);
                    break;
                case "registerPage":
                    request.getRequestDispatcher("User/register.jsp").forward(request, response);
                    break;
                case "login":
                    break;
                default:

                    request.getRequestDispatcher("home_page.jsp").forward(request, response);
                    break;
            }
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action") == null ) {
            request.getRequestDispatcher("home_page.jsp").forward(request, response);
        } else {
            switch (request.getParameter("action")) {
                case "loginPOST":
                    if (request.getParameter("access_Level").equals("instructor")) {
                      //  InstructorDaoImpl instImpl = new InstructorDaoImpl();
                      //  Instructor instructor = new Instructor();
                        instructor.setUsername(request.getParameter("username"));
                        instructor.setPassword(request.getParameter("password"));
                        if (instImpl.instructorExists(instructor)) {
                            request.getRequestDispatcher("home_page.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("failure_page.jsp").forward(request, response);
                        }
                    } else {
                        AdminDaoImpl adminImpl = new AdminDaoImpl();
                        Admin admin = new Admin();
                        admin.setUsername(request.getParameter("username"));
                        admin.setPassword(request.getParameter("password"));
                        if (adminImpl.adminExists(admin)) {
                            request.getRequestDispatcher("home_page.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("failure_page.jsp").forward(request, response);
                        }
                    }
                    break;
                case "registerPOST":

                    break;
                default:
                    request.getRequestDispatcher("failure_page.jsp").forward(request, response);

            }
        }
    }
}

/*
* IDENTITY STORE - it's just a database that stores user data like username, password or anything else
*                   used to verify credentials
* IdentityStore - intended to work with HttpAuthenticationMechanism but not required, IE it can stand alone
*   - Includes an IdentityStoreHandler interface which is delegated by the HttpAuthenticationMechanism to validate users
*          - IdentityStoreHandler then calls on an instance of IdentityStore
* IdentityStoreHandler - can authenticate against multiple IdentityStore instances, which are executed in
*                       order by the priority of each IdentityStore instance
*
* Configuring a database identity Store
@DatabaseIdentityStoreDefinition(
   dataSourceLookup = "${'java:global/permissions_db'}",
   callerQuery = "#{'select password from caller where name = ?'}",
   groupsQuery = "select group_name from caller_groups where caller_name = ?",
   hashAlgorithm = PasswordHash.class,
   priority = 10
)
@ApplicationScoped
@Named
public class ApplicationConfig { ... }
* * * Configuring an LDAP identity store
@LdapIdentityStoreDefinition(
   url = "ldap://localhost:33389/",
   callerBaseDn = "ou=caller,dc=jsr375,dc=net",
   groupSearchBase = "ou=group,dc=jsr375,dc=net"
)
@DeclareRoles({ "admin", "user", "demo" })
@WebServlet("/admin")
public class AdminServlet extends HttpServlet { ... }
* 
*
*  */
