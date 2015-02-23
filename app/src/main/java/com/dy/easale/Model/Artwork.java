package com.dy.easale.Model;

/**
 * Created by Derick Yung on 9/16/2014.
 */
public class Artwork {
    private String title;
    private String price;
    private String description;
    private String icon;

    public Artwork(String title, String price, String description, String icon)
    {
        this.title = title;
        this.price = price;
        this.description = description;
        this.icon = icon;
    }

    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPrice()
    {
        return this.price;
    }
    public void setPrice()
    {
        this.price = price;
    }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return this.icon; }
    public void setIcon(String icon) { this.icon = icon; }

}
