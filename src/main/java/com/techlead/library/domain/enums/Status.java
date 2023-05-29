package com.techlead.library.domain.enums;

public enum Status {
    AVAILABLE(0),
    BORROWED(1),
    OVERDUE(2),
    LOST(3),
    OPEN(8),
    CLOSED(9);

    private Integer code;

    Status(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

