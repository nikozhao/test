package demo.poker.bo;

import lombok.Data;

/**扑克
 * @Author: Niko Zhao
 * @Date: Create in 05/29/18
 * @Email:
 */
@Data
public class Poker {
    //牌面值
    private Integer value;
    //4种花色
    private Integer type;

    public Poker(Integer value, Integer type) {
        this.value = value;
        this.type = type;
    }
}
