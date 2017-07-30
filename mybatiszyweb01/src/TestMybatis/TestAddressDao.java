package TestMybatis;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import qhd.zy.shop.Dao.AddressDao;
import qhd.zy.shop.Dao.IAddressDao;
import qhd.zy.shop.Dao.IUserDao;
import qhd.zy.shop.Util.DaoUtil;
import qhd.zy.shop.model.Address;
import qhd.zy.shop.model.ShopDi;

public class TestAddressDao extends baseTest{
	private IAddressDao ad;
	private IUserDao ud;

	public IAddressDao getAd() {
		return ad;
	}
	@ShopDi
	public void setAddressDao(IAddressDao ad) {
		this.ad = ad;
	}
	public IUserDao getUd() {
		return ud;
	}
	@ShopDi
	public void setUserDao(IUserDao ud) {
		this.ud = ud;
	}
	@Test
	public void add(){
		Address ads=new Address();
		ads.setName("达州");
		ads.setPhone("123456");
		ads.setPostcode("124578");
		ad.add(ads, 1);
	}
	@Test
	public void delete(){
		ad.delete(4);
	}
	@Test
	public void update(){
		Address a=ad.load(1);
		a.setName("眉山市");
		a.setPhone("520");
		a.setPostcode("888888");
		ad.update(a);
	}
	@Test
	public void load(){
		System.out.println(ad.load(1));
	}
	@Test
	public void list(){
		List<Address>list=ad.lists(1);
		for(Address a:list){
			System.out.println(a);
		}
	}
	@Test
	public void test01(){
		new AddressDao();
	}
}
