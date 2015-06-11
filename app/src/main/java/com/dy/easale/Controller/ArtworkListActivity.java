package com.dy.easale.Controller;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import com.dy.easale.ArtworkAdapter;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Artwork;
import com.dy.easale.R;

import java.util.ArrayList;

import static android.view.View.*;

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

    public void openDetailArtworkActivity(Artwork artwork)
    {
        Intent intent = new Intent(this, DetailArtworkActivity.class);
        intent.putExtra("title", artwork.getTitle());
        intent.putExtra("price", artwork.getPrice());
        intent.putExtra("icon", artwork.getIcon());

        this.startActivity(intent);
    }


    public void openAddArtworkActivity()
    {
        Intent intent = new Intent(this, AddArtworkActivity.class);
        this.startActivity(intent);
    }

}
