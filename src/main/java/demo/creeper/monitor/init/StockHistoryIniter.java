package demo.creeper.monitor.init;

import demo.creeper.dao.entry.Stock;
import demo.creeper.dao.entry.StockDay;
import demo.creeper.dao.entry.StockMonitor;
import demo.creeper.dao.repository.StockDayRepository;
import demo.creeper.dao.repository.StockMonitorRepository;
import demo.creeper.monitor.bo.Threshold;
import demo.creeper.monitor.util.StockUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/10/18
 * @Email: nikoz@synnex.com
 */
@Configuration
@EnableScheduling
public class StockHistoryIniter{

    @Autowired
    StockMonitorRepository stockMonitorRepository;
    @Autowired
    StockDayRepository stockDayRepository;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Scheduled(cron = "* */1 21 * * ?")
    public void initHistory() throws Exception{
        List<StockMonitor> stockMonitorList = getMonitoer();
        stockMonitorList.forEach(stockMonitor -> {
            for(int i=-5;i<0;i++){
                String dateString=getDateString(i);
                praseStockDay(dateString,stockMonitor);
            }
        });
    }





    private void praseStockDay(String dateString, StockMonitor stockMonitor) {
        String url = geturl(dateString,stockMonitor);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response repsonse) throws IOException {
                InputStream in  = new ByteArrayInputStream(repsonse.body().bytes());
                Workbook workbook = new HSSFWorkbook(in);
                Sheet sheet = workbook.getSheetAt(0);
                Double max =0.0;
                Double min = 0.0;
                for(int i=0;i<sheet.getLastRowNum();i++){
                    try{
                        Double data = sheet.getRow(i).getCell(1).getNumericCellValue();
                        if(data>max)max = data;
                        if(data<min)min = data;
                    }catch (Exception e){

                    }
                }


            }
        });
    }

    private String getDateString(int i) {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.DATE,i);
        String dateString="";
        try {
            dateString = sdf.format(sdf.parse(sdf.format(cl.getTime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public List<StockMonitor> getMonitoer() {
        List<StockMonitor> stockMonitors =stockMonitorRepository.findAll();
        List<StockMonitor> data = new ArrayList<>();
        stockMonitors.forEach(stockMonitor -> {
            Integer count = stockDayRepository.countByStockNo(stockMonitor.getStockNo());
            if(count>0)
                data.add(stockMonitor);
        });
        return data;
    }

    public String geturl(String dateString, StockMonitor stockMonitor) {
        String url ="http://market.finance.sina.com.cn/downxls.php?date="+dateString+"&symbol=sz002095";
        return url;
    }
}
