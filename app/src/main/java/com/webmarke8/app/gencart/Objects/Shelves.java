package com.webmarke8.app.gencart.Objects;

import java.io.Serializable;

/**
 * Created by GeeksEra on 2/23/2018.
 */

public class Shelves implements Serializable {
    private String id;

    private String updated_at;

    private String description;

    private String department_id;

    private String name;

    private String created_at;

    private String store_id;

    private String slug;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
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

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", updated_at = " + updated_at + ", description = " + description + ", department_id = " + department_id + ", name = " + name + ", created_at = " + created_at + ", store_id = " + store_id + ", slug = " + slug + "]";
    }
}
