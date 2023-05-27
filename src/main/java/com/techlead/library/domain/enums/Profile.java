package com.techlead.library.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Profile {
    ADMIN(0, "ROLE_ADMIN"),
    CUSTOMER(1, "ROLE_CUSTOMER");

    private Integer code;
    private String description;
}

