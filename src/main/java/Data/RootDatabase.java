package Data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:mysql://localhost:3306/dcia",
        callerQuery = "#{'select root_pw from root where root_uname = ?'}",
        groupsQuery = "root",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priorityExpression = "#{100}",
        hashAlgorithmParameters = {
                "Pbkdf2PasswordHash.Iterations=3072",
                "${publicUserDatabaseConfig.dyna}"
        }
)
@ApplicationScoped
@Named
public class RootDatabase {
}
