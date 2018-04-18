package demo.creeper.dao.repository;

import demo.creeper.dao.entry.StockWorkday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/13/18
 * @Email: nikoz@synnex.com
 */
public interface StockWorkdayRepository extends JpaRepository<StockWorkday, String> {
    @Query(value="select w.* from stock_workday w ORDER BY w.day desc limit 0,5",nativeQuery=true)
    List<StockWorkday> getLastFive();

    List<StockWorkday> findByDay(Date date);
}
