package demo.creeper.dao.repository;

import demo.creeper.dao.entry.FundStockTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/19/18
 * @Email:
 */
public interface FundStockTotalRepository extends JpaRepository<FundStockTotal, Integer> {

    @Query(value ="select d.stock_no as stock_no,sum(d.percent_stock*f.fund_money)/100000000 as money" +
            " from fund_stock_detail d  left join fund f on d.fund_no = f.fund_no " +
            " group by d.stock_no" ,nativeQuery = true)
    List<Map> total();

    List<FundStockTotal> findByDay(Timestamp day);
}
