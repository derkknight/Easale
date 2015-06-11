package com.dy.easale.Model;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 11/3/2014.
 */
public class Event {
    private String title;
    private int id;
    private String description;
    private String icon;

    public Event(String title, String description, String icon)
    {
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public Event(int id, String title, String description, String icon)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public String getTitle() {return title;}

    public int getId() {return this.id;}

    public String getDescription() {return this.description;}

    public String getIcon() {return this.icon;}
}
