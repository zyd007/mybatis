package qhd.zy.shop.Dao;

import java.util.List;

import qhd.zy.shop.model.Address;

public interface IAddressDao {
	public void add(Address address,int userid);
	public void delete(int id);
	public void update(Address address);
	public Address load(int id);
	public	List<Address>lists(int userid); 
}
