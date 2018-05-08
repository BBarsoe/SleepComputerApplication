package com.model;


import com.controller.MainController;

public class UserModel {

    private String user_id;
    private String user_pass;
    private String user_firstname;

    public UserModel(String user_id, String user_pass1, String user_firstname) {
        this.user_id = user_id;
        this.user_pass = user_pass1;
        this.user_firstname = user_firstname;

        System.out.println(user_id);
        System.out.println(user_pass);
        System.out.println(user_firstname);

    }

}
