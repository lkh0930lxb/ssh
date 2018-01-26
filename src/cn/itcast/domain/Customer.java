package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cst_customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private Long custId;
	@Column(name = "cust_name")
	private String custName;
	
	//表示客户来源
	//@Column(name = "cust_source")
	//private String custSource;
	/**
	 * @ManyToOne:配置多对一
	 * @JoinColumn:配置外键信息
	 * 		name:外键字段名称
	 * 	 	referencedColumnName:外键指向的主表的字段名称(一般是主键)
	 */
	@ManyToOne(targetEntity=BaseDict.class)
	@JoinColumn(name="cust_source",referencedColumnName="dict_id")
	private BaseDict custSource;

	//表示客户所属行业
	//@Column(name = "cust_industry")
	//private String custIndustry;
	@ManyToOne(targetEntity=BaseDict.class)
	@JoinColumn(name="cust_industry",referencedColumnName="dict_id")
	private BaseDict custIndustry;
	
	//表示客户的级别
	//@Column(name = "cust_level")
	//private String custLevel;
	@ManyToOne(targetEntity=BaseDict.class)
	@JoinColumn(name="cust_level",referencedColumnName="dict_id")
	private BaseDict custLevel;
	
	@Column(name = "cust_phone")
	private String custPhone;

	@Column(name = "cust_mobile")
	private String custMobile;
	
	@OneToMany(targetEntity=Linkman.class,mappedBy="customer",cascade=CascadeType.REMOVE)
	private Set<Linkman> linkmans = new HashSet<>();

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public BaseDict getCustSource() {
		return custSource;
	}

	public void setCustSource(BaseDict custSource) {
		this.custSource = custSource;
	}

	public BaseDict getCustIndustry() {
		return custIndustry;
	}

	public void setCustIndustry(BaseDict custIndustry) {
		this.custIndustry = custIndustry;
	}

	public BaseDict getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(BaseDict custLevel) {
		this.custLevel = custLevel;
	}

	public Set<Linkman> getLinkmans() {
		return linkmans;
	}

	public void setLinkmans(Set<Linkman> linkmans) {
		this.linkmans = linkmans;
	}

}
