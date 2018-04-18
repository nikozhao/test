package demo.creeper;

import demo.creeper.dao.entry.Fund;
import demo.creeper.dao.entry.FundMaster;
import demo.creeper.dao.entry.FundStockDetail;
import demo.creeper.dao.entry.Stock;
import lombok.Data;

import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email: nikoz@synnex.com
 */
@Data
public class FundMasterHoler {
    private FundMaster fundMaster;
    private Fund fund;
    private List<Stock> stocks;
    private List<FundStockDetail> fundStockDetails;
}
