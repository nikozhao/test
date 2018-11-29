package demo.creeper.monitor.util;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/10/18
 * @Email:
 */

/**
 * util to process stockNo
 */
public class StockUtil {
    //url pram to get the real time price of stock
    public static String url = "http://hq.sinajs.cn/list=";
    //get stock string that the sina api need
    public static String getSinaStockNo(String stockNo){
        String sinaStockNo = "";
        if (stockNo.trim().startsWith("00")) {
            sinaStockNo = "sz" + stockNo;
        }
        if (stockNo.trim().startsWith("60")) {
            sinaStockNo = "sh" + stockNo;
        }
        if(stockNo.trim().startsWith("30")){
            sinaStockNo="sz"+stockNo;
        }
        return sinaStockNo;
    }
}
