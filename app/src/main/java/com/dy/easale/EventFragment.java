package com.dy.easale;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dy.easale.Model.Artwork;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 9/16/2014.
 */
public class EventFragment extends ListFragment {

    ArrayList<Artwork> art_array = new ArrayList<Artwork>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflater.inflate(R.layout.fragment_artwork_list, container);
        //EventArtAdapter adapter = new EventArtAdapter(inflater.getContext(), art_array);
        //setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
