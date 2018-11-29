package demo.creeper.poweron;

import demo.creeper.StockClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/29/18
 * @Email:
 */

/**
 * HTF(汇添富基金) Crawl process impl.
 */
@Component
public class HTFPowerOn implements PowerOn{

    @Autowired
    //the service for pull fund's top 10 stock detail info
    private StockClient creeperService;

    private String getUrl(String stockNo){
        return "http://www.99fund.com/main/products/pofund/"+stockNo+"/fundgk.shtml";
    }

    //interface main method
    public void powerOn(){
        //send http request to get the fund list data
        String url = getUrl("000697");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        List<String> fundNos = new ArrayList<>();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String content =response.body().string();
                Document doc = Jsoup.parse(content);
                Elements elements =doc.getElementsByTag("dd");
                for(int i =0;i<elements.size();i++){
                    fundNos.add(elements.get(i).attr("id"));
                }

            }
        });

        //wait the http request return
        while (fundNos.size()<1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }

        }
        //get the top 10 stock detail info per fund
        for(String fundNo : fundNos){
            try {
                creeperService.pullData(getUrl(fundNo));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
