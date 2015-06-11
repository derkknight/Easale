package com.dy.easale;

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
import android.widget.ListView;
import com.dy.easale.Controller.AddEventActivity;
import com.dy.easale.Controller.ArtworkListActivity;
import com.dy.easale.Controller.EventListActivity;

/**
 * Created by Derick Yung on 9/16/2014.
 */
public class ManageFragment extends ListFragment {

    String[] options = new String[] { "View Artworks", "View Events"};
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.fragment_manage, menu);
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        switch (position)
        {
            case 0:
                Intent intent = new Intent(getActivity(), ArtworkListActivity.class);
                getActivity().startActivity(intent);
                break;
            case 1:
                Intent eventListIntent = new Intent(getActivity(), EventListActivity.class);
                getActivity().startActivity(eventListIntent);
                break;

        }

    }




}
