package com.dy.easale.Controller.ListActivities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Artwork;
import com.dy.easale.R;
import com.dy.easale.Adapters.SaleAdapter;

import java.io.File;
import java.util.ArrayList;

public class EditSaleActivity extends Activity {

    private ArrayList<Artwork> _artworks;
    private int _eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sale);

        Bundle extras = getIntent().getExtras();
        _eventId = extras.getInt("id");

        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);
        _artworks = dbProvider.GetArtworkEvent(_eventId);

        ListView salesList = (ListView) findViewById(R.id.SalesListView);
        salesList.setAdapter(new SaleAdapter(this, _artworks));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_sale, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_sales) {
            FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);
            dbProvider.StoreSaleCount(_artworks, _eventId);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
