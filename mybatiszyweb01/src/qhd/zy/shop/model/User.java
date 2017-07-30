package qhd.zy.shop.model;

import java.util.List;


public class User {
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", type=" + type
				+"]";
	}
	private int id;
	@ValidateForm(type=ValidateType.NotNull,errorMsg="用户名不能为空")
	private String username;
	@ValidateForm(type=ValidateType.NotNull,errorMsg="密码不能为空")
	private String password;
	private String nickname;
	private int type;
	private List<Address> addresses;
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
