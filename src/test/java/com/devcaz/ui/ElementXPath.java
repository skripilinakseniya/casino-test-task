package com.devcaz.ui;

public enum ElementXPath {
    USER_LOGOUT_ICON("//*[@id=\"header\"]/header/div[3]/ul[2]/li[3]/a/span"),
    LOGOUT_ICON("//*[@id=\"header\"]/header/div[3]/ul[2]/li[3]/ul/li[3]/a"),
    PLAYERS_MENU_ICON("//*[@id=\"content\"]/div[2]/div[1]/div[3]/a/div/div/p[1]"),
    PLAYERS_TABLE("//*[@id=\"payment-system-transaction-grid\"]/table"),
    PLAYER_TABLE_ELEMENT("//*[@id=\"payment-system-transaction-grid\"]/table/tbody"),
    SORTING_ICON("//*[@id=\"payment-system-transaction-grid_c9\"]/a"),
    FIRST_PLAYER_REGISTRATION_DATE("//*[@id=\"payment-system-transaction-grid\"]/table/tbody/tr[1]/td[10]"),
    SECOND_PLAYER_REGISTRATION_DATE("//*[@id=\"payment-system-transaction-grid\"]/table/tbody/tr[2]/td[10]");

    private String elementsXPath;

    ElementXPath(String elementsXPath) {
        this.elementsXPath = elementsXPath;
    }

    public String getElementsXPath() {
        return elementsXPath;
    }
}
