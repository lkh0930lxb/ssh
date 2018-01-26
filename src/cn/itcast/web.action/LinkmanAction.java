package cn.itcast.web.action;


import java.util.List;

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
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Linkman;
import cn.itcast.service.LinkmanService;
import cn.itcast.utils.PageBean;
@Controller("linkmanAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/linkman")
@Results({
	@Result(name="addUI",location="/jsp/linkman/add.jsp"),
	@Result(name="editUI",location="/jsp/linkman/edit.jsp"),
	@Result(name="findAll",location="/jsp/linkman/list.jsp"),
	@Result(type="redirectAction",location="findAll")
	
})
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman>{
	private Linkman linkman = new Linkman();
	@Override
	public Linkman getModel() {
		return linkman;
	}
	
	@Autowired
	LinkmanService linkmanService;
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@Action("addUI")
	public String addUI(){
		return "addUI";
	}
	
	/**
	 * 保存
	 * @return
	 */
	@Action("save")
	public String save(){
		linkmanService.save(linkman);
		return "success";
	}
	
	/**
	 * 更新
	 * @return
	 */
	@Action("update")
	public String update(){
		linkmanService.update(linkman);
		return "success";
	}
	
	/**
	 * 删除
	 * @return
	 */
	@Action("delete")
	public String delete(){
		linkmanService.delete(linkman);
		return "success";
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	@Action("findAll")
	public String findAll(){
		//1.创建离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Linkman.class);
		
		//判断条件
		if(linkman.getLkmName()!=null &&linkman.getLkmName().trim().length()>0 ){
			dc.add(Restrictions.like("lkmName", linkman.getLkmName().trim(),MatchMode.ANYWHERE));
		}
		if(linkman.getLkmGender()!=null && linkman.getLkmGender().length()>0 ){
			dc.add(Restrictions.eq("lkmGender", linkman.getLkmGender()));
		}
		if(linkman.getCustomer()!=null &&linkman.getCustomer().getCustId()!=null ){
			dc.add(Restrictions.eq("customer.custId", linkman.getCustomer().getCustId()));
		}
		
		
		//2.调用serivce完成查询操作
		List<Linkman> list = linkmanService.findAll(dc);
		
		//3.将list放入值栈中,转发到list.jsp
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "findAll";
	}
	
	private Integer pageNumber = 1;
	private Integer pageSize = 3;
	public void setPageNumber(Integer pageNumber) {
		//判断是否传递了参数,若传递是null使用默认值
		if(pageNumber == null){
			this.pageNumber = 1;
		}else{
			this.pageNumber = pageNumber;
		}
	}
	public void setPageSize(Integer pageSize) {
		//判断是否传递了参数,若传递是null使用默认值
		if(pageSize == null){
			this.pageSize = 3;
		}else{
			this.pageSize = pageSize;
		}
	}

	/**
	 * 分页查询
	 * @return
	 */
	@Action("findByPage")
	public String findByPage(){
		//1.创建离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Linkman.class);
		
		//判断条件
		if(linkman.getLkmName()!=null &&linkman.getLkmName().trim().length()>0 ){
			dc.add(Restrictions.like("lkmName", linkman.getLkmName().trim(),MatchMode.ANYWHERE));
		}
		if(linkman.getLkmGender()!=null && linkman.getLkmGender().length()>0 ){
			dc.add(Restrictions.eq("lkmGender", linkman.getLkmGender()));
		}
		if(linkman.getCustomer()!=null &&linkman.getCustomer().getCustId()!=null ){
			dc.add(Restrictions.eq("customer.custId", linkman.getCustomer().getCustId()));
		}
		
		
		//2.调用serivce完成查询操作
		PageBean<Linkman> pb = linkmanService.findByPage(dc,pageNumber,pageSize);
		
		//3.将pb放入值栈中,转发到list.jsp
		ActionContext.getContext().getValueStack().push(pb);
		
		return "findAll";
	}
	
	/**
	 * 通过id获取一个联系人
	 * @return
	 */
	@Action("findById")
	public String findById(){
		linkman = linkmanService.findById(linkman.getLkmId());
		//放入值栈
		ActionContext.getContext().getValueStack().push(linkman);
		return "editUI";
	}
}
