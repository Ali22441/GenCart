package com.webmarke8.app.gencart.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by GeeksEra on 2/14/2018.
 */

public class Customer implements Serializable {

    @SerializedName("success")
    @Expose
    private Success success;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public class Success {

        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("user")
        @Expose
        private User user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("country_code")
        @Expose
        private String countryCode;
        @SerializedName("authy_id")
        @Expose
        private String authyId;
        @SerializedName("verified")
        @Expose
        private Integer verified;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("image")
        @Expose
        private Object image;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("zipcode")
        @Expose
        private Integer zipcode;
        @SerializedName("place_id")
        @Expose
        private Object placeId;
        @SerializedName("referral_code")
        @Expose
        private Object referralCode;
        @SerializedName("lat_long")
        @Expose
        private String latLong;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("fcm_token")
        @Expose
        private String fcmToken;
        @SerializedName("login_type")
        @Expose
        private String loginType;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("become_a_shopper")
        @Expose
        private Integer becomeAShopper;
        @SerializedName("cart_session_id")
        @Expose
        private Object cartSessionId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getAuthyId() {
            return authyId;
        }

        public void setAuthyId(String authyId) {
            this.authyId = authyId;
        }

        public Integer getVerified() {
            return verified;
        }

        public void setVerified(Integer verified) {
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

        public Integer getZipcode() {
            return zipcode;
        }

        public void setZipcode(Integer zipcode) {
            this.zipcode = zipcode;
        }

        public Object getPlaceId() {
            return placeId;
        }

        public void setPlaceId(Object placeId) {
            this.placeId = placeId;
        }

        public Object getReferralCode() {
            return referralCode;
        }

        public void setReferralCode(Object referralCode) {
            this.referralCode = referralCode;
        }

        public String getLatLong() {
            return latLong;
        }

        public void setLatLong(String latLong) {
            this.latLong = latLong;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
            this.fcmToken = fcmToken;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getBecomeAShopper() {
            return becomeAShopper;
        }

        public void setBecomeAShopper(Integer becomeAShopper) {
            this.becomeAShopper = becomeAShopper;
        }

        public Object getCartSessionId() {
            return cartSessionId;
        }

        public void setCartSessionId(Object cartSessionId) {
            this.cartSessionId = cartSessionId;
        }

    }
}
