package com.ldh.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable{

	/**
	 */
	private static final long serialVersionUID = 3374199725690997635L;

	/**
	 * userId
	 */
	private Integer userId;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 出生日期
	 */
	private Date birthday;
	/**
	 * 余额
	 */
	private BigDecimal balance;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 昵称
	 */
	private String nickName;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", phone=" + phone + ", realName=" + realName + ", birthday=" + birthday
				+ ", balance=" + balance + ", email=" + email + ", nickName=" + nickName + "]";
	}
	public User() {
		super();
	}
	public User(Integer userId, String phone, String realName, Date birthday, BigDecimal balance, String email,
			String nickName) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.realName = realName;
		this.birthday = birthday;
		this.balance = balance;
		this.email = email;
		this.nickName = nickName;
	}
}