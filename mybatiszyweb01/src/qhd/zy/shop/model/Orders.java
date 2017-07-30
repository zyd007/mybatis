package qhd.zy.shop.model;

import java.util.Date;
import java.util.List;

public class Orders {
	private int id;
	private Date buyDate;
	private Date payDate;
	private Date confirmDate;
	/**
	 * 四种状态:1表示已下订单、2表示已付款、3、表示已发货、4、表示确认收货
	 */
	
	private int status;
	private User user;
	private Address address;
	private List<ShopProduct> products;
	/**
	 * 订单价格多少?能修改的(比如打折)
	 * @return
	 */
	private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	/**
	 * 
	 * @return 四种状态:1表示已下订单、2表示已付款、3、表示已发货、4、表示确认收货
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * 
	 * @param status 四种状态:1表示已下订单、2表示已付款、3、表示已发货、4、表示确认收货
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<ShopProduct> getProducts() {
		return products;
	}
	public void setProducts(List<ShopProduct> products) {
		this.products = products;
	}

}
