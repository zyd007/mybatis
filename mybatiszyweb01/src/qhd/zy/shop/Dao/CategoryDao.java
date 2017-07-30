package qhd.zy.shop.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qhd.zy.shop.model.Category;


public class CategoryDao extends baseDao<Category> implements ICategoryDao {

	@Override
	public void add(Category category) {
		 super.add(category);
	}

	@Override
	public void delete(int id) {
		//TODO 如果还有商品就不能删除(主键约束)
			super.delete(Category.class, id);
		
	}

	@Override
	public void update(Category category) {
		super.update(category);
	}

	@Override
	public Category load(int id) {
		return super.load(Category.class, id);
	}

	@Override
	public List<Category> list(String con) {
		Map<String,Object>params=new HashMap<String,Object>();
		if(con!=null&&!"".equals(con.trim()))
		params.put("name", "%"+con+"%");
		return super.listNotpage(Category.class, params);
	}

	@Override
	public List<Category> list() {
		return list(null);
	}

}
