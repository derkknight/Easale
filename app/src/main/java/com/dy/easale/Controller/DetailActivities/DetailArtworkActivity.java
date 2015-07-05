package com.dy.easale.Controller.DetailActivities;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.dy.easale.R;

public class DetailArtworkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artwork);

        Bundle extras = getIntent().getExtras();

        getActionBar().setTitle(extras.getString("title"));
        getActionBar().setDisplayHomeAsUpEnabled(true);

        populateText();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_detail_artwork, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void populateText()
    {
        ImageView artworkImage = (ImageView) findViewById(R.id.imageView_detail_artwork);
        //Get Title
        TextView artworkTitle = (TextView) findViewById(R.id.artwork_title);
        //Get Price Label
        TextView priceLabel = (TextView) findViewById(R.id.artwork_price);
        //Get Description Label
        TextView descriptionLabel = (TextView) findViewById(R.id.artwork_description);


        //Obtain information
        Bundle extras = getIntent().getExtras();
        //Artwork artwork;

        String salesTotal = "TOTAL: " + extras.getInt("sellCount");

        //Populate with information
        artworkImage.setImageURI(Uri.parse(extras.getString("icon")));
        artworkTitle.setText(extras.getString("title"));
        priceLabel.setText(Double.toString(extras.getDouble("price")));
        descriptionLabel.setText(salesTotal);

    }
}
