package demo.creeper.dao.entry;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Author: Niko Zhao
 * @Date: Create in 04/19/18
 * @Email:
 */

/**
 * the total info . total stock buy amount in fund's top 10 stock
 */
@Data
@Entity
@Table(name = "fund_stock_total", catalog = "test")
public class FundStockTotal implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "stock_no")
    private String stockNo;
    @Column(name = "money")
    private BigDecimal money;
    @Column(name = "day")
    private Timestamp day;

    public FundStockTotal() {
    }

    public FundStockTotal(String stockNo, BigDecimal money) {
        this.stockNo = stockNo;
        this.money = money;
    }

    public FundStockTotal(Integer id, String stockNo, BigDecimal money, Timestamp day) {
        this.id = id;
        this.stockNo = stockNo;
        this.money = money;
        this.day = day;
    }
}
