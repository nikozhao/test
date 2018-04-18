package demo.creeper.monitor.init;

import demo.creeper.dao.entry.StockMonitor;
import demo.creeper.dao.repository.StockMonitorRepository;
import demo.creeper.monitor.callback.TodayCallBack;
import demo.creeper.monitor.util.StockUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/11/18
 * @Email: nikoz@synnex.com
 */
@Configuration
@EnableScheduling
public class StockTodyIniter{

    @Autowired
    StockMonitorRepository stockMonitorRepository;
    @Autowired
    TodayCallBack todayCallBack;


    @Scheduled(cron = "* */5 * * * ?")
    public void init(){

        Boolean flag = check();
        //flag=true;
        if(flag){
            List<StockMonitor> stockMonitorList = stockMonitorRepository.findAll();
            StringBuffer sb = new StringBuffer();
            AtomicInteger i= new AtomicInteger();
            stockMonitorList.forEach(stockMonitor -> {
                if(sb.length()>0){
                    sb.append(",");
                }
                sb.append(StockUtil.getSinaStockNo(stockMonitor.getStockNo()));
                if(i.get() %10 ==9 ){
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(getUrl(sb.toString())).build();
                    client.newCall(request).enqueue(todayCallBack);
                    sb.delete(0,sb.length());
                }
                i.getAndIncrement();
            });
            if(sb.length()>0){
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(getUrl(sb.toString())).build();
                client.newCall(request).enqueue(todayCallBack);
                sb.delete(0,sb.length());
            }
        }

    }


    public void initString(){
        List<String> strs =Arrays.asList("601828,300465,000732,600559,603555,300431,600438,002558,600600,600690,300369,000100",
                "001979,000651,600019,000876,000895,601766,600048,002697,000418,300104,601318,601088" ,
                  "600597,600519,000858,002415,600606,002035,600585,600887,600779,002127,000333,002405",
                        "300036,601012,000002,601933");
        strs.forEach(stockNos->{
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
        });

    }

    private String getUrl(String stockNo) {
        return StockUtil.url+ stockNo;
    }

    private Boolean check() {
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
