package com.company.auth;

import com.company.state.Status;
import lombok.Data;


@Data
public class User {
    private String full_name;
    private String email;
    private Status userStatus;
    private int age;
    private String password;



}
