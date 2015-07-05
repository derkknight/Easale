package com.dy.easale.Model;

/**
 * Created by Derick Yung on 9/16/2014.
 */
public class Artwork {
    private int id;
    private String title;
    private int price;
    private String icon;
    private int _sellCount = 0;

    public Artwork(String title, int price, String icon)
    {
        this.title = title;
        this.price = price;
        this.icon = icon;
    }

    public Artwork(int id, String title, int price, String icon)
    {
        this.id = id;
        this.title = title;
        this.price = price;
        this.icon = icon;
    }

    public Artwork(String title, int price, String icon, int sellCount, int currencyType)
    {
        this.id = id;
        this.title = title;
        this.price = price;
        this.icon = icon;
        this._sellCount = sellCount;
        switch (currencyType)
        {
            case 0:
                this.price = price * 100;
        }

    }

    public Artwork(int id, String title, int price, String icon, int sellCount, int currencyType)
    {
        this.id = id;
        this.title = title;
        this.price = price;
        this.icon = icon;
        this._sellCount = sellCount;
        switch (currencyType)
        {
            case 0:
                this.price = price * 100;
        }

    }

    public Artwork(int id, String title, int price, String icon, int sellCount)
    {
        this.id = id;
        this.title = title;
        this.price = price;
        this.icon = icon;
        this._sellCount = sellCount;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getPrice()
    {
        return this.price;
    }
    public void setPrice()
    {
        this.price = price;
    }

    public double getConvertedPrice()
    {
        double hello = this.price * 0.01;
        return hello;
    }

    public String getIcon() { return this.icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public int GetSellCount() { return this._sellCount; }
    public void SetSellCount(Integer sellCount) { this._sellCount = sellCount; }


}
