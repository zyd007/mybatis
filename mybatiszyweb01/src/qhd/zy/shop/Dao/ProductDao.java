package qhd.zy.shop.Dao;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import qhd.zy.shop.model.Category;
import qhd.zy.shop.model.Pager;
import qhd.zy.shop.model.Product;
import qhd.zy.shop.model.ShopDi;
import qhd.zy.shop.model.ShopExecption;
import qhd.zy.shop.model.SystemContext;

public class ProductDao extends baseDao<Product> implements IProductDao{
	private ICategoryDao categoryDao;
	

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}


	
	@Override
	public void add(int cid,Product product) {
		Category c=categoryDao.load(cid);
		if(c==null) throw new ShopExecption("分类不能为空");
		product.setCategory(c);
		super.add(product);
	}
	

	@Override
	public void detele(int id) {
		Product p=this.load(id);
		String img=p.getImg();
		// TODO 如果用户购买了该商品就不能删除，该商品存在订单也不能删除，
				//如果要删除商品的话需要删除商品的图片
		super.delete(Product.class, id);
		String path=SystemContext.getRealPath()+"img/"+img;
		File f=new File(path);
		f.delete();
	}

	@Override
	public void update(Product product,int cid) {
		Category c=categoryDao.load(cid);
		if(c==null) throw new ShopExecption("分类不能为空");
		product.setCategory(c);
		super.update(product);
	}

	@Override
	public Product load(int id) {
		return super.load(Product.class, id);
	}

	@Override
	public Pager<Product> list(int category, String name,int status) {
		Map<String,Object>params=new HashMap<String, Object>();
		if(category>0){
			params.put("cid", category);
		}
		if(name!=null&&!"".equals(name.trim())){
			params.put("name", "%"+name+"%");
		}
		if(status==1||status==-1){
		params.put("status", status);
		}
		return super.listPage(Product.class, params);
	}
	public void changeStatus(int id){
		Product p=this.load(id);
		if(p.getStatus()==1){
			p.setStatus(-1);
		}else{
			p.setStatus(1);
		}
		this.update(p);
	}
	@Override
	public void addStock(int id, int num) {
		Product p=this.load(id);
		p.setStock(p.getStock()+num);
		this.update(p);
	}

	@Override
	public void decStock(int id, int num) {
		Product p=this.load(id);
		p.setStock(p.getStock()-num);
		this.update(p);
	}

}
