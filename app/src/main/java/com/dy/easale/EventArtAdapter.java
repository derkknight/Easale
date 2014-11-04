package com.dy.easale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 9/15/2014.
 */

public class EventArtAdapter extends ArrayAdapter<Event> {
    private Context context;
    private ArrayList<Event> data;

    public EventArtAdapter(Context context, ArrayList<Event> data) {
        super(context, R.layout.eventart_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.eventart_row, parent, false);
        TextView name = (TextView) row.findViewById(R.id.name);
        TextView total = (TextView) row.findViewById(R.id.total);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);

        name.setText(data.get(i).getName());
        total.setText(data.get(i).getTotal());
        icon.setImageResource(R.drawable.ic_launcher);
        return row;
    }
}
