package creeper;

import demo.creeper.dao.entry.StockWorkday;
import demo.creeper.dao.repository.StockWorkdayRepository;
import demo.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/13/18
 * @Email:
 */
public class StockWorkDayTest extends BaseTest {
    @Autowired
    StockWorkdayRepository stockWorkdayRepository;

    @Test
    public void getLastFive(){
        stockWorkdayRepository.getLastFive();
    }
}
