package com.charles.sys.share.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LoginResult {

    private String tokenHead;

    private String token;

    private User user;

    @Data
    public static class User{

        private String username;

        private String avatar;

        private String ip;

        private Date lastLogin;
    }
}
