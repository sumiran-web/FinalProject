package com.example.restaurantfinder.modal;

public class CartModal {

    private String productid;
    private String userid;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public CartModal(String productid, String userid) {
        this.productid = productid;
        this.userid = userid;
    }
}
