package demo.creeper.web;

import demo.creeper.StockClient;
import demo.creeper.config.HTFPowerOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/27/18
 * @Email: nikoz@synnex.com
 */
@RestController
public class CreeperAPI {

    @Autowired
    StockClient creeperService;
    @Autowired
    HTFPowerOn powerOn;

    @RequestMapping(value = "/start", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String creeper(){
        //creeperService.pullData("http://www.99fund.com/main/products/pofund/000697/fundgk.shtml");
        powerOn.powerOn();
        return "start";
    }
}
