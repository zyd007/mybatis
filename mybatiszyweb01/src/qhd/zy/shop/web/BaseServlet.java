package qhd.zy.shop.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;



import qhd.zy.shop.Util.DaoUtil;
import qhd.zy.shop.model.User;

public class BaseServlet extends HttpServlet {
	public final static String redirect="redirect:";
	private Map<String,String>errorMsg=new HashMap<String, String>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7378808699514794011L;
	//获得所有错误的map(主要是给子类设置错误信息的)
	protected Map<String, String> getErrors(){
		return errorMsg;
	}
	//判断是否有错误
	protected Boolean isError(){
		if(errorMsg!=null&&errorMsg.size()>0)return true;
		return false;
	}
	//统一处理异常
	protected String handleException(Exception e,HttpServletRequest req){
		req.setAttribute("exception", e.getMessage());
		return "inc/shopException.jsp";
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			//下次请求开始前先把错误清空,如果这次有得再设置
			errorMsg.clear();
			//在开始请求前把错误内置对象设置好(主要是给jsp页面返回错误的信息的)
			//这个不需要封装
		 	req.setAttribute("errorMsg", errorMsg);
		 	//如果有得就封装
			if(ServletFileUpload.isMultipartContent(req)){
				req=new MultipartFormWrapper(req);
			}
		 	DaoUtil.getDiDao(this);
			String method=req.getParameter("method");
			try {
				Method m=this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				int flag=checkAuth(m,req,resp);
				if(flag==1){
					resp.sendRedirect("user.do?method=loginInput");
					return;
				}else if(flag==2){
					req.setAttribute("exception", "你没有权限访问该功能没有权限");
					req.getRequestDispatcher("/WEB-INF/inc/shopException.jsp").forward(req, resp);
				return;
				}
				
				String path=(String)m.invoke(this, req,resp);
				if(path.startsWith(redirect)){
					resp.sendRedirect(path.substring(redirect.length()));
				}else{
					//服务器跳转
					req.getRequestDispatcher("/WEB-INF/"+path).forward(req, resp);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	}
	/*
	 * 0表示成功，1表示到登录页面，2表示到出错页面
	 */
	
	private int  checkAuth(Method m, HttpServletRequest req,
			HttpServletResponse resp) {
		User u=(User)req.getSession().getAttribute("loginUser");
		if(u!=null&&u.getType()==1) {
			//如果是管理员说明所有的功能都可以访问
			return 0;
		}
		if(!m.isAnnotationPresent(Auth.class)) {
			//没有Annotation说明该方法必须由超级管理员
			if(u==null) {
				return 1;
			}
		} else {
			Auth a = m.getAnnotation(Auth.class);
			String v = a.value();
			if(v.equals("any")) {
				return 0;
			} else if(v.equals("user")){
				if(u==null)
					return 1;
				else return 0;
			}
		}
		return 2;
	}
	
}
