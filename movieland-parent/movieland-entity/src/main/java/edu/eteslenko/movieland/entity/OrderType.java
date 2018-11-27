package edu.eteslenko.movieland.entity;

public enum OrderType {
    ASC("ASC"),
    DESC("DESC"),
    DEFAULT("");

    private String orderType;

    OrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType(){
        return orderType;
    }



}
