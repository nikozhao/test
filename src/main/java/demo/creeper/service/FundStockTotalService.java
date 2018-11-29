package demo.creeper.service;

import demo.creeper.dao.entry.FundStockTotal;
import demo.creeper.dao.entry.StockWorkday;
import demo.creeper.dao.repository.FundStockTotalRepository;
import demo.creeper.dao.repository.StockWorkdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/** the service for total stock's buy amount of money in fund's top 10 stock
 * @Author: Niko Zhao
 * @Date: Create in 04/19/18
 * @Email:
 */
@Service
public class FundStockTotalService {
    @Autowired
    FundStockTotalRepository fundStockTotalRepository;
    @Autowired
    StockWorkdayRepository stockWorkdayRepository;

    public void total(){
        StockWorkday stockWorkday = stockWorkdayRepository.getLastFive().get(0);
        List<FundStockTotal> count = fundStockTotalRepository.findByDay(stockWorkday.getDay());
        if(!ObjectUtils.isEmpty(count)){
            return ;
        }
        List<Map> fundStockTotals = fundStockTotalRepository.total();
        fundStockTotals.forEach(map->{
            FundStockTotal fundStockTotal = new FundStockTotal();
            fundStockTotal.setMoney((new BigDecimal((Double)map.get("money"))).setScale(2, RoundingMode.DOWN));
            fundStockTotal.setStockNo((String)map.get("stock_no"));
            fundStockTotal.setDay(stockWorkday.getDay());
            fundStockTotalRepository.save(fundStockTotal);
        });
    }

}
