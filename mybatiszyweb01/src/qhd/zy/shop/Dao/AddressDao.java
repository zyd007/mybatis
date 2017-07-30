package qhd.zy.shop.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qhd.zy.shop.Util.DaoUtil;
import qhd.zy.shop.model.Address;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.ShopExecption;
import qhd.zy.shop.model.User;

public class AddressDao extends baseDao<Address> implements IAddressDao{
		private IUserDao ad;
		
	public IUserDao getAd() {
			return ad;
		}
	@ShopDi("userDao")	
	public void setUserDao(IUserDao ad) {
			this.ad = ad;
		}
	@Override
	public void add(Address address, int userid) {
		User u=ad.load(userid);
		if(u==null)throw new ShopExecption("用户不存在！不能添加地址");
		address.setUser(u);
		super.add(address);
	}
	public void delete(int id) {
		super.delete(Address.class, id);
	}

	@Override
	public void update(Address address) {
		super.update(address);
	}

	@Override
	public Address load(int id) {
		return super.load(Address.class, id);
	}

	@Override
	public List<Address> lists(int userid) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("userid", userid);
		return super.listNotpage(Address.class, params);
	}

}
