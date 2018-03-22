package com.webmarke8.app.gencart.Objects;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GeeksEra on 3/21/2018.
 */

public class RideResponse implements Serializable{


    /**
     * id : 202
     * customer_id : 9
     * shopper_id : 0
     * driver_id : 11
     * address_lat_lng : null
     * amount : 83000
     * delivery_charge : 0
     * status : In Progress
     * created_at : 2018-03-21 14:37:13
     * updated_at : 2018-03-21 14:37:29
     * stores : [{"id":1,"name":"Store 1","logo":"logos/1.png","banner":"banners/1.png","type":"store","zipcode":"42200","address":"Sadar Rawalpindi","phone":"03064101261","status":1,"place_id":null,"lat_long":"33.572708,73.110080","slug":"store-1","created_at":"2018-03-15 14:52:51","updated_at":"2018-03-15 14:52:52","rating":"0.00","pivot":{"order_id":202,"store_id":1}},{"id":1,"name":"Store 1","logo":"logos/1.png","banner":"banners/1.png","type":"store","zipcode":"42200","address":"Sadar Rawalpindi","phone":"03064101261","status":1,"place_id":null,"lat_long":"33.572708,73.110080","slug":"store-1","created_at":"2018-03-15 14:52:51","updated_at":"2018-03-15 14:52:52","rating":"0.00","pivot":{"order_id":202,"store_id":1}},{"id":2,"name":"Store 2","logo":"logos/2.png","banner":"banners/2.png","type":"store","zipcode":"42200","address":"Commercial  Rawalpindi","phone":"03064101262","status":1,"place_id":null,"lat_long":"33.572708,73.110080","slug":"store-2","created_at":"2018-03-15 14:53:41","updated_at":"2018-03-15 14:53:41","rating":"0.00","pivot":{"order_id":202,"store_id":2}},{"id":2,"name":"Store 2","logo":"logos/2.png","banner":"banners/2.png","type":"store","zipcode":"42200","address":"Commercial  Rawalpindi","phone":"03064101262","status":1,"place_id":null,"lat_long":"33.572708,73.110080","slug":"store-2","created_at":"2018-03-15 14:53:41","updated_at":"2018-03-15 14:53:41","rating":"0.00","pivot":{"order_id":202,"store_id":2}}]
     * products : [{"id":3,"name":"Product 1","image":"products/3.png","quantity":20,"price":5000,"promo_price":null,"description":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","total_sale":0,"store_id":1,"department_id":1,"shelf_id":1,"brand_id":1,"unit":"pcs","tax":5,"status":"available","created_at":"2018-03-15 15:42:41","updated_at":"2018-03-15 15:42:41","slug":"product-1","category_id":1,"pivot":{"order_id":202,"product_id":3}},{"id":4,"name":"P2","image":"products/4.png","quantity":50,"price":6000,"promo_price":null,"description":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","total_sale":0,"store_id":1,"department_id":1,"shelf_id":1,"brand_id":1,"unit":"pcs","tax":5,"status":"available","created_at":"2018-03-15 15:43:28","updated_at":"2018-03-15 15:43:28","slug":"p2","category_id":1,"pivot":{"order_id":202,"product_id":4}},{"id":17,"name":"St 2  Product 2","image":"products/17.png","quantity":500,"price":5000,"promo_price":null,"description":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","total_sale":0,"store_id":2,"department_id":2,"shelf_id":6,"brand_id":1,"unit":"pcs","tax":5,"status":"available","created_at":"2018-03-15 16:00:17","updated_at":"2018-03-15 16:00:17","slug":"st-2-product-2","category_id":1,"pivot":{"order_id":202,"product_id":17}},{"id":18,"name":"St 2 Product 3","image":"products/18.png","quantity":500,"price":3000,"promo_price":null,"description":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","total_sale":0,"store_id":2,"department_id":2,"shelf_id":6,"brand_id":1,"unit":"pcs","tax":3,"status":"available","created_at":"2018-03-15 16:01:31","updated_at":"2018-03-15 16:01:32","slug":"st-2-product-3","category_id":1,"pivot":{"order_id":202,"product_id":18}}]
     * customer : {"id":9,"country_code":"92","authy_id":"73366963","verified":0,"name":"AsifClient","email":"asifclient@gmail.com","phone":"03353888070","image":null,"address":"Gynastic Areena Behria Phase 7","zipcode":42000,"place_id":null,"referral_code":null,"lat_long":"33.525550,73.112831","role":"customer","fcm_token":"c5Ixg70xp2w:APA91bHWGyWBGHGQEItRv6s0QRE2yEemZAXJ5MWVNwB3adr0itbt7ADMR3B8d9aEu0HuCC-UPy43ge0fcsjo5uomnyuX4z8ZxxFkvvBnvLP4RyvY13g__RiX6mCGzuWhNVBbPubc7N1y","login_type":"normal","created_at":"2018-03-15 11:04:55","updated_at":"2018-03-21 14:09:10","become_a_shopper":0,"cart_session_id":null,"status":0}
     * driver : {"id":11,"country_code":"92","authy_id":"76064406","verified":1,"name":"kami","email":"kamiclient1@gmail.com","phone":"03353888079","image":null,"address":"Gynastic Areena Behria Phase 7","zipcode":42000,"place_id":null,"referral_code":null,"lat_long":"33.525550,73.112831","role":"driver","fcm_token":"dUQY-07dhdk:APA91bEf1DuG9sW_iMgkPFupxm02xkcmhv796rqDfX1U5x-RHCtxcz34LqXWNCoYzXBvuCKxBWbaQ5z3JDIKx701_M-i7aQVpcHNB8o0gq382bzdYxFQr_2H8K-9XtxRPREkoLZFoJ8T","login_type":"normal","created_at":"2018-03-19 15:14:36","updated_at":"2018-03-21 14:37:03","become_a_shopper":0,"cart_session_id":null,"status":1}
     */

