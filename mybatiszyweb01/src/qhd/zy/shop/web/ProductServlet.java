package qhd.zy.shop.web;



import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import qhd.zy.shop.Dao.ICategoryDao;
import qhd.zy.shop.Dao.ProductDao;
import qhd.zy.shop.Util.RequestUtil;
import qhd.zy.shop.model.Category;
import qhd.zy.shop.model.Product;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.SystemContext;



public class ProductServlet extends BaseServlet{
	private ICategoryDao categoryDao;
	private ProductDao productDao;
	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	@ShopDi("productDao")
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4139383258865290002L;

	@Auth("any")
	public String list(HttpServletRequest req,HttpServletResponse resp){
		req.setAttribute("products",productDao.list(0, null, 0));
		return "product/list.jsp";
	}
	@Auth("any")
	public String show(HttpServletRequest req,HttpServletResponse resp){
		req.setAttribute("p",productDao.load(Integer.parseInt(req.getParameter("id"))));
		return "product/show.jsp";
	}
	public String changeStatus(HttpServletRequest req,HttpServletResponse resp){
		int id=Integer.parseInt(req.getParameter("id"));
		productDao.changeStatus(id);
		return redirect+"product.do?method=list";
	}
	public String delete(HttpServletRequest req,HttpServletResponse resp){
		try {
			productDao.detele(Integer.parseInt(req.getParameter("id")));
		} catch (NumberFormatException e) {
			return this.handleException(e, req);
		}
		return redirect+"product.do?method=list";
	}
	public String addStockInput(HttpServletRequest req,HttpServletResponse resp){
		int id=Integer.parseInt(req.getParameter("id"));
		req.setAttribute("p", productDao.load(id));
		return "product/addStockInput.jsp";
	}
	public String addStock(HttpServletRequest req,HttpServletResponse resp){
		try {
			int id=Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			int munber=Integer.parseInt(req.getParameter("number"));
			productDao.addStock(id, munber);
		} catch (NumberFormatException e) {	
			int id=Integer.parseInt(req.getParameter("id"));
			req.setAttribute("p", productDao.load(id));
			this.getErrors().put("number", "增加库存要为整数!");
			return "product/addStockInput.jsp";
		}
		return redirect+"product.do?method=list";
	}
	public String updateInput(HttpServletRequest req,HttpServletResponse resp){
		List<Category>list=categoryDao.list();
		req.setAttribute("cs", list);
		req.setAttribute("p", productDao.load(Integer.parseInt(req.getParameter("id"))));
		return "product/updateInput.jsp";
	}
	public String update(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		Product np=(Product)RequestUtil.set(Product.class, req);
		RequestUtil.Vaildate(Product.class, req);
		Product op=productDao.load(Integer.parseInt(req.getParameter("id")));
		op.setName(np.getName());
		op.setIntro(np.getIntro());
		op.setPrice(np.getPrice());
		op.setStock(np.getStock());
		int cid=0;
		//用来分辨是否删除照片
		boolean updateimg=false;
		try {
			cid=Integer.parseInt(req.getParameter("cid"));
		} catch (NumberFormatException e) {}
		//设置错误
		if(cid==0){
			this.getErrors().put("cid","商品类别必须选择" );
		}
		//如果判断是否有文件
		if(np.getImg()==null||np.getImg().trim().equals("")){
			
		}else{
			//如果没错误就上传文件
			if(!this.isError()){
				byte[]bt=(byte[])req.getAttribute("bt");
				String fname=req.getParameter("img");
				fname=FilenameUtils.getName(fname);
				//img是存地址
				RequestUtil.FileUpload(fname, "img", bt, req);
				updateimg=true;
			}
		}
		//如果上传了图片就把以前的图片删除
		if(updateimg){
			String path=SystemContext.getRealPath()+"img/"+op.getImg();
			File f=new File(path);
			f.delete();
			//设置名字
			op.setImg(np.getImg());
		}
		//如果错了返回
		if(this.isError()){
			List<Category>list=categoryDao.list();
			req.setAttribute("cs", list);
			req.setAttribute("p", op);
			return "product/updateInput.jsp";
		}
		productDao.update(op, cid);
		return redirect+"product.do?method=list";
	}
	public String addInput(HttpServletRequest req,HttpServletResponse resp){
		List<Category>list=categoryDao.list();
		req.setAttribute("cs", list);
		return "product/addInput.jsp";
	}
	public String add(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		Product p=(Product)RequestUtil.set(Product.class, req);
		p.setStatus(1);
		RequestUtil.Vaildate(Product.class, req);
		int cid=0;
		try {
			cid=Integer.parseInt(req.getParameter("cid"));
		} catch (NumberFormatException e) {}
		//设置错误
		if(cid==0){
			this.getErrors().put("cid","商品类别必须选择" );
		}
		//如果没错误就上传文件
		if(!this.isError()){
			byte[]bt=(byte[])req.getAttribute("bt");
			String fname=req.getParameter("img");
			fname=FilenameUtils.getName(fname);
			//img是表单域的名字(name)
			RequestUtil.FileUpload(fname, "img", bt, req);
		}
		if(this.isError()){
			this.addInput(req, resp);
			return "product/addInput.jsp";
		}
		p.setCategory(categoryDao.load(cid));
		productDao.add(cid,p);
		return redirect+"product.do?method=list";
	}
}
