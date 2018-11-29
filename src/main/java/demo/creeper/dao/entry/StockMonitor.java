package demo.creeper.dao.entry;// default package

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * StockMonitor entity. @author MyEclipse Persistence Tools
 */

/**
 * to show which stock need monitor. monitor the price. I.E..price gte the max last 5 day or the price lte the min last 5 day.
 */
@Entity
@Table(name = "stock_monitor", catalog = "test")
public class StockMonitor implements java.io.Serializable {

	// Fields

	private Integer id;
	private String stockNo;
	private Integer status;

	// Constructors

	/** default constructor */
	public StockMonitor() {
	}

	/** minimal constructor */
	public StockMonitor(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public StockMonitor(Integer id, String stockNo, Integer status) {
		this.id = id;
		this.stockNo = stockNo;
		this.status = status;
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

	@Column(name = "stock_no")
	public String getStockNo() {
		return this.stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}