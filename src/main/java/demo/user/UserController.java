package demo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: Niko Zhao
 * @Date: Create in 15:06 10/26/17
 * @Email:
 */
@RequestMapping("/demo/user")
@Controller
public class UserController {
    public ModelAndView userPage(){
        return new ModelAndView("demo/user");
    }

    public static void main(String[] args){
        System.out.println(System.getProperty("java.io.tmpdir"));
    }
}
