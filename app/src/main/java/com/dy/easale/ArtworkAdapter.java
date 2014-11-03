package com.dy.easale;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Derick Yung on 9/15/2014.
 */

public class ArtworkAdapter extends ArrayAdapter<Artwork> {
    private Context context;
    private ArrayList<Artwork> data;

    public ArtworkAdapter(Context context, ArrayList<Artwork> data) {
        super(context, R.layout.artwork_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.artwork_row, parent, false);
        TextView title = (TextView) row.findViewById(R.id.title);
        TextView price = (TextView) row.findViewById(R.id.price);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);

        title.setText(data.get(i).getTitle());
        price.setText(data.get(i).getPrice());
        icon.setImageResource(R.drawable.ic_launcher);
        return row;
    }
}
