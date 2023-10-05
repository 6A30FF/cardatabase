package com.packt.cardatabase.domain;

// 자격증명을 포함할 POJO 클래스 데이터베이스에 저장하지 않으므로 어노테이션 지정하지 않음
public class AccountCredentials {
	private String username;
	private String password;
	
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
}
