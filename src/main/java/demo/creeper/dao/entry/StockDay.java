package demo.creeper.dao.entry;// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * StockDay entity. @author MyEclipse Persistence Tools
 */

/**
 * the stock's transaction information . include  day,max,min
 */
@Entity
@Table(name="stock_day"
    ,catalog="test"
)

public class StockDay  implements java.io.Serializable {
    // Fields
     private Integer id;
     private String stockNo;
     private Timestamp day;
     private BigDecimal max;
     private BigDecimal min;
    // Constructors

    /** default constructor */
    public StockDay() {
    }

	/** minimal constructor */
    public StockDay(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public StockDay(Integer id, String stockNo, Timestamp day, BigDecimal max, BigDecimal min) {
        this.id = id;
        this.stockNo = stockNo;
        this.day = day;
        this.max = max;
        this.min = min;
    }

   
    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="stock_no")

    public String getStockNo() {
        return this.stockNo;
    }
    
    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }
    
    @Column(name="day", length=19)

    public Timestamp getDay() {
        return this.day;
    }
    
    public void setDay(Timestamp day) {
        this.day = day;
    }
    
    @Column(name="max", precision=22, scale=0)

    public BigDecimal getMax() {
        return this.max;
    }
    
    public void setMax(BigDecimal max) {
        this.max = max;
    }
    
    @Column(name="min", precision=22, scale=0)

    public BigDecimal getMin() {
        return this.min;
    }
    
    public void setMin(BigDecimal min) {
        this.min = min;
    }
   








}