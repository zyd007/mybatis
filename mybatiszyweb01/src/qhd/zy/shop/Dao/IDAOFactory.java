package qhd.zy.shop.Dao;

public interface IDAOFactory {
	//name写用那个dao
	public Object getDao(String name);
}
