package com.yunqi.auth.model.entity;

import com.yunqi.common.model.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class User extends BaseEntity {

    private String username;

    private String password;

    private String phone;

    private String nickname;

    private String avatar;

    private String email;

    private Boolean enable ;
}
