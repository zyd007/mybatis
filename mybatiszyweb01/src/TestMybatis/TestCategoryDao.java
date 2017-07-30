package TestMybatis;

import java.util.List;

import org.junit.Test;

import qhd.zy.shop.Dao.ICategoryDao;
import qhd.zy.shop.model.Category;
import qhd.zy.shop.model.Product;
import qhd.zy.shop.model.ShopDi;

public class TestCategoryDao extends baseTest {
	private ICategoryDao categoryDao;
	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Test
	public void addTest(){
		Category category=new Category();
		category.setName("益达服装");
		categoryDao.add(category);
		category=new Category();
		category.setName("益达补给");
		categoryDao.add(category);
		category=new Category();
		category.setName("张益帅");
		categoryDao.add(category);
		category=new Category();
		category.setName("武器");
		categoryDao.add(category);
		category=new Category();
		category.setName("大武器");
		categoryDao.add(category);
		category=new Category();
		category.setName("健身房");
		categoryDao.add(category);
	}
	@Test
	public  void deleteTest(){
		categoryDao.delete(2);
	}
	@Test
	public  void loadTest(){
		for(Product p :categoryDao.load(3).getProducts())
		System.out.println(p);
	}
	@Test
	public void updateTest(){
		Category c=categoryDao.load(1);
		c.setName("益达商品");
		categoryDao.update(c);
	}
	@Test
	public void list(){
		List<Category>list=categoryDao.list();
		for(Category c:list){
			System.out.println(c);
		}
	}
	@Test
	public void listCon(){
		List<Category>listCon=categoryDao.list("益");
		for(Category c:listCon){
			System.out.println(c);
		}
	}
	
}
