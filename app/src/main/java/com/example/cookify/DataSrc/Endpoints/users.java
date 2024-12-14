package com.example.cookify.DataSrc.Endpoints;

import com.example.cookify.DataSrc.Data_structure.user_info;

public class users {
    public static boolean isAuthenticated(){
        return true;
    }
    public  static user_info UserInfo(){
    return new user_info(1, "john_doe", "hashed_password_here", "john.doe@example.com", "John Doe");
}
}
