package creeper;

import demo.creeper.dao.repository.StockWorkdayRepository;
import demo.poker.bo.Poker;
import demo.poker.bo.TexasPoker;
import demo.poker.config.TexasPokerMaster;
import demo.poker.service.TexasPokerService;
import demo.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/13/18
 * @Email:
 */
@Slf4j
public class PokerTest extends BaseTest {
    @Autowired
    TexasPokerService texasPokerService;

    /**
     * 测试初始化牌的分数。容器启动时加载好所有可能的牌和它们的分数
     */
    @Test
    public void TestInit(){
        log.info(""+TexasPokerMaster.holder.size());
        Assert.assertTrue(TexasPokerMaster.holder.size()>0);
    }

    /**
     * 构造两手牌，一手是4的炸弹加个2   一首是6-10的同花顺
     * 调用TexasPokerService方法比较大小
     */
    @Test
    public void Testcompare(){
        List<Poker> pokersA = Arrays.asList(new Poker(1,4),new Poker(2,4),
                new Poker(3,4),new Poker(4,4),new Poker(1,2));
        List<Poker> pokersB = Arrays.asList(new Poker(1,10),new Poker(1,9),
                new Poker(1,8),new Poker(1,7),new Poker(1,6));
        TexasPoker texasPokerA = new TexasPoker();
        texasPokerA.setPokerList(pokersA);
        TexasPoker texasPokerB = new TexasPoker();
        texasPokerB.setPokerList(pokersB);
        int value = texasPokerService.compare(texasPokerA,texasPokerB);
        log.info(""+value);
        Assert.assertTrue(value==-1);
    }
}
