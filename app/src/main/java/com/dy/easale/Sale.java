package com.dy.easale;

/**
 * Created by Derick Yung on 9/17/2014.
 */
public class Sale extends Artwork {

    String time;

    public Sale(Artwork artwork, String time)
    {
        super(artwork.getTitle(), artwork.getPrice(), artwork.getIcon());
        this.time = time;
    }
}
