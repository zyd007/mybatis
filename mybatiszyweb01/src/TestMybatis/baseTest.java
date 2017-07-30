package TestMybatis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import qhd.zy.shop.Util.DaoUtil;

public class baseTest {
	public baseTest() {
		DaoUtil.getDiDao(this);
	}
}
