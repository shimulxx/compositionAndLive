package com.example.compositionworkmanytomany;

import androidx.room.ColumnInfo;

public class Address {
    private String city;
    private String street;
    @ColumnInfo(name = "zip_code")
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }
}
