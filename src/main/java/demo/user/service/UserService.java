package demo.user.service;

import demo.user.bo.User;

/**
 * @Author: Niko Zhao
 * @Date: Create in 15:12 10/26/17
 * @Email:
 */
public interface UserService {
    String save(User user);

    User getByUserId(String userId);
}
