package com.mk.entity;

/*
    库存表
 */
public class Stock {
    private int id;
    private String productName;
    private String code;
    private String price;
    private String number;
    private String supplier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", code='" + code + '\'' +
                ", price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
