package demo.poker.service;

import demo.poker.bo.TexasPoker;
import demo.poker.config.TexasPokerMaster;
import demo.poker.util.StringUtil;
import org.springframework.stereotype.Service;

/**
 * @Author: Niko Zhao
 * @Date: Create in 05/29/18
 * @Email:
 */
@Service
public class TexasPokerServiceImpl implements TexasPokerService {

    /**
     * 比较两手牌的大小： 去比较两手牌的分数，分数越低牌越大
     * @param texasPokerA
     * @param texasPokerB
     * @return
     */
    public int compare(TexasPoker texasPokerA,TexasPoker texasPokerB){
        String texasPokerACode = getTexasPokerCode(texasPokerA);
        String texasPokerBCode = getTexasPokerCode(texasPokerB);
        Integer texasPokerAScore = TexasPokerMaster.holder.get(texasPokerACode);
        Integer texasPokerBScore = TexasPokerMaster.holder.get(texasPokerBCode);
        return texasPokerAScore.compareTo(texasPokerBScore);
    }

    /**
     * 获取一手牌的code，所有的牌的组和52*51*50*49*48种可能，每一手牌对应一个code
     *
     * @param texasPoker
     * @return
     */
    private String getTexasPokerCode(TexasPoker texasPoker) {
        StringBuilder sb = new StringBuilder();
        texasPoker.getPokerList().stream().sorted().forEach(a -> {
            sb.append(StringUtil.getTwoDigits(a.getType())).append(StringUtil.getTwoDigits(a.getValue()));
        });
        return sb.toString();
    }
}
