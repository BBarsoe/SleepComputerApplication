package com.model;

public class UserModel {

	private String user_name;
	private String user_pass;
	private String user_firstname;
	private String user_surname;

	public UserModel(String user_name, String user_pass, String user_firstname, String user_surname){
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.user_firstname = user_firstname;
		this.user_surname = user_surname;

	}
}
