package demo.creeper.monitor.bo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/10/18
 * @Email:
 */

/**
 * the Threshold(阀值) holder . and the seed the mail's time holder
 */
public class Threshold {
    public static Map<String,Double> MAX = new HashMap<>();
    public static Map<String,Double> MIN = new HashMap<>();
    public static Map<String,Long>  MAIL_DATE = new HashMap<>();
}
