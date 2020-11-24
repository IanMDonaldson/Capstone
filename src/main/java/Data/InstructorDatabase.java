package Data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:mysql://localhost:3306/dcia",
        callerQuery = "#{'select instructor_pw from instructor where instructor_uname = ?'}",
        groupsQuery = "instructor",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priorityExpression = "#{100}",
        hashAlgorithmParameters = {
                "Pbkdf2PasswordHash.Iterations=3072",
                "${publicUserDatabaseConfig.dyna}"
        }
)
@ApplicationScoped
@Named
public class InstructorDatabase {
}
