package com.webmarke8.app.gencart.Fragments;

import com.google.gson.Gson;

/**
 * Created by GeeksEra on 3/23/2018.
 */

public class Verified_Result {

    /**
     * success : {"id":13,"country_code":"92","authy_id":"76520908","verified":true,"name":"Mir Kamran","email":"kamran@gmail.com","phone":"03326452226","image":null,"address":"Gynastic Areena Behria Phase 7","zipcode":42000,"place_id":null,"referral_code":null,"lat_long":"33.525550,73.112831","role":"customer","fcm_token":"","login_type":"normal","created_at":"2018-03-23 12:26:18","updated_at":"2018-03-23 12:28:15","become_a_shopper":0,"cart_session_id":null,"status":1}
     */

    private SuccessObject success;

    public static Verified_Result objectFromData(String str) {

        return new Gson().fromJson(str, Verified_Result.class);
    }

    public SuccessObject getSuccess() {
        return success;
    }

    public void setSuccess(SuccessObject success) {
        this.success = success;
    }

    public static class SuccessObject {
        /**
         * id : 13
         * country_code : 92
         * authy_id : 76520908
         * verified : true
         * name : Mir Kamran
         * email : kamran@gmail.com
         * phone : 03326452226
         * image : null
         * address : Gynastic Areena Behria Phase 7
         * zipcode : 42000
         * place_id : null
         * referral_code : null
         * lat_long : 33.525550,73.112831
         * role : customer
         * fcm_token :
         * login_type : normal
         * created_at : 2018-03-23 12:26:18
         * updated_at : 2018-03-23 12:28:15
         * become_a_shopper : 0
         * cart_session_id : null
         * status : 1
         */

        private int id;
        private String country_code;
        private String authy_id;
        private boolean verified;
        private String name;
        private String email;
        private String phone;
        private Object image;
        private String address;
        private int zipcode;
        private Object place_id;
        private Object referral_code;
        private String lat_long;
        private String role;
        private String fcm_token;
        private String login_type;
        private String created_at;
        private String updated_at;
        private int become_a_shopper;
        private Object cart_session_id;
        private int status;

        public static SuccessObject objectFromData(String str) {

            return new Gson().fromJson(str, SuccessObject.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getAuthy_id() {
            return authy_id;
        }

        public void setAuthy_id(String authy_id) {
            this.authy_id = authy_id;
        }

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getZipcode() {
            return zipcode;
        }

        public void setZipcode(int zipcode) {
            this.zipcode = zipcode;
        }

        public Object getPlace_id() {
            return place_id;
        }

        public void setPlace_id(Object place_id) {
            this.place_id = place_id;
        }

        public Object getReferral_code() {
            return referral_code;
        }

        public void setReferral_code(Object referral_code) {
            this.referral_code = referral_code;
        }

        public String getLat_long() {
            return lat_long;
        }

        public void setLat_long(String lat_long) {
            this.lat_long = lat_long;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getFcm_token() {
            return fcm_token;
        }

        public void setFcm_token(String fcm_token) {
            this.fcm_token = fcm_token;
        }

        public String getLogin_type() {
            return login_type;
        }

        public void setLogin_type(String login_type) {
            this.login_type = login_type;
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

        public int getBecome_a_shopper() {
            return become_a_shopper;
        }

        public void setBecome_a_shopper(int become_a_shopper) {
            this.become_a_shopper = become_a_shopper;
        }

        public Object getCart_session_id() {
            return cart_session_id;
        }

        public void setCart_session_id(Object cart_session_id) {
            this.cart_session_id = cart_session_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
