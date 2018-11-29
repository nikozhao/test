package demo.creeper.cron;

import demo.creeper.dao.entry.FundStockTotal;
import demo.creeper.dao.entry.StockWorkday;
import demo.creeper.dao.repository.FundStockTotalRepository;
import demo.creeper.dao.repository.StockWorkdayRepository;
import demo.creeper.poweron.PowerOn;
import demo.creeper.service.FundStockTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/19/18
 * @Email:
 */

/**
 * the timer for crawl data from HTF(汇添富基金) web and total the data into FundStockTotal
 */
@Configuration
@EnableScheduling
public class HTFCron {
    @Autowired
    StockWorkdayRepository stockWorkdayRepository;
    @Autowired
    FundStockTotalRepository fundStockTotalRepository;
    //
    @Autowired
    PowerOn powerOn;
    @Autowired
    FundStockTotalService fundStockTotalService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void creeperAndTotal(){
        StockWorkday stockWorkday = stockWorkdayRepository.getLastFive().get(0);
        List<FundStockTotal> fundStockTotals = fundStockTotalRepository.findByDay(stockWorkday.getDay());
        if(ObjectUtils.isEmpty(fundStockTotals)){
            powerOn.powerOn();
            fundStockTotalService.total();
        }
    }
}
