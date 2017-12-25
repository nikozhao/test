package demo.solr.publicApi;

import demo.lock.GobalLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Niko Zhao
 * @Date: Create in 15:35 11/30/17
 * @Email: nikoz@synnex.com
 */
@RestController
public class solrApi {
    @Autowired
    GobalLock gobalLock;

    @RequestMapping(value="/solr/unlock/{key}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean unLock(@PathVariable("key") String key){
        return gobalLock.unLock(key);
    }

    @RequestMapping(value="/solr/expire/{key}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean expire(@PathVariable("key") String key){
        return gobalLock.expire(key,new Long(1000000));
    }

    @RequestMapping(value="/solr/lock/{key}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean test(@PathVariable("key") String key){
        return gobalLock.lock(key);
    }

}
