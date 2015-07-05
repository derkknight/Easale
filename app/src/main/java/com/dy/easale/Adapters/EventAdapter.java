package com.dy.easale.Adapters;

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
import com.dy.easale.Controller.DetailActivities.DetailEventActivity;
import com.dy.easale.Model.Event;
import com.dy.easale.R;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 3/13/2015.
 */
public class EventAdapter extends ArrayAdapter<Event> {
    private Context context;
    private ArrayList<Event> data;

    public EventAdapter(Context context, ArrayList<Event> data) {
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
                openEventArtworkActivity(event);
            }

        });

        return row;
    }


    public void openEventArtworkActivity(Event event)
    {
        Intent intent = new Intent(context, DetailEventActivity.class);
        intent.putExtra("title", event.getTitle());
        intent.putExtra("description", event.getDescription());
        intent.putExtra("icon", event.getIcon());
        intent.putExtra("id", event.getId());

        Log.d("WALUIGI", Integer.toString(event.getId()));

        context.startActivity(intent);
    }
}
