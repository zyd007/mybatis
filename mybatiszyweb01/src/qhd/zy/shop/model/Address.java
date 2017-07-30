package qhd.zy.shop.model;

public class Address {
	private int id;
	@ValidateForm(type=ValidateType.NotNull,errorMsg="地址不能为空")
	private String name;
	@ValidateForm(type=ValidateType.NotNull,errorMsg="电话号码不能为空")
	private String phone;
	private String postcode;
	private User user;
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", postcode=" + postcode + ", user=" + user + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
