package demo.poker.init;

import demo.poker.config.TexasPokerMaster;
import org.springframework.stereotype.Component;

/** 炸弹的分值在9 - 9+13*13
 * @Author: Niko Zhao
 * @Date: Create in 05/29/18
 * @Email:
 */
@Component
public class FourOfKindPolecy implements  InitPolecy{
    public void polecy(){
        int score=9;
        StringBuilder sb = new StringBuilder();
        for(int i=14;i>=2;i--){//i炸弹的牌面值
            for(int j=14;j>=2;j--){//j第5张牌的牌面值
                //j的4种花色都一起初始化了
                sb.delete(0,sb.length());
                sb.append(1).append(i).append(2).append(i).append(3).append(i).append(4).append(i).append(1).append(j);
                TexasPokerMaster.holder.put(sb.toString(),score);
                sb.delete(0,sb.length());
                sb.append(1).append(i).append(2).append(i).append(3).append(i).append(4).append(i).append(2).append(j);
                TexasPokerMaster.holder.put(sb.toString(),score);
                sb.delete(0,sb.length());
                sb.append(1).append(i).append(2).append(i).append(3).append(i).append(4).append(i).append(3).append(j);
                TexasPokerMaster.holder.put(sb.toString(),score);
                sb.delete(0,sb.length());
                sb.append(1).append(i).append(2).append(i).append(3).append(i).append(4).append(i).append(4).append(j);
                TexasPokerMaster.holder.put(sb.toString(),score++);
                sb.delete(0,sb.length());
            }
        }
    }
}
