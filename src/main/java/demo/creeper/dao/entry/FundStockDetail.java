package demo.creeper.dao.entry;// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * FundStockDetail entity. @author MyEclipse Persistence Tools
 */

/**
 * fund's top 10 stock detail info
 */
@Entity
@Table(name = "fund_stock_detail", catalog = "test")
public class FundStockDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private String fundNo;
	private String stockNo;
	private BigDecimal percentStock;
	private Timestamp lastUpdate;

	// Constructors

	/** default constructor */
	public FundStockDetail() {
	}

	/** minimal constructor */
	public FundStockDetail(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public FundStockDetail(Integer id, String fundNo, String stockNo,
						   BigDecimal percentStock, Timestamp lastUpdate) {
		this.id = id;
		this.fundNo = fundNo;
		this.stockNo = stockNo;
		this.percentStock = percentStock;
		this.lastUpdate = lastUpdate;
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

	@Column(name = "fund_no")
	public String getFundNo() {
		return this.fundNo;
	}

	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}

	@Column(name = "stock_no")
	public String getStockNo() {
		return this.stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	@Column(name = "percent_stock", precision = 22, scale = 0)
	public BigDecimal getPercentStock() {
		return this.percentStock;
	}

	public void setPercentStock(BigDecimal percentStock) {
		this.percentStock = percentStock;
	}

	@Column(name = "last_update", length = 19)
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}