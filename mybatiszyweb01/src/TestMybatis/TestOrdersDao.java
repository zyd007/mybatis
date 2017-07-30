package TestMybatis;

import org.junit.Test;

import qhd.zy.shop.Dao.IOrdersDao;
import qhd.zy.shop.model.Orders;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.ShopProduct;

public class TestOrdersDao extends baseTest {
	private IOrdersDao ordersDao;
	@ShopDi
	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}
	@Test
	public void load(){
		Orders o=ordersDao.load(3);
		System.out.println(o.getPrice()+","+o.getStatus()+","+o.getAddress().getName()+
				","+o.getUser().getNickname());
		for(ShopProduct sp:o.getProducts()){
			System.out.println(sp.getProduct().getName()+","+sp.getNumber());
		}
	}
	@Test
	public void list(){
		System.out.println(ordersDao.findByStatus(0).getDatas().size());
		
	}
}
