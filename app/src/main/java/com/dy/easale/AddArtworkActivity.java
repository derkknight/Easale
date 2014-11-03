package com.dy.easale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class AddArtworkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artwork);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_artwork, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveArtwork(View view)
    {
        EditText edit_title = (EditText) findViewById(R.id.edit_title);
        EditText edit_price = (EditText) findViewById(R.id.edit_price);
        EditText edit_description = (EditText) findViewById(R.id.edit_description);
        String title = edit_title.getText().toString();
        String price = edit_price.getText().toString();
        String description = edit_description.getText().toString();
        Artwork to_be_added = new Artwork(title, price, description);
        ArrayList<Artwork> to_write = new ArrayList<Artwork>();
        to_write.add(to_be_added);
        FileHandler file_writer = new FileHandler();
        file_writer.writeFile(to_write, view.getContext());
    }
}
