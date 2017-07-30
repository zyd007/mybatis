package qhd.zy.shop.web;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import qhd.zy.shop.Dao.ICategoryDao;
import qhd.zy.shop.Util.RequestUtil;
import qhd.zy.shop.model.Category;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.ShopExecption;

public class CategoryServlet extends BaseServlet{
	private ICategoryDao categoryDao;
	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5855802771683041518L;
	@Auth
	public String list(HttpServletRequest req,HttpServletResponse resp){
		String name=req.getParameter("name");
		List<Category>list=categoryDao.list(name);
		req.setAttribute("categorys", list);
		return "category/list.jsp";
	}

	public String addInput(HttpServletRequest req,HttpServletResponse resp){
		return "category/addInput.jsp";
	}
	public String updateInput(HttpServletRequest req,HttpServletResponse resp){
		Category category=categoryDao.load(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("category",category);
		return "category/updateInput.jsp";
	}
	public String update(HttpServletRequest req,HttpServletResponse resp){
		Category ct=categoryDao.load(Integer.parseInt(req.getParameter("id")));
		Category category=(Category)RequestUtil.set(Category.class, req);
		ct.setName(category.getName());
		if(!RequestUtil.Vaildate(Category.class, req)){
			req.setAttribute("category", ct);
			return "category/updateInput.jsp";
		}
		categoryDao.update(ct);
		return redirect+"category.do?method=show&id="+category.getId();
	}

	public String add(HttpServletRequest req,HttpServletResponse resp){
		Category category=(Category)RequestUtil.set(Category.class, req);
		if(!RequestUtil.Vaildate(Category.class, req)){
			return "category/addInput.jsp";
		}
		categoryDao.add(category);
		return redirect+"category.do?method=list";
	}
	@Auth
	public String show(HttpServletRequest req,HttpServletResponse resp){
		Category category=categoryDao.load(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("category", category);
		return "category/show.jsp";
	}
	public String delete(HttpServletRequest req,HttpServletResponse resp){
		try {
			int id=Integer.parseInt(req.getParameter("id"));
			categoryDao.delete(id);
		} catch (ShopExecption e) {
			req.setAttribute("exception", e.getMessage());
			return "inc/shopException.jsp";
		}
		return redirect+"category.do?method=list";
	}
}
