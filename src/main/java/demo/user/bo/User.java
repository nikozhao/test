package demo.user.bo;

import java.io.Serializable;

/**
 * @Author: Niko Zhao
 * @Date: Create in 15:12 10/26/17
 * @Email:
 */
public class User implements Serializable {
    private String userId;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
