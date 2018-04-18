package demo.creeper.parser.stock;

import demo.creeper.dao.entry.Fund;
import demo.creeper.parser.AbstractStockParser;
import demo.creeper.parser.stock.bo.Hypothesis;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email: nikoz@synnex.com
 */
@Component
public class HTFStockParser extends AbstractStockParser {

    @Override
    protected Fund paraseFund(Document doc) {
        Fund fund = new Fund();
        Element name = doc.getElementsByClass("H1Pro").get(0);
        String fundName = name.html();
        fundName = fundName.substring(0,fundName.indexOf("<span"));
        Element no = name.child(0);
        String noString = no.html().replaceAll("\\(","").replaceAll("\\)","")
                .replaceAll("基金代码","").replaceAll("封闭期","").trim();

        //prase totel money
        String moneyString = doc.getElementsByClass("sharetable intervalbg2").get(0).child(0).child(1).child(1)
                .html().replaceAll(",","");
        double fundMoney =0;
        if(StringUtils.hasText(moneyString)){
            fundMoney = Double.parseDouble(moneyString);
        }
        fund.setFundNo(noString);
        fund.setFundName(fundName);
        fund.setFundMoney(new BigDecimal(fundMoney));
        return fund;
    }


    protected List<Hypothesis> praseZC(Document doc) {
        String html = doc.getElementById("sdgpcc_table").child(0).html();
        List<Hypothesis> data = new ArrayList<>();
        int i=0;
        for (Element tr : doc.getElementById("sdgpcc_table").child(0).children()) {
            if(i==0){
                i++;
                continue;
            }
            Elements tds = tr.children();
            String stockNo= tds.get(1).html();
            String stockName = tds.get(2).html();
            String percentStock = tds.get(3).html();

            Hypothesis hypothesis = new Hypothesis();
            hypothesis.setStockName(stockName);
            if (StringUtils.hasLength(percentStock)) {
                hypothesis.setPercentStock(Double.parseDouble(percentStock));
            }
            if(StringUtils.hasText(stockNo)){
                hypothesis.setStockNo(stockNo);
            }
            data.add(hypothesis);
        }
        return data;
    }


}
