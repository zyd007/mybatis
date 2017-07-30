package qhd.zy.shop.web;


import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qhd.zy.shop.Dao.IOrdersDao;
import qhd.zy.shop.Dao.ProductDao;
import qhd.zy.shop.Dao.UserDao;
import qhd.zy.shop.model.Orders;
import qhd.zy.shop.model.Product;
import qhd.zy.shop.model.ShopCart;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.ShopExecption;
import qhd.zy.shop.model.ShopProduct;
import qhd.zy.shop.model.SystemContext;
import qhd.zy.shop.model.User;


public class OrdersServlet extends BaseServlet{
	private ProductDao productDao;
	private UserDao userDao;
	private IOrdersDao ordersDao;
	@ShopDi
	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}
	@ShopDi
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@ShopDi
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7466312967799437594L;
	@Auth
	public String addToCart(HttpServletRequest req,HttpServletResponse resp){
		ShopCart shopCart=(ShopCart)req.getSession().getAttribute("shopCart");
		if(shopCart==null){
			shopCart=new ShopCart();
			req.getSession().setAttribute("shopCart", shopCart);
		}
		Product p=productDao.load(Integer.parseInt(req.getParameter("id")));
		try {
			shopCart.add(p);
		} catch (Exception e) {
			return this.handleException(e, req);
		}
		return redirect+"product.do?method=list";
	}
	@Auth
	public String showCart(HttpServletRequest req,HttpServletResponse resp){
		//添加完地址后需要重新加载登录用户
		User u=(User)req.getSession().getAttribute("loginUser");
		//保证整个过程都有效关联表用mybatis中定义好的方法才能取出来）
		req.getSession().setAttribute("addresses",userDao.load(u.getId()).getAddresses() );
		return "orders/showCart.jsp";
	}
	@Auth
	public String clearProduct(HttpServletRequest req,HttpServletResponse resp){
		int pid=Integer.parseInt(req.getParameter("pid"));
		ShopCart shopCart=(ShopCart)req.getSession().getAttribute("shopCart");
		if(shopCart!=null){			
			shopCart.clearProduct(pid);
		}
		return redirect+"orders.do?method=showCart";
	}
	//清理订单中的一个购物对象
	@Auth
	public String clearOrdersProduct(HttpServletRequest req,HttpServletResponse resp){
		int oid=Integer.parseInt(req.getParameter("oid"));
		int pid=Integer.parseInt(req.getParameter("pid"));
		Orders o=ordersDao.load(oid);
		for(ShopProduct p:o.getProducts()){
			if(p.getId()==pid){
				//删除订单中的shopProduct
				ordersDao.deleteSimpleShopProduct(pid, oid);
			}
		}
		//当订单中长度为1时，就没shopProduct(购物对象),就把订单删除
		if(o.getProducts().size()<=1){
				return redirect+"orders.do?method=deleteOrders&oid="+o.getId();
		}
		return redirect+"orders.do?method=showOrders&oid="+o.getId();
	}
	@Auth
	public String clearCart(HttpServletRequest req,HttpServletResponse resp){
		ShopCart shopCart=(ShopCart)req.getSession().getAttribute("shopCart");
		if(shopCart!=null){			
			shopCart.clearCart();
		}
		return redirect+"orders.do?method=showCart";
	}
	//在购物车中的对购物对象减数量
	@Auth
	public String reduceProductNumberInput(HttpServletRequest req,HttpServletResponse resp){
	int pid=Integer.parseInt(req.getParameter("pid"));
	req.setAttribute("pid", pid);
	return "orders/reduceProductNumberInput.jsp";
	}
	//在购物车中的对购物对象减数量
	@Auth
	public String reduceProductNumber(HttpServletRequest req,HttpServletResponse resp){
	int pid=Integer.parseInt(req.getParameter("pid"));
	try {
		int number=Integer.parseInt(req.getParameter("number"));
		ShopCart shopCart=(ShopCart)req.getSession().getAttribute("shopCart");
		if(shopCart!=null){			
			shopCart.reduceProductNumber(pid,number);
		}
	} catch (NumberFormatException e) {
		this.getErrors().put("number", "请输入正确格式");
		req.setAttribute("pid", pid);
		return "orders/reduceProductNumberInput.jsp";
	}catch (ShopExecption e) {
		return this.handleException(e, req);
	}
	return redirect+"orders.do?method=showCart";
	}
	//在购物车中的对购物对象加数量
	@Auth
	public String addProductNumberInput(HttpServletRequest req,HttpServletResponse resp){
	int pid=Integer.parseInt(req.getParameter("pid"));
	req.setAttribute("pid", pid);
	return "orders/addProductNumberInput.jsp";
	}
	//在购物车中的对购物对象加数量
	@Auth
	public String addProductNumber(HttpServletRequest req,HttpServletResponse resp){
	int pid=Integer.parseInt(req.getParameter("pid"));
	try {
		int number=Integer.parseInt(req.getParameter("number"));
		ShopCart shopCart=(ShopCart)req.getSession().getAttribute("shopCart");
		if(shopCart!=null){			
			shopCart.addProductNumber(pid,number);
		}
	} catch (NumberFormatException e) {
		this.getErrors().put("number", "请输入正确格式");
		req.setAttribute("pid", pid);
		return "orders/addProductNumberInput.jsp";
	}catch (ShopExecption e) {
		return this.handleException(e, req);
	}
	return redirect+"orders.do?method=showCart";
	}
	//增加订单中的购物对象的数量
	@Auth
	public String addOrdersProductNumberInput(HttpServletRequest req,HttpServletResponse resp){
	int pid=Integer.parseInt(req.getParameter("pid"));
	int oid=Integer.parseInt(req.getParameter("oid"));
	req.setAttribute("pid", pid);
	req.setAttribute("oid", oid);
	return "orders/addOrdersProductNumberInput.jsp";
	}
	//增加订单中的购物对象的数量
	@Auth
	public String addOrdersProductNumber(HttpServletRequest req,HttpServletResponse resp){
	int pid=Integer.parseInt(req.getParameter("pid"));
	int oid=Integer.parseInt(req.getParameter("oid"));
	Orders o=ordersDao.load(oid);
	try {
		int number=Integer.parseInt(req.getParameter("number"));
		for(ShopProduct p:o.getProducts()){
			//购物对象id相等
			if(p.getId()==pid){
				if((p.getNumber()+number)<=p.getProduct().getStock()){
				p.setNumber(p.getNumber()+number);
				ordersDao.updateShopProduct(p);
				}else{
					throw new ShopExecption("库存不足");
				}
			}
		}
	} catch (NumberFormatException e) {
		this.getErrors().put("number", "请输入正确格式");
		req.setAttribute("pid", pid);
		req.setAttribute("oid", oid);
		return "orders/addOrdersProductNumberInput.jsp";
	}catch (ShopExecption e) {
		return this.handleException(e, req);
	}
	return redirect+"orders.do?method=showOrders&oid="+o.getId();
	}
	//减少订单中的购物对象的数量
	@Auth
	public String reduceOrdersProductNumberInput(HttpServletRequest req,HttpServletResponse resp){
	int pid=Integer.parseInt(req.getParameter("pid"));
	int oid=Integer.parseInt(req.getParameter("oid"));
	req.setAttribute("pid", pid);
	req.setAttribute("oid", oid);
	return "orders/reduceOrdersProductNumberInput.jsp";
	}
	//减少订单中的购物对象的数量
	@Auth
	public String reduceOrdersProductNumber(HttpServletRequest req,HttpServletResponse resp){
	int pid=Integer.parseInt(req.getParameter("pid"));
	int oid=Integer.parseInt(req.getParameter("oid"));
	Orders o=ordersDao.load(oid);
	try {
		int number=Integer.parseInt(req.getParameter("number"));
		for(ShopProduct p:o.getProducts()){
			//购物对象id相等
			if(p.getId()==pid){
				if((p.getNumber()-number)>=0){
				p.setNumber(p.getNumber()-number);
				//如果减到0就把购物对象从订单中删除
				if(p.getNumber()<=0)
					return redirect+"orders.do?method=clearOrdersProduct&oid="+o.getId()+"&pid="+p.getId();
				ordersDao.updateShopProduct(p);
				}else{
					throw new ShopExecption("没有那么多数量");
				}
			}
		}
	} catch (NumberFormatException e) {
		this.getErrors().put("number", "请输入正确格式");
		req.setAttribute("pid", pid);
		req.setAttribute("oid", oid);
		return "orders/reduceOrdersProductNumberInput.jsp";
	}catch (ShopExecption e) {
		return this.handleException(e, req);
	}
	return redirect+"orders.do?method=showOrders&oid="+o.getId();
	}
	
	@Auth
	public String addOrders(HttpServletRequest req,HttpServletResponse resp){
	int aid=Integer.parseInt(req.getParameter("address"));
	double price=Double.parseDouble(req.getParameter("price"));
	Orders o=new Orders();
	o.setPrice(price);
	o.setStatus(1);
	o.setBuyDate(new Date());
	User u=(User)req.getSession().getAttribute("loginUser");
	ordersDao.add(o, u, aid, ((ShopCart)req.getSession().getAttribute("shopCart")).getShopProduct());
	return redirect+"user.do?method=show&id="+u.getId();
	}
	
	
	public String list(HttpServletRequest req,HttpServletResponse resp){
	SystemContext.setSort("buy_date");
	SystemContext.setOrder("desc");
	//当它为0时，就代表查看所有列表
	int status=0;
	try {
		status=Integer.parseInt(req.getParameter("status"));
	} catch (NumberFormatException e) {}
	req.setAttribute("orders",ordersDao.findByStatus(status) );
	return "orders/list.jsp";
	}
	@Auth
	public String userList(HttpServletRequest req,HttpServletResponse resp){
		SystemContext.setSort("buy_date");
		SystemContext.setOrder("desc");
		//当它为0时，就代表查看所有列表
		int status=0;
		int id=0;
		try {
			//id最好放status的上面(防止查询用户订单的时候uid直接被catch)
			id=Integer.parseInt(req.getParameter("uid"));
			status=Integer.parseInt(req.getParameter("status"));
		} catch (NumberFormatException e) {}
		req.setAttribute("orders",ordersDao.findByUser(id, status) );
		req.setAttribute("uid",id);
		return "orders/userList.jsp";
		}
	@Auth
	public String deleteOrders(HttpServletRequest req,HttpServletResponse resp){
		int oid=Integer.parseInt(req.getParameter("oid"));
		Orders o=ordersDao.load(oid);
		try {
			ordersDao.delete(oid);
		} catch (ShopExecption e) {
			this.handleException(e, req);
		}
		return redirect+"orders.do?method=userList&uid="+o.getUser().getId();
		}
	@Auth
	public String showOrders(HttpServletRequest req,HttpServletResponse resp){
		int oid=Integer.parseInt(req.getParameter("oid"));
		req.setAttribute("o", ordersDao.load(oid));
		return "orders/showOrders.jsp";
		}
	@Auth
	public String payPrice(HttpServletRequest req,HttpServletResponse resp){
		int oid=Integer.parseInt(req.getParameter("oid"));
		try {
			ordersDao.updatePayStatus(oid);
		} catch (ShopExecption e) {
			return this.handleException(e, req);
		}
		return redirect+"orders.do?method=userList&uid="+((User)req.getSession().getAttribute("loginUser")).getId();
		}
	
	public String sendProduct(HttpServletRequest req,HttpServletResponse resp){
		int oid=Integer.parseInt(req.getParameter("oid"));
		ordersDao.updateSendStatus(oid);
		return redirect+"orders.do?method=list";
		}
	@Auth
	public String confirm(HttpServletRequest req,HttpServletResponse resp){
		int oid=Integer.parseInt(req.getParameter("oid"));
		ordersDao.updateConfirmStatus(oid);
		return redirect+"orders.do?method=userList&uid="+((User)req.getSession().getAttribute("loginUser")).getId();
		}
	
}
