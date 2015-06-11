package com.dy.easale.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.dy.easale.EventArtAdapter;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Artwork;
import com.dy.easale.R;

import java.util.ArrayList;

///Class to display artworks associated with selected event
public class DetailEventActivity extends Activity {

    ArrayList _eventArtworkList = new ArrayList<Artwork>();
    GridView _eventArtworkGrid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        final FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);


        _eventArtworkGrid = (GridView) findViewById(R.id.detailEventArtworkGrid);
        _eventArtworkList = dbProvider.GetArtworkEvent(getIntent().getExtras().getInt("id"));
        EventArtAdapter adapter = new EventArtAdapter(this, _eventArtworkList);

        registerForContextMenu(_eventArtworkGrid);

        _eventArtworkGrid.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_event, menu);
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


    public void openDetailArtworkActivity(Artwork artwork)
    {
        Intent intent = new Intent(this, DetailArtworkActivity.class);
        intent.putExtra("title", artwork.getTitle());
        intent.putExtra("price", artwork.getPrice());
        intent.putExtra("icon", artwork.getIcon());

        this.startActivity(intent);
    }
}
