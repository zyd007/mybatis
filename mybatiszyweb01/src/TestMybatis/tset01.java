package TestMybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import qhd.zy.shop.Dao.IAddressDao;
import qhd.zy.shop.Dao.IUserDao;
import qhd.zy.shop.Util.DaoUtil;
import qhd.zy.shop.model.User;




public class tset01 {
	public static void main(String[]args){
add();
//	update();
//	delete();
//	load();

		System.out.println(1);
	}
	private static void load(){
		try {
			InputStream in=Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
			SqlSession session=factory.openSession();
	
			System.out.println(session.selectOne("qhd.zy.shop.model.User.select",498 ));
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void delete(){
		try {
			InputStream in=Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
			SqlSession session=factory.openSession();
			session.delete("qhd.zy.shop.model.User.delete",499);
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private static void update(){
		try {
//			InputStream re=Resources.getResourceAsStream("mybatis-config.xml");
			Reader re=Resources.getResourceAsReader("mybatis-config.xml");
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(re);
			SqlSession session=factory.openSession();
			User u=new User();
			u.setPassword("520");
			u.setNickname("唯");
			u.setType(0);
			u.setId(498);
			session.update("qhd.zy.shop.model.User.update", u);
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private static void add(){
		try {
//			InputStream re=Resources.getResourceAsStream("mybatis-config.xml");
			Reader re=Resources.getResourceAsReader("mybatis-config.xml");
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(re);
			SqlSession session=factory.openSession();
			User u=new User();
			u.setUsername("杜");
			u.setPassword("520");
			u.setNickname("唯爱");
			u.setType(0);
			session.insert("qhd.zy.shop.model.User.add", u);
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void  testSingle(){
		IUserDao ud1=(IUserDao)DaoUtil.createFactory().getDao("userDao");
		IUserDao ud2=(IUserDao)DaoUtil.createFactory().getDao("userDao");
		System.out.println(ud2.equals(ud1));
		IAddressDao ad1=(IAddressDao)DaoUtil.createFactory().getDao("addressDao");
		IAddressDao ad2=(IAddressDao)DaoUtil.createFactory().getDao("addressDao");
		System.out.println(ad1==ad2);
	}
	
}
