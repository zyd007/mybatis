package qhd.zy.shop.Util;

import java.io.IOException;
import java.util.Properties;

public class propertiesUtil {
	private static Properties pr;
	public static Properties getProperties(){
			try {
				if(pr==null){
					pr=new Properties();
					pr.load(propertiesUtil.class.getClassLoader().getResourceAsStream("dao.properties"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return pr;
	}
}
