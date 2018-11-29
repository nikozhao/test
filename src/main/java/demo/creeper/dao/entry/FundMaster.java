package demo.creeper.dao.entry;// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FundMaster entity. @author MyEclipse Persistence Tools
 */

/**
 * fund company
 */
@Entity
@Table(name = "fund_master", catalog = "test")
public class FundMaster implements java.io.Serializable {

	// Fields

	private Integer fundMasterNo;
	private String shortCode;
	private String detailName;

	// Constructors

	/** default constructor */
	public FundMaster() {
	}

	/** minimal constructor */
	public FundMaster(Integer fundMasterNo) {
		this.fundMasterNo = fundMasterNo;
	}

	/** full constructor */
	public FundMaster(Integer fundMasterNo, String shortCode, String detailName) {
		this.fundMasterNo = fundMasterNo;
		this.shortCode = shortCode;
		this.detailName = detailName;
	}

	// Property accessors
	@Id
	@Column(name = "fund_master_no", unique = true, nullable = false)
	public Integer getFundMasterNo() {
		return this.fundMasterNo;
	}

	public void setFundMasterNo(Integer fundMasterNo) {
		this.fundMasterNo = fundMasterNo;
	}

	@Column(name = "short_code")
	public String getShortCode() {
		return this.shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	@Column(name = "detail_name")
	public String getDetailName() {
		return this.detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

}