package qhd.zy.shop.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import qhd.zy.shop.Dao.IUserDao;

import qhd.zy.shop.Util.RequestUtil;
import qhd.zy.shop.model.Pager;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.User;

public class UserServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5855802771683041518L;
	private IUserDao userDao;
	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public String delete(HttpServletRequest req,HttpServletResponse resp){
		int id=Integer.parseInt(req.getParameter("id"));
		userDao.delete(id);
		return redirect+"user.do?method=list";
	}
	public String list(HttpServletRequest req,HttpServletResponse resp){
		Pager<User>users=userDao.find("");
		req.setAttribute("users", users);
		return "user/list.jsp";
	}
	@Auth("any")
	public String addInput(HttpServletRequest req,HttpServletResponse resp){
		return "user/addInput.jsp";
	}
	public String updateInput(HttpServletRequest req,HttpServletResponse resp){
		User u=userDao.load(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("u", u);
		return "user/updateInput.jsp";
	}
	public String update(HttpServletRequest req,HttpServletResponse resp){
		User u=(User)RequestUtil.set(User.class, req);
		 u=userDao.load(Integer.parseInt(req.getParameter("id")));
		u.setPassword(req.getParameter("password"));
		u.setNickname(req.getParameter("nickname"));
		Boolean confrm=RequestUtil.Vaildate(User.class, req);
		if(!confrm){
			req.setAttribute("u",u );
			return "user/updateInput.jsp";
		}
		userDao.update(u);
		return redirect+"user.do?method=list";
	}
	@Auth("any")
	public String validate(HttpServletRequest req,HttpServletResponse resp){
		req.getSession().invalidate();
		return redirect+"user.do?method=loginInput";
	}
	@Auth("any")
	public String updateSelfInput(HttpServletRequest req,HttpServletResponse resp){
		User u=(User)req.getSession().getAttribute("loginUser");
		if(u==null)return redirect+"user.do?method=loginInput";
		return "user/updateSelfInput.jsp";
	}
	@Auth("any")
	public String updateSelf(HttpServletRequest req,HttpServletResponse resp){
		User u=(User)req.getSession().getAttribute("loginUser");
		u.setPassword(req.getParameter("password"));
		u.setNickname(req.getParameter("nickname"));
		Boolean confrm=RequestUtil.Vaildate(User.class, req);
		if(!confrm){
			req.setAttribute("u",u );
			return "user/updateSelfInput.jsp";
		}
		userDao.update(u);
		return redirect+"user.do?method=list";
	}
	@Auth("user")
	public String show(HttpServletRequest req,HttpServletResponse resp){
		User user=userDao.load(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("user", user);
		return "user/show.jsp";
	}
	@Auth("any")
	public String loginInput(HttpServletRequest req,HttpServletResponse resp){
		return "user/loginInput.jsp";
	}
	@Auth("any")
	public String login(HttpServletRequest req,HttpServletResponse resp){
		try {
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			User u=userDao.login(username, password);
			req.getSession().setAttribute("loginUser", u);
		} catch (Exception e) {
			req.setAttribute("exception", e.getMessage());
			return "inc/shopException.jsp";
		}
		
		return redirect+"product.do?method=list";
	}
	@Auth("any")
	public String add(HttpServletRequest req,HttpServletResponse resp){
		User u=(User)RequestUtil.set(User.class, req);
		Boolean confrm=RequestUtil.Vaildate(User.class, req);
		if(!confrm){
			return "user/addInput.jsp";
		}
	try {
		userDao.add(u);
	} catch (Exception e) {
		req.setAttribute("exception", e);
		return "inc/shopException.jsp";
	}
		//以下是一种方法
	/*	//得到key和value(用string[]是因为有些一个key映射多个值-->兴趣)
		Map<String,String[]>Maps=req.getParameterMap();
		//得到所有key
		Set<String>keys=Maps.keySet();
		User u=new User();
		Method m;
		for(String k:keys){
			//得到方法名
			String sk="set"+k.substring(0, 1).toUpperCase()+k.substring(1);
			try {
				//得到方法（第一个参数为u的方法名，第二个为参数类）
				 
				m=(Method)u.getClass().getMethod(sk, String.class);
				try {
					//注入k的values的第一个值
					 * * 如果有不存在的u中的sk方法就继续不注入
					m.invoke(u,Maps.get(k)[0] );
				} catch (IllegalAccessException e) {
					continue;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		userDao.add(u);*/
		//以下是一种方法
		/*String username=req.getParameter("username");
		String password=req.getParameter("password");
		String nickname=req.getParameter("nickname");
		User u=new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setNickname(nickname);*/
		
		return redirect+"user.do?method=list";
	}
}
