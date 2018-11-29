package demo.poker.bo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**一首德州扑克
 * @Author: Niko Zhao
 * @Date: Create in 05/29/18
 * @Email:
 */
@Data
public class TexasPoker {
    //每一手德州扑克包含5张拍
    private List<Poker> pokerList;
}
