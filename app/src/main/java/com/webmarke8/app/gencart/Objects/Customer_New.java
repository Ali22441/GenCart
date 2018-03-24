package com.webmarke8.app.gencart.Objects;

import com.google.gson.Gson;

/**
 * Created by GeeksEra on 3/23/2018.
 */

public class Customer_New {

    /**
     * success : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYxZjRjM2Q2NGU5Mzg5YjZlMzc4YTlhZmExZjRmODhhOWYzYjhlMTgyNmQ3ODA5ZTA2NTI2MWM3ZjUxYTNhNTcyMTcwZmE5YTI4ZDA4YjkxIn0.eyJhdWQiOiIzIiwianRpIjoiNjFmNGMzZDY0ZTkzODliNmUzNzhhOWFmYTFmNGY4OGE5ZjNiOGUxODI2ZDc4MDllMDY1MjYxYzdmNTFhM2E1NzIxNzBmYTlhMjhkMDhiOTEiLCJpYXQiOjE1MjE4MDYyNTQsIm5iZiI6MTUyMTgwNjI1NCwiZXhwIjoxNTUzMzQyMjU0LCJzdWIiOiIxMiIsInNjb3BlcyI6W119.j_PKNAfak_je-V7ECaA_cLuZvNzRD13DlCocSAVqt8_V6XknL39Ut8J1p_A3vQf9wWSuW9ABviC0fCgdUdgl6hrKwayrbv5n2m-HsIYGqSKtbwNOGiivYFzwxVlgGZPWxspAg9aplu_h5OJf1uEigcOHjYhgUJkszGg4wnp94ZCXEzaD1n7G6OcY1zwsYb8FdxOyBi1P500giDyCB5RpGVGUrDlP1raqJymSOyml2FjzhHTvO0FbgH12mN6LvR6Odkd7k9kR6dY3iNx9Q05FRLI6nlbfRvh7Z_z6dgc8LHOOSrIg8ygWorhf_jxFWtOYPREaY4OHH2FXI5B560EWy9xLrjLb5e-6e0mvESR91b6S09M-erS75kEozgjcvBAOUo7PROrydydTfFTsKoqprnDuto3Jha1ZjR3bfZVgEfGSLDuJ4Fh-nwQ2rsghyDcej7ppj70cB1EcJoBttFCvYwLoCyi0n6gJx2p3Adq7oRMtXszc1yfMiU1fAkzqaDcf7X5jB1OevJsaD6uxRxQnoMwfKNtk3ZsxSFNqisz9qLfxmHsb_tNAibMum_SUS0RZiEXGXrcV-d0CqqmQGFpdiSaxc0X4UqIpm5cS421pBHPyLFGBIZMEFzcQABWUIuiQDa6SqJugM-P6zllYPxhpmJDPSHnlfrvs51qpqygIfYU","data":{"name":"AsifClient","email":"asifclient11@gmail.com","address":"Gynastic Areena Behria Phase 7","zipcode":"42000","role":"customer","phone":"033538880711","lat_long":"33.525550,73.112831","country_code":"92","authy_id":76519045,"updated_at":"2018-03-23 11:57:34","created_at":"2018-03-23 11:57:32","id":12}}
     */

    private SuccessObject success;

    public static Customer_New objectFromData(String str) {

        return new Gson().fromJson(str, Customer_New.class);
    }

    public SuccessObject getSuccess() {
        return success;
    }

    public void setSuccess(SuccessObject success) {
        this.success = success;
    }

    public static class SuccessObject {
        /**
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYxZjRjM2Q2NGU5Mzg5YjZlMzc4YTlhZmExZjRmODhhOWYzYjhlMTgyNmQ3ODA5ZTA2NTI2MWM3ZjUxYTNhNTcyMTcwZmE5YTI4ZDA4YjkxIn0.eyJhdWQiOiIzIiwianRpIjoiNjFmNGMzZDY0ZTkzODliNmUzNzhhOWFmYTFmNGY4OGE5ZjNiOGUxODI2ZDc4MDllMDY1MjYxYzdmNTFhM2E1NzIxNzBmYTlhMjhkMDhiOTEiLCJpYXQiOjE1MjE4MDYyNTQsIm5iZiI6MTUyMTgwNjI1NCwiZXhwIjoxNTUzMzQyMjU0LCJzdWIiOiIxMiIsInNjb3BlcyI6W119.j_PKNAfak_je-V7ECaA_cLuZvNzRD13DlCocSAVqt8_V6XknL39Ut8J1p_A3vQf9wWSuW9ABviC0fCgdUdgl6hrKwayrbv5n2m-HsIYGqSKtbwNOGiivYFzwxVlgGZPWxspAg9aplu_h5OJf1uEigcOHjYhgUJkszGg4wnp94ZCXEzaD1n7G6OcY1zwsYb8FdxOyBi1P500giDyCB5RpGVGUrDlP1raqJymSOyml2FjzhHTvO0FbgH12mN6LvR6Odkd7k9kR6dY3iNx9Q05FRLI6nlbfRvh7Z_z6dgc8LHOOSrIg8ygWorhf_jxFWtOYPREaY4OHH2FXI5B560EWy9xLrjLb5e-6e0mvESR91b6S09M-erS75kEozgjcvBAOUo7PROrydydTfFTsKoqprnDuto3Jha1ZjR3bfZVgEfGSLDuJ4Fh-nwQ2rsghyDcej7ppj70cB1EcJoBttFCvYwLoCyi0n6gJx2p3Adq7oRMtXszc1yfMiU1fAkzqaDcf7X5jB1OevJsaD6uxRxQnoMwfKNtk3ZsxSFNqisz9qLfxmHsb_tNAibMum_SUS0RZiEXGXrcV-d0CqqmQGFpdiSaxc0X4UqIpm5cS421pBHPyLFGBIZMEFzcQABWUIuiQDa6SqJugM-P6zllYPxhpmJDPSHnlfrvs51qpqygIfYU
         * data : {"name":"AsifClient","email":"asifclient11@gmail.com","address":"Gynastic Areena Behria Phase 7","zipcode":"42000","role":"customer","phone":"033538880711","lat_long":"33.525550,73.112831","country_code":"92","authy_id":76519045,"updated_at":"2018-03-23 11:57:34","created_at":"2018-03-23 11:57:32","id":12}
         */

        private String token;
        private DataObject data;

        public static SuccessObject objectFromData(String str) {

            return new Gson().fromJson(str, SuccessObject.class);
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public DataObject getData() {
            return data;
        }

        public void setData(DataObject data) {
            this.data = data;
        }

        public static class DataObject {
            /**
             * name : AsifClient
             * email : asifclient11@gmail.com
             * address : Gynastic Areena Behria Phase 7
             * zipcode : 42000
             * role : customer
             * phone : 033538880711
             * lat_long : 33.525550,73.112831
             * country_code : 92
             * authy_id : 76519045
             * updated_at : 2018-03-23 11:57:34
             * created_at : 2018-03-23 11:57:32
             * id : 12
             */

            private String name;
            private String email;
            private String address;
            private String zipcode;
            private String role;
            private String phone;
            private String lat_long;
            private String country_code;
            private int authy_id;
            private String updated_at;
            private String created_at;
            private int id;

            public static DataObject objectFromData(String str) {

                return new Gson().fromJson(str, DataObject.class);
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
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

            public String getCountry_code() {
                return country_code;
            }

            public void setCountry_code(String country_code) {
                this.country_code = country_code;
            }

            public int getAuthy_id() {
                return authy_id;
            }

            public void setAuthy_id(int authy_id) {
                this.authy_id = authy_id;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
