package web;

import Data.*;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.apache.catalina.authenticator.FormAuthenticator;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.mbeans.MemoryUserDatabaseMBean;
import org.apache.catalina.mbeans.UserMBean;
import org.apache.catalina.realm.DataSourceRealm;
import org.apache.catalina.realm.UserDatabaseRealm;

import javax.annotation.Resource;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
       loginPage="/User/login.jsp"

   )
)
*/
/*
@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage="/login‑servlet",
                errorPage="/error",
                useForwardToLoginExpression="${appConfig.forward}"
        )
)
@ApplicationScoped
/*@WebServlet("/admin")
@DeclareRoles({ "admin", "user", "demo" })
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
*/
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Resource
    private DataSource resource;
    private static final long serialVersionUID = 1L;
    private TermDaoImpl termDao = new TermDaoImpl();
    private InstructorDaoImpl instructorDao = new InstructorDaoImpl();
    private PublicUserDaoImpl publicUserDao = new PublicUserDaoImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("TermList.jsp").forward(req, resp);
        } else {
            switch (req.getParameter("action")) {
                case "loginGET":
                    req.getRequestDispatcher("User/login.jsp").forward(req, resp);
                    break;
                default:
                    req.getRequestDispatcher("home_page.jsp").forward(req, resp);
            }
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") == null) {
            req.getRequestDispatcher("TermList.jsp").forward(req, resp);
        } else {
            switch (req.getParameter("action")) {
                case "loginPOST":
                    String username = req.getParameter("j_username");
                    String password = req.getParameter("j_password");
                    Map<String, String> messages = new HashMap<String, String>();
                    if (username == null || username.isEmpty()) {
                        messages.put("username", "please enter username");
                    }
                    if (password == null || password.isEmpty()) {
                        messages.put("password", "Please enter password");
                    }

                    if (messages.isEmpty()) {
                        Connection conn = ConnectionFactory.getConnection();
                        try {
                            PreparedStatement ps = conn.prepareStatement("select * from user " +
                                    "where user_uname = ? AND user_pw = ?;");
                            ps.setString(1, username);
                            ps.setString(2, password);
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) {
                                switch (rs.getString("user_type")) {
                                    case "admin":
                                        req.getSession().setAttribute("uname", username);
                                        resp.sendRedirect("AdminServlet?action=getAllTerms");
                                        break;
                                    case "root":
                                        req.getSession().setAttribute("pubUsers",publicUserDao.getAllPublicUsers());
                                        req.getRequestDispatcher("Root/publicUserList.jsp").forward(req,resp);
                                        break;
                                    case "instructor":
                                        req.getSession().setAttribute("uname", username);
                                        req.getSession().setAttribute("term", termDao.getLastTerm());
                                        req.getSession().setAttribute("courses",
                                                instructorDao.getCoursesTaught(username, termDao.getLastTerm().getTermId()));
                                        req.getRequestDispatcher("Instructor/changeCourse.jsp").forward(req, resp);
                                        break;
                                }
                                return;
                            } else {
                                messages.put("login", "Unknown login, please try again");
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }

                    req.getSession().setAttribute("messages", messages);
                    req.getRequestDispatcher("User/login.jsp").forward(req, resp);
                    break;
                default:
                    break;
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
