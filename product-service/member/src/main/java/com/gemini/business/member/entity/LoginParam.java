package com.gemini.business.member.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginParam {
    private String phone;
    private String code;
}
