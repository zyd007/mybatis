package qhd.zy.shop.Dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import qhd.zy.shop.Util.propertiesUtil;

public class PropertiesFactory implements IDAOFactory{
	/*
	 * 单列
	 */
	private static IDAOFactory f=new PropertiesFactory();
	private static Map<String,Object>adds=new HashMap<String, Object>();
	/*
	 * 防止new
	 */
	private PropertiesFactory(){}
	public static IDAOFactory getinstance(){
		return f;
	}
	/*
	 * 得到使用那个Dao（mysql,jdbc,mybatis,orecle）
	 */
	@Override
	public Object getDao(String name) {
		try {
			if(adds.containsKey(name))
				return adds.get(name);
			Properties p=propertiesUtil.getProperties();
			String str=p.getProperty(name);
			Object obj=Class.forName(str).newInstance();
			adds.put(name, obj);
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
