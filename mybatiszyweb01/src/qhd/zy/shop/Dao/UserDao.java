package qhd.zy.shop.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import qhd.zy.shop.Util.SqlSessionUtil;
import qhd.zy.shop.model.Pager;
import qhd.zy.shop.model.ShopExecption;
import qhd.zy.shop.model.SystemContext;
import qhd.zy.shop.model.User;

public class UserDao extends baseDao<User> implements IUserDao {

	@Override
	public User add(User user) {
		if(loadByuserName(user.getUsername())!=null)throw new ShopExecption("用户名存在 ！不能添加");
		super.add(user);
		return user;
	}

	@Override
	public void delete(int id) {
		//TODO 需要先删除关联对象（外键）
		super.delete(User.class,id);
	}

	@Override
	public User update(User user) {
		if(load(user.getId())==null)throw new ShopExecption("用户名不存在 ！不能修改");
		super.update(user);
		return user;
	}

	@Override
	public User load(int id) {
		return super.load(User.class, id);
	}

	@Override
	public User loadByuserName(String username) {
		String sql=User.class.getName()+".loadUsername";
		return super.loadBySqlID(sql, username);
	}
	//用id或username查用户
	public User loadMore(String more){
		Map<String,Object>params=new HashMap<String,Object>();
		//记着是&&
		if(more!=null&&!"".equals(more)){
		params.put("nickname", more);
		params.put("username",more);
		}
		String sql=User.class.getName()+".loadMore";
		return super.loadMore(sql, params);
	}
	
	@Override
	public Pager<User> find(String name) {
		Map<String, Object>maps=new HashMap<String, Object>();
		maps.put("name", "%"+name+"%");
		maps.put("name", "%"+name+"%");
		return super.listPage(User.class, maps);
	}

	@Override
	public User login(String username, String password) {
		User u=loadByuserName(username);
		if(u==null)throw new ShopExecption("用户不存在");
		if(!u.getPassword().equals(password))throw new ShopExecption("密码不正确");
		return u;
	}
	public List<User> listNotpage(String condition){
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("listnotpage", "%"+condition+"%");
		return super.listNotpage(User.class, params);
	}
}
