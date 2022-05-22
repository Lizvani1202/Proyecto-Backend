package bo.edu.ucb.sis.BackendProy.model;

public class JwtUser {
    private String userName;
    private long userid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
}
