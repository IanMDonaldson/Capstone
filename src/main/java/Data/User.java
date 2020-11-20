package Data;

public class User {
    private String userUname;
    private String userPw;
    private String userFname;

    public String getUserUname() {
        return userUname;
    }

    public void setUserUname(String userUname) {
        this.userUname = userUname;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUsersLname() {
        return usersLname;
    }

    public void setUsersLname(String usersLname) {
        this.usersLname = usersLname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private String usersLname;
    private String userType;
}
