package com.webmarke8.app.gencart.Objects;

import java.io.Serializable;

/**
 * Created by GeeksEra on 2/23/2018.
 */

public class Products  implements Serializable {
    private String promo_price;

    private String status;

    private String department_id;

    private String brand_id;

    private String image;

    private String store_id;

    private String total_sale;

    private String id;

    private String unit;

    private String updated_at;

    private String price;

    private String tax;

    private String description;

    private String name;

    private String created_at;

    private String shelf_id;

    private String slug;

    private String quantity;

    public String getPromo_price() {
        return promo_price;
    }

    public void setPromo_price(String promo_price) {
        this.promo_price = promo_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getTotal_sale() {
        return total_sale;
    }

    public void setTotal_sale(String total_sale) {
        this.total_sale = total_sale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getShelf_id() {
        return shelf_id;
    }

    public void setShelf_id(String shelf_id) {
        this.shelf_id = shelf_id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ClassPojo [promo_price = " + promo_price + ", status = " + status + ", department_id = " + department_id + ", brand_id = " + brand_id + ", image = " + image + ", store_id = " + store_id + ", total_sale = " + total_sale + ", id = " + id + ", unit = " + unit + ", updated_at = " + updated_at + ", price = " + price + ", tax = " + tax + ", description = " + description + ", name = " + name + ", created_at = " + created_at + ", shelf_id = " + shelf_id + ", slug = " + slug + ", quantity = " + quantity + "]";
    }
}
