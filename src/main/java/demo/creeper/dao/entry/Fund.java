package demo.creeper.dao.entry;// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Fund entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fund", catalog = "test")
public class Fund implements java.io.Serializable {

	// Fields

	private String fundNo;
	private String fundName;
	private BigDecimal fundMoney;
	private Timestamp lastUpdateDatetime;
	private Integer fundMasterNo;

	// Constructors

	/** default constructor */
	public Fund() {
	}

	/** minimal constructor */
	public Fund(String fundNo) {
		this.fundNo = fundNo;
	}

	/** full constructor */
	public Fund(String fundNo, String fundName, BigDecimal fundMoney,
			Timestamp lastUpdateDatetime, Integer fundMasterNo) {
		this.fundNo = fundNo;
		this.fundName = fundName;
		this.fundMoney = fundMoney;
		this.lastUpdateDatetime = lastUpdateDatetime;
		this.fundMasterNo = fundMasterNo;
	}

	// Property accessors
	@Id
	@Column(name = "fund_no", unique = true, nullable = false)
	public String getFundNo() {
		return this.fundNo;
	}

	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}

	@Column(name = "fund_name")
	public String getFundName() {
		return this.fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	@Column(name = "fund_money", precision = 255, scale = 0)
	public BigDecimal getFundMoney() {
		return this.fundMoney;
	}

	public void setFundMoney(BigDecimal fundMoney) {
		this.fundMoney = fundMoney;
	}

	@Column(name = "last_update_datetime", length = 19)
	public Timestamp getLastUpdateDatetime() {
		return this.lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(Timestamp lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	@Column(name = "fund_master_no")
	public Integer getFundMasterNo() {
		return this.fundMasterNo;
	}

	public void setFundMasterNo(Integer fundMasterNo) {
		this.fundMasterNo = fundMasterNo;
	}

}