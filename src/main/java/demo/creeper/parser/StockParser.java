package demo.creeper.parser;

import demo.creeper.FundMasterHoler;

/** the interface for parse web data into FundMasterHoler
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email:
 */
public interface StockParser {
    FundMasterHoler parserData(String content);
}
