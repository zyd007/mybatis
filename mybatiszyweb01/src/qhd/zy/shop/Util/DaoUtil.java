package qhd.zy.shop.Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;



import qhd.zy.shop.Dao.IDAOFactory;
import qhd.zy.shop.model.ShopDi;

public class DaoUtil {
	public static void main(String[] args) {
		createFactory();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/*
	 *得到总工厂
	 */
	public static void getDiDao(Object o){
		try {
			//得到o里面的所以定义方法
			Method[]ms=o.getClass().getDeclaredMethods();
			//遍历方法
			for(Method m:ms){
				//判断方法是否应用annotation类
				if(m.isAnnotationPresent(ShopDi.class)){
//					得到annotation类的对象
					ShopDi sd=m.getAnnotation(ShopDi.class);
//					得到annotation类中的value属性值
					String s=sd.value();
//					如果属性值不存在，那就只有用m方法来注入Dao
					if(s==null||"".equals(s.trim())){
//						从第四个字符开始得到字符串
						s=m.getName().substring(3);
						//得到配置文件中的字符
						s=s.substring(0,1).toLowerCase()+s.substring(1);
					}
				
					//得到需要的DAO类
					Object ob=DaoUtil.createFactory().getDao(s);
//					把m方法设置方法类（第一个属性为那个方法的类，第二个为属性）
					m.invoke(o, ob);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
/*	public static void getDiDao(Object o){
		try {
			Method[] ms = o.getClass().getDeclaredMethods();
			for (Method m : ms) {
				String s = m.getName();
				if (s.contains("set")) {
					String str = s.substring(3);
					str = str.substring(0, 1).toLowerCase() + str.substring(1);
					Object obj = DaoUtil.createFactory().getDao(str);
					m.invoke(o, obj);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}*/
	public static IDAOFactory createFactory(){
		IDAOFactory f=null;
		Properties p=propertiesUtil.getProperties();
		String s=p.getProperty("factory");
		try {
			Class clz=Class.forName(s);
			String name="getinstance";
		  try {
			Method m=clz.getMethod(name);
			try {
				f=(IDAOFactory)m.invoke(clz);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return f;
	}
}
