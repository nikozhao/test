package demo.creeper.dao.repository;

import demo.creeper.dao.entry.FundStockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email: nikoz@synnex.com
 */
public interface FundStockDetailRepository extends JpaRepository<FundStockDetail, Integer> {
    FundStockDetail findByStockNoAndFundNo(String stockNo,String fundNo);
}
