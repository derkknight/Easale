package com.dy.easale;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 11/3/2014.
 */
public class Event extends ArrayList<Sale> {
    private String name;

    public Event(String name)
    {
        this.name = name;
    }

    public String getName() {return name;}

    public int getTotal()
    {
        this.get(0);
        return 0;
    }

}
