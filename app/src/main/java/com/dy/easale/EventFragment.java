package com.dy.easale;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.dy.easale.Controller.ArtworkListActivity;
import com.dy.easale.Controller.EventListActivity;
import com.dy.easale.Controller.EventToActivateListActivity;
import com.dy.easale.Model.Artwork;

import java.util.ArrayList;

/**
 * Created by Derick Yung on 9/16/2014.
 */
public class EventFragment extends ListFragment {

    String[] options = new String[] { "Edit Ongoing Event Sales", "View Events" };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setListAdapter(new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,options));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onListItemClick(ListView l, View v, int position, long id)
    {
        switch (position)
        {
            case 0:
                Intent intent = new Intent(getActivity(), EventToActivateListActivity.class);
                getActivity().startActivity(intent);
                break;

            case 1:
                Intent eventListIntent = new Intent(getActivity(), EventListActivity.class);
                getActivity().startActivity(eventListIntent);

        }

    }
}
