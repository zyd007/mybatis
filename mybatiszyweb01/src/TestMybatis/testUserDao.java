package TestMybatis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import qhd.zy.shop.Dao.AddressDao;
import qhd.zy.shop.Dao.IUserDao;
import qhd.zy.shop.Util.DaoUtil;
import qhd.zy.shop.model.Address;
import qhd.zy.shop.model.Pager;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.SystemContext;
import qhd.zy.shop.model.User;

public class testUserDao extends baseTest{
	private IUserDao ud;
	
	public IUserDao getUd() {
		return ud;
	}
	@ShopDi("userDao")
	public void setUserDao(IUserDao ud) {
		this.ud = ud;
	}
	@Test
	public void test01(){
		new AddressDao();
	}
	@Test
	public void test(){
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("s", "saa");
		System.out.println(map.get("s"));
	}
	@Test
	public void lists(){
		SystemContext.setOffset(0);
		SystemContext.setPageRecord(15);
		SystemContext.setOrder("desc");
		SystemContext.setSort("id");
		Pager<User>pages=ud.find("");
		System.out.println(pages.getTotalRecord());
		for(User u:pages.getDatas()){
			System.out.println(u);
		}
	}
	@Test
	public void add(){
		User u=new User();
		u.setUsername("zx");
		u.setPassword("454");
		u.setNickname("但是");
		u.setType(0);
		ud.add(u);
		
	}
	@Test
	public void load(){
		User u=ud.load(1);
		for(Address as:u.getAddresses()){
			System.out.println(as+":"+u.getUsername());
		}
	}
	@Test
	public void update(){
		User u=ud.load(506);
		u.setPassword("530");
		u.setNickname("是谁");
		ud.update(u);
	}
	@Test
	public void loadUsername(){
		User u=ud.loadByuserName("zy");
		System.out.println(u);
	}
	@Test
	public void delete(){
		ud.delete(505);
	}
	@Test
	public void loadMore(){
	/*	System.out.println(ud.loadMore(""));*/
		
	}

}
