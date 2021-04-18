package com.devcaz.ui;

public enum ElementId {
    USER_LOGIN_NAME("UserLogin_username"),
    USER_PASSWORD("UserLogin_password");

    private String elementId;

    ElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getElementId() {
        return elementId;
    }
}
