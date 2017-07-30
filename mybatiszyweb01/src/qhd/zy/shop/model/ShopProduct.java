package qhd.zy.shop.model;

public class ShopProduct {
	//单独创建一个对象放购物车的一条的信息(一个购物车中的信息)
	private int id;

	private Product product;
	private int pid;
	private int number;
	private double price;
	//因为购物车没有永久保存,所以必须要确定属于那个订单的
	private Orders orders;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ShopProduct [id=" + id + ", product=" + product + ", pid="
				+ pid + ", number=" + number + ", price=" + price + ", orders="
				+ orders + "]";
	}
	
}
