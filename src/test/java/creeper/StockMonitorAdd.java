package creeper;

import demo.creeper.dao.entry.Stock;
import demo.creeper.dao.entry.StockMonitor;
import demo.creeper.dao.repository.StockMonitorRepository;
import demo.creeper.dao.repository.StockRepository;
import demo.creeper.monitor.bo.Threshold;
import demo.creeper.monitor.callback.TodayCallBack;
import demo.creeper.monitor.init.StockTodyIniter;
import demo.creeper.monitor.util.StockUtil;
import demo.test.BaseTest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/12/18
 * @Email: nikoz@synnex.com
 */

@EnableScheduling
public class StockMonitorAdd extends BaseTest {
    @Autowired
    StockMonitorRepository stockMonitorRepository;
    @Autowired
    StockRepository stockRepository;

    @Autowired
    StockTodyIniter stockTodyIniter;

    @Autowired
    TodayCallBack todayCallBack;

    @Test
    public void addAll(){
        List<Stock> stocks =stockRepository.findAll();
        stocks.forEach(stock -> {
            if(check(stock.getStockNo())){
                List<StockMonitor> s = stockMonitorRepository.findAll();
                List<StockMonitor> stockMonitors = stockMonitorRepository.findByStockNo(stock.getStockNo());
                if(ObjectUtils.isEmpty(stockMonitors)){
                    StockMonitor stockMonitor = new StockMonitor();
                    stockMonitor.setStatus(1);
                    stockMonitor.setStockNo(stock.getStockNo());
                    stockMonitorRepository.save(stockMonitor);
                }
            }
        });
    }

    @Test
    public void add(){
        String stockNos = "601820,300465,000732,600559,603555,300431,600438,002558,600600,600690,300369,000100";
        String [] s =stockNos.split(",");
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<s.length;i++){
            if(sb.length()>0){
                sb.append(",");
            }
            sb.append(StockUtil.getSinaStockNo(s[i]));
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(StockUtil.url+sb.toString()).build();
        client.newCall(request).enqueue(todayCallBack);
    }

    private Boolean check(String stockNo){
        Boolean f = true;
        try{
            Double.parseDouble(stockNo);
            if(stockNo.trim().length()!=6){
                f = false;
            }
        }catch (Exception e){
            f=false;
        }
        return f;
    }



}
