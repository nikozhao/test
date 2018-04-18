package demo.creeper.monitor.callback;

import demo.creeper.dao.entry.Stock;
import demo.creeper.dao.entry.StockDay;
import demo.creeper.dao.entry.StockWorkday;
import demo.creeper.dao.repository.StockDayRepository;
import demo.creeper.dao.repository.StockRepository;
import demo.creeper.dao.repository.StockWorkdayRepository;
import demo.creeper.monitor.bo.Threshold;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/11/18
 * @Email: nikoz@synnex.com
 */
@Component
public class TodayCallBack implements Callback {
    @Autowired
    StockDayRepository stockDayRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockWorkdayRepository stockWorkdayRepository;
    @Override
    public void onFailure(okhttp3.Call call, IOException e) {

    }

    @Override
    public void onResponse(okhttp3.Call call, okhttp3.Response repsonse) throws IOException {
        String context = repsonse.body().string().trim();
        String[] strs = context.split(";");
        int index=0;
        for (int i = 0; i < strs.length; i++) {
            String[] data = strs[i].split("=");
            if(data.length<2){
                continue;
            }
            String stockNo = data[0].substring(data[0].length() - 6);
            if (!ObjectUtils.isEmpty(data) && data.length > 0) {
                synchronized (this.getClass()){
                    String[] stocks = data[1].split(",");
                    if(stocks.length<2)continue;
                    List<Stock> tmp = stockRepository.findByStockNo(stockNo);
                    if(ObjectUtils.isEmpty(tmp) ){
                        Stock stock = new Stock();
                        stock.setStockNo(stockNo);
                        stock.setStockName(stocks[0].replaceAll("\"",""));
                        stockRepository.save(stock);
                    }

                    List<StockDay> s = stockDayRepository.findByStockNoAndDay(stockNo,getToday());
                    if(!ObjectUtils.isEmpty(s)){
                        continue;
                    }

                    StockDay stockDay= new StockDay();
                    stockDay.setStockNo(stockNo);
                    stockDay.setMin(new BigDecimal(Double.parseDouble(stocks[5])));
                    stockDay.setMax(new BigDecimal(Double.parseDouble(stocks[4])));
                    stockDay.setDay(new Timestamp(getToday().getTime()));
                    if(Double.parseDouble(stocks[5])>1)
                    stockDayRepository.save(stockDay);
                    index++;

                }
            }
            if(index>0){
                synchronized (this.getClass()){
                    Date date = getToday();
                    List<StockWorkday> stockWorkdays= stockWorkdayRepository.findByDay(getToday());
                    if(ObjectUtils.isEmpty(stockWorkdays)){
                        StockWorkday stockWorkday = new StockWorkday();
                        stockWorkday.setDay(new Timestamp(date.getTime()));
                        stockWorkdayRepository.save(stockWorkday);
                    }
                }
            }
        }
    }

    private Date getToday() {
        Calendar cl = Calendar.getInstance();
        int hour = cl.get(Calendar.HOUR_OF_DAY);
        if(hour<9){
            cl.add(Calendar.DATE, -1);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date =sdf.parse(sdf.format(cl.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}
