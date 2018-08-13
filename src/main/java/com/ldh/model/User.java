package com.ldh.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
}