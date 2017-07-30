package qhd.zy.shop.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import qhd.zy.shop.Dao.IAddressDao;
import qhd.zy.shop.Dao.IUserDao;
import qhd.zy.shop.Util.RequestUtil;
import qhd.zy.shop.model.Address;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.User;

public class AddressServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5855802771683041518L;
	private IUserDao userDao;
	private IAddressDao addressDao;
	@ShopDi
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	@Auth("user")
	public String updateInput(HttpServletRequest req,HttpServletResponse resp){
		Address address=addressDao.load(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("address", address);
		return "address/updateInput.jsp";
	}
	@Auth("user")
	public String update(HttpServletRequest req,HttpServletResponse resp){
		Address address=addressDao.load(Integer.parseInt(req.getParameter("id")));
		Address a=(Address)RequestUtil.set(Address.class, req);
		address.setName(a.getName());
		address.setPhone(a.getPhone());
		address.setPostcode(a.getPostcode());
		if(!RequestUtil.Vaildate(Address.class, req)){
			req.setAttribute("address", address);
			return "address/updateInput.jsp";
		}
		addressDao.update(address);
		return redirect+req.getContextPath()+"/user.do?method=show&id="+address.getUser().getId();
	}
	@Auth("user")
	public String delete(HttpServletRequest req,HttpServletResponse resp){
		Address address=addressDao.load(Integer.parseInt(req.getParameter("id")));
		addressDao.delete(Integer.parseInt(req.getParameter("id")));
		 return redirect+req.getContextPath()+"/user.do?method=show&id="+address.getUser().getId();
	}
	@Auth("user")
	public String addInput(HttpServletRequest req,HttpServletResponse resp){
		User user=userDao.load(Integer.parseInt(req.getParameter("userId")));
		req.setAttribute("user", user);
		return "address/addInput.jsp";
	}
	@Auth("user")
	public String add(HttpServletRequest req,HttpServletResponse resp){
		User user=userDao.load(Integer.parseInt(req.getParameter("userId")));
		Address a=(Address)RequestUtil.set(Address.class, req);
		if(!RequestUtil.Vaildate(Address.class, req)){
			req.setAttribute("user", user);
			return "address/addInput.jsp";
		}
		addressDao.add(a, user.getId());
		return redirect+req.getContextPath()+"/user.do?method=show&id="+user.getId();
	}
}
