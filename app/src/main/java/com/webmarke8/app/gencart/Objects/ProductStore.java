package com.webmarke8.app.gencart.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by GeeksEra on 2/16/2018.
 */

public class ProductStore implements Serializable {

    private String logo;

    private String place_id;

    private String phone;

    private String lat_long;

    private String status;

    private Shelves[] shelves;

    private String zipcode;

    private Departments[] departments;

    private String id;

    private String updated_at;

    private String address;

    private String name;

    private String created_at;

    private String slug;

    private Products[] products;

    private String banner;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLat_long() {
        return lat_long;
    }

    public void setLat_long(String lat_long) {
        this.lat_long = lat_long;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Shelves[] getShelves() {
        return shelves;
    }

    public void setShelves(Shelves[] shelves) {
        this.shelves = shelves;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Departments[] getDepartments() {
        return departments;
    }

    public void setDepartments(Departments[] departments) {
        this.departments = departments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Products[] getProducts() {
        return products;
    }

    public void setProducts(Products[] products) {
        this.products = products;
    }

    public String

    getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

}
