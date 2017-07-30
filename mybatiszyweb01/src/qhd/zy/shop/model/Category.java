package qhd.zy.shop.model;

import java.util.List;

public class Category {
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	private int id;
	@ValidateForm(type=ValidateType.NotNull,errorMsg="商品类别不能为空")
	private String name;
	private List<Product>products;
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
