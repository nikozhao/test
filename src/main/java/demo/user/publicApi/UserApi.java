package demo.user.publicApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import demo.user.bo.User;
import demo.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Niko Zhao
 * @Date: Create in 15:09 10/26/17
 * @Email:
 */
@RestController
@RequestMapping("/user1")
public class UserApi {
    @Autowired
    UserService userService;

    @RequestMapping(value="/ss",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String save(User user) {
        return userService.save(user);
    }

    @RequestMapping(value="/userName/{userName}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object get(@PathVariable String userName) {
        Map map = new HashMap();
        map.put("111","222");
        return map;
    }
}
