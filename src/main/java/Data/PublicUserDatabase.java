package Data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:mysql://localhost:3306/dcia",
        callerQuery = "#{'select public_pw from public_user where public_uname = ?'}",
        groupsQuery = "publicUser",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priorityExpression = "#{100}",
        hashAlgorithmParameters = {
                "Pbkdf2PasswordHash.Iterations=3072",
                "${publicUserDatabaseConfig.dyna}"
        }
)
@ApplicationScoped
@Named
public class PublicUserDatabase {
        public String[] getDyna() {
                return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", "Pbkdf2PasswordHash.SaltSizeBytes=64"};
        }
}
