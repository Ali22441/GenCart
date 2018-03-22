package com.webmarke8.app.gencart.Objects;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by GeeksEra on 2/16/2018.
 */

public class Store implements Serializable {


    /**
     * id : 9
     * name : Store 9
     * logo : logos/9.png
     * banner : banners/9.png
     * type : store
     * zipcode : 42200
     * address : Behria phase 7 gate 2
     * phone : 03064101269
     * status : 1
     * place_id : null
     * lat_long : 33.572708,73.110080
     * slug : store-9
     * created_at : 2018-03-15 00:00:00
     * updated_at : 2018-03-15 00:00:00
     * rating : 0.00
     * store_id : 9
     * opening_hours : 6:00 PM
     * closing_hours : 12:00 AM
     * week_days : a:4:{i:0;i:1;i:1;i:2;i:2;i:3;i:3;i:4;}
     * weekend_days : a:3:{i:0;i:5;i:1;i:6;i:2;i:7;}
     * store_status : false
     * distance : 6.426668797664404
     */

    private int id;
    private String name;
    private String logo;
    private String banner;
    private String type;
    private String zipcode;
    private String address;
    private String phone;
    private int status;
    private Object place_id;
    private String lat_long;
    private String slug;
    private String created_at;
    private String updated_at;
    private String rating;
    private int store_id;
    private String opening_hours;
    private String closing_hours;
    private String week_days;
    private String weekend_days;
    private boolean store_status;
    private double distance;

    public static Store objectFromData(String str) {

        return new Gson().fromJson(str, Store.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Object place_id) {
        this.place_id = place_id;
    }

    public String getLat_long() {
        return lat_long;
    }

    public void setLat_long(String lat_long) {
        this.lat_long = lat_long;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public String getClosing_hours() {
        return closing_hours;
    }

    public void setClosing_hours(String closing_hours) {
        this.closing_hours = closing_hours;
    }

    public String getWeek_days() {
        return week_days;
    }

    public void setWeek_days(String week_days) {
        this.week_days = week_days;
    }

    public String getWeekend_days() {
        return weekend_days;
    }

    public void setWeekend_days(String weekend_days) {
        this.weekend_days = weekend_days;
    }

    public boolean isStore_status() {
        return store_status;
    }

    public void setStore_status(boolean store_status) {
        this.store_status = store_status;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
