package demo.creeper.parser;

import demo.creeper.FundMasterHoler;
import demo.creeper.dao.entry.Fund;
import demo.creeper.dao.entry.FundStockDetail;
import demo.creeper.dao.entry.Stock;
import demo.creeper.parser.stock.bo.Hypothesis;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email:
 */

/**
 * the template of stockParser.
 */
public abstract class AbstractStockParser implements StockParser {
    /**
     * it defined the flow to parse data into FundMasterHoler. need impl detail in sub class
     * @param content
     * @return
     */
    @Override
    public FundMasterHoler parserData(String content) {
        FundMasterHoler fundMasterHoler = new FundMasterHoler();
        try {
            Document doc = Jsoup.parse(content);
            Fund fund = paraseFund(doc);
            List<Hypothesis> data = praseZC(doc);
            List<Stock> stocks = new ArrayList<>();
            List<FundStockDetail> fundStockDetails = new ArrayList<>();
            for (Hypothesis hypothesis : data) {
                Stock stock = processStock(hypothesis);
                FundStockDetail fundStockDetail = processFundStockDetail(fund, hypothesis);
                stocks.add(stock);
                fundStockDetails.add(fundStockDetail);
            }
            fundMasterHoler.setFund(fund);
            fundMasterHoler.setStocks(stocks);
            fundMasterHoler.setFundStockDetails(fundStockDetails);
        }catch (Exception e){
            System.out.println("===========>error");
            e.printStackTrace();
        }
        return fundMasterHoler;
    }

    private FundStockDetail processFundStockDetail(Fund fund, Hypothesis hypothesis) {
        FundStockDetail fundStockDetail = new FundStockDetail();
        fundStockDetail.setFundNo(fund.getFundNo());
        fundStockDetail.setStockNo(hypothesis.getStockNo());
        fundStockDetail.setPercentStock(BigDecimal.valueOf(hypothesis.getPercentStock()));
        return fundStockDetail;
    }

    private Stock processStock(Hypothesis hypothesis) {
        Stock stock = new Stock();
        BeanUtils.copyProperties(hypothesis,stock);
        stock.setStockNo(hypothesis.getStockNo());
        return stock;
    }

    protected abstract Fund paraseFund(Document doc);

    protected abstract List<Hypothesis> praseZC(Document doc);


}
