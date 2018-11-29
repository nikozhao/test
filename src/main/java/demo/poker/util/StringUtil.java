package demo.poker.util;

/**
 * @Author: Niko Zhao
 * @Date: Create in 05/29/18
 * @Email:
 */
public class StringUtil {
    public static String getTwoDigits(int a) {
        if (a < 10) {
            return "0" + a;
        } else {
            return a + "";
        }
    }
}
