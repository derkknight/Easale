package com.dy.easale;

/**
 * Created by Derick Yung on 9/16/2014.
 */
public class Artwork {
    private String title;
    private String price;
    private String icon;


    public Artwork(String title, String price, String icon)
    {
        this.title = title;
        this.price = price;
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

    public String getIcon() { return this.icon; }

    public void setIcon(String icon) { this.icon = icon; }

}
