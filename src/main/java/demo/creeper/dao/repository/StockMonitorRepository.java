package demo.creeper.dao.repository;

import demo.creeper.dao.entry.StockMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/10/18
 * @Email: nikoz@synnex.com
 */
public interface StockMonitorRepository extends JpaRepository<StockMonitor, String> {

    List<StockMonitor> findByStockNo(@Param("stockNo") String stockNo);
}