    private int id;
    private int customer_id;
    private int shopper_id;
    private int driver_id;
    private Object address_lat_lng;
    private int amount;
    private int delivery_charge;
    private String status;
    private String created_at;
    private String updated_at;
    private CustomerObject customer;
    private DriverObject driver;
    private List<StoresObject> stores;
    private List<ProductsObject> products;
    private String OrderID;

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public static RideResponse objectFromData(String str) {

        return new Gson().fromJson(str, RideResponse.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getShopper_id() {
        return shopper_id;
    }

    public void setShopper_id(int shopper_id) {
        this.shopper_id = shopper_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public Object getAddress_lat_lng() {
        return address_lat_lng;
    }

    public void setAddress_lat_lng(Object address_lat_lng) {
        this.address_lat_lng = address_lat_lng;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(int delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public CustomerObject getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerObject customer) {
        this.customer = customer;
    }

    public DriverObject getDriver() {
        return driver;
    }

    public void setDriver(DriverObject driver) {
        this.driver = driver;
    }

    public List<StoresObject> getStores() {
        return stores;
    }

    public void setStores(List<StoresObject> stores) {
        this.stores = stores;
    }

    public List<ProductsObject> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsObject> products) {
        this.products = products;
    }

    public static class CustomerObject implements Serializable {
        /**
         * id : 9
         * country_code : 92
         * authy_id : 73366963
         * verified : 0
         * name : AsifClient
         * email : asifclient@gmail.com
         * phone : 03353888070
         * image : null
         * address : Gynastic Areena Behria Phase 7
         * zipcode : 42000
         * place_id : null
         * referral_code : null
         * lat_long : 33.525550,73.112831
         * role : customer
         * fcm_token : c5Ixg70xp2w:APA91bHWGyWBGHGQEItRv6s0QRE2yEemZAXJ5MWVNwB3adr0itbt7ADMR3B8d9aEu0HuCC-UPy43ge0fcsjo5uomnyuX4z8ZxxFkvvBnvLP4RyvY13g__RiX6mCGzuWhNVBbPubc7N1y
         * login_type : normal
         * created_at : 2018-03-15 11:04:55
         * updated_at : 2018-03-21 14:09:10
         * become_a_shopper : 0
         * cart_session_id : null
         * status : 0
         */

        private int id;
        private String country_code;
        private String authy_id;
        private int verified;
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

        public static CustomerObject objectFromData(String str) {

            return new Gson().fromJson(str, CustomerObject.class);
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

        public int getVerified() {
            return verified;
        }

        public void setVerified(int verified) {
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

    public static class DriverObject implements Serializable {
        /**
         * id : 11
         * country_code : 92
         * authy_id : 76064406
         * verified : 1
         * name : kami
         * email : kamiclient1@gmail.com
         * phone : 03353888079
         * image : null
         * address : Gynastic Areena Behria Phase 7
         * zipcode : 42000
         * place_id : null
         * referral_code : null
         * lat_long : 33.525550,73.112831
         * role : driver
         * fcm_token : dUQY-07dhdk:APA91bEf1DuG9sW_iMgkPFupxm02xkcmhv796rqDfX1U5x-RHCtxcz34LqXWNCoYzXBvuCKxBWbaQ5z3JDIKx701_M-i7aQVpcHNB8o0gq382bzdYxFQr_2H8K-9XtxRPREkoLZFoJ8T
         * login_type : normal
         * created_at : 2018-03-19 15:14:36
         * updated_at : 2018-03-21 14:37:03
         * become_a_shopper : 0
         * cart_session_id : null
         * status : 1
         */

        private int id;
        private String country_code;
        private String authy_id;
        private int verified;
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

        public static DriverObject objectFromData(String str) {

            return new Gson().fromJson(str, DriverObject.class);
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

        public int getVerified() {
            return verified;
        }

        public void setVerified(int verified) {
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

    public static class StoresObject implements Serializable{
        /**
         * id : 1
         * name : Store 1
         * logo : logos/1.png
         * banner : banners/1.png
         * type : store
         * zipcode : 42200
         * address : Sadar Rawalpindi
         * phone : 03064101261
         * status : 1
         * place_id : null
         * lat_long : 33.572708,73.110080
         * slug : store-1
         * created_at : 2018-03-15 14:52:51
         * updated_at : 2018-03-15 14:52:52
         * rating : 0.00
         * pivot : {"order_id":202,"store_id":1}
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
        private PivotObject pivot;

        public static StoresObject objectFromData(String str) {

            return new Gson().fromJson(str, StoresObject.class);
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

        public PivotObject getPivot() {
            return pivot;
        }

        public void setPivot(PivotObject pivot) {
            this.pivot = pivot;
        }

        public static class PivotObject implements Serializable {
            /**
             * order_id : 202
             * store_id : 1
             */

            private int order_id;
            private int store_id;

            public static PivotObject objectFromData(String str) {

                return new Gson().fromJson(str, PivotObject.class);
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }
        }
    }

    public static class ProductsObject implements Serializable {
        /**
         * id : 3
         * name : Product 1
         * image : products/3.png
         * quantity : 20
         * price : 5000
         * promo_price : null
         * description : Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
         * total_sale : 0
         * store_id : 1
         * department_id : 1
         * shelf_id : 1
         * brand_id : 1
         * unit : pcs
         * tax : 5
         * status : available
         * created_at : 2018-03-15 15:42:41
         * updated_at : 2018-03-15 15:42:41
         * slug : product-1
         * category_id : 1
         * pivot : {"order_id":202,"product_id":3}
         */

        private int id;
        private String name;
        private String image;
        private int quantity;
        private int price;
        private Object promo_price;
        private String description;
        private int total_sale;
        private int store_id;
        private int department_id;
        private int shelf_id;
        private int brand_id;
        private String unit;
        private int tax;
        private String status;
        private String created_at;
        private String updated_at;
        private String slug;
        private int category_id;
        private PivotObjectX pivot;

        public static ProductsObject objectFromData(String str) {

            return new Gson().fromJson(str, ProductsObject.class);
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Object getPromo_price() {
            return promo_price;
        }

        public void setPromo_price(Object promo_price) {
            this.promo_price = promo_price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getTotal_sale() {
            return total_sale;
        }

        public void setTotal_sale(int total_sale) {
            this.total_sale = total_sale;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(int department_id) {
            this.department_id = department_id;
        }

        public int getShelf_id() {
            return shelf_id;
        }

        public void setShelf_id(int shelf_id) {
            this.shelf_id = shelf_id;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getTax() {
            return tax;
        }

        public void setTax(int tax) {
            this.tax = tax;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public PivotObjectX getPivot() {
            return pivot;
        }

        public void setPivot(PivotObjectX pivot) {
            this.pivot = pivot;
        }

        public static class PivotObjectX implements Serializable {
            /**
             * order_id : 202
             * product_id : 3
             */

            private int order_id;
            private int product_id;

            public static PivotObjectX objectFromData(String str) {

                return new Gson().fromJson(str, PivotObjectX.class);
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }
        }
    }
}
