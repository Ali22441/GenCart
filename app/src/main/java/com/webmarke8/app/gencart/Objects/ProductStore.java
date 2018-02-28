package com.webmarke8.app.gencart.Objects;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private String zipcode;

    private String type;

    private String id;

    private String updated_at;

    private String address;

    private String name;

    private String created_at;

    private Categories[] categories;

    private String slug;

    private String rating;

    private Products[] products;

    private String banner;

    public String getLogo ()
    {
        return logo;
    }

    public void setLogo (String logo)
    {
        this.logo = logo;
    }

    public String getPlace_id ()
    {
        return place_id;
    }

    public void setPlace_id (String place_id)
    {
        this.place_id = place_id;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getLat_long ()
    {
        return lat_long;
    }

    public void setLat_long (String lat_long)
    {
        this.lat_long = lat_long;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getZipcode ()
    {
        return zipcode;
    }

    public void setZipcode (String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public Categories[] getCategories ()
    {
        return categories;
    }

    public void setCategories (Categories[] categories)
    {
        this.categories = categories;
    }

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public Products[] getProducts ()
    {
        return products;
    }

    public void setProducts (Products[] products)
    {
        this.products = products;
    }

    public String getBanner ()
    {
        return banner;
    }

    public void setBanner (String banner)
    {
        this.banner = banner;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [logo = "+logo+", place_id = "+place_id+", phone = "+phone+", lat_long = "+lat_long+", status = "+status+", zipcode = "+zipcode+", type = "+type+", id = "+id+", updated_at = "+updated_at+", address = "+address+", name = "+name+", created_at = "+created_at+", categories = "+categories+", slug = "+slug+", rating = "+rating+", products = "+products+", banner = "+banner+"]";
    }
}
