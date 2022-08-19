package com.struts2.member;

public class MemberDTO {

	private String userId;
	private String userPwd;
	private String userName;
	private String userGender;
	private String[] userBirth;
	private String[] userAddr;
	private String userEmail;
	private String userTel;
	private int registration;

	private String mode;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String[] getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String[] userBirth) {
		this.userBirth = userBirth;
	}

	public String[] getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String[] userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public int getRegistration() {
		return registration;
	}

	public void setRegistration(int registration) {
		this.registration = registration;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
