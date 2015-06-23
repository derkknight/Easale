package com.dy.easale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dy.easale.Controller.DetailEventActivity;
import com.dy.easale.Controller.EditSaleActivity;
import com.dy.easale.Model.Event;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 3/13/2015.
 */
public class ActiveEventAdapter extends ArrayAdapter<Event> {
    private Context context;
    private ArrayList<Event> data;

    public ActiveEventAdapter(Context context, ArrayList<Event> data) {
        super(context, R.layout.event_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.event_row, parent, false);
        TextView name = (TextView) row.findViewById(R.id.event_title);
        ImageView icon = (ImageView) row.findViewById(R.id.event_icon);

        final Event event = data.get(i);

        name.setText(data.get(i).getTitle());
        icon.setImageURI(Uri.parse(data.get(i).getIcon()));


        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openEditSaleActivity(event);
            }

        });

        return row;
    }


    public void openEditSaleActivity(Event event)
    {
        Intent intent = new Intent(context, EditSaleActivity.class);
        intent.putExtra("id", event.getId());

        context.startActivity(intent);
    }
}
