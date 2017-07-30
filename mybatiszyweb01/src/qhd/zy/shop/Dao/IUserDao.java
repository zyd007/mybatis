package qhd.zy.shop.Dao;

import qhd.zy.shop.model.Pager;
import qhd.zy.shop.model.User;

public interface IUserDao {
	public User add(User user);
	public void delete(int id);
	public User update(User user);
	public User load(int id);
	public User loadByuserName(String username);
	public Pager<User> find(String name);
	public User login(String username,String password);
}
