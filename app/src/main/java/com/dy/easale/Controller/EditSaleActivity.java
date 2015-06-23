package com.dy.easale.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Artwork;
import com.dy.easale.R;
import com.dy.easale.SaleAdapter;

import java.util.ArrayList;

public class EditSaleActivity extends Activity {

    private ArrayList<Artwork> _artworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sale);

        Bundle extras = getIntent().getExtras();


        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(this);
        _artworks = dbProvider.GetArtworkEvent(extras.getInt("id"));

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
