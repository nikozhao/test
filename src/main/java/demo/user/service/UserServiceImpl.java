package demo.user.service;

import org.springframework.stereotype.Service;
import demo.user.bo.User;

/**
 * @Author: Niko Zhao
 * @Date: Create in 15:12 10/26/17
 * @Email:
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String save(User user) {
        return user.getUserId();
    }

    @Override
    public User getByUserId(String userId) {
        return null;
    }
}
