package com.dy.easale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dy.easale.Controller.DetailArtworkActivity;
import com.dy.easale.Model.Artwork;

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

        final Artwork artwork = data.get(i);

        title.setText(data.get(i).getTitle());
        price.setText(data.get(i).getPrice());
        icon.setImageURI(Uri.parse(data.get(i).getIcon()));

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openDetailArtworkActivity(artwork);
            }

        });

        icon.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(context);
                dbProvider.deleteArtwork(artwork.getId());
                return true;
            }
        });

        return row;
    }

    public void openDetailArtworkActivity(Artwork artwork)
    {
        Intent intent = new Intent(context, DetailArtworkActivity.class);
        intent.putExtra("title", artwork.getTitle());
        intent.putExtra("price", artwork.getPrice());
        intent.putExtra("icon", artwork.getIcon());

        context.startActivity(intent);
    }
}
