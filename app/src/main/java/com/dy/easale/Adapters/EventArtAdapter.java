package com.dy.easale.Adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.dy.easale.Model.Artwork;
import com.dy.easale.Model.Event;
import com.dy.easale.R;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 9/15/2014.
 */

public class EventArtAdapter extends ArrayAdapter<Artwork> {
    private Context context;
    private ArrayList<Artwork> data;

    public EventArtAdapter(Context context, ArrayList<Artwork> data) {
        super(context, R.layout.eventart_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int i, View view, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Log.d("TAGGING","" + parent.getId());

        final GridView gridParent = (GridView) parent;

        /*
        GridView gridView = (GridView) view.findViewById(R.id.eventArtworkGrid);
        gridView.setItemChecked(i, true);
        */


        View row = inflater.inflate(R.layout.eventart_row, parent, false);
        TextView name = (TextView) row.findViewById(R.id.name);
        ImageView icon = (ImageView) row.findViewById(R.id.eventIcon);
        final CheckBox checkBox = (CheckBox) row.findViewById(R.id.cbxEventArt);
        checkBox.setClickable(false);
        final int position = i;

        name.setText(data.get(i).getTitle());
        icon.setImageURI(Uri.parse(data.get(i).getIcon()));
        return row;
    }
}
