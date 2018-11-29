package demo.poker.init;

import demo.poker.config.TexasPokerMaster;
import demo.poker.util.StringUtil;
import org.springframework.stereotype.Component;

/**同花顺的分数在1-8
 * @Author: Niko Zhao
 * @Date: Create in 05/29/18
 * @Email:
 */
@Component
public class StraightFlushPolecy implements InitPolecy{

    public  void polecy(){
        int score=1;
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<5;i++){//1-4表示4中花色
            score=1;
            for(int j=14;j>=6;j--){//14表示A，14-2 表是A-2的牌面值
                sb.delete(0,sb.length());
                sb.append(i).append(StringUtil.getTwoDigits(j))
                .append(i).append(StringUtil.getTwoDigits(j-1))
                .append(i).append(StringUtil.getTwoDigits(j))
                .append(i).append(StringUtil.getTwoDigits(j));
                TexasPokerMaster.holder.put(sb.toString(),score++);
            }
        }
    }
}
