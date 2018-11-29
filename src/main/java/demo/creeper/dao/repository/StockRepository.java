package demo.creeper.dao.repository;

import demo.creeper.dao.entry.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/27/18
 * @Email:
 */
public interface StockRepository extends JpaRepository<Stock, String> {

    List<Stock> findByStockNo(@Param("stockNo") String stockNo);
}
