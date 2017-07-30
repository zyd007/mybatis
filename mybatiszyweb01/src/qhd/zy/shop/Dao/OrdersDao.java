package qhd.zy.shop.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qhd.zy.shop.model.Address;
import qhd.zy.shop.model.Orders;
import qhd.zy.shop.model.Pager;
import qhd.zy.shop.model.Product;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.ShopExecption;
import qhd.zy.shop.model.ShopProduct;
import qhd.zy.shop.model.SystemContext;
import qhd.zy.shop.model.User;

public class OrdersDao extends baseDao<Orders> implements IOrdersDao {
	private IAddressDao addressDao;
	private IProductDao productDao;
	
	@ShopDi
	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	@ShopDi
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}


	public void add(Orders orders, User user, int aid, List<ShopProduct> sps) {
		orders.setUser(user);
		Address ads=addressDao.load(aid);
		orders.setAddress(ads);
		//Shop列表必须遍历添加在ShopProduct中Orders.xml没法添加;
		super.add(orders);
		//虽然添加了orders了,但是下面还没有orders.id
		//，必须添加useGeneratedKeys="true" keyProperty="id"
		for(ShopProduct sp:sps){
			//下面有添加的方法
			this.addShopProduct(sp, orders,sp.getProduct());
		}
	}

	
	public void delete(int id) {
		Orders o=this.load(id);
		if(o.getStatus()!=1)throw new ShopExecption("只能删除未付款的订单");
		//先删除订单中的所有购物对象ShopProduct
			this.deleteShopProduct(id);
		super.delete(Orders.class, id);
	}

	
	public void update(Orders orders) {
		super.update(orders);
	}

	
	public void updatePrice(int id, double price) {
		Orders o=this.load(id);
		o.setPrice(price);
		this.update(o);
	}

	
	public void updatePayStatus(int id) {
		//TODO 这里和老师不一样
		Orders o=this.load(id);
		List<ShopProduct>products=o.getProducts();
		//这个list的目的是为了让整个订单能运行完才更新product
		List<Product>pd=new ArrayList<Product>();
		for(ShopProduct sp:products){
			//因为这里才有分类
			Product p=productDao.load(sp.getProduct().getId());
			//要在order.xml中把load方法的stock映射了
			if(sp.getNumber()>p.getStock()){
				throw new ShopExecption(sp.getProduct().getName()+"库存不足");
			}else{
				p.setStock(p.getStock()-sp.getNumber());
				pd.add(p);
			}
		}
		for(Product p:pd){
			productDao.update(p, p.getCategory().getId());
		}
		o.setStatus(2);
		o.setPayDate(new Date());
		//因为应用update改变的所以在Orders.xml的update方法中改变
		this.update(o);
	}

	
	public void updateSendStatus(int id) {
		Orders o=this.load(id);
		o.setStatus(3);
		this.update(o);
	}

	
	public void updateConfirmStatus(int id) {
		Orders o=this.load(id);
		o.setStatus(4);
		o.setConfirmDate(new Date());
		this.update(o);
	}

	
	public Orders load(int id) {
		return super.load(Orders.class, id);
	}

	
	public Pager<Orders> findByUser(int userId, int status) {
		//如果通过多个东西寻找的话肯定用map
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("userId",userId);
		//status是orders中的status(0这状态在商品列表里)
		if(status>0)
			params.put("status",status);
		//说明通过什么寻找（有很多寻找的不能都用baseDao中的同一名字）
		return super.listPage(Orders.class.getName()+".find_by_user", params);
	}

	
	public Pager<Orders> findByStatus(int status) {
		Map<String,Object>params=new HashMap<String,Object>();
		//status是orders中的status(0这状态在商品列表里)
		if(status>0)
			params.put("status", status);
		//说明通过什么寻找（有很多寻找的不能都用baseDao中的同一名字）
		return super.listPage(Orders.class.getName()+".find_by_status",params );
	}

	
	public void addShopProduct(ShopProduct sp, Orders o, Product p) {
		//这些其他类只会添加id在orders数据库中(其他dao也是同样道理)
		sp.setProduct(p);
		sp.setOrders(o);
		//和老师略微不一样;
		super.add(sp);
	}

	//删除订单中全部ShopProduct
	public void deleteShopProduct(int oid) {
		//这里是根据oid用map给以根据很多条件删除
		Map<String,Object>params=new HashMap<String,Object >();
		params.put("oid", oid);
		super.delete(ShopProduct.class.getName()+".deleteByOrders",params);
	}
	//删除订单单个shopProduct
	public void deleteSimpleShopProduct(int id,int oid) {
		Map<String,Object>params=new HashMap<String,Object >();
		params.put("id", id);
		params.put("oid", oid);
		super.delete(ShopProduct.class.getName()+".delete",params);
	
	}
	public void updateShopProduct(ShopProduct p){
		super.update(p);
	}

}
