package com.dy.easale.Controller.ListActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.dy.easale.Controller.AddActivities.AddEventActivity;
import com.dy.easale.Adapters.EventAdapter;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Event;
import com.dy.easale.R;

import java.util.ArrayList;

public class EventListActivity extends Activity {

    ArrayList<Event> _eventList = new ArrayList<Event>();
    private EventAdapter _adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);

        ListView eventListView = (ListView) findViewById(R.id.event_listview);
        registerForContextMenu(eventListView);


        _eventList = dbProvider.getEvents();
        _adapter = new EventAdapter(this, _eventList);

        eventListView.setAdapter(_adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add_event)
        {
            openAddEventActivity();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.deleteItem:
                DeleteEvent(_eventList.get(info.position).getId());
                return true;
            default:
                return false;
        }
    }

    public void DeleteEvent(int eventId)
    {
        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);
        dbProvider.DeleteEvent(eventId);
        _adapter.notifyDataSetChanged();
    }

    public void openAddEventActivity()
    {
        Intent intent = new Intent(this, AddEventActivity.class);
        this.startActivity(intent);
    }
}
