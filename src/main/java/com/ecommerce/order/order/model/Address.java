package com.ecommerce.order.order.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址类
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {
    private String province;
    private String city;
    private String detail;

    private Address(String province, String city, String detail) {
        this.province = province;
        this.city = city;
        this.detail = detail;
    }

    public static Address of(String province, String city, String detail) {
        return new Address(province, city, detail);
    }


    public Address changeDetailTo(String detail) {
        return new Address(this.province, this.city, detail);
    }

}
