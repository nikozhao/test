package demo.creeper;

import demo.creeper.analysis.Analysiser;
import demo.creeper.dao.repository.StockRepository;
import demo.creeper.parser.StockParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: Niko Zhao
 * @Date: Create in 15:57 02/26/18
 * @Email: nikoz@synnex.com
 */

@Component("creeperService")

public class StockClient {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockParser stockParser;
    @Autowired
    private Analysiser analysiser;


    String uri = "http://www.99fund.com/main/products/pofund/000697/fundgk.shtml";


    public void pullData(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    FundMasterHoler fundMasterHoler = stockParser.parserData(response.body().string());
                    analysiser.analysiser(fundMasterHoler);
            }
        });
    }



    public static void main(String[] args) {
        //CreeperHttpClientImpl creeperHttpClient = new CreeperHttpClientImpl();
        //creeperHttpClient.pullData(creeperHttpClient.uri);
    }
}
