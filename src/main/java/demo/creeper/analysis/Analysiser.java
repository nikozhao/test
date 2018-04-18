package demo.creeper.analysis;

import demo.creeper.FundMasterHoler;
import demo.creeper.dao.entry.Fund;
import demo.creeper.dao.entry.FundStockDetail;
import demo.creeper.dao.entry.Stock;
import demo.creeper.dao.repository.FundRepository;
import demo.creeper.dao.repository.FundStockDetailRepository;
import demo.creeper.dao.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email: nikoz@synnex.com
 */
@Component
@Slf4j
public class Analysiser {

    @Autowired
    private FundRepository fundRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private FundStockDetailRepository fundStockDetailRepository;

    public void analysiser(FundMasterHoler fundMasterHoler){
        analysisFund(fundMasterHoler);
        analysisStock(fundMasterHoler);
        analysisFundStockDetail(fundMasterHoler);
    }

    private void analysisFundStockDetail(FundMasterHoler fundMasterHoler) {
        for(FundStockDetail data : fundMasterHoler.getFundStockDetails()){
            FundStockDetail fundStockDetail = fundStockDetailRepository.findByStockNoAndFundNo(data.getStockNo(),data.getFundNo());
            if(ObjectUtils.isEmpty(fundStockDetail)){
                //BeanUtils.copyProperties(data,fundStockDetail);
                data.setLastUpdate(new Timestamp(new Date().getTime()));
                fundStockDetailRepository.save(data);
            }else{
                Integer id = fundStockDetail.getId();
                BeanUtils.copyProperties(data,fundStockDetail);
                fundStockDetail.setId(id);
                fundStockDetail.setLastUpdate(new Timestamp(new Date().getTime()));
                fundStockDetailRepository.save(fundStockDetail);
            }
        }
    }

    private void analysisStock(FundMasterHoler fundMasterHoler) {
        for(Stock data : fundMasterHoler.getStocks()){
            Optional<Stock> stock = stockRepository.findById(data.getStockNo());
            if(ObjectUtils.isEmpty(stock)){
                stockRepository.save(data);
            }else {

            }
        }
    }

    private void analysisFund(FundMasterHoler fundMasterHoler) {

        Optional<Fund> fund = fundRepository.findById(fundMasterHoler.getFund().getFundNo());
        if(ObjectUtils.isEmpty(fund)){
            fundMasterHoler.getFund().setLastUpdateDatetime(new Timestamp(new Date().getTime()));
            fundRepository.save(fundMasterHoler.getFund());
        }else{
            //BeanUtils.copyProperties(fundMaster.getFund(),fund);
           // fundRepository.save(fund);
        }
    }


}
