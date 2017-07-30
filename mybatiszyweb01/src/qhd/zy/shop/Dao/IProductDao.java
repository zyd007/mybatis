package qhd.zy.shop.Dao;

import qhd.zy.shop.model.Product;
import qhd.zy.shop.model.Pager;

public interface IProductDao {
	//cid为分类得id
	public void add(int cid,Product product);
	public void detele(int id);
	public void update(Product product,int cid);
	public Product load(int id);
	/*
	 * 根据分类id和名称查找
	 * 默认值0表示上架 1表示下架
	 */
	public Pager<Product> list(int category,String name,int status);
	/*
	 * 增加库存
	 */
	public void addStock(int id,int num);
	/*
	 * 减少库存
	 */
	public void decStock(int id,int num);
	
	public void changeStatus(int id);
}