package com.shariq.lc.api;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserInfoDTO {
	@NotBlank(message= " * Your Name cannot be blank")
	@Size(min=3,max=15,message ="{userName.size}")
	private String userName;
	@NotBlank(message= " * Crush Name cannot be blank")
	@Size(min=3,max=15,message =" * Crush name should have atleast 3 to 15 characters")
	private String crushName;
	
	@AssertTrue(message ="Agree to use our app")
	private boolean termAndCondition;
	
	private String result;
	
	public UserInfoDTO() {
		super();
		System.out.println("user info dto contr called..");
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCrushName() {
		return crushName;
	}
	public void setCrushName(String crushName) {
		this.crushName = crushName;
	}
	
	
	public boolean isTermAndCondition() {
		return termAndCondition;
	}
	public void setTermAndCondition(boolean termAndCondition) {
		this.termAndCondition = termAndCondition;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "UserInfoDTO [userName=" + userName + ", crushName=" + crushName + "]";
	}
	
	
	
	
	
	
	

}
