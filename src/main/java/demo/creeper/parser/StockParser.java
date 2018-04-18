package demo.creeper.parser;

import demo.creeper.FundMasterHoler;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email: nikoz@synnex.com
 */
public interface StockParser {
    FundMasterHoler parserData(String content);
}
