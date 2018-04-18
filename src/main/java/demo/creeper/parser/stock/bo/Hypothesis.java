package demo.creeper.parser.stock.bo;

import lombok.Data;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email: nikoz@synnex.com
 */
@Data
public class Hypothesis {
    private String stockNo;
    private String stockName;
    private Double percentStock;
}
