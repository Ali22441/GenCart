package com.webmarke8.app.gencart.Objects;

import java.io.Serializable;

/**
 * Created by GeeksEra on 2/23/2018.
 */

public class Departments  implements Serializable {

    private String id;

    private String updated_at;

    private String description;

    private String name;

    private String image;

    private String created_at;

    private String slug;

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

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", updated_at = "+updated_at+", description = "+description+", name = "+name+", image = "+image+", created_at = "+created_at+", slug = "+slug+"]";
    }
}
