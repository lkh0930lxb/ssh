package cn.itcast.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.BaseDict;
import cn.itcast.domain.Customer;
import cn.itcast.service.BaseDictService;
import cn.itcast.service.CustomerService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
@Namespace("/customer")
@ParentPackage("struts-default")
@Results({
			@Result(type="redirectAction",location="findAll"),
			@Result(name="findAll",location="/jsp/customer/list.jsp"),
			@Result(name="addUI",location="/jsp/customer/add.jsp"),
			@Result(name="editUI",location="/jsp/customer/edit.jsp")
		})
@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	BaseDictService baseDictService;
	
	
	//存放客户来源
	private List<BaseDict> custSourceList;
	
	//存放客户级别
	private List<BaseDict> custLevleList;
	
	//存放所属行业
	private List<BaseDict> custIndustryList;
	
	/**
	 * 查询所有的客户 为linkman准备
	 * @throws IOException 
	 */
	@Action("findAll4LinkmanByAjax")
	public void findAll4LinkmanByAjax() throws IOException{
		//1.调用service查询所有的客户
		List<Customer> list = customerService.findAll(DetachedCriteria.forClass(Customer.class));
		
		//2.将list转成json返回浏览器
		//排除一些属性
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"custSource","custIndustry","custLevel","custPhone","custMobile","linkmans"});
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(JSONArray.fromObject(list, config));
	}
	
	
	/**
	 * 删除
	 * @return
	 */
	@Action("delete")
	public String delete(){
		customerService.delete(customer);
		return "success";
	}
	
	@Action("findById")
	/**
	 * 通过id查询一个客户
	 * @return
	 */
	public String findById(){
		//1.调用service查询对象
		Customer customer_ = customerService.findById(customer.getCustId());
		//customer = customerService.findById(customer.getCustId());
		
		//2.将对象放入值栈中 转发到edit.jsp
		ActionContext.getContext().getValueStack().push(customer_);
		
		//3.查询三个字典集合
		custIndustryList = baseDictService.findByDictTypeCode("001");
		custSourceList  = baseDictService.findByDictTypeCode("002");
		custLevleList  = baseDictService.findByDictTypeCode("006");
		return "editUI";
	}
	
	@Action("findAll")
	/**
	 * 查询列表
	 * @return
	 */
	public String findAll(){
		//1.创建离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		//判断条件是否为空
		if(customer.getCustName()!=null && customer.getCustName().trim().length()>0){
			//dc.add(Restrictions.like("custName", "%"+customer.getCustName().trim()+"%"));
			dc.add(Restrictions.like("custName",customer.getCustName().trim(), MatchMode.ANYWHERE));
		}
		
		//2.调用service完成查询操作 返回值:list
		List<Customer> list = customerService.findAll(dc);
		
		//3.将list放入值栈中 转发到list.jsp
		ActionContext.getContext().getValueStack().set("list", list);
		return "findAll";
	}
	
	/**
	 * 更新
	 * @return
	 */
	@Action("update")
	public String update(){
		customerService.update(customer);
		return "success";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@Action("save")
	public String save(){
		customerService.save(customer);
		return "success";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@Action("addUI")
	public String addUI(){
		//查询三个字典表的集合
		custIndustryList = baseDictService.findByDictTypeCode("001");
		custSourceList  = baseDictService.findByDictTypeCode("002");
		custLevleList  = baseDictService.findByDictTypeCode("006");
		return "addUI";
	}

	public List<BaseDict> getCustSourceList() {
		return custSourceList;
	}

	public void setCustSourceList(List<BaseDict> custSourceList) {
		this.custSourceList = custSourceList;
	}

	public List<BaseDict> getCustLevleList() {
		return custLevleList;
	}

	public void setCustLevleList(List<BaseDict> custLevleList) {
		this.custLevleList = custLevleList;
	}

	public List<BaseDict> getCustIndustryList() {
		return custIndustryList;
	}

	public void setCustIndustryList(List<BaseDict> custIndustryList) {
		this.custIndustryList = custIndustryList;
	}
	
	

}
