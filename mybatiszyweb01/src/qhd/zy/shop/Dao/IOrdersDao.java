package qhd.zy.shop.Dao;

import java.util.List;

import qhd.zy.shop.model.Orders;
import qhd.zy.shop.model.Pager;
import qhd.zy.shop.model.Product;
import qhd.zy.shop.model.ShopProduct;
import qhd.zy.shop.model.User;

public interface IOrdersDao {
	//增加订单肯定要把其他类用参数的形式 不可用jsp传参的形式传过来
	public void add(Orders orders,User user,int aid,List<ShopProduct> sps);
	public void delete(int id);
	public void update(Orders orders);
	public void updatePrice(int id,double price);
	public void updatePayStatus(int id);
	public void updateSendStatus(int id);
	public void updateConfirmStatus(int id);
	public Orders load(int id);
	public Pager<Orders> findByUser(int userId,int status);
	public Pager<Orders> findByStatus(int status);
	public void addShopProduct(ShopProduct sp, Orders o,Product p);
	public void deleteShopProduct(int oid);
	public void deleteSimpleShopProduct(int id,int oid);
	public void updateShopProduct(ShopProduct p);
}
