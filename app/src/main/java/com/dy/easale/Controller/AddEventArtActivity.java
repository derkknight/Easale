package com.dy.easale.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.*;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import com.dy.easale.EventArtAdapter;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Artwork;
import com.dy.easale.Model.Event;
import com.dy.easale.R;

import java.util.ArrayList;

public class AddEventArtActivity extends Activity implements AdapterView.OnItemClickListener{

    ArrayList art_array = new ArrayList<Artwork>();
    GridView artworkGrid = null;
    SparseBooleanArray _idToChecked = new SparseBooleanArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_art);

        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);

        artworkGrid = (GridView) findViewById(R.id.eventArtworkGrid);
        art_array = dbProvider.getArtworks();
        EventArtAdapter adapter = new EventArtAdapter(this, art_array);

        registerForContextMenu(artworkGrid);

        artworkGrid.setAdapter(adapter);
        artworkGrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);

        artworkGrid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView adapter, View view, int position, long lo)
            {
                Log.d("DINNUH", ""+position);

                CheckBox checkBox = (CheckBox) artworkGrid.getChildAt(position).findViewById(R.id.cbxEventArt);
                checkBox.setChecked(!checkBox.isChecked());
                if (checkBox.isChecked())
                {
                    checkBox.setChecked(true);
                    _idToChecked.put(position, true);
                }
                else
                {
                    checkBox.setChecked(false);
                    _idToChecked.put(position, false);
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_event_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemClick(AdapterView adapter, View v, int position, long args)
    {
        Log.d("SUPPERS", position +"");

    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        int index = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        switch (item.getItemId())
        {
            case R.id.deleteItem:
                return true;
            default:
                return false;
        }
    }



    public void SaveEventArt(View view)
    {
        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(view.getContext());

        dbProvider.createArtworkEvent(new Artwork("","",""), new Event("","",""));

    }

    public void SaveEventArts(View view)
    {
        //TODO: Check out emptyartowrk/selected
        ArrayList selectedArtworkList = new ArrayList<Artwork>();
        SparseBooleanArray checkItemsArray = artworkGrid.getCheckedItemPositions();

        for (int i = 0; i < checkItemsArray.size(); i++)
        {
            if(_idToChecked.get(i))
            {
                selectedArtworkList.add(art_array.get(i));
            }
        }
        Bundle extras = getIntent().getExtras();
        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(view.getContext());
        dbProvider.createArtworkEvent(selectedArtworkList, extras.getLong("eventId"));
        finish();
    }
}
