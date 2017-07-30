package TestMybatis;

import org.junit.Test;

import qhd.zy.shop.model.ShopDi;

public class TestAnnotation {
	
	@ShopDi(value="sas",abd="sda")
	@Test
	public void test01(){
		System.out.println("dsf");
	}
}
