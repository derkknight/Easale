package com.dy.easale;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.dy.easale.Controller.AddArtworkActivity;
import com.dy.easale.Controller.DetailArtworkActivity;
import com.dy.easale.Model.Artwork;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 9/16/2014.
 */
public class ArtworkListFragment extends ListFragment {

    ArrayList<Artwork> art_array = new ArrayList<Artwork>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.artwork_list, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflater.inflate(R.layout.fragment_artwork_list, container);
        FileHelper.TextProvider textProvider = new FileHelper.TextProvider();
        art_array = textProvider.getAllArtwork();

        ArtworkAdapter adapter = new ArtworkAdapter(inflater.getContext(), art_array);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_add_artwork:
                openAddArtworkActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Artwork artwork = art_array.get(position);
        openDetailArtworkActivity(artwork);
    }

    public void openAddArtworkActivity()
    {
        Intent intent = new Intent(getActivity(), AddArtworkActivity.class);
        getActivity().startActivity(intent);
    }

    public void openDetailArtworkActivity(Artwork artwork)
    {
        Intent intent = new Intent(getActivity(), DetailArtworkActivity.class);
        intent.putExtra("title", artwork.getTitle());
        intent.putExtra("price", artwork.getPrice());
        intent.putExtra("description", artwork.getDescription());
        intent.putExtra("icon", artwork.getIcon());


        getActivity().startActivity(intent);
    }
}
