package com.dy.easale.Controller.ListActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import com.dy.easale.Adapters.ArtworkAdapter;
import com.dy.easale.Controller.AddActivities.AddArtworkActivity;
import com.dy.easale.Controller.DetailActivities.DetailArtworkActivity;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Artwork;
import com.dy.easale.R;

import java.util.ArrayList;

public class ArtworkListActivity extends Activity {


    ArrayList<Artwork> art_array = new ArrayList<Artwork>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_list);
        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);

        GridView artworkGrid = (GridView) findViewById(R.id.artwork_grid);

        art_array = dbProvider.getArtworks();
        ArtworkAdapter adapter = new ArtworkAdapter(this, art_array);

        artworkGrid.setAdapter(adapter);
        artworkGrid.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        openContextMenu(view);
                        return true;
                    }
                }
        );
        registerForContextMenu(artworkGrid);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artwork_list, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
/**
 *         switch(item.getItemId())
 {
 case R.id.action_add_artwork:
 openAddArtworkActivity();
 return true;
 default:
 return super.onOptionsItemSelected(item);
 }

 */
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_artwork)
        {
            openAddArtworkActivity();
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add(Menu.NONE, 0, Menu.NONE, "Edit");
        menu.add(Menu.NONE, 1, Menu.NONE, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        int artworkIndex = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        int selectedArtworkId = art_array.get(artworkIndex).getId();

        switch (item.getItemId())
        {
            case 0:
                this.startActivity(new Intent(this, AddArtworkActivity.class));
                return true;
            case 1:
                FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);
                dbProvider.deleteArtwork(selectedArtworkId);
                art_array.remove(artworkIndex);

                return true;
            default:
                return false;

        }
    }

    public void openAddArtworkActivity()
    {
        Intent intent = new Intent(this, AddArtworkActivity.class);
        this.startActivity(intent);
    }

}
