package qhd.zy.shop.model;

import java.util.ArrayList;
import java.util.List;



public class ShopCart {
	private List<ShopProduct> shopProduct;
	private boolean isEmpty;
//必须要get--EL表达式${}才能识别
	public boolean getisEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public ShopCart(){
		isEmpty=true;
		shopProduct=new ArrayList<ShopProduct>();
	}

	public List<ShopProduct> getShopProduct() {
		return shopProduct;
	}

	public void setShopProduct(List<ShopProduct> shopProduct) {
		this.shopProduct = shopProduct;
	}
	public void clearCart(){
		shopProduct.clear();
		isEmpty=true;
	}
	public void add(Product product){
		ShopProduct p=this.findShopProduct(product.getId());
		if(p==null){
			if(product.getStock()<1) throw new ShopExecption("库存不足！不能添加");
			p=new ShopProduct();
			p.setNumber(1);
			p.setPid(product.getId());
			p.setProduct(product);
			p.setPrice(product.getPrice());
			//自加
			shopProduct.add(p);
		}else{
			if(product.getStock()<(p.getNumber()+1))throw new ShopExecption("库存不足！不能添加");
			p.setNumber(p.getNumber()+1);
			p.setPrice(p.getPrice()+product.getPrice());
		}
		isEmpty=false;
	}
	public ShopProduct findShopProduct(int pid){
		for(ShopProduct p:shopProduct){
			if(p.getPid()==pid)return p;
		}
		return null;
	}
	public void clearProduct(int pid){
		//这里不能用for（XXX x：xxxx）这个遍历类似迭代器删除的话不好控制
		for(int i=0;i<shopProduct.size();i++){
			ShopProduct sp=shopProduct.get(i);
			if(sp.getPid()==pid)
				shopProduct.remove(sp);
		}
		if(shopProduct.size()<=0)isEmpty=true;
	}

	public void addProductNumber(int pid, int number) {
		for(ShopProduct p:shopProduct){
			if(p.getPid()==pid){
				if(p.getProduct().getStock()<p.getNumber()+number)throw new ShopExecption("库存不足！不能添加");
				p.setNumber(p.getNumber()+number);
				p.setPrice(p.getPrice()+(number*p.getProduct().getPrice()));
			}
		}
	}

	public void reduceProductNumber(int pid, int number) {
		for(ShopProduct p:shopProduct){
			if(p.getPid()==pid){
				if(p.getNumber()-number<0)throw new ShopExecption("删除过多!购物车没有那么多存货");
				p.setNumber(p.getNumber()-number);
				p.setPrice(p.getPrice()-(number*p.getProduct().getPrice()));
			}
		}
	}
	
}
