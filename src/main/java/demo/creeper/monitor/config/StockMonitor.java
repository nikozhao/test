package demo.creeper.monitor.config;

import demo.creeper.dao.entry.StockDay;
import demo.creeper.dao.entry.StockWorkday;
import demo.creeper.dao.repository.StockDayRepository;
import demo.creeper.dao.repository.StockWorkdayRepository;
import demo.creeper.monitor.bo.Threshold;
import demo.creeper.monitor.callback.MonitorCallBack;
import demo.creeper.monitor.util.StockUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/10/18
 * @Email: nikoz@synnex.com
 */
@Configuration
@EnableScheduling
public class StockMonitor implements InitializingBean {

    public String stockNos = "sh600887,sz000651";
    public static String url = "http://hq.sinajs.cn/list=";
    @Autowired
    MonitorCallBack monitorCallBack;

    @Autowired
    private StockDayRepository stockDayRepository;

    @Autowired
    StockWorkdayRepository stockWorkdayRepository;


    @Scheduled(cron = "0 */1 * * * ?")
    public void monitor() {
        if(!checkTime()){
            String url = geturl();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(monitorCallBack);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StringBuffer sb = new StringBuffer();
       Date start = getLastFive();
        List<StockDay> stockDays = stockDayRepository.getStockDay(start);
        if (!ObjectUtils.isEmpty(stockDays)) {
            stockDays.forEach(stockDay -> {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(StockUtil.getSinaStockNo(stockDay.getStockNo()));
                if (ObjectUtils.isEmpty(Threshold.MAX.get(stockDay.getStockNo()))
                        || Threshold.MAX.get(stockDay.getStockNo()) < stockDay.getMax().doubleValue()) {
                    Threshold.MAX.put(stockDay.getStockNo(), stockDay.getMax().doubleValue());

                }
                if (ObjectUtils.isEmpty(Threshold.MIN.get(stockDay.getStockNo()))
                        || Threshold.MIN.get(stockDay.getStockNo()) < stockDay.getMin().doubleValue()) {
                    Threshold.MIN.put(stockDay.getStockNo(), stockDay.getMin().doubleValue());
                }
            });
            this.stockNos = sb.toString();
        }
    }

    private Date getLastFive() {
        List<StockWorkday> stockWorkdays =stockWorkdayRepository.getLastFive();
        return new Date(stockWorkdays.get(stockWorkdays.size()-1).getDay().getTime());
    }

    private String geturl() {
        return url + stockNos;
    }

    private Boolean checkTime() {
        boolean f = false;
        Calendar cl =Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
        String[] dateStrings = sdf.format(cl.getTime()).split(":");
        if(Integer.parseInt(dateStrings[0])>=15){
            f= true;
        }
        return f;
    }
}
