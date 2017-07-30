package qhd.zy.shop.Dao;

import java.util.List;

import qhd.zy.shop.model.Category;

public interface ICategoryDao {
	public void add(Category category);
	public void delete(int id);
	public void update(Category category);
	public Category load(int id);
	public List<Category> list(String con);
	public List<Category> list();
}
