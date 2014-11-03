package com.dy.easale;


import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

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
        art_array.add(new Artwork("Mona Lisa", "15.99", "Icon_placeholder"));
        art_array.add(new Artwork("The Scream", "5.99", "Icon2_placeholder"));

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

    public void openAddArtworkActivity()
    {
        Intent intent = new Intent(getActivity(), AddArtworkActivity.class);
        getActivity().startActivity(intent);
    }
}
