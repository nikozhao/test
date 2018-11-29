package creeper;

import com.alibaba.fastjson.JSON;
import demo.creeper.dao.repository.FundStockTotalRepository;
import demo.creeper.service.FundStockTotalService;
import demo.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/19/18
 * @Email:
 */
@Slf4j
public class FundStockTotalTest extends BaseTest {
    @Autowired
    FundStockTotalRepository fundStockTotalRepository;
    @Autowired
    FundStockTotalService fundStockTotalService;

    @Test
    public void total() {
        //List<Map> fundStockTotals = fundStockTotalRepository.total();
        fundStockTotalService.total();
       // log.debug(ObjectUtils.identityToString(JSON.toJSON(fundStockTotals)));
    }

}
