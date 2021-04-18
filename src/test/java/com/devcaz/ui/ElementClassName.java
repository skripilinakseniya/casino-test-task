package com.devcaz.ui;

public enum ElementClassName {
    ADMIN_MENU("main-side-menu");

    private String elementClassName;

    ElementClassName(String elementClassName) {
        this.elementClassName = elementClassName;
    }

    public String getElementClassName() {
        return elementClassName;
    }
}
