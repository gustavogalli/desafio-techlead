package com.techlead.library.domain.enums;

public enum Profile {
    ADMIN(0, "ROLE_ADMIN"),
    CUSTOMER(1, "ROLE_CUSTOMER");

    private Integer code;
    private String description;

    Profile(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Profile toEnum (Integer code){
        if(code == null){
            return null;
        }

        for(Profile x : Profile.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid profile.");
    }
}

