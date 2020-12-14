package Data;

import java.util.List;

public interface PublicUserDao {
    boolean addPublicUser(PublicUser pubUser);
    List<PublicUser> getAllPublicUsers();
    boolean deletePublicUser(String username);
    boolean swapToUserTable(PublicUser pubUser);
    PublicUser getPublicUser(String username);
}
