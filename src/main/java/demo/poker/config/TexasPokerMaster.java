package demo.poker.config;

import demo.poker.bo.TexasPoker;
import demo.poker.init.InitPolecy;
import demo.poker.util.StringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Niko Zhao
 * @Date: Create in 05/29/18
 * @Email:
 */
@Component
public class TexasPokerMaster implements InitializingBean {
    public static Map<String, Integer> holder = new HashMap<>();

    @Autowired
    InitPolecy fullhousePolecy;
    @Autowired
    InitPolecy fourOfKindPolecy;
    @Autowired
    InitPolecy straightFlushPolecy;

    @Override
    public void afterPropertiesSet() throws Exception {
        fullhousePolecy.polecy();
        fourOfKindPolecy.polecy();
        straightFlushPolecy.polecy();
    }

    /**
     * 获取一首牌的code，所有的牌的组和52*51*50*49*48种可能，每一种牌对应一个code
     *
     * @param texasPoker
     * @return
     */
    public String getTexasPokerCode(TexasPoker texasPoker) {
        StringBuilder sb = new StringBuilder();
        texasPoker.getPokerList().stream().sorted().forEach(a -> {
            sb.append(StringUtil.getTwoDigits(a.getType())).append(StringUtil.getTwoDigits(a.getValue()));
        });
        return sb.toString();
    }


}
