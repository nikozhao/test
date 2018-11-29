package demo.creeper.monitor.callback;

import demo.creeper.monitor.bo.Threshold;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Callback;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/11/18
 * @Email:
 */

/**
 * the callback after the http request to get  the price of stock  real time
 */
@Component
@Slf4j
public class MonitorCallBack implements Callback{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void onFailure(okhttp3.Call call, IOException e) {

    }

    @Override
    public void onResponse(okhttp3.Call call, okhttp3.Response repsonse) throws IOException {
        String context = repsonse.body().string().trim();
        String[] strs = context.split(";");
        for (int i = 0; i < strs.length; i++) {
            String[] data = strs[i].split("=");
            String stockNo = data[0].substring(data[0].length() - 6);
            if (!ObjectUtils.isEmpty(data) && data.length > 0) {
                String[] stocks = data[1].split(",");
                //stocks[4] max price today
                log.info("stockNo: "+stockNo+" max:"+Threshold.MAX.get(stockNo)+" today max:"+Double.parseDouble(stocks[4])+" now:"+Double.parseDouble(stocks[3]));
                if (Double.parseDouble(stocks[3]) > Threshold.MAX.get(stockNo) && Double.parseDouble(stocks[3])>=Double.parseDouble(stocks[4])) {
                    try {
                        if(cheack(stockNo)) {
                            SimpleMailMessage mailMessage = new SimpleMailMessage();
                            mailMessage.setTo("364027009@qq.com");
                            mailMessage.setFrom("364027009@qq.com");
                            mailMessage.setText(stocks[0]+ "max:" + stocks[3]);
                            mailMessage.setSubject(stocks[0] + " ("+stockNo+") " + "max:" + stocks[3]);
                            javaMailSender.send(mailMessage);
                            Threshold.MAIL_DATE.put(stockNo,new Date().getTime());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //stocks[5] min price today
                log.info("stockNo: "+stockNo+" min:"+Threshold.MIN.get(stockNo)+" today min:"+Double.parseDouble(stocks[5])+" now:"+Double.parseDouble(stocks[3]));
                if (Double.parseDouble(stocks[3]) < Threshold.MIN.get(stockNo) && Double.parseDouble(stocks[3])<=Double.parseDouble(stocks[5])) {
                    if(cheack(stockNo)) {
                        try {
                            SimpleMailMessage mimeMessage = new SimpleMailMessage();
                            mimeMessage.setFrom("364027009@qq.com");
                            mimeMessage.setText(stocks[0] + "min:" + stocks[3]);
                            mimeMessage.setSubject(stocks[0] +" ("+stockNo+") " + "min:" + stocks[3]);
                            mimeMessage.setTo("364027009@qq.com");
                            javaMailSender.send(mimeMessage);
                            Threshold.MAIL_DATE.put(stockNo,new Date().getTime());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    //check the time which last send mail by stockNo
    private boolean cheack(String stockNo) {
        if(ObjectUtils.isEmpty(Threshold.MAIL_DATE.get(stockNo))){
            return true;
        }
        Date d = new Date();
        return d.getTime()>Threshold.MAIL_DATE.get(stockNo)+1000*60*60;
    }
}
